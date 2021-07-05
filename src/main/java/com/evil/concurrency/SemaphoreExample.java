package com.evil.concurrency;

import lombok.val;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreExample {
    void execute() throws InterruptedException {
        val semaphore = new Semaphore(2);
        val executorService = Executors.newCachedThreadPool();
        val nThread = 10;
        val latch = new CountDownLatch(nThread);

        for (int i = 0; i < nThread; i++) {
            val index = i;
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    System.out.println("线程:" + Thread.currentThread().getName() + "获得许可:" + index);
                    TimeUnit.SECONDS.sleep(1);
                    semaphore.release();
                    System.out.println("允许TASK个数：" + semaphore.availablePermits());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    latch.countDown();
                }
            });
        }
        latch.await();
        executorService.shutdown();
    }
}
