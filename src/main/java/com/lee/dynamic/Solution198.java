package com.lee.dynamic;

public class Solution198 {

    public int rob(int[] nums) {
        // [2,7,9,3,1]
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }

        int tmp = 0, twoStepBefore = nums[0], oneStepBefore = Math.max(nums[0], nums[1]);
        for (int i = 0; i < nums.length - 2; i++) {
            tmp = twoStepBefore;
            twoStepBefore = oneStepBefore;

            // best(n) = max( best(n-2) + cost(n),  best(n-1) )
            oneStepBefore = Math.max(tmp + nums[i + 2] // steal this room
                    , oneStepBefore // don't steal
            );
        }
        return oneStepBefore;
    }
}
