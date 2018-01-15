package algorithms.dynamicprogramming;

import java.util.Arrays;

/**
 * @author tiwariabhishek
 * @Date 15/01/2018
 * <p>
 * http://www.geeksforgeeks.org/dynamic-programming-set-15-longest-bitonic-subsequence/
 */
public class LongestBitonicSubsequence {

    private int longestBitonicSubsequence(int arr[]) {
        int[] lbs = new int[arr.length];
        int[] lis = getLIS(arr);
        int[] lds = getLDS(arr);

        int maxLength = 0;
        for (int i = 0; i < arr.length; i++) {
            if (lis[i] + lds[i] - 1 > maxLength) maxLength = lis[i] + lds[i] - 1;
        }
        return maxLength;
    }

    private int[] getLIS(int arr[]) {
        int[] lis = new int[arr.length];
        Arrays.fill(lis, 1);
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j] && lis[i] < lis[j] + 1) {
                    lis[i] = lis[j] + 1;
                }
            }
        }
        //print(lis);
        return lis;
    }

    private int[] getLDS(int arr[]) {
        int[] lds = new int[arr.length];
        Arrays.fill(lds, 1);
        for (int i = arr.length - 2; i >= 0; i--) {
            for (int j = arr.length - 1; j > i; j--) {
                if (arr[i] > arr[j] && lds[i] < lds[j] + 1) {
                    lds[i] = lds[j] + 1;
                }
            }
        }
        //print(lds);
        return lds;
    }

    private void print(int arr[]) {
        for (int i = 0; i < arr.length; i++) System.out.print(arr[i] + " ");
        System.out.println();
    }

    public static void main(String args[]) {
        LongestBitonicSubsequence lbs = new LongestBitonicSubsequence();
        int[] arr = {1, 4, 3, 7, 2, 1, 8, 11, 13, 0};
        int r = lbs.longestBitonicSubsequence(arr);
        System.out.print(r);
    }
}
