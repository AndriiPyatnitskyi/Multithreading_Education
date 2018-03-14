package com.company;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new MyThread();
        Thread thread1 = new MyThread();
        //Устанавливаем приоритет потока
        thread1.setPriority(Thread.MAX_PRIORITY);

        thread.start();
        //Поток main и остальные потоки ждут, пока поток thread не закончит полностью свою работу.
        thread.join();

        //Сообщаем что данный поток может на время уйти в пул потоков
        Thread.yield();
        thread1.start();

        testPrint();
    }

    public static void testPrint() {
        for (int i = 0; i < 10; i++) {
            //Поток засыпает
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "     >>> " + i);
        }
    }

}

class MyThread extends Thread {
    @Override
    public void run() {
        Main.testPrint();
    }
}


