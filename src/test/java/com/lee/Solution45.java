package com.lee;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

// https://leetcode-cn.com/problems/jump-game-ii/
public class Solution45 {
    // approach 1: recurse
    public int jump(int[] nums) {
        List<Integer> answers = new ArrayList<>();
        jump(nums, 0, 0, answers);

        return answers.get(0);
    }

    void jump(int[] nums, int index, int step, List<Integer> answers) {
        if (index == nums.length - 1) {
            if (answers.isEmpty()) {
                answers.add(step);
            } else {
                if (answers.get(0) > step) {
                    answers.remove(0);
                    answers.add(step);
                }
            }
        }
        int stepsCanTake = nums[index];
        if (stepsCanTake == 0) {
            // impossible
            return;
        }
        for (int i = 1; i <= stepsCanTake; i++) {
            if (i + index <= nums.length - 1) {
                jump(nums, index + i, step + 1, answers);
            }
        }
    }

    // approach 2
    int dynamic(int[] nums) {
        boolean[][] states = new boolean[nums.length + 1][nums.length];
        states[0][0] = true;
        // i means the count of step
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (states[i - 1][j]) {
                    int stepCanTake = nums[j];
                    if (stepCanTake == 0) {
                        continue;
                    }

                    for (int k = 1; k <= stepCanTake; k++) {
                        if (j + k < nums.length - 1) {
                            states[i][j + k] = true;
                        }
                        if (j + k == nums.length - 1) {
                            return i;
                        }
                    }
                }
            }
        }
        return 0;
    }

    @Test
    void test() {
//        int[] arr = {2, 3, 1, 1, 4};
//        int[] arr = {2, 3, 0, 1, 4};
        int[] arr = {2, 0, 2, 0, 1};
//        int r = jump(arr);
        int r = dynamic(arr);
        System.out.println("r: " + r);
    }
}
