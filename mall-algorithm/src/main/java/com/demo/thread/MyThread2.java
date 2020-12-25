package com.demo.thread;

/**
 * @Author Zhangnana
 * @DATE 2020/12/23 18:14
 * @Version 1.0
 */
public class MyThread2 implements Runnable{
    Marker marker;

    public MyThread2(Marker marker){
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
        this.marker.b = true;
        System.out.println("修改后" + Thread.currentThread().getName() + marker.b);
    }

    public static void main(String[] args) throws InterruptedException {
        Marker marker = new Marker(true);
        Thread thread1 = new Thread(new MyThread(marker));
        Thread thread2 = new Thread(new MyThread2(marker));
        thread1.start();
        thread2.start();

        while (thread1.isAlive() || thread2.isAlive()){
        }
        System.out.println("测试结束");
    }
}
