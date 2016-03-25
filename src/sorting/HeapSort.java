package sorting;

/**
 * Created by Leonti on 2016-02-18.
 */
public class HeapSort {
    private HeapSort() {}

    private static int heapSize;

    public static void setHeapSize(int heapSize) {
        HeapSort.heapSize = heapSize;
    }

    private static int parent(int i) {
        return (i - 1) >> 1;
    }

    private static int left(int i) {
        return 2 * i + 1;
    }

    private static int right(int i) {
        return 2 * i + 2;
    }

    private static void swap(int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

    private static void maxHeapify(int[] A, int i) {
        int largest;
        int left = left(i);
        int right = right(i);
        if (left <= heapSize && A[left] > A[i])
            largest = left;
        else
            largest = i;
        if (right <= heapSize && A[right] > A[largest])
            largest = right;
        if (largest != i) {
            swap(A, i, largest);
            maxHeapify(A, largest);
        }
    }

    private static void buildMaxHeap(int[] A) {
        setHeapSize(A.length - 1);
        for (int i = heapSize / 2; i >= 0; i--) {
            maxHeapify(A, i);
        }
    }

    public static void heapsort(int[] A) {
        buildMaxHeap(A);
        for (int i = A.length - 1; i >= 0; i--) {
            swap(A, 0, i);
            heapSize -= 1;
            maxHeapify(A, 0);
        }
    }


//    //Priority queues
//    public static int heapMaximum(int[] A) {
//        return A[0];
//    }
//
//    public static int heapExtractMax(int[] A) {
//        setHeapSize(A.length - 1);
//        if (heapSize < 1)
//            throw new RuntimeException("The queue is empty");
//        int max = A[0];
//        A[0] = A[heapSize];
//        heapSize--;
//        maxHeapify(A, 0);
//        return max;
//    }
//
//    public static void heapIncreaseKey(int[] A, int i, int key) {
//        setHeapSize(A.length - 1);
//        if (key < A[i])
//            throw new RuntimeException("The new key is less than current one");
//        A[i] = key;
//        while (i > 0 && A[parent(i)] < A[i]) {
//            swap(A, A[i], A[parent(i)]);
//            i = parent(i);
//        }
//    }
//
//    public static void maxHeapInsert(int[] A, int key) {
//        setHeapSize(A.length - 1);
//
//    }
}
