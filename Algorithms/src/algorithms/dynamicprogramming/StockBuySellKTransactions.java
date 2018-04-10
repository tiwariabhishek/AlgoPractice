package algorithms.dynamicprogramming;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Date 10/04/2018
 *
 * @author tiwariabhishek
 *         <p>
 *         Given stockc prices for certain days and at most k transactions how to buy and sell
 *         to maximize profit.
 *         <p>
 *         Time complexity - O(number of transactions * number of days):
 *         TODO: Fast Solution. Will update the method soon.
 *         Space complexity - O(number of transcations * number of days)
 *         <p>
 *         https://leetcode.com/discuss/15153/a-clean-dp-solution-which-generalizes-to-k-transactions
 *         https://www.geeksforgeeks.org/maximum-profit-by-buying-and-selling-a-share-at-most-k-times/
 */

public class StockBuySellKTransactions {

    public int maxProfitSlowSolution(int[] prices, int k) {
        int[][] table = new int[k + 1][prices.length];
        for (int i = 1; i <= k; i++) {
            for (int j = 1; j < prices.length; j++) {
                int maxSoFar = 0;
                for (int m = 0; m < j; m++) {
                    maxSoFar = Math.max(maxSoFar, prices[j] - prices[m] + table[i - 1][m]);
                }
                table[i][j] = Math.max(table[i][j - 1], maxSoFar);
            }
        }
        printActualSolution(table, prices);
        return table[k][prices.length - 1];
    }

    private void printActualSolution(int[][] table, int[] prices) {
        int i = table.length - 1;
        int j = table[0].length - 1;
        Deque<Integer> stack = new LinkedList<>();
        while (true) {
            if (i == 0 || j == 0) break;
            else if (table[i][j] == table[i][j - 1]) j--;
            else {
                stack.addFirst(prices[j]);
                int maxDiff = table[i][j] - prices[j];
                for (int k = j - 1; k >= 0; k--) {
                    if (table[i - 1][k] - prices[k] == maxDiff) {
                        i--;
                        j = k;
                        stack.addFirst(prices[j]);
                        break;
                    }
                }
            }
        }
        while (!stack.isEmpty()) {
            System.out.println("Buy at price: " + stack.pollFirst());
            System.out.println("Sell at price: " + stack.pollFirst());
        }
    }

    public static void main(String args[]) {
        StockBuySellKTransactions sbt = new StockBuySellKTransactions();
        int prices[] = {2, 5, 7, 1, 4, 3, 1, 3};

        System.out.println("Max profit slow solution " + sbt.maxProfitSlowSolution(prices, 3));
    }
}
