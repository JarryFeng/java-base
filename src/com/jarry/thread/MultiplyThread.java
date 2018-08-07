package com.jarry.thread;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by jarry on 2018/7/30.
 */
public class MultiplyThread {

    public static void main(String[] args) {

        System.out.println("begin:" + (System.currentTimeMillis() / 1000));
            /*模拟处理16行日志，下面的代码产生了16个日志对象，当前代码需要运行16秒才能打印完这些日志。
            修改程序代码，开四个线程让这16个对象在4秒钟打完。
			*/

        //BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<String>(1);

        Queue<String> queue = new ArrayBlockingQueue<String>(1);

        for (int i = 0; i < 4; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {

                        String take = queue.poll();
                        parseLog(take);

                    }

                }
            }).start();
        }

        for (int i = 0; i < 16; i++) {  //这行代码不能改动
            final String log = "" + (i + 1);//这行代码不能改动
            {
                //parseLog(log);
                queue.add(log);
            }
        }

    }

    //parseLog方法内部的代码不能改动
    public static void parseLog(String log) {
        System.out.println(Thread.currentThread().getName() + "=====" + log + ":" + (System.currentTimeMillis() / 1000));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
