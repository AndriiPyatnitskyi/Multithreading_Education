package com.company;

import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    static AtomicInteger atomicInteger = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        for (int j = 0; j < 1000; j++) {
            new MyThread().start();
        }
        //засыпаем, чтоб потоки со счетчиком успели до конца отрабоать
        Thread.sleep(1000);
        System.out.println(atomicInteger.get());
    }

    static class MyThread extends Thread {
        @Override
        public void run() {
            atomicInteger.incrementAndGet();
        }
    }
}







