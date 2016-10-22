package paradigms.dp;

/**
 * Created by Leonti on 2016-09-18.
 */
public class RSQ1D {
    // TODO: REMEMBER EXTREMAL CASES
    private long maxRangeSumQuery(int[] a) {
        long currentSum = 0;
        long res = 0;
        for (int i = 0; i < a.length; i++) {
            currentSum += a[i];
            res = Math.max(res, currentSum);
            if (currentSum < 0) currentSum = 0;
        }
        return res;
    }
}
