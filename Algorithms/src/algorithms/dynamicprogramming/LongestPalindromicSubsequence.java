package algorithms.dynamicprogramming;

/**
 * Date 14/01/2018
 *
 * @author tiwariabhishek
 * <p>
 * Given a string find longest palindromic subsequence in this string.
 * <p>
 * Time complexity - O(n^2)
 * Space complexity - O(n^2)
 * <p>
 * References
 * http://www.geeksforgeeks.org/dynamic-programming-set-12-longest-palindromic-subsequence/
 */

public class LongestPalindromicSubsequence {

    //BottomUp DP Approach
    private int findLongestPalindromicSubsequence(char[] str) {
        int[][] table = new int[str.length][str.length];
        for (int i = 0; i < str.length; i++) {
            table[i][i] = 1;
        }
        for (int len = 2; len <= str.length; len++) {
            for (int i = 0; i <= str.length - len; i++) {
                int j = i + len - 1;
                if (str[i] == str[j]) {
                    table[i][j] = len > 2 ? table[i + 1][j - 1] + 2 : 2;
                } else {
                    table[i][j] = Math.max(table[i + 1][j], table[i][j - 1]);
                }
            }
        }
        return table[0][str.length - 1];
    }

    //TopDownDP Approach
    private int findLPSRecursive(char[] str, int low, int high) {
        if (low > high) return 0;
        else if (low == high) return 1;
        else if (str[low] == str[high]) {
            return 2 + findLPSRecursive(str, low + 1, high - 1);
        }
        return Math.max(findLPSRecursive(str, low + 1, high), findLPSRecursive(str, low, high - 1));
    }

    public static void main(String args[]) {
        LongestPalindromicSubsequence lps = new LongestPalindromicSubsequence();
        String str = "agbdba";
        int r1 = lps.findLPSRecursive(str.toCharArray(), 0, str.length() - 1);
        int r2 = lps.findLongestPalindromicSubsequence(str.toCharArray());
        System.out.print(r1 + " " + r2);
    }
}
