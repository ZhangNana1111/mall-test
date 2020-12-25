package com.demo.thread;

/**
 * @Author Zhangnana
 * @DATE 2020/12/23 18:39
 * @Version 1.0
 */
public class Main {
    private static int number;

    public static boolean isRuning;
    public Main(boolean isRuning){
        this.isRuning = isRuning;
    }
    private static class ReaderThread extends Thread {


        public void run() {
            while (!isRuning) {
                System.out.println(number);
            }
        }
    }

    private static class ReaderThread2 extends Thread{

        Main main;
        public ReaderThread2(Main main) {
            this.main = main;
        }

        public void run() {
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new ReaderThread().start();
        Thread.sleep(100);
        new ReaderThread2(new Main(true)).start();
        number = 42;
       // isRuning = true;
        Thread.sleep(100);
    }
}
