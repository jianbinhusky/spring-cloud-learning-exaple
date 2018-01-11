package com.hyike.zuuldemo;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestSingleton2 {
    public static void main(String[] args) throws InterruptedException {
        int num = 100;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(num, new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "good");
            }
        });
        final Set<String> instanceStr = Collections.synchronizedSet(new HashSet<String>());
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < num; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(Thread.currentThread().getName());
                        cyclicBarrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName());
                    Singleton singleton = Singleton.getInstance();
                    instanceStr.add(singleton.toString());
                }
            });
        }
        Thread.sleep(2000);
        for (String s : instanceStr) {
            System.out.println("-----> " + s);
        }
        executorService.shutdown();
    }
}
