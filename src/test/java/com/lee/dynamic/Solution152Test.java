package com.lee.dynamic;

import lombok.val;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class Solution152Test {
    @ParameterizedTest
    @MethodSource("args")
    void testA(int[] nums, int expectedMax) {
        val s = new Solution152();
        // when
        val r = s.maxProduct(nums);
        // then
        assertThat(r).isEqualTo(expectedMax);

    }

    private static Stream<Arguments> args() {
        return Stream.of(
                Arguments.of(new int[]{2, 3, -2, 4}, 6),
                Arguments.of(new int[]{-2, 0, -1}, 0),
                Arguments.of(new int[]{-2, 3, -4}, 24),
                Arguments.of(new int[]{-1, -2, -9, -6}, 108)
        );
    }

}