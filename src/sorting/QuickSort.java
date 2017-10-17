package sorting;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by Leonti on 2016-02-22.
 */
public class QuickSort {
    private QuickSort() {}

    public static void sort(int[] array) {
        //shuffle
        sort(array, 0, array.length - 1);
    }

    // VERSION 1

    private static void sort(int[] array, int l, int r) {
        int[] m;
        while (l < r) {
            m = partition(array, l, r);
            if (m[0] - l < r - m[1]) {
                sort(array, l, m[0] - 1);
                l = m[1] + 1;
            } else {
                sort(array, m[1] + 1, r);
                r = m[0] - 1;
            }
        }
    }

    private static int[] partition(int[] array, int l, int r) {
        int pivot = array[l];
        int j = l;
        for (int i = l + 1; i <= r; i++) {
            if (array[i] <= pivot) {
                j++;
                swap(array, i, j);
            }
        }

        swap(array, j, l);
        int m = j;
        for (int i = l; i < j; i++) {
            if (array[i] == pivot) {
                swap(array, i, --j);
            }
        }

        return new int[] {j, m};
    }

    // VERSION 2

//    private static void sort(int[] array, int low, int high) {
//        if  (high <= low) return;
//        int j = partition(array, low, high);
//        sort(array, low, j - 1);
//        sort(array, j + 1, high);
//    }
//
//    private static int partition(int[] array, int low, int high) {
//        int i = low;
//        int j = high + 1;
//        while (true) {
//            while (array[++i] <= array[low]) {
//                if (i == high) break;
//            }
//            while (array[low] <= array[--j]) {
//                if (j == low) break;
//            }
//            if (i >= j) break;
//            swap(array, i, j);
//        }
//
//        swap(array, low, j);
//        return j;
//    }

    // VERSION 3

//    private static int partition(int[] A, int p, int r) {
//        int pivot = A[r];
//        int i = p - 1;
//        for (int j = p; j <= r - 1 ; j++) {
//            if (A[j] <= pivot) {
//                i++;
//                swap(A, i, j);
//            }
//        }
//        swap(A, i + 1, r);
//        return i + 1;
//    }

    private static void swap(int[] A, int i, int j) {
//        A[i] ^= A[j];
//        A[j] ^= A[i];
//        A[i] ^= A[j];
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

//    public static void quickSort(int[] A, int p, int r) {
//        if (p < r) {
//            int q = partition(A, p, r);
//            quickSort(A, p, q - 1);
//            quickSort(A, q + 1, r);
//        }
//    }

    public static void main(String[] args) {
        int[] arrayToSort1, arrayToSort2;
        Random random = new Random();
        out: for (int i = 0; i < 100; i++) {
            arrayToSort1 = new int[random.nextInt(100) + 1];
            for (int j = 0; j < arrayToSort1.length; j++)
                arrayToSort1[j] = random.nextInt(10000) - 5000;

            arrayToSort2 = arrayToSort1.clone();

            Arrays.sort(arrayToSort1);
            sort(arrayToSort2);

            for (int j = 0; j < arrayToSort1.length; j++) {
                if (arrayToSort1[j] != arrayToSort2[j]) {
                    System.out.println("Failed on test " + (i + 1));
                    continue out;
                }
            }

            System.out.println("Succeeded on test " + (i + 1));
        }
    }
}
