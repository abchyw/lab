package com.lee.dynamic;

import lombok.val;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class Solution198Test {

    @Test
    void test() {
        val s = new Solution198();
        // when
        val r = s.rob(new int[]{2, 7, 9, 3, 1});
        // then
        assertThat(r).isEqualTo(12);
    }

    @Test
    void test2() {
        val s = new Solution198();
        // when
        val r = s.rob(new int[]{2,1,1,2});
        // then
        assertThat(r).isEqualTo(4);
    }
}