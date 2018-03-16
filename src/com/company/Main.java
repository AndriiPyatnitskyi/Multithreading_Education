package com.company;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Store store = new Store();
        Producer producer = new Producer();
        Consumer consumer = new Consumer();
        producer.setStore(store);
        consumer.setStore(store);
        new Thread(producer).start();
        new Thread(consumer).start();
    }
}

class Store {
    List<Object> objectList = new ArrayList<>();

    public synchronized void produce() throws InterruptedException {
        while (objectList.isEmpty()){
            System.out.println(Thread.currentThread().getName()+ " >>> produce method");
            Object object = new Object();
            objectList.add(object);
            System.out.println("Produced: " + object);
            notify();
            Thread.sleep(1000);
        }
    }

    public synchronized void get() throws InterruptedException {
        while(!objectList.isEmpty()){
            System.out.println(Thread.currentThread().getName() + " >>> consume method");
            Object object = objectList.remove(0);
            System.out.println("Consumed: " + object);
        }
        wait();
        Thread.sleep(1000);

    }
}

class Producer implements Runnable{
    private Store store;

    public void setStore(Store store) {
        this.store = store;
    }

    @Override
    public void run() {
        while (true){
            try {
                store.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Consumer implements Runnable{
    private Store store;

    public void setStore(Store store) {
        this.store = store;
    }

    @Override
    public void run() {
        while (true){
            try {
                store.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}