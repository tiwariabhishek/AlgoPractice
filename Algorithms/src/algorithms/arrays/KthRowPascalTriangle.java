package Algorithms.src.algorithms.arrays;

import java.util.ArrayList;
import java.util.List;

/**
 * Date 16/11/2018
 *
 * @author tiwariabhishek
 * <p>
 * Given an index k, return the kth row of the Pascal’s triangle.
 * <p>
 * 1) Simply calculate kC0 -> kCk
 * <p>
 * Space complexity is O(n)
 * Time complexity - O(n)
 * <p>
 * References
 * https://medium.com/i-math/top-10-secrets-of-pascals-triangle-6012ba9c5e23
 */

public class KthRowPascalTriangle {

    private List<Integer> getKthRowPascalTriangle(int n) {
        List<Integer> kthRowVals = new ArrayList<>();
        kthRowVals.add(1);
        for(int k = 0; k < n; k++) {
            kthRowVals.add((kthRowVals.get(k) * (n-k))/(k+1));
        }
        return kthRowVals;
    }

    public static void main(String args[]) {
        KthRowPascalTriangle kthRowPascalTriangle = new KthRowPascalTriangle();
        System.out.println(kthRowPascalTriangle.getKthRowPascalTriangle(3));
        System.out.println(kthRowPascalTriangle.getKthRowPascalTriangle(4));
        System.out.println(kthRowPascalTriangle.getKthRowPascalTriangle(11));
    }
}