package com.lee;

import org.junit.jupiter.api.Test;

// 验证二叉搜索树
// https://leetcode-cn.com/problems/validate-binary-search-tree/solution/yan-zheng-er-cha-sou-suo-shu-by-leetcode-solution/
// 深度优先
public class Solution98 {

    public boolean isValidBST(TreeNode root) {
        return deep(root, null, null);
    }

    boolean deep(TreeNode node, Integer lower, Integer upper) {
        if (isValidNode(node, lower, upper)) {
            if (node != null) {
                // left
                if (!deep(node.left, lower, node.val)) {
                    return false;
                }
                // right
                if (!deep(node.right, node.val, upper)) {
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }

    boolean isValidNode(TreeNode node, Integer lower, Integer upper) {
        if (node == null) {
            return true;
        }
        int val = node.val;
        TreeNode left = node.left;
        TreeNode right = node.right;
        if (left == null && right == null) {
            return true;
        }
        if ((left == null || (left.val < val && (lower == null || left.val > lower)))
                && (right == null || (right.val > val && (upper == null || right.val < upper)))) {
            return true;
        }
        return false;
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    @Test
    void test() {
        TreeNode n = new TreeNode(
                5, new TreeNode(1), new TreeNode(4, new TreeNode(3), new TreeNode(6))
        );

//        TreeNode n = new TreeNode(2, new TreeNode(1), new TreeNode(3));
        System.out.println(isValidBST(n));

    }
}

