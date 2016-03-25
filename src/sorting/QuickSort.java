package sorting;

/**
 * Created by Leonti on 2016-02-22.
 */
public class QuickSort {
    private QuickSort() {}

    private static void swap(int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

    private static int partition(int[] A, int p, int r) {
        int pivot = A[r];
        int i = p - 1;
        for (int j = p; j <= r - 1 ; j++) {
            if (A[j] <= pivot) {
                i++;
                swap(A, i, j);
            }
        }
        swap(A, i + 1, r);
        return i + 1;
    }

    public static void quickSort(int[] A, int p, int r) {
        if (p < r) {
            int q = partition(A, p, r);
            quickSort(A, p, q - 1);
            quickSort(A, q + 1, r);
        }
    }
}
