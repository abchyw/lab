package com.lee.dynamic;

public class Solution152 {
    public int maxProduct(int[] nums) {
        // [2,3,-2,4]
        if (nums.length == 1) {
            return nums[0];
        }
        // init, till 2
        int maxExcluded = nums[0];
        int maxIncluded = Math.max(nums[0] * nums[1], nums[1]);
        int minIncluded = Math.min(nums[0] * nums[1], nums[1]);

        int previousMaxIncluded, previousMaxExcluded, previousMinIncluded;
        for (int i = 2; i < nums.length; i++) {
            int n = nums[i];
            // not use this n
            previousMaxIncluded = maxIncluded;
            previousMaxExcluded = maxExcluded;
            previousMinIncluded = minIncluded;

            maxExcluded = Math.max(previousMaxIncluded, previousMaxExcluded);
            // include
            maxIncluded = Math.max(Math.max(previousMaxIncluded * n, n), previousMinIncluded * n);
            // min
            minIncluded = Math.min(Math.min(previousMinIncluded * n, n), previousMaxIncluded * n);
        }
        return Math.max(maxExcluded, maxIncluded);
    }
    // leecode的题解更直观
    // 算出以每个元素为结尾的最大值 （nums 长度个）放入一个数组
    // 最后再遍历这个数组，选出最大值
}
