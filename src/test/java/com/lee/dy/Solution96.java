package com.lee.dy;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by heeven on 2021/06/28.
 */
public class Solution96 {
    public int numTrees(int n) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            numbers.add(i);
        }
        int count = 0;
        for (int upper : numbers) {
            List<Integer> nums = new ArrayList<>();
            for (int n1 : numbers) {
                if (n1 != upper) {
                    nums.add(n1);
                }
            }
            int count1 = count(upper, nums);
            count = count + count1;
//            System.out.println("upper: " + upper);
//            System.out.println("this count: " + count1);
        }
        return count;
    }

    // count
    int count(int upper, List<Integer> numbers) {
        if (numbers.size() == 0 || numbers.size() == 1) {
            return 1;
        }
        List<Integer> less = new ArrayList<>();
        List<Integer> greater = new ArrayList<>();
        for (int i = 0; i < numbers.size(); i++) {
            int num = numbers.get(i);
            if (num < upper) {
                less.add(num);
            } else {
                greater.add(num);
            }
        }

        // count left
        int countLeft = 0;
        for (int numInLess : less) {
            List<Integer> nums = new ArrayList<>();
            for (int n : less) {
                if (n != numInLess) {
                    nums.add(n);
                }
            }
            countLeft = countLeft + count(numInLess, nums);
        }

        // count right
        int countRight = 0;
        for (int numberInRight : greater) {
            List<Integer> nums = new ArrayList<>();
            for (int n : greater) {
                if (n != numberInRight) {
                    nums.add(n);
                }
            }
            countRight = countRight + count(numberInRight, nums);
        }

        if (countLeft > 0 || countRight > 0) {
            return (countLeft > 0 ? countLeft : 1) *
                    (countRight > 0 ? countRight : 1);
        }
        return 0;
    }

    @Test
    void test() {
        int r = numTrees(3);
        System.out.print("r: " + r);
    }
}
