package com.evil.concurrency;

import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

class CancellationExampleTest {

    @Test
    void test() throws InterruptedException {
        System.out.println("main thread: " + Thread.currentThread().getName());
        val start = System.nanoTime();
        try {
            CancellationExample.timedRun(
                    () -> {
                        try {
                            Thread.sleep(10000);
                            System.out.println("sleep is completed");
                        } catch (InterruptedException e) {
                            // it seems that calling thread.interrupt will stops the job of the runnable (in this case sleep)
                            // isInterrupted would be false even if e is thrown...
                            System.out.println(Thread.currentThread().getName() + " status: " + Thread.currentThread().isInterrupted());
                            throw new RuntimeException(e);
                        }
                    },
                    3,
                    TimeUnit.SECONDS
            );
        } finally {
            val end = System.nanoTime();
            System.out.println("time used: " + (end - start) / 1000 / 1000 / 1000);
        }
    }
}