package com.company;

public class Main {

    public static void main(String[] args) {

        MyThread1 thread1 = new MyThread1();
        MyThread2 thread2 = new MyThread2();


        thread1.start();
        thread2.start();
    }

}

class MyThread1 extends Thread {
//    Resources resources;
//
//    public void setResources(Resources resources) {
//        this.resources = resources;
//    }

    @Override
    public void run() {
        for (int i = 0; i < 500; i++) {
            Resources.increaseI();
            System.out.println(">>>     " + Thread.currentThread().getName());
            System.out.println(">>>     " + Resources.i);
        }
    }
}

class MyThread2 extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 5000; i++) {
            Resources.increaseI();
            System.out.println(Thread.currentThread().getName());
            System.out.println(Resources.i);
        }
    }
}

class Resources {
    static int i;

    //Static synchronized
    public synchronized static void increaseI() {
        //Static synchronized block
        synchronized (Resources.class){

        }

        int i = Resources.i;

        if (Thread.currentThread().getName().equals("Thread-1")) {
            Thread.yield();
        }
        i++;
        Resources.i = i;
    }
}


