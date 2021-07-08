package com.lee.dynamic;

import lombok.val;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class Solution70Test {

    @ParameterizedTest
    @CsvSource({
            "3, 3",
            "4, 5",
    })
    void test(int input, int expected) {
        val s = new Solution70();
        val r = s.climbStairs(input);
        assertThat(r).isEqualTo(expected);
    }
}