package com.jarry.thread;

import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by jarry on 2018/8/5.
 * 读与读之间不互斥
 * 读与写之间互斥
 * 写与写之间互斥
 */
public class ReadWriterLockTest {

    static Queue queue = new Queue();

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        queue.get();
                    }
                }

            }).start();


            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        queue.write(new Random().nextInt(1000));
                    }
                }
            }).start();
        }


    }
}


class Queue {
    static int data;

    ReadWriteLock rwl = new ReentrantReadWriteLock(true);

    public void get() {
        rwl.readLock().lock();
        System.out.println(Thread.currentThread().getName() + "开始读数据...");
        try {
            Thread.sleep((long)(Math.random()*1000));
            System.out.println(Thread.currentThread().getName() + "已经读完数据" + data);
        } catch (Exception e) {
        } finally {
            rwl.readLock().unlock();
        }
    }

    public void write(int data2) {
        rwl.writeLock().lock();
        System.out.println(Thread.currentThread().getName() + "开始写数据...");
        try {
            Thread.sleep((long)(Math.random()*1000));
            data = data2;
            System.out.println(Thread.currentThread().getName() + "已经写完数据" + data);
        } catch (Exception e) {
            return;
        } finally {
            rwl.writeLock().unlock();
        }

    }
}