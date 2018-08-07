package com.jarry.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by jarry on 2018/8/5.
 * <p>
 * 子线程循环10次，接着主线程循环100次，接着又回到子线程循环10次，接着再回到主线程又循环100次， 如此循环50次
 */
public class TwoThreadTest {

    static int isMainDo = 1;

    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public static void main(String[] args) throws InterruptedException {

        TwoThreadTest twoThreadTest = new TwoThreadTest();
        new Thread(
            new Runnable() {
                @Override
                public void run() {
                    for (int j = 1; j <= 50; j++) {
                        twoThreadTest.sub(j);
                    }
                }
            }
        ).start();

        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        for (int j = 1; j <= 50; j++) {
                            twoThreadTest.sub2(j);
                        }
                    }
                }
        ).start();

        for (int j = 1; j <= 50; j++) {
            twoThreadTest.mainDo(j);
        }


    }


    public void sub(int k) {
        lock.lock();
        while (isMainDo != 2) {
            try {
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (int i = 1; i <= 10; i++) {
            System.out.println( Thread.currentThread().getName() + "第" + k + "次, 二线程执行" + i + "次");
        }

        isMainDo = 3;
        condition.signalAll();
        lock.unlock();
    }

    public void sub2(int k) {
        lock.lock();
        while (isMainDo != 3) {
            try {
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (int i = 1; i <= 10; i++) {
            System.out.println(Thread.currentThread().getName() + "第" + k + "次, 三线程执行" + i + "次");
        }

        isMainDo = 1;
        condition.signalAll();
        lock.unlock();
    }


    public void mainDo(int k) {
lock.lock();
        while (isMainDo != 1) {
            try {
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (int i = 1; i <= 10; i++) {
            System.out.println(Thread.currentThread().getName() + "第" + k + "次, 主线程执行" + i + "次");
        }
        isMainDo = 2;
        condition.signalAll();
        lock.unlock();
    }
}
