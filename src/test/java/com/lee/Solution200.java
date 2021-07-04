package com.lee;

import java.nio.channels.Pipe;
import java.util.ArrayDeque;
import java.util.Queue;

import org.junit.jupiter.api.Test;

// 200. 岛屿数量
// 广度优先
public class Solution200 {
    public int numIslands(char[][] grid) {
        boolean[][] states = new boolean[grid.length][grid[0].length];

        int numberOfLands = 0;
        Queue<Point> landQueue = new ArrayDeque<>();
        for (int i = 0; i < states.length; i++) {
            for (int j = 0; j < states[0].length; j++) {
                if (states[i][j]) {
                    // visited
                    continue;
                }
                int value = grid[i][j];
                if (value == '1') {
                    numberOfLands++;
                    landQueue.add(new Point(i, j));
                    while (!landQueue.isEmpty()) {
                        Point p = landQueue.poll();
                        if (states[p.row][p.col]) {
                            // visited
                            continue;
                        }
                        if (p.row > 0) {
                            int up = grid[p.row - 1][p.col];
                            if (up == '1') {
                                landQueue.add(new Point(p.row - 1, p.col));
                            }
                        }
                        if (p.row < states.length - 1) {
                            int down = grid[p.row + 1][p.col];
                            if (down == '1') {
                                landQueue.add(new Point(p.row + 1, p.col));
                            }
                        }
                        if (p.col > 0) {
                            int left = grid[p.row][p.col - 1];
                            if (left == '1') {
                                landQueue.add(new Point(p.row, p.col - 1));
                            }
                        }
                        if (p.col < states[0].length - 1) {
                            int right = grid[p.row][p.col + 1];
                            if (right == '1') {
                                landQueue.add(new Point(p.row, p.col + 1));
                            }
                        }
                        // set visited
                        states[p.row][p.col] = true;
                    }
                }

            }
        }
        return numberOfLands;
    }

    static class Point {
        int row;
        int col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    @Test
    void test() {
//        char[][] grid = {
//                {'1', '1', '1', '1', '0'},
//                {'1', '1', '0', '1', '0'},
//                {'1', '1', '0', '0', '0'},
//                {'0', '0', '0', '0', '0'}
//        };
        char[][] grid = {
                {'1', '1', '1'},
                {'0', '1', '0'},
                {'1', '1', '1'},
        };
//        char[][] grid = {
//                {'1', '1', '1', '1', '0'},
//                {'1', '1', '0', '1', '0'},
//                {'1', '0', '1', '0', '0'},
//                {'0', '0', '0', '0', '0'},
//                {'0', '0', '0', '1', '1'}
//        };
        int r = numIslands(grid);
        System.out.println(r);
    }
}
