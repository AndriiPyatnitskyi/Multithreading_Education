package com.company;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();

        MyThread1 thread1 = new MyThread1(lock);
        MyThread2 thread2 = new MyThread2(lock);


        thread1.start();
        thread2.start();
    }

}

class MyThread1 extends Thread {

    Lock lock;

    public MyThread1(Lock lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        lock.lock();
        System.out.println(getName() + " lock");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock.unlock();
        System.out.println(getName() + " unlock");


    }
}

class MyThread2 extends Thread {
    Lock lock;

    public MyThread2(Lock lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        while (true) {
            if (lock.tryLock()) {
                System.out.println(getName() + "  get lock");
            } else {
                System.out.println(getName() + " can not get lock");
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}


