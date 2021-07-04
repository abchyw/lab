package com.lee;

import org.junit.jupiter.api.Test;

// 从中序与后序遍历序列构造二叉树
// https://leetcode-cn.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
// 深度优先
// print tree. imperfect
public class Solution106 {

    // 中序遍历：首先遍历左子树，然后访问根节点，最后遍历右子树（左->根->右）
    // 后序遍历: 首先遍历左子树，然后遍历右子树，最后访问根节点（左->右->根）
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        TreeNode root = resolve(0, postorder.length - 1, 0, postorder.length - 1, inorder, postorder);
        return root;
    }

    TreeNode resolve(int startInorder, int endInorder, int startPost, int endPost, int[] inorder, int[] postorder) {
        int val = postorder[endPost];
        int idx = -1;
        if (startInorder > endInorder || startPost > endPost) {
            return null;
        }
        for (int i = startInorder; i <= endInorder; i++) {
            if (val == inorder[i]) {
                idx = i;
                break;
            }
        }
//        int[] inorder = {9, 3, 15, 20, 7};
//        int[] postorder = {9, 15, 7, 20, 3};
        // left
        TreeNode root = new TreeNode(val);
        if (startPost + (idx - 1 - startInorder) >= 0) {
            root.left = resolve(startInorder, idx - 1, startPost, startPost + (idx - 1 - startInorder), inorder, postorder);
        }

        // right
        if (endPost - 1 >= 0) {
            root.right = resolve(idx + 1, endInorder, startPost + (idx - 1 - startInorder) + 1, endPost - 1, inorder, postorder);
        }
        return root;
    }

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

        @Override
        public String toString() {
            return val +
                    "\n, left=" + left +
                    ", right=" + right;
        }
    }

    @Test
    void test() {
//        int[] inorder = {9, 3, 15, 20, 7};
//        int[] postorder = {9, 15, 7, 20, 3};
        int[] inorder = {1, 2};
        int[] postorder = {1, 2};

        TreeNode root = buildTree(inorder, postorder);
        System.out.println(root.toString());
    }
}
