package com.lee.dynamic;

public class Solution55 {
    public boolean canJump(int[] nums) {
        int reachable = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > reachable) {
                return false;
            }
            reachable = Math.max(i + nums[i], reachable);
        }
        return true;
    }
//    public boolean canJump(int[] nums) {
//        boolean[] reachableArr = new boolean[nums.length];
//        reachableArr[0] = true;
//        for (int i = 0; i < nums.length; i++) {
//            boolean reachable = reachableArr[i];
//            if (!reachable) {
//                continue;
//            }
//            for (int j = 1; j <= nums[i]; j++) {
//                if (i + j < nums.length) {
//                    reachableArr[i + j] = true;
//                }
//            }
//
//        }
//        return reachableArr[reachableArr.length - 1];
//    }
}
