package com.evil.concurrency;

import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BoundedBufferTest {

    private static final long LOCKUP_DETECT_TIMEOUT = 5;

    @Test
    void isEmptyWhenConstructed() {
        // when
        val bb = new BoundedBuffer<Integer>(10);
        // then
        assertThat(bb.isEmpty()).isTrue();
        assertThat(bb.isFull()).isFalse();
    }

    @Test
    void isFullWhenPuts() throws InterruptedException, BrokenBarrierException {
        // when
        val barrier = new CyclicBarrier(3);
        val bb = new BoundedBuffer<Integer>(10);
        new Thread(() -> {
            try {
                barrier.await();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            for (int i = 0; i < 5; i++) {
                try {
                    bb.put(i);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            try {
                barrier.await();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).start();
        new Thread(() -> {
            try {
                barrier.await();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            for (int i = 0; i < 5; i++) {
                try {
                    bb.put(i);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            try {
                barrier.await();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).start();
        barrier.await();
        barrier.await();
        // then
        assertThat(bb.isFull()).isTrue();
        assertThat(bb.isEmpty()).isFalse();
    }

    @Test
    void takeCanBeBlockedWhenEmpty() {
        val buff = new BoundedBuffer<Integer>(10);
        // when
        val taker = new Thread(() -> {
            try {
                int unused = buff.take();
                fail();
            } catch (InterruptedException success) {
            }
        });
        try {
            taker.start();
            Thread.sleep(LOCKUP_DETECT_TIMEOUT);
            taker.interrupt();
            taker.join(LOCKUP_DETECT_TIMEOUT);
            assertThat(taker.isAlive()).isFalse();
        } catch (Exception unexpected) {
            fail();
        }
    }

}