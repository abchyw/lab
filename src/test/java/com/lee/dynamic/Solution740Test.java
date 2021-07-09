package com.lee.dynamic;

import lombok.val;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class Solution740Test {
    @Test
    void test() {
        val t = new Solution740();
        val r = t.deleteAndEarn(new int[]{3,4,2});
        assertThat(r).isEqualTo(6);
    }
    @Test
    void test2() {
        val t = new Solution740();
        val r = t.deleteAndEarn(new int[]{2, 2, 3, 3, 3, 4});
        assertThat(r).isEqualTo(9);
    }

}