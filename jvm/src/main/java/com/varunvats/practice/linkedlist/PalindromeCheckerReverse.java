package com.varunvats.practice.linkedlist;

public class PalindromeCheckerReverse {

    public static <T> boolean isPalindrome(Node<T> head) {
        if (head == null)
            return false;
        Node<T> tail = reverse(head);
        while (tail != null) {
            if (head.data != tail.data)
                return false;
            head = head.next;
            tail = tail.next;
        }
        return true;
    }

    private static <T> Node<T> reverse(Node<T> head) {
        Node<T> tail = new Node<T>(head.data);
        head = head.next;
        while (head != null) {
            Node<T> newTail = new Node(head.data, tail);
            tail = newTail;
            head = head.next;
        }
        return tail;
    }

}
