package com.jarry.thread;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by jarry on 2018/8/6.
 * 信号灯
 * 锁可以由其他线程来释放，解决死锁的问题。
 */
public class SemaphoreTest {
    //有10个线程同时执行
    static ExecutorService executorService = Executors.newFixedThreadPool(10);
    //启到限流的作用
    static Semaphore semaphore = new Semaphore(3);
    public static void main(String[] args) {

        for(int i=0; i<10 ;i++){
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    //获取到权限
                    try {
                        semaphore.acquire();
                        System.out.println("线程" + Thread.currentThread().getName() + "进入，当前已有" + (3 - semaphore.availablePermits()) + "个并发");
                        Thread.sleep((long)(Math.random() * 10000.0D));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {
                        System.out.println("线程" + Thread.currentThread().getName() + "即将离开");
                        //释放权限
                        semaphore.release();
                        System.out.println("线程" + Thread.currentThread().getName() + "已离开，当前已有" + (3 - semaphore.availablePermits()) + "个并发");
                    }
                }
            });
        }

        executorService.shutdown();
    }
}
