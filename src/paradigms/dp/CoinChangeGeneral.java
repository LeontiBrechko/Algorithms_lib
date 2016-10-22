package paradigms.dp;

import java.util.Arrays;

/**
 * Created by Leonti on 2016-09-18.
 */
public class CoinChangeGeneral {
    // TODO: REMEMBER EXTREMAL CASES. Not for n * S >> 1M
    private int solve(int value, int[] coinValues) {
        int[] dp = new int[value + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        return change(value, coinValues, dp);
    }

    private int change(int remainingValue, int[] coinValues, int[] dp) {
        if (remainingValue == 0) return dp[0] = 0;
        if (remainingValue < 0) return Integer.MAX_VALUE;
        if (dp[remainingValue] != Integer.MAX_VALUE) return dp[remainingValue];
        for (int i = 0; i < coinValues.length; i++)
            dp[remainingValue] = Math.min(dp[remainingValue], change(remainingValue - coinValues[i], coinValues, dp));
        return ++dp[remainingValue];
    }
}
