package com.macro.mall.common.log;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.json.JSONUtil;
import com.macro.mall.common.domain.Weblog;
import io.swagger.annotations.ApiOperation;
import net.logstash.logback.marker.Markers;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * 统一日志处理 切面
 * @Author Zhangnana
 * @DATE 2020/12/12 18:29
 * @Version 1.0
 */
@Aspect
@Component
@Order(1)
public class WebLogAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebLogAspect.class);

    @Pointcut(" execution(public * com.macro.mall.controller.*.*(..)) || execution(public * com.macro.mall.*.controller.*.*(..))")
    public void webLog(){
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
    }

    @AfterReturning(value = "webLog()",returning = "ret")
    public void doAfterReturning(Object ret) throws Throwable{
    }

    @Around("webLog()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable{
        long startTime = System.currentTimeMillis();
        //获取当前请求对象
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录请求信息（通过LogStash传入Elasticsearch）
        Weblog weblog = new Weblog();
        Object result = joinPoint.proceed();
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature  = (MethodSignature)signature;
        Method method = methodSignature.getMethod();
        if (method.isAnnotationPresent(ApiOperation.class)){
            ApiOperation log = method.getAnnotation(ApiOperation.class);
            weblog.setDescription(log.value());
        }
        long endTime = System.currentTimeMillis();
        String urlStr = request.getRequestURL().toString();
        weblog.setBasePath(StrUtil.removeSuffix(urlStr, URLUtil.url(urlStr).getPath()));
        weblog.setIp(request.getRemoteUser());
        weblog.setMethod(request.getMethod());
        weblog.setParameter(getParameter(method,joinPoint.getArgs()));
        weblog.setReturnResult(result);
        weblog.setSpendTime((int)(endTime - startTime));
        weblog.setStartTime(startTime);
        weblog.setURI(request.getRequestURI());
        weblog.setURL(request.getRequestURL().toString());
        Map<String, Object> logMap = new HashMap<>();
        logMap.put("url", weblog.getURL());
        logMap.put("method",request.getMethod());
        logMap.put("parameter",weblog.getParameter());
        logMap.put("spendTime",weblog.getSpendTime());
        logMap.put("description",weblog.getDescription());

        LOGGER.info(Markers.appendEntries(logMap), JSONUtil.parse(weblog).toString());
        return result;
    }

    /**
     * 根据方法和传入的参数获取请求参数
     * @param method 方法
     * @param args 参数
     * @return
     */
    private Object getParameter(Method method, Object[] args){
        List<Object> argList = new ArrayList<>();
        Parameter[] parameters = method.getParameters();
        for (int i = 0; i < parameters.length; i++) {
            // 将requestBody注解修饰的参数作为请求参数
            RequestBody requestBody = parameters[i].getAnnotation(RequestBody.class);
            if (requestBody != null){
                argList.add(args[i]);
            }
            // 将requestParam注解修饰的参数作为请求参数
            RequestParam requestParam = parameters[i].getAnnotation(RequestParam.class);
            if (requestParam != null){
                HashMap<String, Object> map = new HashMap<>();
                String key = parameters[i].getName();
                if ( !StringUtils.isEmpty(requestParam.value()) ){
                    key = requestParam.value();
                }
                map.put(key,args[i]);
                argList.add(map);
            }
        }
        if (argList.size() == 0){
            return null;
        }else if (argList.size() == 1){
            return argList.get(0);
        }else {
            return argList;
        }
    }


}
