package com.varunvats.practice.sorting;

public class MergeBase extends SortBase {

    protected static <T extends Comparable<T>> void merge(T[] a, int low, int mid, int high, T[] aux) {
        System.arraycopy(a, low, aux, low, high - low + 1);
        int leftCursor = low;
        int rightCursor = mid + 1;
        for (int i = low; leftCursor <= mid || rightCursor <= high; i++) {
            if (leftCursor > mid)
                a[i] = (T) aux[rightCursor++];
            else if (rightCursor > high)
                a[i] = (T) aux[leftCursor++];
            else if (less(aux[leftCursor], aux[rightCursor]))
                a[i] = (T) aux[leftCursor++];
            else
                a[i] = (T) aux[rightCursor++];
        }
    }
}
