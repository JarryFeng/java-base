package com.jarry.thread;

import java.util.Random;
import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by jarry on 2018/8/6.
 * 两个线程之间的数据交换
 *
 */
public class ExchangerTest {

    public static void main(String[] args) {

        Exchanger<String> exchanger = new Exchanger<>();
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "已经到达交易地点,等待交易");
                try {
                    Thread.sleep((long)new Random().nextDouble()*1000);
                    String data = exchanger.exchange("大王");
                    System.out.println(Thread.currentThread().getName() + "我用大王换取到了" + data);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "已经到达交易地点,等待交易");
                try {
                    Thread.sleep((long)new Random().nextDouble()*1000);
                    String data = exchanger.exchange("小王");
                    System.out.println(Thread.currentThread().getName() + "我用小王换取到了" + data);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        executorService.shutdown();
    }
}
