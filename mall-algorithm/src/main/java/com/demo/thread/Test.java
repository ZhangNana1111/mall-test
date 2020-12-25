package com.demo.thread;

/**
 * @Author Zhangnana
 * @DATE 2020/12/25 17:33
 * @Version 1.0
 */
public class Test {

    public static void main(String[] args) {

        Number n1 = new Number();
        Number n2 = new Number();

        new Thread(new Runnable() {
            @Override
            public void run() {
                n1.getOne();
            }

        }).start();

        new Thread(new Runnable() {

            @Override
            public void run() {
                n2.getTwo();
            }

        }).start();


    }

}

class Number {

    public synchronized void getOne() {

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("one");

    }

    public synchronized void getTwo() {

        System.out.println("two");

    }

}
