package Algorithms.src.algorithms.arrays;

import java.util.Arrays;

/**
 * Date 09/12/2019
 *
 * @author tiwariabhishek
 *
 * Given circular array of integers, find subarray in it which has the largest sum.
 *
 * Space complexity is O(1)
 * Time complexity - O(n)
 * References
 * https://www.techiedelight.com/maximum-sum-circular-subarray/
 */

public class CircularSumSubarray {

    private static int kadane(int[] a) {
        int sum = 0, max_so_far = 0;
        for(int i=0;i<a.length;i++) {
            sum += a[i];
            sum = Math.max(0, sum);
            max_so_far = Math.max(max_so_far, sum);
        }
        return max_so_far;
    }

    private static int getMaxSum(int[] a) {
        int n = a.length;
        for(int i=0;i<n;i++) a[i] = -a[i];
        int negMaxSum = kadane(a);
        for(int i=0;i<n;i++) a[i] = -a[i];
        return Math.max(kadane(a), Arrays.stream(a).sum() + negMaxSum);
    }

    public static void main(String args[]) {
        int[] array = {2, 1, -5, 4, -3, 1, -3, 4, -1};
        int maxSum = getMaxSum(array);
        System.out.println(maxSum);
    }
}
