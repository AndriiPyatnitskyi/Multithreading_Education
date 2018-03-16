package com.company;

import java.util.concurrent.locks.ReentrantLock;

public class Main {

    public static void main(String[] args) {
        Resources resources = new Resources();
        MyThread thread1 = new MyThread(resources);
        MyThread thread2 = new MyThread(resources);


        thread1.start();
        thread2.start();
    }

}

class MyThread extends Thread {
    Resources resources;

    public MyThread(Resources resources) {
        this.resources = resources;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            resources.increaseI();
            System.out.println(">>>     " + Thread.currentThread().getName());
            System.out.println(">>>     " + resources.i);
        }
    }
}


class Resources {
    int i;
    ReentrantLock reentrantlock = new ReentrantLock();

    public void increaseI() {
        reentrantlock.lock();
        int i = this.i;

        if (Thread.currentThread().getName().equals("Thread-1")) {
            Thread.yield();
        }
        i++;
        this.i = i;

        reentrantlock.unlock();
    }
}


