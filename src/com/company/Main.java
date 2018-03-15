package com.company;

import sun.awt.windows.ThemeReader;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        ResourceA resourceA = new ResourceA();
        ResourceB resourceB = new ResourceB();

        resourceA.setResourceB(resourceB);
        resourceB.setResourceA(resourceA);
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Стартуем " + Thread.currentThread().getName() + " поток");
                try {
                    resourceB.startBlock();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();
        System.out.println("Стартуем " + Thread.currentThread().getName() + " поток");
        resourceA.startBlock();
    }

    static class ResourceA {
        ResourceB resourceB;

        public void setResourceB(ResourceB resourceB) {
            this.resourceB = resourceB;
        }

        public synchronized void startBlock() throws InterruptedException {
            System.out.println(this.getClass().getSimpleName() + "    >>> startBlock.   В потоке " + Thread.currentThread().getName());
            Thread.sleep(1000);
            resourceB.methodB();
        }

        public synchronized void methodA() {
            System.out.println("methodA : вывод после блокировки");
        }
    }

    static class ResourceB {
        ResourceA resourceA;

        public void setResourceA(ResourceA resourceA) {
            this.resourceA = resourceA;
        }

        public synchronized void startBlock() throws InterruptedException {
            System.out.println(this.getClass().getSimpleName() + "    >>> startBlock.   В потоке " + Thread.currentThread().getName());
            Thread.sleep(1000);
            resourceA.methodA();
        }

        public synchronized void methodB() {
            System.out.println("methodB: вывод после блокировки");
        }

    }
}







