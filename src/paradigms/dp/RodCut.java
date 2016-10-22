package paradigms.dp;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Leonti on 2016-05-21.
 */
public class RodCut {
    private static int dp(int[] prices, int n) {
        int[] revenue = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            revenue[i] = Integer.MIN_VALUE;
        }
        return topDownDP(prices, n, revenue);
    }

    private static int topDownDP(int[] prices, int n, int[] revenue) {
        int maxRevenue;
        if (revenue[n] >= 0) {
            return revenue[n];
        }
        if (n == 0) {
            maxRevenue = 0;
        } else {
            maxRevenue = Integer.MIN_VALUE;
            for (int i = 1; i < n + 1; i++) {
                maxRevenue = Math.max(maxRevenue, prices[i - 1] + topDownDP(prices, n - i, revenue));
            }
        }
        revenue[n] = maxRevenue;
        return maxRevenue;
    }

    private static List<int[]> bottomUpDP(int[] prices, int n, int cutCost) {
        int[] revenue = new int[n + 1];
        int[] slices = new int[n + 1];
        int[] numberOfCuts = new int[n + 1];
        int maxRevenue;
        int currentNumberOfCuts = 0;
        for (int i = 1; i < n + 1; i++) {
            maxRevenue = Integer.MIN_VALUE;
            for (int j = 1; j < i + 1; j++) {
                if (maxRevenue < prices[j - 1] + revenue[i - j] - Math.min(i - j, 1) * cutCost) {
                    maxRevenue = prices[j - 1] + revenue[i - j] - Math.min(i - j, 1) * cutCost;
                    currentNumberOfCuts = numberOfCuts[i - j] + Math.min(i - j, 1);
                    slices[i] = j;
                }
            }
            revenue[i] = maxRevenue;
            numberOfCuts[i] = currentNumberOfCuts;
        }
        return Arrays.asList(revenue, slices, numberOfCuts);
    }

    public static void main(String[] args) {
        int[] prices = new int[] {4, 5, 7, 8};
        List<int[]> tuple = bottomUpDP(prices, 4, 3);
        for (int[] array : tuple) {
            for (int i : array) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
}
