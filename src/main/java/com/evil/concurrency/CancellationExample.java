package com.evil.concurrency;

import lombok.val;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CancellationExample {
    private static final ScheduledExecutorService cancelExec = new ScheduledThreadPoolExecutor(2);

    public static void timedRun(final Runnable r,
                                long timeout,
                                TimeUnit unit) throws InterruptedException {
        class RethrowableTask implements Runnable {
            private volatile Throwable t;

            @Override
            public void run() {
                try {
                    r.run();
                } catch (Throwable t) {
                    this.t = t;
                }
            }

            void rethrow() {
                System.out.println("rethrow is called");
                if (t != null) {
                    System.out.println("t is thrown");
                    throw new RuntimeException(t);
                }
            }
        }

        val task = new RethrowableTask();
        val taskThread = new Thread(task);
        taskThread.start();
        cancelExec.schedule(() -> {
            System.out.println("interrupt is called");
            taskThread.interrupt();
        }, timeout, unit);
        taskThread.join(unit.toMillis(timeout) + 3000);
        task.rethrow();
    }
}
