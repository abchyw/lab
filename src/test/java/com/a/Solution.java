package com.a;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class Solution {
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> r = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (i < nums.length - 1) {
                if (num == nums[i + 1]) {
                    r.add(num);
                    i++;
                }
            }

        }
        return r;
    }

    @Test
    void test() {
        List<Integer> duplicates = findDuplicates(new int[]{4, 3, 2, 7, 8, 2, 3, 1});
        System.out.println(duplicates);
    }


    public int numDupDigitsAtMostN(int n) {
        if (n < 11) {
            return 0;
        }
        int result = 0;
        for (int i = 11; i <= n; i++) {

            Set<Integer> numbers = new HashSet<>();

            int tmp = i;
            while (tmp > 0) {
                int left = tmp % 10;
                if (numbers.contains(left)) {
                    result++;
                    break;
                } else {
                    numbers.add(left);
                }
                tmp = tmp / 10;
            }
        }
        return result;
    }

    @Test
    void test2() {
        int r = numDupDigitsAtMostN(1000);
        System.out.println(r);
    }

    void num3() {

        // update
        //
        // right join select (id , student) i_s
        /**
         * update student s1
         *   inner join student s2
         *   on case
         *        when s1.id % 2 != 0 then s1.id  = s2.id -1
         *        else s1.id = s2.id + 1
         *     end
         * set
         *   s1.name = s2.name;
         */
    }
}
