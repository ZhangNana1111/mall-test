package com.demo.thread;

/**
 * @Author Zhangnana
 * @DATE 2020/12/19 9:39
 * @Version 1.0
 */
public class SynchronizedTest implements Runnable{
    //创建两个不同的对象
    static SynchronizedTest instance1 = new SynchronizedTest();
    static SynchronizedTest instance2 = new SynchronizedTest();

    @Override
    public void run() {
        if (Thread.currentThread().getName().equals("Thread-0")){
            method1();
        }
        if (Thread.currentThread().getName().equals("Thread-1")){
            method0();
        }

    }

    // 同步方法
    private synchronized static void method0(){
        System.out.println("线程名" + Thread.currentThread().getName()+",开始运行");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程" + Thread.currentThread().getName() + "运行结束。");
    }

    //普通方法
    public synchronized void method1(){
        System.out.println("线程名" + Thread.currentThread().getName()+",开始运行");
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程" + Thread.currentThread().getName() + ",运行结束");
    }

    public static void main(String[] args) {
        Thread thread1 = new Thread(instance1);
        Thread thread2 = new Thread(instance2);
        thread1.start();
        thread2.start();
        while (thread1.isAlive() || thread2.isAlive()){
        }
        System.out.println("测试结束");
    }
}
