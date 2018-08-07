package com.jarry.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by jarry on 2018/8/5.
 * <p>
 * 通过读写锁还实现缓存系统
 */
public class CacheDemo {

    private static Map<String, String> cache = new HashMap<>();

    private static ReadWriteLock rwl = new ReentrantReadWriteLock();

    public static void main(String[] args) {
        for(int i=0;i<20;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String value = getData("key");
                    System.out.println(Thread.currentThread().getName() + "：获取到数据" + value);
                }
            }).start();
        }
    }

    public static String getData(String key) {
        rwl.readLock().lock();
        String value = null;
        try{
            value = cache.get(key);
            if (value == null) {
                rwl.readLock().unlock();
                rwl.writeLock().lock();
                if(value == null){
                    try{
                        System.out.println(Thread.currentThread().getName() +":查询数据库...");
                        value = "123";
                        cache.put(key, value);
                    }finally {
                        rwl.readLock().lock();
                        rwl.writeLock().unlock();
                    }
                }
            }
        }finally {
            rwl.readLock().unlock();
        }

        return value;
    }
}
