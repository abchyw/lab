package com.lee.dynamic;

public class Solution509 {
//    public int fib(int n) {
//        if (n == 0) {
//            return 0;
//        }
//        if (n == 1) {
//            return 1;
//        }
//        return fib(n - 1) + fib(n - 2);
//    }

    public int fib(int n) {
        if (n < 2) {
            return n;
        }
        int o = 0, p = 1, q = 0;
        for (int i = 0; i < n - 1; i++) {
            q = o + p;
            o = p;
            p = q;
        }
        return q;
    }
}
