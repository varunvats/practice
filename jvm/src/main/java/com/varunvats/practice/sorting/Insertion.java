package com.varunvats.practice.sorting;

public class Insertion extends SortBase {

    private Insertion() {
        // This class cannot be instantiated.
    }

    /**
     * Sorts an array in-place using the insertion-sort algorithm.
     *
     * @param a The array to be sorted.
     */
    public static <T extends Comparable<T>> void sort(T[] a) {
        for (int i = 1; i < a.length; i++) {
            int j = i;
            while (j > 0 && less(a[i], a[j - 1])) {
                j--;
            }
            shiftAndInsert(a, i, j);
        }
    }

    private static <T extends Comparable<T>> void shiftAndInsert(T[] a, int deleteIndex, int insertAtIndex) {
        T savedValue = a[deleteIndex];
        System.arraycopy(a, insertAtIndex, a, insertAtIndex + 1, deleteIndex - insertAtIndex);
        a[insertAtIndex] = savedValue;
    }
}
