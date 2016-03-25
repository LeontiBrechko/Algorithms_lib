package divide_and_conquer;

/**
 * Created by Leonti on 2015-12-07.
 */

public class InversionsCount {
    private InversionsCount() {}

    private static int splitCountAndSortMerge(int[] array, int p, int q, int r) {
        int splitCount = 0;
        int n1 = q - p;
        int n2 = r - q;
        int[] L = new int[n1];
        int[] R = new int[n2];
        for (int i = 0; i < n1; i++) {
            L[i] = array[p + i];
        }
        for (int i = 0; i < n2; i++) {
            R[i] = array[q + i];
        }
        for (int k = p, i = 0, j = 0; k < r; k++) {
            if (i == n1) {
                for (;j < n2; j++, k++) {
                    array[k] = R[j];
                }
            } else if (j == n2) {
                for (;i < n1; i++, k++) {
                    array[k] = L[i];
                }
            } else if (L[i] > R[j]) {
                splitCount += n1 - i;
                array[k] = R[j];
                j++;
            } else {
                array[k] = L[i];
                i++;
            }
        }
        return splitCount;
    }

    public static int countInversions(int[] array, int p, int r){
        if (p == r - 1) return 0;
        else {
            int x = 0, y = 0, z = 0;
            int q = ((p + r) / 2);
            x = countInversions(array, p, q);
            y = countInversions(array, q, r);
            z = splitCountAndSortMerge(array, p, q, r);
            return x + y + z;
        }
    }
}