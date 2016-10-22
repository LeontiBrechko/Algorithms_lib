package paradigms.dp;

import java.util.Arrays;

/**
 * Created by Leonti on 2016-09-18.
 */

public class SubsetSum {
    // TODO: REMEMBER EXTREMAL CASES. Not for n * S >> 1M
    // UVa10130
    private long subsetSumQuery(int[] values, int[] weights, int knapsackSize) {
        long[][] dp = new long[values.length][knapsackSize + 1];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], - 1);
        }

        return value(0, knapsackSize, values, weights, dp);
    }

    private long value(int index, int remainingWeight, int[] values, int[] weights, long[][] dp) {
        if (remainingWeight == 0 || index == values.length) return 0;
        if (dp[index][remainingWeight] != -1) return dp[index][remainingWeight];
        if (weights[index] > remainingWeight)
            return dp[index][remainingWeight] = value(index + 1, remainingWeight, values, weights, dp);
        return dp[index][remainingWeight] =
                Math.max(value(index + 1, remainingWeight, values, weights, dp),
                        values[index] + value(index + 1, remainingWeight - weights[index], values, weights, dp));
    }
}
