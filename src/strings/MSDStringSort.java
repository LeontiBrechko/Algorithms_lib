package strings;

import java.util.Arrays;

/**
 * Created by Leonti on 2016-04-07.
 */
public class MSDStringSort {
    private final static int R = 256;
    private final static int CUT_OFF = 15;

    private MSDStringSort() {}

    public static void sort(String[] array) {
        String[] res = new String[array.length];
        sort(array, res, 0, res.length - 1, 0);
    }

    private static void sort(String[] array, String[] res, int low, int high, int index) {
        if (high <= low + CUT_OFF) {
            insertionSort(array, low, high, index);
            return;
        }
        int[] count = new int[R + 1];
        for (int i = low; i <= high; i++) {
            count[charAt(array[i], index) + 1]++;
        }
        for (int i = 1; i < R + 1; i++) {
            count[i] += count[i - 1];
        }
        for (int i = low; i <= high; i++) {
            res[--count[charAt(array[i], index) + 1]] = array[i];
        }
        for (int i = low; i <= high; i++) {
            array[i] = res[i - low];
        }

        for (int i = 0; i < R; i++) {
            sort(array, res, low + count[i], low + count[i + 1] - 1, index + 1);
        }
    }

    private static void insertionSort(String[] array, int low, int high, int index) {
        for (int i = low; i <= high; i++) {
            for (int j = i; j > low && less(array[j], array[j - 1], index); j--) {
                swap(array, j, j - 1);
            }
        }
    }

    private static void swap(String[] array, int i, int j) {
        String temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private static boolean less(String s1, String s2, int index) {
        for (int i = index; i < Math.min(s1.length(), s2.length()); i++) {
            if (s1.charAt(i) < s2.charAt(i)) return true;
            if (s1.charAt(i) > s2.charAt(i)) return false;
        }
        return s1.length() < s2.length();
    }

    private static int charAt(String s, int index) {
        if (index < s.length()) return s.charAt(index);
        else return -1;
    }

    public static void main(String[] args) {
        String[] a = new String[]{"dab", "add", "cab", "fad", "fee", "bad", "dad", "bee", "fed", "bed", "ebb", "ace"};
        MSDStringSort.sort(a);
        for (String s : a) {
            System.out.println(s);
        }
    }
}
