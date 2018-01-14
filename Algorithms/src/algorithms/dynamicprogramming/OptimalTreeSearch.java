package algorithms.dynamicprogramming;

/**
 * Date 14/01/2018
 *
 * @author tiwariabhishek
 * <p>
 * http://www.geeksforgeeks.org/dynamic-programming-set-24-optimal-binary-search-tree/
 */
public class OptimalTreeSearch {

    //DP Solution (BottomUp Approach) O(n^3)
    private int minCost(int[] input, int[] freq) {
        int n = input.length;
        int[] prefixSum = new int[n];
        for (int i = 0; i < n; i++) {
            prefixSum[i] = (i > 0 ? prefixSum[i - 1] + freq[i] : freq[i]);
        }
        int[][] table = new int[n][n];
        for (int i = 0; i < n; i++) {
            table[i][i] = freq[i];
        }
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                table[i][j] = Integer.MAX_VALUE;
                for (int k = i; k <= j; k++) {
                    table[i][j] = Math.min(table[i][j], (k > i ? table[i][k - 1] : 0) +
                            (k < j ? table[k + 1][j] : 0) + (i > 0 ? prefixSum[j] - prefixSum[i - 1] : prefixSum[j]));
                }
            }
        }
        return table[0][n - 1];
    }

    // TopDown DP Approach O(2^n)
    // Can be optimized using Memoization
    private int minCostRec(int[] freq, int low, int high, int level) {
        if (low > high) return 0;
        int minVal = Integer.MAX_VALUE;
        for (int i = low; i <= high; i++) {
            minVal = Math.min(minVal, minCostRec(freq, low, i - 1, level + 1) +
                    minCostRec(freq, i + 1, high, level + 1) + freq[i] * level);
        }
        return minVal;
    }

    public static void main(String args[]) {
        int input[] = {10, 12, 20, 35, 46};
        int freq[] = {34, 8, 50, 21, 16};
        OptimalTreeSearch ots = new OptimalTreeSearch();
        System.out.println(ots.minCost(input, freq));
        System.out.println(ots.minCostRec(freq, 0, input.length - 1, 1));
    }
}
