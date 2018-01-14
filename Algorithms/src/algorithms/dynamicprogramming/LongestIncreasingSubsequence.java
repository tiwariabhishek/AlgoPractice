package algorithms.dynamicprogramming;

/**
 * Date 14/01/2018
 * @author tiwariabhishek
 *
 *
 * Find a subsequence in given array in which the subsequence's elements are
 * in sorted order, lowest to highest, and in which the subsequence is as long as possible
 *
 * Solution :
 * Dynamic Programming is used to solve this question. DP equation is
 * if(arr[i] > arr[j]) { T[i] = max(T[i], T[j] + 1 }
 *
 * Time complexity is O(n^2).
 * Space complexity is O(n)
 *
 * Reference
 * http://en.wikipedia.org/wiki/Longest_increasing_subsequence
 * http://www.geeksforgeeks.org/dynamic-programming-set-3-longest-increasing-subsequence/
 */

public class LongestIncreasingSubsequence {

    private int findLongestIncSubsequence(int input[]) {
        int[] table = new int[input.length];
        int[] lis = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            lis[i] = i;
            table[i] = 1;
        }
        for (int i = 1; i < input.length; i++) {
            for (int j = 0; j < i; j++) {
                if (input[j] < input[i] && table[j] + 1 > table[i]) {
                    table[i] = table[j] + 1;
                    lis[i] = j;
                }
            }
        }
        int maxIndex = findMaxIndex(table);
        int tempIndex = maxIndex;
        do {
            System.out.print(input[tempIndex] + " ");
            tempIndex = lis[tempIndex];
        } while (tempIndex != lis[tempIndex]);
        System.out.println(input[tempIndex]);
        return table[maxIndex];
    }

    private int findMaxIndex(int[] arr) {
        int maxIndex = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[maxIndex] < arr[i]) maxIndex = i;
        }
        return maxIndex;
    }

    public static void main(String args[]) {
        LongestIncreasingSubsequence lis = new LongestIncreasingSubsequence();
        int arr[] = {23, 10, 22, 5, 33, 8, 9, 21, 50, 41, 60, 80, 99, 22, 23, 24, 25, 26, 27};
        int result = lis.findLongestIncSubsequence(arr);
        System.out.println(result);
    }
}
