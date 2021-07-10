package com.lee.dynamic;

import lombok.val;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class Solution152Test {

    // 21:37
    @Test
    void test() {
        val s = new Solution152();
        // when
        val r = s.maxProduct(new int[]{2, 3, -2, 4});
        // then
        assertThat(r).isEqualTo(6);
    }
}