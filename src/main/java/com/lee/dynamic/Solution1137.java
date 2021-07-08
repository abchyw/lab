package com.lee.dynamic;

public class Solution1137 {
    public int tribonacci(int n) {
        if (n < 2) {
            return n;
        }
        if (n == 2){
            return 1;
        }
        int a = 0, b = 0, c = 1, d = 1;
        for (int i = 0; i < n - 2; i++) {
            a = b;
            b = c;
            c = d;
            d = a + b + c;
        }
        return d;
    }
}
