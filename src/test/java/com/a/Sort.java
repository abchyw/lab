package com.a;

import org.junit.jupiter.api.Test;

public class Sort {

    @Test
    void bubbleSort() {
        int[] arr = new int[]{3, 4, 7, 1, 2};

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                int p = arr[j];
                int n = arr[j + 1];
                if (p > n) {
                    // swap
                    arr[j + 1] = p;
                    arr[j] = n;
                }
            }
        }

        for (int i : arr) {
            System.out.println(i);
        }
    }
}
