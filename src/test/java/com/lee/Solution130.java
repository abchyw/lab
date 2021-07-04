package com.lee;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

import org.junit.jupiter.api.Test;

// 被围绕的区域
// 广度优先
public class Solution130 {
    public void solve(char[][] board) {
        boolean[][] visited = new boolean[board.length][board[0].length];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                char value = board[i][j];
                if (visited[i][j]) {
                    continue;
                }
                if (value == 'O') {
                    Queue<Cell> queue = new ArrayDeque<>();
                    queue.add(new Cell(i, j));
                    List<Cell> surrounded = new ArrayList<>();
                    boolean isSurrounded = true;
                    while (!queue.isEmpty()) {
                        Cell cell = queue.poll();
                        if (visited[cell.row][cell.col]) {
                            continue;
                        }
                        surrounded.add(cell);
                        if (cell.isEdge(board)) {
                            isSurrounded = false;
                        }
                        // up
                        if (cell.row > 0) {
                            Cell up = cell.up();
                            if (up.value(board) == 'O') {
                                queue.add(up);
                            }
                        }
                        // down
                        if (cell.row < board.length - 1) {
                            Cell down = cell.down();
                            if (down.value(board) == 'O') {
                                queue.add(down);
                            }
                        }
                        // left
                        if (cell.col > 0) {
                            Cell left = cell.left();
                            if (left.value(board) == 'O') {
                                queue.add(left);
                            }
                        }
                        // right
                        if (cell.col < board[0].length - 1) {
                            Cell right = cell.right();
                            if (right.value(board) == 'O') {
                                queue.add(right);
                            }
                        }
                        visited[cell.row][cell.col] = true;
                    }
                    if (isSurrounded) {
                        for (Cell cell : surrounded) {
                            board[cell.row][cell.col] = 'X';
                        }
                    }
                } else {
                    visited[i][j] = true;
                }
            }
        }
    }

    static class Cell {
        public Cell(int row, int col) {
            this.row = row;
            this.col = col;
        }

        int row;
        int col;

        Cell up() {
            return new Cell(row - 1, col);
        }

        public Cell down() {
            return new Cell(row + 1, col);
        }

        public Cell left() {
            return new Cell(row, col - 1);
        }

        public Cell right() {
            return new Cell(row, col + 1);
        }

        char value(char[][] board) {
            return board[row][col];
        }

        public boolean isEdge(char[][] board) {
            return row == 0 || col == 0 || row == board.length - 1 || col == board[0].length - 1;
        }
    }

    @Test
    void test() {
//        char[][] in = new char[][]{
//                {'X', 'X', 'X', 'X'},
//                {'X', 'O', 'O', 'X'},
//                {'X', 'X', 'O', 'X'},
//                {'X', 'O', 'X', 'X'},
//        };
        char[][] in = new char[][]{
                {'X'}
        };
        solve(in);
        System.out.println(Arrays.deepToString(in));
    }
}
