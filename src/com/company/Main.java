package com.company;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Resources resources = new Resources();
        MyThread1 thread1 = new MyThread1();
        MyThread2 thread2 = new MyThread2();
        thread1.setResources(resources);
        thread2.setResources(resources);
        thread1.start();
        thread2.start();

//        Thread thread1 = new MyThread();
//        //Устанавливаем приоритет потока
//        thread1.setPriority(Thread.MAX_PRIORITY);
//
//        thread.start();
//        //Поток main и остальные потоки ждут, пока поток thread не закончит полностью свою работу.
//        thread.join();
//
//        //Сообщаем что данный поток может на время уйти в пул потоков
//        Thread.yield();
//        thread1.start();

    }



}

class MyThread1 extends Thread {
    Resources resources;

    public void setResources(Resources resources) {
        this.resources = resources;
    }

    @Override
    public void run() {
        for (int i = 0; i < 500; i++) {
            resources.increaseI();
            System.out.println(">>>     " + Thread.currentThread().getName());
            System.out.println(">>>     " + resources.i);
        }
    }
}
class MyThread2 extends Thread {
    Resources resources;

    public void setResources(Resources resources) {
        this.resources = resources;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5000; i++) {
            resources.increaseI();
            System.out.println(Thread.currentThread().getName());
            System.out.println(resources.i);
        }
    }
}

class Resources{
    int i;

    //Блокирует вызов метода другим потоком, пока данный поток не отрабоатет полностью метод
    //Также можно блокировать чать кодав synchronized блоке
    public synchronized void increaseI(){
        int i = this.i;

        if (Thread.currentThread().getName().equals("Thread-1")){
            Thread.yield();
        }
        i++;
        this.i = i;
    }
}


