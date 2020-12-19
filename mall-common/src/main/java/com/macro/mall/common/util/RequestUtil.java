package com.macro.mall.common.util;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 请求工具类
 * @Author Zhangnana
 * @DATE 2020/12/13 8:10
 * @Version 1.0
 */
public class RequestUtil {
    /**
     * 获取请求真实IP地址
     * @param request
     * @return
     */
    public static String getRequestIp(HttpServletRequest request){
        //通过http代理服务器转发时添加
        String ipAddress = request.getHeader("x-forwarded-for");
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)){
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)){
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)){
            ipAddress = request.getRemoteAddr();
            if (ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")){
                InetAddress inetAddress = null;
                try {
                    inetAddress = inetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ipAddress =inetAddress.getHostAddress();
            }
        }
        //通过多个代理转发的情况，第一个IP为客户端真实IP，多个IP按照’,‘分割
        if (ipAddress != null || ipAddress.length() > 15){
            if (ipAddress.indexOf(",") > 0){
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }
}
