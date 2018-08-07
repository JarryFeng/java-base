package com.jarry.thread;

import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class CachedData {
    Object data;
    volatile boolean cacheValid;
    ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

    void processCachedData() {
        rwl.readLock().lock();
        if (!cacheValid) {
            // Must release read lock before acquiring write lock
            rwl.readLock().unlock();
            rwl.writeLock().lock();
            // Recheck state because another thread might have acquired
            //   write lock and changed state before we did.
            System.out.println(Thread.currentThread().getName() + "获取写锁");
            if (!cacheValid) {

                data = "123";
                cacheValid = true;
            }
            // Downgrade by acquiring read lock before releasing write lock
            rwl.readLock().lock();
            rwl.writeLock().unlock(); // Unlock write, still hold read
        }

        System.out.println(Thread.currentThread().getName() + ":" + data);
        rwl.readLock().unlock();
    }

    volatile int i = 0;
    ExecutorService executorService = Executors.newFixedThreadPool(5);
    public static void main(String[] args) throws InterruptedException {
        CachedData cachedData = new CachedData();
        cachedData.dod();
        while(true){
            if(cachedData.executorService.isTerminated()){
                System.out.println();
                System.out.println(cachedData.i);
                break;
            }
        }
    }

    public void dod(){
        for(int j=0 ;j <10; j++){
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    processCachedData();
                }
            });
        }
        executorService.shutdown();
    }
}
