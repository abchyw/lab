package com.lee.dynamic;

import lombok.val;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Solution746Test {
    @Test
    void test() {
        val s = new Solution746();
        // when
        val r = s.minCostClimbingStairs(new int[]{10, 15, 20});

        // then
        assertThat(r).isEqualTo(15);
    }

    @Test
    void test2() {
        val s = new Solution746();
        // when
        val r = s.minCostClimbingStairs(new int[]{1, 100, 1, 1, 1, 100, 1, 1, 100, 1});

        // then
        assertThat(r).isEqualTo(6);
    }

}