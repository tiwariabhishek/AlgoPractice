package algorithms.dynamicprogramming;

/**
 * Date 10/04/2018
 *
 * @author tiwariabhishek
 *         <p>
 *         Given stockc prices for certain days and at most k transactions how to buy and sell
 *         to maximize profit.
 *         <p>
 *         Time complexity - O(number of transactions * number of days)
 *         Space complexity - O(number of transcations * number of days)
 *         <p>
 *         https://leetcode.com/discuss/15153/a-clean-dp-solution-which-generalizes-to-k-transactions
 *         https://www.geeksforgeeks.org/maximum-profit-by-buying-and-selling-a-share-at-most-k-times/
 */

public class StockBuySellKTransactions {

    public int maxProfitSlowSolution(int[] profits, int k) {
        int[][] table = new int[k + 1][profits.length];
        for (int i = 1; i <= k; i++) {
            for (int j = 1; j < profits.length; j++) {
                int maxSoFar = 0;
                for (int m = 0; m < j; m++) {
                    maxSoFar = Math.max(maxSoFar, profits[j] - profits[m] + table[i - 1][m]);
                }
                table[i][j] = Math.max(table[i][j - 1], maxSoFar);
            }
        }
        return table[k][profits.length - 1];
    }

    public static void main(String args[]) {
        StockBuySellKTransactions sbt = new StockBuySellKTransactions();
        int prices[] = {2, 5, 7, 1, 4, 3, 1, 3};

        System.out.println("Max profit slow solution " + sbt.maxProfitSlowSolution(prices, 3));
    }
}
