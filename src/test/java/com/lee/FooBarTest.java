package com.lee;

import org.junit.jupiter.api.Test;

public class FooBarTest {

    @Test
    void test() throws InterruptedException {
        FooBar fb = new FooBar(10);
        Thread threadBar = new Thread(() -> {
            try {
                fb.bar(() -> System.out.println("bar"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread threadFoo = new Thread(() -> {
            try {
                fb.foo(() -> System.out.println("foo"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        threadBar.start();
        Thread.sleep(1000);
        threadFoo.start();
        Thread.sleep(10000);
        System.out.println("test end");
    }
}
