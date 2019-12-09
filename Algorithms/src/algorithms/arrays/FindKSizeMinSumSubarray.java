package Algorithms.src.algorithms.arrays;

/**
 * Date 09/12/2019
 *
 * @author tiwariabhishek
 *
 * Given array of integers, find minimum sum subarray of size k.
 *
 * Space complexity is O(1)
 * Time complexity - O(n)
 * References
 * https://www.techiedelight.com/find-minimum-sum-subarray-given-size-k/
 */

public class FindKSizeMinSumSubarray {

    private static void findKSizeMinSumSubarray(int[] a, int k) {
        if(k > a.length) {
            System.out.println("Error! k can't be greater than array length.");
        }

        int sum, minSum, leftIndex = -1;
        sum = 0; minSum = Integer.MAX_VALUE;
        for(int i = 0;i < a.length; i++) {
            sum += a[i];
            if(i+1>=k) {
                if(minSum > sum) {
                    minSum = sum;
                    leftIndex = i+1-k;
                }
                sum -= a[i + 1 - k];
            }
        }
        System.out.println("Minimum sum starting at index: " + leftIndex + " with minSum as: " + minSum);
    }

    public static void main(String[] args) {
        int[] array = {10, 4, 2, 5, 6, 3, 8, 1};
        int k=3;
        findKSizeMinSumSubarray(array, k);
    }
}
