package Algorithms.src.algorithms.arrays;

import com.sun.tools.javac.util.Assert;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Date 19/11/2018
 *
 * @author tiwariabhishek
 * <p>
 * Given an array A of integers, find the maximum of j - i subjected to the constraint of A[i] <= A[j].
 * If there is no solution possible, return -1.
 * <p>
 * Use DP and build bottom-up solution.
 * <p>
 * Space complexity - O(n)
 * Time complexity - O(n)
 * <p>
 * References
 * https://www.interviewbit.com/problems/max-distance/
 */

public class MaxDistance {

    // Space complexity - O(n)
    // Time complexity - O(nlogn)
    private int findMaxDist(int[] inp) {
        // Store the inp val and curr index
        int n = inp.length;
        int[][] ary = new int[n][2];
        for(int i=0;i<n;i++) {
            ary[i][0] = inp[i];
            ary[i][1] = i;
        }

        Arrays.sort(ary, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0] == o2[0]) return o1[1] - o2[1];
                return o1[0] - o2[0];
            }
        });

        // Store maximum index j for the i in ary[0] such that j-i is maximum
        int[] maxi = new int[n];
        maxi[n-1] = ary[n-1][1];
        for(int i=n-2;i>=0;i--){
            maxi[i] = Math.max(maxi[i+1], ary[i][1]);
        }

        // Calculate max j-i
        int max = 0;
        for(int i=0;i<n;i++) {
            max = Math.max(max, maxi[i] - ary[i][1]);
        }
        return max == 0 ? -1: max;
    }

    // Space complexity - O(n)
    // Time complexity - O(n)
    private int findMaxDistSoln2(int[] inp) {
        int n = inp.length;
        int[] lmin = new int[n];
        int[] rmax = new int[n];

        lmin[0] = inp[0];
        for(int i=1;i<n;i++) {
            lmin[i] = Math.min(lmin[i-1], inp[i]);
        }

        rmax[n-1] = inp[n-1];
        for(int i = n-2;i>=0;i--) {
            rmax[i] = Math.max(rmax[i+1], inp[i]);
        }

        int max, i, j;
        max = i = j = 0;
        while(i < n && j < n) {
            if(rmax[j] >= lmin[i]) {
                max = Math.max(j-i, max);
                j++;
            } else i++;
        }
        return max == 0 ? -1: max;
    }

    public static void main(String args[]) {
        int[] inp = {3,5,4,2};
        MaxDistance maxDistance = new MaxDistance();
        System.out.println(maxDistance.findMaxDist(inp));
        System.out.println(maxDistance.findMaxDistSoln2(inp));
        Assert.check(maxDistance.findMaxDist(inp) == maxDistance.findMaxDistSoln2(inp));
    }
}
