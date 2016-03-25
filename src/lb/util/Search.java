package lb.util;

/**
 * Created by Leonti on 2015-11-30.
 */


public class Search {
    private Search() {}

    public static int upperBinarySearch (int[] array, int start, int finish, int key) {
        int low = start;
        int high = finish - 1;
        while (low < high) {
            int mid = (low + high + 1) / 2;
            if (array[mid] <= key) low = mid;
            else high = mid - 1;
        }
        if (array[low] > key) low--;
        return low;
    }
}