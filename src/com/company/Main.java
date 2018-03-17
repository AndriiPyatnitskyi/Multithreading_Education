package com.company;

public class Main {

    public static void main(String[] args) {
        Resource resource = new Resource();
        new MyThread1(resource).start();
        new MyThread2(resource).start();
    }

    static class MyThread1 extends Thread {
        Resource resource;

        public MyThread1(Resource resource) {
            this.resource = resource;
        }

        @Override
        public void run() {
            setName(">>>     " + Thread.currentThread().getName());
            System.out.println(Thread.currentThread().getName() + " начал работу");

            resource.method1();
            System.out.println(getName() + " отпустил монитор");
            System.out.println(Thread.currentThread().getName() + " закончил работу");


        }
    }

    static class MyThread2 extends Thread {
        Resource resource;

        public MyThread2(Resource resource) {
            this.resource = resource;
        }

        @Override
        public void run() {
            int sleepTime = 500;
            System.out.println(Thread.currentThread().getName() + " начал работу");

            try {
                System.out.println(Thread.currentThread().getName()
                        + " заснул на " + sleepTime + " мс, "
                        + "чтобы не войти в method2() раньше первого потока");
                Thread.sleep(sleepTime);
                System.out.println(Thread.currentThread().getName() + " проснулся и хочет захватить монитор и зайти в method2()");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            resource.method2();
            System.out.println(Thread.currentThread().getName() + " закончил работу");

        }
    }

    static class Resource {
        public synchronized void method1() {
            int sleepTime = 10000;
            System.out.println(Thread.currentThread().getName() + " вошел в method1()");
            try {
                System.out.println(Thread.currentThread().getName()
                        + " заснул в методе method1 на " + sleepTime + " мс, "
                        + "чтобы показать, что второй поток ждет пока первый отпустит монитор");
                Thread.sleep(sleepTime);
                System.out.println(Thread.currentThread().getName() + " в методе method1 проснулся");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public synchronized void method2() {
            System.out.println(Thread.currentThread().getName() + " вошел в method2()");

        }
    }
}







