package algorithms.dynamicprogramming;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tiwariabhishek
 * @Date 14/01/2018
 * <p>
 * Problem 1
 * Given a total and coins of certain denominations find number of ways total
 * can be formed from coins assuming infinity supply of coins.
 * <p>
 * Problem 2
 * Given a total and coins of certain denomination with infinite supply, what is the minimum number
 * of coins it takes to form this total.
 * <p>
 * References:
 * http://www.geeksforgeeks.org/dynamic-programming-set-7-coin-change/
 */

public class CoinChanging {

    // DP Solution O(coins.size*total)
    private int numberOfSolutions(int total, int[] coins) {
        int[][] table = new int[coins.length + 1][total + 1];
        for (int i = 0; i <= coins.length; i++) table[i][0] = 1;
        for (int i = 1; i <= coins.length; i++) {
            for (int j = 1; j <= total; j++) {
                if (j >= coins[i - 1]) table[i][j] = table[i][j - coins[i - 1]] + table[i - 1][j];
                else table[i][j] = table[i - 1][j];
            }
        }
        return table[coins.length][total];
    }

    // Space efficient DP solution
    private int numberOfSolutionsOnSpace(int total, int[] coins) {
        int[] table = new int[total + 1];
        table[0] = 1;
        for (int i = 0; i < coins.length; i++) {
            for (int j = 1; j <= total; j++) {
                if (j >= coins[i]) table[j] += table[j - coins[i]];
            }
        }
        return table[total];
    }

    /**
     * Top down dynamic programing. Using map to store intermediate results.
     * Returns Integer.MAX_VALUE if total cannot be formed with given coins
     */
    private int minimumCoinTopDown(int total, int[] coins, Map<Integer, Integer> map) {
        if (total == 0) {
            return 0;
        }
        if (map.containsKey(total)) {
            return map.get(total);
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; i++) {
            if (total < coins[i]) continue;
            int val = minimumCoinTopDown(total - coins[i], coins, map);
            if (min > val) min = val;
        }
        min = min == Integer.MAX_VALUE ? min : min + 1;
        map.put(total, min);
        return min;
    }

    /**
     * Bottom up way of solving this problem.
     * Keep input sorted. Otherwise temp[j-arr[i]) + 1 can become Integer.Max_value + 1 which
     * can be very low negative number
     * Returns Integer.MAX_VALUE - 1 if solution is not possible.
     */
    private int minimumCoinBottomUp(int total, int[] coins) {
        int[] table = new int[total + 1];
        int[] pickedCoins = new int[total + 1];
        int INF = Integer.MAX_VALUE - 1;
        for (int i = 0; i <= total; i++) {
            table[i] = INF;
            pickedCoins[i] = -1;
        }
        table[0] = 0;
        for (int i = 0; i < coins.length; i++) {
            for (int j = 1; j <= total; j++) {
                if (j >= coins[i] && table[j] > table[j - coins[i]] + 1) {
                    pickedCoins[j] = i;
                    table[j] = table[j - coins[i]] + 1;
                }
            }
        }
        printActualCoinCombination(coins, total, pickedCoins);
        return table[total];
    }

    private void printActualCoinCombination(int[] coins, int total, int[] pickedCoins) {
        int start = total;
        while (start != 0) {
            if (pickedCoins[start] == -1) {
                System.out.println("No Solution Possible");
                return;
            }
            System.out.print(coins[pickedCoins[start]] + " ");
            start -= coins[pickedCoins[start]];
        }
        System.out.println();
    }

    public static void main(String[] args) {
        CoinChanging cc = new CoinChanging();
        int total = 15;
        int coins[] = {3, 2, 4, 6, 7, 9};
        System.out.println(cc.numberOfSolutions(total, coins));
        System.out.println(cc.numberOfSolutionsOnSpace(total, coins));
        System.out.println(cc.minimumCoinBottomUp(total, coins));
        System.out.println(cc.minimumCoinTopDown(total, coins, new HashMap<>()));
    }
}
