package com.varunvats.practice.sorting;

public class MergeBottomUp extends MergeBase {

    private MergeBottomUp() {
        // This class cannot be instantiated.
    }

    /**
     * Sorts an array in-place using the bottom-up merge-sort algorithm.
     *
     * @param a The array to be sorted.
     */
    public static <T extends Comparable<T>> void sort(T[] a) {
        final int N = a.length;
        T[] aux = (T[]) new Comparable[N];
        for (int size = 1; size < N; size = 2 * size) {
            for (int cursor = 0; cursor < N; cursor = cursor + 2 * size) {
                final int low = cursor;
                final int highMaybe = cursor + (2 * size) - 1;
                final int midMaybe = (low + highMaybe) / 2;
                final int high = highMaybe < N ? highMaybe : N - 1;
                final int mid = midMaybe < N ? midMaybe : N - 1;
                if (mid < N - 1 && !less(a[mid], a[mid + 1]))
                    merge(a, low, mid, high, aux);
            }
        }
    }

}
