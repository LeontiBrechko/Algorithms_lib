package sorting;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by Leonti on 2016-04-07.
 */
public class LSDIntSort {
    private LSDIntSort() {}
    
    public static int[] sort(int[] array) {
        int N = array.length;
        int[] res = new int[N];
        int[] count;
        int bitNumber;
        int index;

        // sort all
        for (int i = 0; i <= 30; i++) {
            bitNumber = 1;
            bitNumber <<= i;
            count = new int[2];
            for (int j = 0; j < N; j++) {
                count[(array[j] & bitNumber) >>> i]++;
            }
            count[1] += count[0];
            for (int j = N - 1; j >= 0; j--) {
                index = (array[j] & bitNumber) >>> i;
                res[count[index] - 1] = array[j];
                count[index]--;
            }
            System.arraycopy(res, 0, array, 0, res.length);
        }

        // sort negative
        bitNumber = 1;
        bitNumber <<= 31;
        count = new int[2];
        for (int j = 0; j < N; j++) {
            count[(array[j] & bitNumber) >>> 31]++;
        }
        count[0] += count[1];
        for (int j = N - 1; j >= 0; j--) {
            index = (array[j] & bitNumber) >>> 31;
            res[count[index] - 1] = array[j];
            count[index]--;
        }
        System.arraycopy(res, 0, array, 0, res.length);

        return array;
    }

    public static void main(String[] args) throws Exception {
        int n = 10;
        int[] array;
        Random random = new Random();
        long startTime, finishTime;

        array = new int[n];
        for (int j = n - 1; j >= 0; j--) {
            array[n - j - 1] = random.nextInt(20);
        }
        sort(array);

        System.out.println(Arrays.toString(array));

//        for (int i = 0; i < 4; i++) {
//            System.out.println("-----------------------------");
//
//            array = new int[n];
//            for (int j = n - 1; j >= 0; j--) {
//                array[n - j - 1] = random.nextInt();
//            }
//            startTime = System.currentTimeMillis();
//            sort(array);
//            finishTime = System.currentTimeMillis();
//            System.out.println("My total time: " + ((double) finishTime - startTime) / 1000);
//
//            array = new int[n];
//            for (int j = n - 1; j >= 0; j--) {
//                array[n - j - 1] = random.nextInt();
//            }
//            startTime = System.currentTimeMillis();
//            Arrays.sort(array);
//            finishTime = System.currentTimeMillis();
//            System.out.println("Java total time: " + ((double) finishTime - startTime) / 1000);
//        }
    }
}
