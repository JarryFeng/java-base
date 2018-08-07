package com.jarry.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by jarry on 2018/8/6.
 * <p>
 * 计数器
 */
public class CountDownLatchTest {

    public static void main(String[] args) throws InterruptedException {
        //裁判发令枪
        CountDownLatch start = new CountDownLatch(1);
        CountDownLatch end = new CountDownLatch(3);

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        //裁判准备发号施令

        //运动员准备起跑
        for(int i =0; i<3; i++){
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + "我已经在起跑线上准备好了，等待发枪");
                    try {
                        start.await();
                        System.out.println(Thread.currentThread().getName() + "我在狂奔中");
                        Thread.sleep((long)(Math.random()* 1000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(Thread.currentThread().getName() + "我已到达终点");
                    end.countDown();
                }
            });
        }



        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("各就各位预备!");
        start.countDown();
        end.await();
        System.out.println("哔。。。哔。。。");
        executorService.shutdown();


    }
}
