package com.evil.concurrency;

import lombok.val;

import java.util.concurrent.*;

public class CancellationExample {
    private static final ScheduledExecutorService cancelExec = new ScheduledThreadPoolExecutor(2);
    private static final ExecutorService taskExec = new ThreadPoolExecutor(2, 3, 1000, TimeUnit.SECONDS, new LinkedBlockingDeque<>());

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
        // try interrupting the thread after timeout
        cancelExec.schedule(() -> {
            System.out.println("interrupt is called");
            taskThread.interrupt();
        }, timeout, unit);
        taskThread.join(unit.toMillis(timeout) + 3000);
        task.rethrow();
    }

    public static void timedRunWithTask(final Runnable r,
                                        long timeout,
                                        TimeUnit unit) throws InterruptedException {
        Future<?> task = taskExec.submit(r);
        try {
            task.get(timeout, unit);
        } catch (TimeoutException e) {
            System.out.println("time out");
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } finally {
            task.cancel(true);
        }
    }
}
