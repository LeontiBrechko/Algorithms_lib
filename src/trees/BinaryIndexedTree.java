package trees;

/**
 * Created by Leonti on 2016-08-06.
 */
public class BinaryIndexedTree {
    private int[] BIT;

    public BinaryIndexedTree(int[] a) {
        BIT = new int[a.length + 1];
        for (int i = 1; i < BIT.length; i++) {
            increaseValue(i, a[i - 1]);
        }
    }

    void increaseValue(int index, int value) {
        for (;index < BIT.length; index += index&-index)
            BIT[index] += value;
    }

    long query(int index) {
        long sum = 0;
        for (;index > 0; index -= index&-index)
            sum += BIT[index];
        return sum;
    }
}
