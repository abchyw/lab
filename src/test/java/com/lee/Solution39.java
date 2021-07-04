package com.lee;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

// 39. 组合总和
public class Solution39 {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> answers = new ArrayList<>();
//        calculate(candidates, target, new ArrayList<>(), answers);
        calculate2(candidates, target, new ArrayList<>(), answers, 0);
        return answers;
    }

    void calculate(int[] candidates, int target, List<Integer> curr, List<List<Integer>> answers) {
        for (int c : candidates) {
            if (curr.size() > 0) {
                if (c < curr.get(curr.size() - 1)) {
                    continue;
                }
            }
            if (target - c == 0) {
                // right answer
                List<Integer> newCurr = new ArrayList<>(curr);
                newCurr.add(c);
                answers.add(newCurr);
//                System.out.println(newCurr);

            } else if (target - c >= 0) {
                List<Integer> newCurr = new ArrayList<>(curr);
                newCurr.add(c);
                calculate(candidates, target - c, newCurr, answers);
            } else {
                // no answer
            }
        }
    }

    void calculate2(int[] candidates, int target, List<Integer> curr, List<List<Integer>> answers, int index) {
        if (index == candidates.length) {
            return;
        }

        // do not use this number in this index
        calculate2(candidates, target, curr, answers, index + 1);

        // use this number
        int number = candidates[index];
        if (target - number > 0) {
            List<Integer> newCurr = new ArrayList<>(curr);
            newCurr.add(number);
            calculate2(candidates, target - number, newCurr, answers, index);
        } else if (target - number == 0) {
            List<Integer> newCurr = new ArrayList<>(curr);
            newCurr.add(number);
            answers.add(newCurr);
        }

    }

    @Test
    void test() {
        List<List<Integer>> lists = combinationSum(new int[]{2, 3, 6, 7}, 7);
        System.out.println(lists);
//        combinationSum(new int[]{2, 3, 5}, 8);
    }
}
