package com.lee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

// 15. 三数之和
public class Solution15 {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        Set<List<Integer>> answers = new HashSet<>();
        calculate(nums, 0, new ArrayList<>(), 0, answers);
        return new ArrayList<>(answers);
    }

    void calculate(int[] nums, int currSum, List<Integer> currNums, int index, Set<List<Integer>> answers) {
        if (currNums.size() == 3) {
            if (currSum == 0) {
                answers.add(currNums);
            }
            return;
        }
        if (index > nums.length - 1) {
            return;
        }
        int currNumber = nums[index];
        // use this number
        {
            List<Integer> newNums = new ArrayList<>(currNums);
            newNums.add(currNumber);
            calculate(nums, currSum + currNumber, newNums, index + 1, answers);
        }

        // don't use this number
        calculate(nums, currSum, currNums, index + 1, answers);
    }

    public List<List<Integer>> threeSum2(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> r = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            List<List<Integer>> found = twoSum(nums, nums[i], i + 1, nums.length - 1);
            r.addAll(found);
        }

        return r;
    }

    private List<List<Integer>> twoSum(int[] nums, int num, int startIndex, int endIndex) {
        int ori = startIndex;
        List<List<Integer>> answers = new ArrayList<>();
        while (startIndex < endIndex) {
            if (endIndex < nums.length - 2 && nums[endIndex + 1] == nums[endIndex]) {
                endIndex--;
                continue;
            }
            if (startIndex != ori && nums[startIndex - 1] == nums[startIndex]) {
                startIndex++;
                continue;
            }
            int sum = num + nums[startIndex] + nums[endIndex];
            if (sum > 0) {
                endIndex--;
            } else if (sum < 0) {
                startIndex++;
            } else {
                // fixme not compile
                answers.add(List.of(num, nums[startIndex], nums[endIndex]));
                startIndex++;
            }
        }
        return answers;
    }


    @Test
    void test() {
//        int[] nums = {-1, 0, 1, 2, -1, -4};
        int[] nums = {0, 0, 0};
//        int[] nums = {-2, 0, 1, 1, 2};
//        List<List<Integer>> lists = threeSum(nums);
        List<List<Integer>> lists = threeSum2(nums);
        System.out.println(lists);
    }
}
