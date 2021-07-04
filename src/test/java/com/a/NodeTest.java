package com.a;

import java.util.List;
import java.util.Stack;

import org.junit.jupiter.api.Test;

public class NodeTest {

    @Test
    void reverse() {
        Node head = new Node(0);
        Node curr = head;
        for (int i = 0; i < 5; i++) {
            curr.next = new Node(i + 1);
            curr = curr.next;
        }

        Stack<Node> stack = new Stack<>();
        stack.add(head);
        Node n = head;
        System.out.println("sss");
        while (n.hasNext()) {
            n = n.next;
            stack.push(n);
        }

        System.out.println("sss");
        while (stack.size() > 0) {
            Node node = stack.pop();
            System.out.println("node: " + node.value);
        }

    }

    @Test
    void reverse2() {
        Node head = new Node(0);
        Node curr = head;
        for (int i = 0; i < 5; i++) {
            curr.next = new Node(i + 1);
            curr = curr.next;
        }

        Node current = head;
        Node reverseHead = null;
        while (current != null) {
            Node next = current.next;

            current.next = reverseHead;
            reverseHead = current;
            current = next;
        }

        Node n = reverseHead;
        while (n != null) {
            System.out.println(n.value);
            n = n.next;
        }
    }

    @Test
    void reverse3() {
        Node head = new Node(0);
        Node curr = head;
        for (int i = 0; i < 5; i++) {
            curr.next = new Node(i + 1);
            curr = curr.next;
        }

        Node newHead = reverse(head);
        System.out.println(newHead.value);
        Node n = newHead;
        while (n.hasNext()) {
            n = n.next;
            System.out.println(n.value);
        }

    }

    // https://blog.csdn.net/c_z_w/article/details/107463906
    Node reverse(Node n) {
        if (n == null || n.next == null) {
            return n;
        }
        Node newNode = reverse(n.next);
        // node的下一个node指向
        n.next.next = n;
        n.next = null;
        return newNode;
    }

    static class Node {
        private int value;
        private Node next;

        Node(int v) {
            this.value = v;
        }

        void link(Node n) {
            next = n;
        }

        boolean hasNext() {
            return next != null;
        }

    }
}
