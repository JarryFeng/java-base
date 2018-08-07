package com.jarry.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by jarry on 2018/8/6.
 *
 * 它允许一组线程互相等待，直到到达某个公共屏障点
 */
public class CyclicBarriertTest {

    public static void main(String[] args) {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);

        for(int i=0;i < 3; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep((long)(Math.random()* 1000));
                        System.out.println(Thread.currentThread().getName() + "即将到达站点1,当前已有" + (cyclicBarrier.getNumberWaiting() + 1 ) +"到达," + (cyclicBarrier.getNumberWaiting() == 2? "已到齐，继续走":"等待其他人"));
                        cyclicBarrier.await();

                        Thread.sleep((long)(Math.random()* 1000));
                        System.out.println(Thread.currentThread().getName() + "即将到达站点2,当前已有" + (cyclicBarrier.getNumberWaiting() + 1 ) +"到达," + (cyclicBarrier.getNumberWaiting() == 2? "已到齐，继续走":"等待其他人"));
                        cyclicBarrier.await();

                        Thread.sleep((long)(Math.random()* 1000));
                        System.out.println(Thread.currentThread().getName() + "即将到达站点3,当前已有" + (cyclicBarrier.getNumberWaiting() + 1 ) +"到达," + (cyclicBarrier.getNumberWaiting() == 2? "已到齐，继续走":"等待其他人"));
                        cyclicBarrier.await();

                        System.out.println("小伙伴都已经到达站点3，结束");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
