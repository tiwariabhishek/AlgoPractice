package algorithms.dynamicprogramming;

import com.sun.tools.javac.util.Assert;

/**
 * @author tiwariabhishek
 * @Date 25/01/2018
 * http://www.geeksforgeeks.org/dynamic-programming-set-13-cutting-a-rod/
 */
public class CuttingRod {

    private int maxValue(int[] p) {
        int[] table = new int[p.length + 1];
        for (int i = 1; i <= p.length; i++) {
            for (int j = i; j <= p.length; j++) {
                table[j] = Math.max(table[j], table[j - i] + p[i - 1]);
            }
        }
        return table[p.length];
    }

    private int maxValue1(int[] p) {
        int[] table = new int[p.length + 1];
        for (int i = 1; i <= p.length; i++) {
            table[i] = p[i - 1];
        }
        for (int i = 1; i <= p.length; i++) {
            for (int j = 1; j < i; j++) {
                table[i] = Math.max(table[i], table[i - j] + p[j - 1]);
            }
        }
        return table[p.length];
    }

    private int recursiveMaxValue(int p[], int len) {
        if (len <= 0) return 0;
        int max_val = 0;
        for (int i = 1; i <= len; i++) {
            max_val = Math.max(max_val, p[i - 1] + recursiveMaxValue(p, len - i));
        }
        return max_val;
    }

    public static void main(String args[]) {
        CuttingRod cr = new CuttingRod();
        int[] price = {3, 5, 8, 9, 10, 20, 22, 25};
        long t1 = System.currentTimeMillis();
        int r1 = cr.recursiveMaxValue(price, 8);
        long t2 = System.currentTimeMillis();
        int r2 = cr.maxValue1(price);
        System.out.println(r1);
        System.out.println(r2);
        assert r1 == r2;
        System.out.println(t2 - t1);
    }
}
