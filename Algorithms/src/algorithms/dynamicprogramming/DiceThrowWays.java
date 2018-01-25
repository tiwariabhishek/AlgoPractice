package algorithms.dynamicprogramming;

/**
 * @author tiwariabhishek
 * @Date 25/01/2018
 * http://www.geeksforgeeks.org/dice-throw-problem/
 * This solution assumes that 1,2,1 is different from 2,1,1 which is different from 1,1 2
 * so total 3 ways are possible
 */

public class DiceThrowWays {

    private int numberOfWays(int n, int f, int k) {
        int[][] table = new int[n + 1][k + 1];
        table[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i * f && j <= k; j++) {
                if (i == j) table[i][j] = 1;
                else if (j > i) {
                    for (int m = 1; m <= f; m++) {
                        if (j >= m) table[i][j] += table[i - 1][j - m];
                    }
                }
            }
        }
        return table[n][k];
    }

    public static void main(String args[]) {
        DiceThrowWays dtw = new DiceThrowWays();
        System.out.println(dtw.numberOfWays(3, 3, 6));
    }
}
