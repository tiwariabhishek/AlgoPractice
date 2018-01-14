package algorithms.dynamicprogramming;

/**
 * Date 14/01/2018
 *
 * @author tiwariabhishek
 * <p>
 * Given two strings how many minimum edits(update, delete or add) is needed to convert one string to another
 * <p>
 * Time complexity is O(m*n)
 * Space complexity is O(m*n)
 * <p>
 * References:
 * http://www.geeksforgeeks.org/dynamic-programming-set-5-edit-distance/
 * https://en.wikipedia.org/wiki/Edit_distance
 */

public class EditDistance {

    private int dynamicEditDistance(char[] str1, char[] str2) {
        int[][] table = new int[str1.length + 1][str2.length + 1];
        for (int i = 0; i <= str1.length; i++) table[i][0] = i;
        for (int i = 0; i <= str2.length; i++) table[0][i] = i;
        for (int i = 1; i <= str1.length; i++) {
            for (int j = 1; j <= str2.length; j++) {
                if (str1[i - 1] == str2[j - 1]) table[i][j] = table[i - 1][j - 1];
                else table[i][j] = Math.min(table[i - 1][j], Math.min(table[i][j - 1], table[i - 1][j - 1])) + 1;
            }
        }
        return table[str1.length][str2.length];
    }

    public static void main(String args[]) {
        String str1 = "azced";
        String str2 = "abcdef";
        EditDistance editDistance = new EditDistance();
        int result = editDistance.dynamicEditDistance(str1.toCharArray(), str2.toCharArray());
        System.out.print(result);
    }
}
