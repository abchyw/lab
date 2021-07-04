package com.a;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class Dynamic {
    private int maxW = Integer.MIN_VALUE; // 结果放到maxW中
    private int[] weight = {2, 2, 4, 6, 3};// 物品重量
    private int[] value = {3, 4, 8, 9, 6}; // 物品的价值
    private int n = 5; // 物品个数
    private int w = 9; // 背包承受的最大重量

    @Test
    void dynamic() {
        // an array
        //   | 0 | 1 |...| 9 |
        // 0 | f | f |...| f |
        // 1 | f | f |...| f |
        boolean[][] states = new boolean[n + 1][w + 1];
        states[0][0] = true;
        for (int i = 0; i < n; i++) {
            int numberOfItems = i + 1;
            int weightOfItem = weight[i];
            for (int j = 0; j <= 9; j++) {
                boolean existing = states[numberOfItems - 1][j];
                if (existing) {
                    // put this item in
                    if (j + weightOfItem <= 9) {
                        states[numberOfItems][j + weightOfItem] = true;
                    }

                    // don't put this item in
                    states[numberOfItems][j] = true;
                }
            }
        }
        for (int i = w; i >= 0; i--) {
            boolean existing = states[n][i];
            if (existing) {
                System.out.println("sum: " + i);
                break;
            }
        }
        for (boolean[] state : states) {
            System.out.println(Arrays.toString(state));
        }
    }

    @Test
    void dynamicWithValue() {
        // an array
        //   | 0 | 1 | 2 |...| 9 |
        // 0 | 0 | 0 | 0 |...| 0 |
        // 1 | 0 | 0 | 3 |...| 0 |
        // 1 | 0 | 0 | 4 |...| 0 |
        int[][] states = new int[n + 1][w + 1];
        for (int[] state : states) {
            Arrays.fill(state, -1);
        }
        states[0][0] = 0;
        for (int i = 0; i < n; i++) {
            int numberOfItems = i + 1;
            int weightOfItem = weight[i];
            int valueOfItem = value[i];
            for (int j = 0; j <= 9; j++) {
                int valueBeforeAdding = states[numberOfItems - 1][j];
                if (valueBeforeAdding == -1) {
                    continue;
                }
                // put this item in
                int weightAfterAdding = j + weightOfItem;
                if (weightAfterAdding <= 9) {
                    states[numberOfItems][weightAfterAdding] = Math.max(valueBeforeAdding + valueOfItem, states[numberOfItems - 1][weightAfterAdding]);
                }

                // don't put this item in
                states[numberOfItems][j] = Math.max(valueBeforeAdding, states[numberOfItems][j]);
            }
        }

        int result = -1;
        for (int i = w; i >= 0; i--) {
            if (states[n][i] != -1) {
                result = states[n][i];
                break;
            }
        }
        System.out.println("result: " + result);
        int allValue = result;
        // figure out the items put in
        for (int i = n; i > 0; i--) {
            for (int j = w; j >= 0; j--) {
                int currValue = states[i][j];
                if (currValue == allValue) {
                    if (states[i - 1][j] == currValue) {
                        System.out.println("item " + i + " not put in");
                    } else {
                        System.out.println("item " + i + " put in");
                        allValue = allValue - value[i - 1];
                    }
                    break;
                }
            }
        }
        // 2, 3, 5

        for (int[] state : states) {
            System.out.println(Arrays.toString(state));
        }
//        int r = knapsack3(weight, value, n, w);
//        System.out.println("result: " + r);
    }

    public static int knapsack3(int[] weight, int[] value, int n, int w) {
        int[][] states = new int[n][w + 1];
        for (int i = 0; i < n; ++i) { // 初始化states
            for (int j = 0; j < w + 1; ++j) {
                states[i][j] = -1;
            }
        }
        states[0][0] = 0;
        if (weight[0] <= w) {
            states[0][weight[0]] = value[0];
        }
        for (int i = 1; i < n; ++i) { //动态规划，状态转移
            for (int j = 0; j <= w; ++j) { // 不选择第i个物品
                if (states[i - 1][j] >= 0) states[i][j] = states[i - 1][j];
            }
            for (int j = 0; j <= w - weight[i]; ++j) { // 选择第i个物品
                if (states[i - 1][j] >= 0) {
                    int v = states[i - 1][j] + value[i];
                    if (v > states[i][j + weight[i]]) {
                        states[i][j + weight[i]] = v;
                    }
                }
            }
        }
        // 找出最大值
        int maxvalue = -1;
        for (int j = 0; j <= w; ++j) {
            if (states[n - 1][j] > maxvalue) maxvalue = states[n - 1][j];
        }
        return maxvalue;
    }
}
