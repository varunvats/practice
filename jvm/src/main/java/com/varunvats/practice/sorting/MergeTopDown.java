package com.varunvats.practice.sorting;

public class MergeTopDown extends MergeBase {

    /**
     * Sorts an array in-place using the top-down merge-sort algorithm.
     *
     * @param a The array to be sorted.
     */
    public static <T extends Comparable<T>> void sort(T[] a) {
        final int N = a.length;
        aux = new Comparable[N];
        sort(a, 0, N - 1);
    }

    private static <T extends Comparable<T>> void sort(T[] a, int low, int high) {
        if (low >= high)
            return;
        final int mid = (low + high) / 2;
        sort(a, low, mid);
        sort(a, mid + 1, high);
        if (!less(a[mid], a[mid + 1]))
            merge(a, low, mid, high);
    }

}
