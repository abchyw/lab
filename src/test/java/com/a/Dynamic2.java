package com.a;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class Dynamic2 {

    void shortest(int[][] w) {
        int[][] shortest = new int[w.length][w[0].length];

        // fill the first row
        shortest[0][0] = w[0][0];
        for (int i = 1; i < w[0].length; i++) {
            shortest[0][i] = shortest[0][i - 1] + w[0][i];
        }
        // fill the first column
        for (int i = 1; i < w.length; i++) {
            shortest[i][0] = shortest[i - 1][0] + w[i][0];
        }
        printArray(shortest);
        for (int i = 1; i < w.length; i++) {
            // vertically
            for (int j = 1; j < w[0].length; j++) {
                // horizontally
                int curr = w[i][j];
                shortest[i][j] = Math.min(shortest[i][j - 1] + curr, shortest[i - 1][j] + curr);
            }
        }
        printArray(shortest);
    }

    @Test
    void test() {
        int[][] w = {
                {1, 3, 5, 9},
                {2, 1, 3, 4},
                {5, 2, 6, 7},
                {6, 8, 4, 3}
        };
        shortest(w);
    }

    void printArray(int[][] arr) {
        for (int[] s : arr) {
            System.out.println(Arrays.toString(s));
        }
    }

}
