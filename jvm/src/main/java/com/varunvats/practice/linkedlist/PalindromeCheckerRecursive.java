package com.varunvats.practice.linkedlist;

public class PalindromeCheckerRecursive {

    public static <T> boolean isPalindrome(Node<T> head) {
        if (head == null)
            return false;
        final int length = getLength(head);
        PartialPalindromeResult<T> partialPalindromeResult = isPalindrome(head, length);
        return partialPalindromeResult.isPalindrome;
    }

    private static <T> PartialPalindromeResult<T> isPalindrome(Node<T> head, int length) {
        if (length == 0 || length == 1) {
            return new PartialPalindromeResult<T>(true, length == 0 ? head : head.next);
        }
        PartialPalindromeResult partialPalindromeResult = isPalindrome(head.next, length - 2);
        final Node<T> next = partialPalindromeResult.next;
        boolean isPalindrome = partialPalindromeResult.isPalindrome && head.data == next.data;
        return new PartialPalindromeResult<T>(isPalindrome, next.next);
    }

    private static <T> int getLength(Node<T> head) {
        int length = 0;
        while (head != null) {
            head = head.next;
            length++;
        }
        return length;
    }

    private static class PartialPalindromeResult<T> {
        boolean isPalindrome;
        Node<T> next;

        PartialPalindromeResult(boolean isPalindrome, Node<T> next) {
            this.isPalindrome = isPalindrome;
            this.next = next;
        }
    }
}
