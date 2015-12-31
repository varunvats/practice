package com.varunvats.practice.sorting;

public class Selection extends SortBase {

    /**
     * Sorts an array in-place.
     *
     * @param a The array to sort.
     */
    public static <T extends Comparable<T>> void sort(T[] a) {
        for (int i = 0; i < a.length; i++) {
            int minValueIndex = i;
            for (int j = i + 1; j < a.length; j++) {
                if (less(a[j], a[minValueIndex]))
                    minValueIndex = j;
            }
            exchange(a, i, minValueIndex);
        }
    }
}
