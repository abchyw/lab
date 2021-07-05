package com.evil.concurrency;

import lombok.val;

import java.util.concurrent.CountDownLatch;

public class LatchHarness {
    public long timeTasks(int nThreads, final Runnable task) throws InterruptedException {
        val startGate = new CountDownLatch(1);
        val endGate = new CountDownLatch(nThreads);

        for (int i = 0; i < nThreads; i++) {
            val t = new Thread(() -> {
                try {
                    startGate.await();
                    try {
                        task.run();
                    } finally {
                        endGate.countDown();
                    }
                } catch (InterruptedException ignored) {

                }

            });
            t.start();
        }
        val start = System.nanoTime();
        // start executing tasks
        startGate.countDown();
        // wait until all the tasks are completed
        endGate.await();
        val end = System.nanoTime();
        return end - start;
    }
}
