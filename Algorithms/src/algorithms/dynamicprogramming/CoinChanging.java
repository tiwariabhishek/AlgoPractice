package algorithms.dynamicprogramming;

/**
 * @Date 14/01/2018
 * @author tiwariabhishek
 * <p>
 * Given a total and coins of certain denominations find number of ways total
 * can be formed from coins assuming infinity supply of coins
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

    public static void main(String[] args) {
        CoinChanging cc = new CoinChanging();
        int total = 15;
        int coins[] = {3, 4, 6, 7, 9};
        System.out.println(cc.numberOfSolutions(total, coins));
        System.out.println(cc.numberOfSolutionsOnSpace(total, coins));
        //cc.printCoinChangingSolution(total, coins);
    }
}
