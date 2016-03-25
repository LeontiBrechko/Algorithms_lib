package sorting;

/**
 * Created by Leonti on 2016-02-24.
 */
public class CountingSort {
    private CountingSort() {}

    public static int[] sort(int[] A, int low, int high) {
        int[] B = new int[A.length];
        int[] C = new int[high - low + 1];
        for (int i : A) {
            C[i - low]++;
        }
        for (int i = 1; i < C.length; i++) {
            C[i] += C[i - 1];
        }
        for (int i = A.length - 1; i >= 0; i--) {
            B[C[A[i] - low] - 1] = A[i];
            C[A[i] - low]--;
        }
        return B;
    }
}
