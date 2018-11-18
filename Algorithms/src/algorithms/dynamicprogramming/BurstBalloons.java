package algorithms.dynamicprogramming;

/**
 * Date 14/01/2018
 *
 * @author tiwariabhishek
 * <p>
 * Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented
 * by array nums. You are asked to burst all the balloons. If the you burst balloon i you will
 * get nums[left] * nums[i] * nums[right] coins. Here left and right are adjacent indices of i. After the burst,
 * the left and right then becomes adjacent.
 * Find the maximum coins you can collect by bursting the balloons wisely.
 * <p>
 * Time complexity O(n^3)
 * Space complexity O(n^2)
 * <p>
 * Reference
 * https://leetcode.com/problems/burst-balloons/
 */

public class BurstBalloons {

    //DP Solution
    private int maxProfitBottomUpDp(int[] input) {
        int n = input.length;
        int[][] table = new int[n][n];

        for (int len = 1; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                for (int k = i; k <= j; k++) {
                    int before = 0, after = 0, left = 1, right = 1;
                    if (k > 0) left = input[k - 1];
                    if (k < n - 1) right = input[k + 1];
                    if (i != k) before = table[i][k - 1];
                    if (j != k) after = table[k + 1][j];
                    table[i][j] = Math.max(table[i][j], after + before + left * input[k] * right);
                }
            }
        }
        return table[0][input.length - 1];
    }

    public static void main(String args[]) {
        BurstBalloons bb = new BurstBalloons();
        int input[] = {2, 4, 3, 5};
        System.out.print(bb.maxProfitBottomUpDp(input));
    }
}
