package com.lee.dynamic;

import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Solution509Test {
    @Test
    void test() {
        val s = new Solution509();
        val r = s.fib(4);
        Assertions.assertEquals(3, r);
    }

}