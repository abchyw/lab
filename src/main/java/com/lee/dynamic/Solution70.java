package com.lee.dynamic;

public class Solution70 {
    public int climbStairs(int n) {
        // 1, 1
        // 2, 2
        // 3, 2 + 1 = 3
        // 4, 3 + 2 = 5
        // n = n-1 + n-2
        if (n < 3) {
            return n;
        }
        int a = 0, b = 1, c = 2;
        // when n is three, loop once
        for (int i = 0; i < n - 2; i++) {
            a = b;
            b = c;
            c = a + b;
        }
        return c;
    }
}
