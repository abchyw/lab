package com.lee.dynamic;

import java.util.*;

public class Solution740 {
    public int deleteAndEarn(int[] nums) {
        // [2,2,3,3,3,4]
        int max = 0;
        for (int i : nums) {
            max = Math.max(i, max);
        }
        int[] rooms = new int[max + 1];
        for (int i : nums) {
            rooms[i] = rooms[i] + 1;
        }
        if (rooms.length == 2) {
            return rooms[1];
        }
        if (rooms.length == 3) {
            return Math.max(rooms[1], rooms[2] * 2);
        }

        int twoStepsBefore, oneStepBefore = rooms[1], curr = Math.max(rooms[1], rooms[2] * 2);
        // eg: max is 3, length is 4, 1 loop
        for (int i = 3; i < rooms.length; i++) {
            twoStepsBefore = oneStepBefore;
            oneStepBefore = curr;
            curr = Math.max(twoStepsBefore + rooms[i] * i // use this room
                    , oneStepBefore // don't use this
            );
        }
        return curr;
    }
}
