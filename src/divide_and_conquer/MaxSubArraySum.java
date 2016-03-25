package divide_and_conquer;

/**
 * Created by Leonti on 2016-02-16.
 */


public class MaxSubArraySum {
    private MaxSubArraySum() {}

    //Max sum of SubArray
    public static int[] findMaxCrossingSubArray(int[] A, int low, int mid, int high) {
        int leftSum = Integer.MIN_VALUE;
        int sum = 0;
        int maxLeft = mid;
        for (int i = mid; i >= low ; i--) {
            sum += A[i];
            if (sum > leftSum) {
                leftSum = sum;
                maxLeft = i;
            }
        }

        int rightSum = Integer.MIN_VALUE;
        sum = 0;
        int maxRight = mid + 1;
        for (int i = mid + 1; i <= high; i++) {
            sum += A[i];
            if (sum > rightSum) {
                rightSum = sum;
                maxRight = i;
            }
        }

        return new int[] {maxLeft, maxRight, leftSum + rightSum};
    }

    public static int[] findMaxSubArray(int[] A, int low, int high) {
        if (high == low)
            return new int[] {low, high, A[low]};
        else {
            int mid = (low + high) / 2;
            int[] left = findMaxSubArray(A, low, mid);
            int[] right = findMaxSubArray(A, mid + 1, high);
            int[] cross = findMaxCrossingSubArray(A, low, mid, high);

            if (left[2] >= right[2] && left[2] >= cross[2])
                return left;
            else if (right[2] >= left[2] && right[2] >= cross[2])
                return right;
            else return cross;
        }
    }

    public static int[] findMaxSubArrayBruteForce(int[] A) {
        int sum = 0;
        int maxSum = Integer.MIN_VALUE;
        int maxLow = -1;
        int maxHigh = -1;
        for (int i = 0; i < A.length; i++) {
            sum = 0;
            for (int j = i; j < A.length; j++) {
                sum += A[j];
                if (maxSum < sum) {
                    maxSum = sum;
                    maxLow = i;
                    maxHigh = j;
                }
            }
        }
        return new int[] {maxLow, maxHigh, maxSum};
    }

    public static int findMaxSubArrayLinear(int[] A, int low, int high) {
        int maxSumNow = A[low];
        int maxSum = A[low];
        for (int i = low + 1; i <= high; i++) {
            maxSumNow = Math.max(A[i], A[i] + maxSumNow);
            maxSum = Math.max(maxSum, maxSumNow);
        }
        return maxSum;
    }

    public static void main(String[] args) {
        int[] array = new int[] {-4, 23, -1, 3 };
//        int[] sub = findMaxSubArrayLinear(array, 0, array.length - 1);
//        for (int i : sub) {
//            System.out.println(i);
//        }
        System.out.println(findMaxSubArrayLinear(array, 0, array.length - 1));
    }
}