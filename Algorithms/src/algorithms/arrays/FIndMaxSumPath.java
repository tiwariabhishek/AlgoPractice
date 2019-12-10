package Algorithms.src.algorithms.arrays;

/**
 * Date 10/12/2019
 *
 * @author tiwariabhishek
 *
 * Given two sorted array of integers, find a maximum sum path involving elements of both arrays whose sum is maximum.
 * We can start with either of the arrays but we can only switch through the common elements.
 *
 * Space complexity is O(1)
 * Time complexity - O(n+m)
 * References
 * https://www.techiedelight.com/find-maximum-sum-path-involving-elements-given-arrays/
 */

public class FIndMaxSumPath {

    private static int getMaxPathSum(int[] x, int[] y) {
        int sum_x, sum_y, maxSum;
        sum_x = sum_y = maxSum = 0;
        int i = 0, j = 0;
        while(i < x.length && j < y.length) {
            while(i+1<x.length&&x[i]==x[i+1]) sum_x += x[i++];
            while(j+1<y.length&&y[j]==y[j+1]) sum_y += y[j++];
            if(x[i] < y[j]) sum_x += x[i++];
            else if(x[i] > y[j]) sum_y += y[j++];
            else {
                maxSum += Math.max(sum_x, sum_y) + x[i];
                sum_x = sum_y = 0;
                i++; j++;
            }
        }
        while (i<x.length) sum_x += x[i++];
        while (j<y.length) sum_y += y[j++];
        maxSum += Math.max(sum_x, sum_y);
        return maxSum;
    }

    public static void main(String args[]) {
        int[] array1 = {3, 6, 7, 8, 10, 12, 15, 18, 100};
        int[] array2 = {1, 2, 3, 5, 7, 9, 10, 11, 15, 16, 18, 25, 80};
        int maxSum = getMaxPathSum(array1, array2);
        System.out.println(maxSum);
    }
}
