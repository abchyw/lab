package com.a;

import java.util.function.Supplier;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class Linked {

    private static Stream<Node> lists() {
        Supplier<Node> f = () -> {
            Node head = null;
            Node curr = null;
            for (int i = 0; i < 5; i++) {
                Node newNode = new Node(i);
                if (head == null) {
                    head = newNode;
                } else {
                    curr.next = newNode;
                }
                curr = newNode;
            }
            // make it a circle;
            curr.next = head.next;
            return head;
        };
        Supplier<Node> f2 = () -> {
            Node head = null;
            Node curr = null;
            for (int i = 0; i < 6; i++) {
                Node newNode = new Node(i);
                if (head == null) {
                    head = newNode;
                } else {
                    curr.next = newNode;
                }
                curr = newNode;
            }
            return head;
        };

        return Stream.of(
                f.get(),
                f2.get()
        );
    }

    @Test
    void reversible() {
        // 回文

    }

    @Test
    void reverse() {
        Node head = null;
        Node curr = null;
        for (int i = 0; i < 5; i++) {
            Node newNode = new Node(i);
            if (head == null) {
                head = newNode;
            } else {
                curr.next = newNode;
            }
            curr = newNode;
        }
        head.print();

        // reverse
        Node newHead = reverse(head);
        newHead.print();
    }

    @ParameterizedTest
    @MethodSource("lists")
    void checkCircle(Node node) {

        System.out.println("circle: " + node.isCircle());
    }

    @Test
    void deleteTheLastN() {
        // remove the last 2;
        Node head = null;
        Node curr = null;
        for (int i = 0; i < 4; i++) {
            Node newNode = new Node(i);
            if (head == null) {
                head = newNode;
            } else {
                curr.next = newNode;
            }
            curr = newNode;
        }
        head.print();

        Node p1 = head.next;
        Node p2 = head.next.next;
        while (p2.next != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        remove(head, p1);

        head.print();
    }

    void remove(Node head, Node toRemove) {
        Node curr = head;
        while (curr != null) {
            if (curr.next == toRemove) {
                curr.next = curr.next.next;
                break;
            }
            curr = curr.next;
        }
    }

    Node reverse(Node node) {
        Node newHead = null;
        Node curr = node;
        while (curr != null) {
            Node next = curr.next;
            // take it down
            // link to the new linked
            curr.next = newHead;
            // make it self the new head
            newHead = curr;
            // go to the next node
            curr = next;
        }
        return newHead;
    }

    static class Node {
        int v;
        Node next;

        Node(int v) {
            this.v = v;
        }

        Node link(Node n) {
            next = n;
            return n;
        }

        boolean isCircle() {
            Node curr = this;
            Node currTwo = this.next;
            while (currTwo != null) {
                if (curr == currTwo) {
                    return true;
                }
                if (currTwo.next == null || currTwo.next.next == null) {
                    return false;
                }
                curr = curr.next;
                currTwo = currTwo.next.next;
            }
            return false;
        }

        void print() {
            if (isCircle()) {
                return;
            }
            System.out.print("a list: ");
            Node curr = this;
            while (curr != null) {
                System.out.print(curr.v);
                System.out.print(" ");
                curr = curr.next;
            }
            System.out.println();
        }
    }
}
