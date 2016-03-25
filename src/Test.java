import sorting.CountingSort;


/**
 * Created by Leonti on 2016-02-07.
 */


public class Test {
    public static void main(String[] args) {
        int[] array = new int[] {2, 5, 3, 0, 2, 3, 0, 3};
        array = CountingSort.sort(array, 0, 5);
        for (int i : array) System.out.print(i + " ");
    }
}