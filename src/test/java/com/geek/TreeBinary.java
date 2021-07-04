package com.geek;

import java.util.Stack;

import org.junit.jupiter.api.Test;

public class TreeBinary {

    static class TreeNode {
        char val;
        TreeNode left;
        TreeNode right;

        public TreeNode(char val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        public TreeNode(char val) {
            this.val = val;
        }
    }

    void preorderTransversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            System.out.println(node.val);
            if (node.right != null) {
                stack.add(node.right);
            }
            if (node.left != null) {
                stack.add(node.left);
            }
        }
    }

    void inorderTransversal(TreeNode root) {
        // left -> middle -> right
        TreeNode curr = root;
        Stack<TreeNode> stack = new Stack<>();
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.add(curr);
                curr = curr.left;
            }
            if (!stack.isEmpty()) {
                curr = stack.pop();
                System.out.println(curr.val);
                curr = curr.right;
            }
        }

    }

    void postorderTransversal(TreeNode root) {
        // left -> right -> middle
        TreeNode curr = root;
        Stack<TreeNode> stack = new Stack<>();
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.add(curr);
                curr = curr.left;
            }
            if (!stack.isEmpty()) {
                curr = stack.pop();
                if (curr.right == null) {
                    System.out.println(curr.val);
                }
                curr = curr.right;
            }
        }
    }

    @Test
    void test() {
        TreeNode root = new TreeNode(
                'A',
                new TreeNode('B', new TreeNode('D'), new TreeNode('E')),
                new TreeNode('C', new TreeNode('F'), new TreeNode('G'))
        );
        postorderTransversal(root);
    }
}
