package algorithms.dynamicprogramming;


/**
 * Date: 12/01/2018
 *
 * @author tiwariabhishek
 * <p>
 * TimeComplexity: O(len(str1)*len(str2))
 * <p>
 * http://www.geeksforgeeks.org/dynamic-programming-set-4-longest-common-subsequence/
 */

public class LongestCommonSubsequence {

    private int lcsDynamic(char[] str1, char[] str2) {
        int[][] table = new int[str1.length + 1][str2.length + 1];
        for (int i = 1; i <= str1.length; i++) {
            for (int j = 1; j <= str2.length; j++) {
                if (str1[i - 1] == str2[j - 1]) {
                    table[i][j] = table[i - 1][j - 1] + 1;
                } else {
                    table[i][j] = Math.max(table[i][j - 1], table[i - 1][j]);
                }
            }
        }
        return table[str1.length][str2.length];
    }

    public static void main(String[] args) {
        LongestCommonSubsequence lcs = new LongestCommonSubsequence();
        String str1 = "ABCDGHLQR";
        String str2 = "AEDPHR";

        int result = lcs.lcsDynamic(str1.toCharArray(), str2.toCharArray());
        System.out.print(result);
    }
}
