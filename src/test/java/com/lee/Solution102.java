package com.lee;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import org.junit.jupiter.api.Test;

// 102. 二叉树的层序遍历
// 广度优先
public class Solution102 {
    public class TreeNode {
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

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> r = new ArrayList<>();
        if (root == null) {
            return r;
        }
        int i = 0;
        while (true) {
            List<Integer> level = new ArrayList<>();
            find(root, i, level);
            if (level.size() == 0) {
                break;
            }
            r.add(level);
            i++;
        }

        return r;
    }

    void find(TreeNode tree, int level, List<Integer> result) {
        if (level == 0 && tree != null) {
            result.add(tree.val);
            return;
        }
        if (level < 0) {
            return;
        }

        if (tree.left != null) {
            find(tree.left, level - 1, result);
        }
        if (tree.right != null) {
            find(tree.right, level - 1, result);
        }
    }

    List<List<Integer>> iterate(TreeNode root) {
        TreeNode split = new TreeNode(-Integer.MAX_VALUE);
        List<List<Integer>> r = new ArrayList<>();
        if (root == null) {
            return r;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        queue.add(split);
        List<Integer> elementsOfLevel = new ArrayList<>();
        while (!queue.isEmpty()) {
            TreeNode treeNode = queue.poll();
            if (treeNode == split) {
                r.add(elementsOfLevel);
                elementsOfLevel = new ArrayList<>();
                if (!queue.isEmpty()) {
                    queue.add(split);
                }
                continue;
            }
            elementsOfLevel.add(treeNode.val);
            if (treeNode.left != null) {
                queue.add(treeNode.left);
            }
            if (treeNode.right != null) {
                queue.add(treeNode.right);
            }
        }
        return r;
    }

    @Test
    void test() {
//        TreeNode tree = new TreeNode(
//                3,
//                new TreeNode(9), new TreeNode(20, new TreeNode(15), new TreeNode(7))
//        );
        TreeNode tree = new TreeNode();
//        List<List<Integer>> r = levelOrder(tree);
        List<List<Integer>> r = iterate(tree);
        System.out.println(r);
    }

}
