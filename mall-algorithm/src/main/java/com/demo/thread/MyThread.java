package com.demo.thread;

/**
 * @Author Zhangnana
 * @DATE 2020/12/23 18:12
 * @Version 1.0
 */
public class MyThread implements Runnable{

    Marker marker;

    public MyThread(Marker marker){
        this.marker = marker;
    }
    @Override
    public void run() {
        try{
            System.out.println("修改前"+Thread.currentThread().getName() + marker.b);
            Thread.sleep(100);
        }catch (Exception e){
            System.out.println(e.toString());
        }
        this.marker.b = false;
        System.out.println("修改后" + Thread.currentThread().getName() + marker.b);
    }
}
