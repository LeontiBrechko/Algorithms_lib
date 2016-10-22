package paradigms.dp;

import java.util.Arrays;

/**
 * Created by Leonti on 2016-09-18.
 */
public class CoinChangeWays {
    // TODO: REMEMBER EXTREMAL CASES. Not for n * S >> 1M
    // UVa674
    private long solve(int value, int[] coinValues) {
        long[] dp = new long[value + 1];
        Arrays.fill(dp, -1);
        return ways(0, value, dp, coinValues);
    }

    private long ways(int coinType, int remainingValue, long[] dp, int[] coinValues) {
        if (remainingValue == 0) return dp[remainingValue] = 1;
        if (remainingValue < 0 || coinType == coinValues.length) return 0;
        if (dp[remainingValue] != -1) return dp[remainingValue];
        return dp[remainingValue] = ways(coinType + 1, remainingValue, dp, coinValues) +
                ways(coinType, remainingValue - coinValues[coinType], dp, coinValues);
    }

    public static void main(String[] args) {
        System.out.print(new CoinChangeWays().solve(10, new int[]{1, 5}));
    }
}
