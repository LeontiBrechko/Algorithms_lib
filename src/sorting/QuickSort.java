package sorting;

/**
 * Created by Leonti on 2016-02-22.
 */
public class QuickSort {
    private QuickSort() {}

    public static void sort(int[] array) {
        //shuffle
        sort(array, 0, array.length - 1);
    }

    private static void sort(int[] array, int low, int high) {
        if  (high <= low) return;
        int j = partition(array, low, high);
        sort(array, low, j - 1);
        sort(array, j + 1, high);
    }

    private static int partition(int[] array, int low, int high) {
        int i = low;
        int j = high + 1;
        while (true) {
            while (array[++i] <= array[low]) {
                if (i == high) break;
            }
            while (array[low] <= array[--j]) {
                if (j == low) break;
            }
            if (i >= j) break;
            swap(array, i, j);
        }

        swap(array, low, j);
        return j;
    }

    private static void swap(int[] A, int i, int j) {
        A[i] ^= A[j];
        A[j] ^= A[i];
        A[i] ^= A[j];
    }

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

//    public static void quickSort(int[] A, int p, int r) {
//        if (p < r) {
//            int q = partition(A, p, r);
//            quickSort(A, p, q - 1);
//            quickSort(A, q + 1, r);
//        }
//    }
}
