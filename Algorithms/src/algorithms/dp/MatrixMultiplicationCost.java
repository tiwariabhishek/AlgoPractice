package algorithms.dp;

/**
 * Date: 12/01/2018
 *
 * @author tiwariabhishek
 * <p>
 * TimeComplexity: O((len(arr))^3)
 * <p>
 * http://www.geeksforgeeks.org/dynamic-programming-set-8-matrix-chain-multiplication/
 */

public class MatrixMultiplicationCost {

    private int findCost(int[] arr) {
        int n = arr.length;
        int[][] table = new int[n][n];
        for (int len = 2; len < n; len++) {
            for (int i = 0; i < n - len; i++) {
                int j = i + len;
                table[i][j] = (int) 1e9;
                for (int k = i; k < j; k++) {
                    table[i][j] = Math.min(table[i][j], table[i][k] + arr[i] * arr[k] * arr[j] + table[k][j]);
                }
            }
        }
        return table[0][n - 1];
    }

    public static void main(String args[]) {
        MatrixMultiplicationCost mmc = new MatrixMultiplicationCost();
        int arr[] = {4, 2, 3, 5, 3};
        System.out.println(mmc.findCost(arr));
    }
}
