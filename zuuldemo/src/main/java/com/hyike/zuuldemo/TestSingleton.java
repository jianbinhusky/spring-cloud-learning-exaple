package com.hyike.zuuldemo;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestSingleton {
    private boolean lock;

    public boolean isLock() {
        return lock;
    }

    public void setLock(boolean lock) {
        this.lock = lock;
    }

    public static void main(String[] args) throws InterruptedException {
        final Set<String> instanceSet = Collections.synchronizedSet(new HashSet<String>());
        final TestSingleton lock = new TestSingleton();
        lock.setLock(true);
        ExecutorService executorService = Executors.newCachedThreadPool();
        CountDownLatch countDownLatch = new CountDownLatch(1);
        for (int i = 0; i < 100; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName());
                    try {
                        countDownLatch.await();
                        Singleton singleton = Singleton.getInstance();
                        instanceSet.add(singleton.toString());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
//                    while (true) {
//                        if (!lock.isLock()) {
//                            Singleton singleton = Singleton.getInstance();
//                            instanceSet.add(singleton.toString());
//                            break;
//                        }
//                    }
                }
            });
        }
//        Thread.sleep(5000);
//        lock.setLock(false);
//        Thread.sleep(5000);
        Thread.sleep(1000);
        countDownLatch.countDown();
        Thread.sleep(2000);
        for (String s : instanceSet) {
            System.out.println(" ---> " +s);
        }
        executorService.shutdown();
    }
}
