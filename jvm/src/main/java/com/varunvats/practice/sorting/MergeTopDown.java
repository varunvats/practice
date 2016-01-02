package com.varunvats.practice.sorting;

public class MergeTopDown extends MergeBase {

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
        merge(a, low, mid, high);
    }

}
