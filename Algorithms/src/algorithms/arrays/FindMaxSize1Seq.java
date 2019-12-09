package Algorithms.src.algorithms.arrays;

/**
 * Date 09/12/2019
 *
 * @author tiwariabhishek
 *
 * Given a boolean array, find maximum sequence of continuous 1's that can be formed by replcing at most k zeros.
 *
 * Space complexity is O(1)
 * Time complexity - O(n)
 * References
 * https://www.techiedelight.com/find-maximum-sequence-of-continuous-1s-can-formed-replacing-k-zeroes-ones/
 */

public class FindMaxSize1Seq {


    private static int findMaxSize1Seq(int[] a, int k) {
        int left, maxWindowSize, zeroCt;
        left = maxWindowSize = zeroCt = 0;
        for(int i=0;i<a.length;i++) {
            if(a[i] == 0) zeroCt++;
            while (zeroCt > k) {
                if(a[left] == 0) zeroCt--;
                left++;
            }
            if(i-left + 1> maxWindowSize) maxWindowSize = i - left + 1;
        }
        return maxWindowSize;
    }

    public static void main(String[] args) {
        int[] array = {1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 0, 0};
        int k=2;
        System.out.println(findMaxSize1Seq(array, k));
    }
}
