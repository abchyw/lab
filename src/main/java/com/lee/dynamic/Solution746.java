package com.lee.dynamic;

public class Solution746 {
    public int minCostClimbingStairs(int[] cost) {
        // cost = [10, 15, 20]

        int tmp = 0, twoStepsBefore = cost[0], oneStepBefore = cost[1];
        for (int i = 0; i < cost.length - 2; i++) {
            tmp = twoStepsBefore;
            twoStepsBefore = oneStepBefore;
            oneStepBefore = Math.min(tmp, twoStepsBefore) + cost[i + 2];
        }
        return Math.min(twoStepsBefore, oneStepBefore);
    }
}
