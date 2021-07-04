package com.lee;

import org.junit.jupiter.api.Test;

public class Solution64 {
    public int minPathSum(int[][] grid) {
        int[][] records = new int[grid.length][grid[0].length];
        records[0][0] = grid[0][0];
        for (int i = 1; i < grid.length; i++) {
            records[i][0] = records[i - 1][0] + grid[i][0];
        }
        for (int i = 1; i < grid[0].length; i++) {
            records[0][i] = records[0][i - 1] + grid[0][i];
        }
//        System.out.println(Arrays.deepToString(records));
        for (int i = 1; i < grid.length; i++) {
            for (int j = 1; j < grid[0].length; j++) {
                records[i][j] = grid[i][j] + Math.min(records[i - 1][j], records[i][j - 1]);
            }
        }
//        System.out.println(Arrays.deepToString(records));
        // 1, 1, 4
        // 1, 6, 5
        // 4, 6, 6

        return records[grid.length - 1][grid[0].length - 1];
    }

    @Test
    void test() {
        int r = minPathSum(new int[][]{
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        });
        System.out.println("r: " + r);
    }
}
