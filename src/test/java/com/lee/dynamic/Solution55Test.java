package com.lee.dynamic;

import lombok.val;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Solution55Test {
    @Test
    void test() {
        val s = new Solution55();
        // when
        val r = s.canJump(new int[]{2, 3, 1, 1, 4});
        // then
        assertThat(r).isEqualTo(true);
    }

    @Test
    void test2() {
        val s = new Solution55();
        // when
        val r = s.canJump(new int[]{3, 2, 1, 0, 4});
        // then
        assertThat(r).isEqualTo(false);
    }
}