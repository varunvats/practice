package com.varunvats.practice.sorting;

import edu.princeton.cs.algs4.StdRandom;

public class Quick extends SortBase {

    private Quick() {
        // This class cannot be instantiated.
    }

    public static <T extends Comparable<T>> void sort(T[] a) {
        final int N = a.length;
        StdRandom.shuffle(a);
        createSentinel(a);
        sort(a, 0, N - 1);
    }

    private static <T extends Comparable<T>> void sort(T[] a, int low, int high) {
        if (low >= high)
            return;
        final int j = partition(a, low, high);
        sort(a, low, j - 1);
        sort(a, j + 1, high);
    }

    private static <T extends Comparable<T>> int partition(T[] a, int low, int high) {
        final T v = a[low];
        int leftCursor = low;
        int rightCursor = high + 1;
        while (true) {
            do {
                ++leftCursor;
            }
            while (less(a[leftCursor], v));

            do {
                --rightCursor;
            }
            while (less(v, a[rightCursor]));

            if (leftCursor >= rightCursor)
                break;
            exchange(a, leftCursor, rightCursor);
        }
        exchange(a, low, rightCursor);
        return rightCursor;
    }

    private static <T extends Comparable<T>> void createSentinel(T[] a) {
        final int N = a.length;
        if (N > 1) {
            final int maxValueIndex = getMaxValueIndex(a);
            exchange(a, maxValueIndex, N - 1);
        }
    }

    private static <T extends Comparable<T>> int getMaxValueIndex(T[] a) {
        int maxValueIndex = 0;
        for (int i = 1; i < a.length; i++)
            if (less(a[maxValueIndex], a[i]))
                maxValueIndex = i;
        return maxValueIndex;
    }
}
