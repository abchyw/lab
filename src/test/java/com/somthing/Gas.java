package com.somthing;

import org.junit.jupiter.api.Test;

public class Gas {

    // 2 -> 4 -> 3 + 5 -> 6 -> 4
    // out put 7 -> 0 -> 8
    String calculate(Node first, Node second) {

        Node newNodeHead = null;
        Node newCurrNode = null;

        Node nodeFromF = first;
        Node nodeFromS = second;

        int offset = 0;
        while (true) {
            if (nodeFromF == null && nodeFromS == null) {
                break;
            }
            int f = nodeFromF == null ? 0 : nodeFromF.value;
            int s = nodeFromS == null ? 0 : nodeFromS.value;

            int value = f + s + offset;
            if (value >= 10) {
                offset = 1;
                value = value - 10;
            } else {
                offset = 0;
            }

            Node newNode = new Node(value);
            if (newNodeHead == null) {
                newNodeHead = newNode;
                newCurrNode = newNode;
            } else {
                newCurrNode.next = newNode;
                newCurrNode = newNode;
            }

            nodeFromF = nodeFromF == null ? null : nodeFromF.next;
            nodeFromS = nodeFromS == null ? null : nodeFromS.next;
        }

        StringBuffer result = new StringBuffer();
        Node n = newNodeHead;
        while (n != null) {
            result.append(n.value);

            n = n.next;
        }
        return result.toString();
    }

    @Test
    void test() {
        Node first = new Node(3);
        first.next = new Node(4);
        first.next.next = new Node(2);

        Node second = new Node(4);
        second.next = new Node(6);
        second.next.next = new Node(5);

        String r = calculate(first, second);

        System.out.println("r: " + r);
    }

    @Test
    void test2() {
        Node first = new Node(3);

        Node second = new Node(4);
        second.next = new Node(6);

        String r = calculate(first, second);

        System.out.println("r: " + r);
    }

    static class Node {
        int value;
        Node next;

        Node(int value) {
            this.value = value;
        }
    }
    // student, id, score;
    // select * from student order score decs limit 3;
}
