package com.lee;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.junit.jupiter.api.Test;

// 62. 不同路径
// 动态规划
// https://leetcode-cn.com/problems/unique-paths/
public class Solution62 {

    public int uniquePaths(int m, int n) {
//        move(1, 1, m, n);
//        return r;
        int[][] countRecords = new int[m + 1][n + 1];
        Set<Position> previousPositions = new HashSet<>();
        countRecords[1][1] = 1;
        previousPositions.add(new Position(1, 1));
        Set<Position> currPositions = new HashSet<>();


        for (int i = 0; i < m - 1 + n - 1; i++) {
            for (Position p : previousPositions) {
                int count = countRecords[p.x][p.y];
                if (p.x < m) {
                    Position left = p.left();
                    countRecords[left.x][left.y] = countRecords[left.x][left.y] + count;
                    currPositions.add(left);
                }
                if (p.y < n) {
                    Position down = p.down();
                    countRecords[down.x][down.y] = countRecords[down.x][down.y] + count;
                    currPositions.add(down);
                }
            }
            previousPositions = currPositions;
            currPositions = new HashSet<>();
        }
        return countRecords[m][n];
    }

    static class Position {
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        Position left() {
            return new Position(x + 1, y);
        }

        public Position down() {
            return new Position(x, y + 1);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Position position = (Position) o;
            return x == position.x && y == position.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    @Test
    void test() {
        int r = uniquePaths(3, 2);
//        int r = uniquePaths(3, 7);
        System.out.println("r: " + r);
    }

    int r = 0;

    void move(int currX, int currY, int m, int n) {
        if (currX == m && currY == n) {
            r = r + 1;
            return;
        }
        // right
        if (currX < m) {
            move(currX + 1, currY, m, n);
        }
        // down
        if (currY < n) {
            move(currX, currY + 1, m, n);
        }
    }

}
