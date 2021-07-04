package com.lee;

import java.util.*;

import org.junit.jupiter.api.Test;

// 电话号码的字母组合
public class Solution17 {
    public List<String> letterCombinations(String digits) {
        Map<Character, List<Character>> map = new HashMap<>();
                // fixme
//                = Map.of(
//                '2', List.of('a', 'b', 'c'),
//                '3', List.of('d', 'e', 'f'),
//                '4', List.of('g', 'h', 'i'),
//                '5', List.of('j', 'k', 'l'),
//                '6', List.of('m', 'n', 'o'),
//                '7', List.of('p', 'q', 'r', 's'),
//                '8', List.of('t', 'u', 'v'),
//                '9', List.of('w', 'x', 'y', 'z')
//        );
        Stack<String> stack = new Stack<>();

        if (digits.length() == 0) {
            return new ArrayList<>();
        }
        List<Character> characters = map.get(digits.charAt(0));
        for (char c : characters) {
            stack.add(String.valueOf(c));
        }
        List<String> r = new ArrayList<>();
        while (!stack.isEmpty()) {
            String curr = stack.pop();
            if (curr.length() < digits.length()) {
                List<Character> nextChars = map.get(digits.charAt(curr.length()));
                for (char c : nextChars) {
                    stack.add(curr + c);
                }
            } else {
                r.add(curr);
            }
        }
        return r;
    }

    @Test
    void test() {
        List<String> strings = letterCombinations("23");
        System.out.println(strings.toString());
    }
}
