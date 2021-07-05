package com.evil.concurrency;

import lombok.val;
import org.junit.jupiter.api.Test;

class SemaphoreExampleTest {
    @Test
    void test() throws InterruptedException {
        val eg = new SemaphoreExample();
        eg.execute();
    }

}