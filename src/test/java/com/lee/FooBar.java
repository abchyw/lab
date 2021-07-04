package com.lee;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class FooBar {
    private int n;

    public FooBar(int n) {
        this.n = n;
    }

    // 1 ================
//    boolean isFoo = true;
//
//    synchronized void waitForFoo() throws InterruptedException {
//        while (!isFoo) {
//            wait();
//        }
//    }
//
//    synchronized void waitForBar() throws InterruptedException {
//        while (isFoo) {
//            wait();
//        }
//    }
//
//    synchronized void changeToBarAndWait() throws InterruptedException {
//        isFoo = false;
//        notify();
//        wait();
//    }
//
//    synchronized void changeToFooAndWait() throws InterruptedException {
//        isFoo = true;
//        notify();
//        wait();
//    }
//
//    public void foo(Runnable printFoo) throws InterruptedException {
//
//        for (int i = 0; i < n; i++) {
//            waitForFoo();
//            // printFoo.run() outputs "foo". Do not change or remove this line.
//            printFoo.run();
//            changeToBarAndWait();
//        }
//    }
//
//    public void bar(Runnable printBar) throws InterruptedException {
//
//        for (int i = 0; i < n; i++) {
//            waitForBar();
//            // printBar.run() outputs "bar". Do not change or remove this line.
//            printBar.run();
//            changeToFooAndWait();
//        }
//    }

    int flag = 0;
    private ReentrantLock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();

    // 2 ================
    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            lock.lock();
            try {
                while (flag != 0) {
                    // The lock associated with this {@code Condition} is atomically
                    //     * released and the current thread becomes disabled for thread scheduling
                    //     * purposes and lies dormant until <em>one</em> of four things happens:
                    c1.await();
                }
                printFoo.run();
                flag = 1;
                c2.signal();
            } finally {
                lock.unlock();
            }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            lock.lock();
            try {
                while (flag != 1) {
                    c2.await();
                }
                printBar.run();
                flag = 0;
                c1.signal();
            } finally {
                lock.unlock();
            }
        }
    }
}
