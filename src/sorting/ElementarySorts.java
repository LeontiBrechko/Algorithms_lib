package sorting;

/**
 * Created by Leonti on 2015-11-27.
 */

public class ElementarySorts {
    private ElementarySorts() {}

    public static void insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int key = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
    }

    public static void selectionSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int min = array[i];
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < min) {
                    min = array[j];
                    minIndex = j;
                }
            }
            array[minIndex] = array[i];
            array[i] = min;
         }
    }

    private static void merge(int[] array, int p, int q, int r) {
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
            } else if (L[i] <= R[j]) {
                array[k] = L[i];
                i++;
            } else {
                array[k] = R[j];
                j++;
            }
        }
    }

    public static void mergeSort(int[] array, int p, int r){
        if (p == r - 1) return;
        int q = ((p + r) / 2);
        mergeSort(array, p, q);
        mergeSort(array, q, r);
        merge(array, p, q, r);
    }
}