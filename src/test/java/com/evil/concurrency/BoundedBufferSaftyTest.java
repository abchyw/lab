package com.evil.concurrency;

import lombok.val;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

class BoundedBufferSaftyTest {

    @Test
    void test() {
        val t = new PutTakeTest(5, 2, 10);
        t.test();
        t.shutdown();
    }

}