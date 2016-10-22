package paradigms.dp;

/**
 * Created by Leonti on 2016-09-18.
 */
public class RSQ2D {
    // TODO: REMEMBER EXTREMAL CASES
    // UVa108
    private long maxRangeSumQuery(int[][] a) {
        int[][] dp = new int[a.length][a[0].length];
        long res = Long.MIN_VALUE;
        long currentSum;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                if (i > 0) dp[i][j] += dp[i - 1][j];
                else dp[i][j] = a[i][j];
                if (j > 0) dp[i][j] += dp[i][j - 1];
                if (i > 0 && j > 0) dp[i][j] -= dp[i - 1][j - 1];
            }
        }

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                for (int k = i; k < a.length; k++) {
                    for (int l = j; l < a[0].length; l++) {
                        currentSum = dp[k][l];
                        if (i > 0) currentSum -= dp[i - 1][l];
                        if (j > 0) currentSum -= dp[k][j - 1];
                        if (i > 0 && j > 0) currentSum += dp[i - 1][j - 1];
                        res = Math.max(res, currentSum);
                    }
                }
            }
        }

        return res;
    }
}
