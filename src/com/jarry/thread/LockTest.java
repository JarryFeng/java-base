package com.jarry.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by jarry on 2018/8/1.
 */
public class LockTest {

    public static void main(String[] args) {
        new Thread(new Runnable() {
            int i =0;
            @Override
            public void run() {
                while(i <1000){
                    print("zhangxiaoxiang");

                    i++;
                }
            }
        }).start();
        new Thread(new Runnable() {
            int j = 0;
            @Override
            public void run() {
                while(j < 1000){
                    print("liheming");

                    j ++;
                }
            }
        }).start();
    }
    static Lock lock = new ReentrantLock();
    public static void print(String name){
        lock.lock();
        for(int i =0; i< name.length(); i++){
            System.out.print(name.charAt(i));
        }
        System.out.println();
        lock.unlock();
    }
}
