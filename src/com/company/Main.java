package com.company;

public class Main {
     volatile static boolean isExit = false;

    public static void main(String[] args) {
        new MyThreadWritable().start();
        new MyThreadReadable().start();
    }

    static class MyThreadWritable extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            isExit = true;
        }
    }

    static class MyThreadReadable extends Thread {
        @Override
        public void run() {
            while (true) {
                //Без volatile isExit не прочитается нормально
                if(isExit){
                    System.out.println("isExit");
                }
                //если раскоментировать данный код, то программа работает нормально и без volatile. странно.
//                else {
//                    System.out.println("!!!");
//                }
            }
        }
    }

}







