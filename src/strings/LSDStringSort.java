package strings;

import java.util.Arrays;

/**
 * Created by Leonti on 2016-04-07.
 */
public class LSDStringSort {
    private LSDStringSort() {}

    public static String[] sort(String[] a, int stringLength) {
        int R = 256;
        int N = a.length;
        int[] count;
        String[] res = new String[N];
        for (int i = stringLength - 1; i >= 0; i--) {
            count = new int[R];
            for (int j = 0; j < N; j++) {
                count[a[j].charAt(i)]++;
            }
            for (int j = 1; j < R; j++) {
                count[j] += count[j - 1];
            }
            for (int j = N - 1; j >= 0; j--) {
                res[count[a[j].charAt(i)] - 1] = a[j];
                count[a[j].charAt(i)]--;
            }
            System.arraycopy(res, 0, a, 0, res.length);
        }

        return a;
    }

    public static void main(String[] args) {
        String[] a = new String[]{"dab", "add", "cab", "fad", "fee", "bad", "dad", "bee", "fed", "bed", "ebb", "ace"};
        a = LSDStringSort.sort(a, 3);
        for (String s : a) {
            System.out.println(s);
        }
    }
}
