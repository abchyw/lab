package com.evil.concurrency;

import lombok.val;
import org.junit.jupiter.api.Test;

class LatchHarnessTest {

    @Test
    void test() throws InterruptedException {
        val latchHarness = new LatchHarness();
        val time = latchHarness.timeTasks(5,
                () -> System.out.println("runnable executed")
        );
        System.out.println("time: " + time);
    }

}