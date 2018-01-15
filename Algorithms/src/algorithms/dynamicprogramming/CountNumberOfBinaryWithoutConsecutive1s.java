package algorithms.dynamicprogramming;


/**
 * @author tiwariabhishek
 * @Date 15/01/2018
 * <p>
 * http://www.geeksforgeeks.org/count-number-binary-strings-without-consecutive-1s/
 * It is really a straight up fibonacci series with values
 * 1,2,3,5,8,13....
 * Look how we assign a[i] value of a[i-1] + b[i-1] and then b[i] becomes a[i]
 */

public class CountNumberOfBinaryWithoutConsecutive1s {

    private int count(int n) {
        int[] cnt = new int[n + 1];
        cnt[0] = 1;
        cnt[1] = 2;
        for (int i = 2; i <= n; i++) {
            cnt[i] = cnt[i - 1] + cnt[i - 2];
        }
        return cnt[n];
    }

    public static void main(String args[]) {
        CountNumberOfBinaryWithoutConsecutive1s cnb = new CountNumberOfBinaryWithoutConsecutive1s();
        System.out.println(cnb.count(5));
    }
}
