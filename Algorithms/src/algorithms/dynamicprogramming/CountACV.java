package algorithms.dynamicprogramming;

/**
 * Date 25/01/2018
 *
 * @author tiwariabhishek
 * <p>
 * http://www.geeksforgeeks.org/how-to-print-maximum-number-of-a-using-given-four-keys/
 * Test cases
 * Negative number
 * Number less than 7
 * Number greater than equal to 7
 */

public class CountACV {

    //TopDownApproach
    private int countACVRec(int n) {
        if (n < 7) return n;
        int cnt = 0;
        for (int b = n - 3; b > 0; b--) {
            cnt = Math.max(cnt, countACVRec(b) * (n - b - 1));
        }
        return cnt;
    }

    //BottomUpApproach
    private int countACV(int n) {
        if (n < 7) return n;
        int[] table = new int[n + 1];
        for (int i = 1; i < 7; i++) {
            table[i] = i;
        }
        for (int i = 7; i <= n; i++) {
            for (int b = i - 3; b > 0; b--) {
                table[i] = Math.max(table[i], table[b] * (i - b - 1));
            }
        }
        return table[n];
    }

    public static void main(String args[]) {
        CountACV ca = new CountACV();
        System.out.println(ca.countACVRec(35));
        System.out.println(ca.countACV(35));

    }
}
