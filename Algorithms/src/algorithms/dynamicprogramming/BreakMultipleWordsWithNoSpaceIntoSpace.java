package algorithms.dynamicprogramming;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Date 16/01/2018
 *
 * @author tiwariabhishek
 * <p>
 * Given a string and a dictionary, split this string into multiple words such that
 * each word belongs in dictionary.
 * <p>
 * e.g peanutbutter -> pea nut butter
 * e.g Iliketoplay -> I like to play
 * <p>
 * Solution
 * DP solution to this problem
 * if( input[i...j] belongs in dictionary) T[i][j] = i
 * else{
 * T[i][j] = k if T[i][k-1] != -1 && T[k][j] != -1
 * <p>
 * Test cases
 * 1) Empty string
 * 2) String where entire string is in dictionary
 * 3) String which cannot be split into words which are in dictionary
 * 4) String which can be split into words which are in dictionary
 */

public class BreakMultipleWordsWithNoSpaceIntoSpace {

    /**
     * BottomUp Approach
     * Dynamic programming version for breaking word problem.
     * It returns null string if string cannot be broken into multipe words
     * such that each word is in dictionary.
     * Gives preference to longer words over splits
     * e.g peanutbutter with dict{pea nut butter peanut} it would result in
     * pea nut butter.
     */
    private String breakWordDP(String str, Set<String> dictionary) {
        if (str == null || str.isEmpty()) return null;
        int strLen = str.length();
        int[][] table = new int[strLen][strLen];
        for (int i = 0; i < strLen; i++) Arrays.fill(table[i], -1);
        for (int len = 1; len <= strLen; len++) {
            for (int i = 0; i <= strLen - len; i++) {
                int j = i + len - 1;
                String subString = str.substring(i, j + 1);
                if (dictionary.contains(subString)) {
                    table[i][j] = i;
                    continue;
                }
                for (int k = i + 1; k <= j; k++) {
                    if (table[i][k - 1] != -1 && table[k][j] != -1) {
                        table[i][j] = k;
                        break;
                    }
                }
            }
        }

        //Solution is not possible for the given input
        if (table[0][strLen - 1] == -1) return null;

        StringBuilder sb = new StringBuilder();
        int i = 0, j = strLen - 1;
        while (i < j) {
            int k = table[i][j];
            if (i == k) {
                sb.append(str.substring(i, j + 1));
                break;
            }
            sb.append(str.substring(i, k) + " ");
            i = k;
        }
        //print(table);
        return sb.toString();
    }

    private void print(int[][] table) {
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[0].length; j++) {
                System.out.print(table[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Set<String> dictionary = new HashSet<String>();
        dictionary.add("I");
        dictionary.add("like");
        dictionary.add("had");
        dictionary.add("play");
        dictionary.add("to");
        dictionary.add("pea");
        dictionary.add("nut");
        dictionary.add("peanut");
        dictionary.add("butter");
        String str1 = "Ihadliketoplay";
        String str2 = "peanutbutter";
        BreakMultipleWordsWithNoSpaceIntoSpace bmw = new BreakMultipleWordsWithNoSpaceIntoSpace();
        String result1 = bmw.breakWordDP(str1, dictionary);
        String result2 = bmw.breakWordDP(str2, dictionary);

        System.out.println(result1);
        System.out.println(result2);
    }
}
