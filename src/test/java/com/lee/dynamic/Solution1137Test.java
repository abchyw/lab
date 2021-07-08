package com.lee.dynamic;

import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class Solution1137Test {
    @ParameterizedTest
    @CsvSource({
            "2, 1",
            "25, 1389537",
    })
    void test(int input, int expected) {
        val s = new Solution1137();
        val r = s.tribonacci(input);
        assertThat(r).isEqualTo(expected);
    }

}