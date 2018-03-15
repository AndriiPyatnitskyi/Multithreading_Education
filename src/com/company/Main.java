package com.company;

public class Main {
    static int i = 0;
    public static void main(String[] args) {
        new MyThreadWritable().start();
        new MyThreadReadable().start();
    }

    static class MyThreadWritable extends Thread {
        @Override
        public void run() {
            while (i < 5){
                System.out.println("increase: " + (++i));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class MyThreadReadable extends Thread {
        @Override
        public void run() {
            int localVar = 0;
            while (localVar < 5){
                localVar = i;
                if(localVar != i){
                    System.out.println("read: " + i);
                    localVar = i;
                }
            }
        }
    }

}







