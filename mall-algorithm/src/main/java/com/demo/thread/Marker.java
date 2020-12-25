package com.demo.thread;

/**
 * @Author Zhangnana
 * @DATE 2020/12/23 18:12
 * @Version 1.0
 */
public class Marker {
    public static volatile boolean b;
    public Marker(boolean b){
        this.b = b;
    }
}
