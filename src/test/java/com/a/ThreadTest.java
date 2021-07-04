package com.a;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ThreadTest {
    Object lock1 = new Object();
    Object lock2 = new Object();

    @Test
    void deadLock() throws InterruptedException {
        new Thread(() -> {
            synchronized (lock1) {
                try {
                    Thread.sleep(1000);
                    System.out.println("thread 1. getting lock 1");
                    synchronized (lock2) {
                        System.out.println("thread 1. getting lock 2");
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
        new Thread(() -> {
            synchronized (lock2) {
                try {
                    Thread.sleep(1000);
                    System.out.println("thread 2. getting lock 2");
                    synchronized (lock1) {
                        System.out.println("thread 2. getting lock 1");
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        Thread.sleep(15000);
    }

    @Test
    public void lockInterruptibly() throws Exception {
        final Lock lock = new ReentrantLock();
        lock.lock();
        Thread.sleep(1000);
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
//                    lock.lock();
                    lock.lockInterruptibly();
                    System.out.println(Thread.currentThread().getName() + " interrupted. 1");
                } catch (Exception e) {
                    System.out.println(Thread.currentThread().getName() + " interrupted. 2");
                }
            }
        });
        t1.start();
        Thread.sleep(1000);
        t1.interrupt();
        Thread.sleep(10000);
    }

    @Test
    public void threadPool() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<Integer> task = executorService.submit(() -> {
            System.out.println(Thread.currentThread().getName() + " run");
            return 1;
        });
        Future<Integer> task2 = executorService.submit(() -> {
            System.out.println(Thread.currentThread().getName() + " run");
            return 2;
        });

        Thread.sleep(20000);
    }

    @Test
    public void threadPoolScheduler() throws Exception {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);
        AtomicInteger count = new AtomicInteger();
        scheduler.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " count: " + count.incrementAndGet());
                if (count.intValue() == 5) {
                    scheduler.shutdown();
                    System.out.println("shut down");
                }
            }
        }, 1, 3, TimeUnit.SECONDS);

        Thread.sleep(20000);
    }

    @Test
    public void threadLocal() throws Exception {
        ThreadLocal<Integer> tl = new ThreadLocal<>();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " tl: " + tl.get());
            tl.set(1);
            System.out.println(Thread.currentThread().getName() + " tl: " + tl.get());
        }).start();
        Thread.sleep(1000);
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " tl: " + tl.get());
            tl.set(2);
            System.out.println(Thread.currentThread().getName() + " tl: " + tl.get());
        }).start();
        Thread.sleep(1000);
    }

    @Test
    public void lockAndCondition() throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        new Thread(() -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "thread got lock");
                /* <p>The lock associated with this {@code Condition} is atomically
                 * released and the current thread becomes disabled for thread scheduling
                 * purposes and lies dormant until <em>one</em> of four things happens:
                 * <ul>
                 */
                condition.await();
                System.out.println(Thread.currentThread().getName() + "Thread is going on");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }).start();

        Thread.sleep(1000);
        lock.lock();
        condition.signal();
        System.out.println("signal in main thread");
        lock.unlock();

    }
}