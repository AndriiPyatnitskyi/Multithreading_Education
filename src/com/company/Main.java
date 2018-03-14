package com.company;

public class Main {

    public static void main(String[] args) {
        Thread thread = new MyThread();
        Thread runnable = new Thread(new MyRunnable());

        thread.start();

        for (int i = 0; i < 1000; i++) {
            System.out.println(Thread.currentThread().getName() + "     >>> " + i);
        }

        runnable.start();
    }

}

class MyThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println(Thread.currentThread().getName() + "     >>> " + i);
        }
    }
}

class MyRunnable implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println(Thread.currentThread().getName() + "     >>> " + i);
        }
    }
}
