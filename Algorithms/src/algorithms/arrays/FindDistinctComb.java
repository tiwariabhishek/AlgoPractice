package Algorithms.src.algorithms.arrays;

import java.util.Arrays;

/**
 * Date 09/12/2019
 *
 * @author tiwariabhishek
 *
 * Find all distinct combinations of given length.
 *
 * Space complexity is O(k)
 * Time complexity - O(n*k)
 * References
 * http://www.techiedelight.com/find-distinct-combinations-given-length-repetition-allowed/
 */

public class FindDistinctComb {

    private static void printArray(int[] a, boolean revFlag) {
        if(revFlag) {
            for(int i=a.length-1;i>=0;i--) {
                System.out.print(a[i] + " ");
            }
        } else {
            for(int i=0;i<a.length;i++) {
                System.out.print(a[i] + " ");
            }
        }
        System.out.println();
    }

    private static void printDistinctCombRecur(int[] a, int[] output, int stpos, int n, int k) {
        if(k==0) {
            printArray(output, true);
            return;
        }
        for(int i=stpos;i<n;i++) {
            output[k-1] = a[i];
            printDistinctCombRecur(a, output, i+1, n, k-1);
            // handle duplicates
            while(i+1<n && a[i]==a[i+1]) i++;
        }
    }

    private static void printDistinctComb(int[] a, int k) {
        if(k>a.length) {
            System.out.println("Error! k is greater than array length");
            return;
        }
        Arrays.sort(a);
        int[] output = new int[k];
        printDistinctCombRecur(a, output, 0, a.length, k);
    }

    public static void main(String[] args) {
        int[] array = {1,2,3,4,5};
        int k=2;
        printDistinctComb(array, k);
    }
}
