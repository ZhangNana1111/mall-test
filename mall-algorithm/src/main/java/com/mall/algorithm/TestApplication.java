package com.mall.algorithm;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author Zhangnana
 * @DATE 2020/12/19 8:41
 * @Version 1.0
 */
public class TestApplication {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");

        TestController testController = (TestController) context.getBean("testController");
        testController.save();

    }
}
