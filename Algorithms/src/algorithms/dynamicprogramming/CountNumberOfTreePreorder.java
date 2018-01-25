package algorithms.dynamicprogramming;

/**
 * @author tiwariabhishek
 * @Date 25/01/2018
 * Given a preorder sequence how many unique trees can be created
 * Solution is catalan number. Number of tree is exactly same
 * as number of unique BST create with array of size n
 * <p>
 * The way it works for preorder sequence is as follows
 * <p>
 * Suppose our preorder sequence is 1 2 3 4
 * So we need to compute following things
 * count(3)* 2 (combination of 2,3 and 4 on both side of 1)
 * count(1)*count(2) (combination of 2 on one side and 3, 4 on other side)
 * count(2)*count(1) (combinatino of 2,3 on one side and 4 on other side)
 * count(3)*2 can be broken into count(3)*count(0) + count(0)*count(3)
 * <p>
 * So our final result is
 * count(0)*count(3) + count(1)*count(2) + count(2)*count(1) + count(3)*count(0)
 * which is a catalan number
 */

public class CountNumberOfTreePreorder {

    private int count(int n) {
        if (n < 0) return n;
        int[] table = new int[n + 1];
        table[0] = table[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                table[i] += table[j] * table[i - j - 1];
            }
        }
        return table[n];
    }

    private int countRec(int n) {
        if (n < 0) return n;
        else if (n == 0 || n == 1) return 1;
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            cnt += countRec(i) * countRec(n - i - 1);
        }
        return cnt;
    }

    public static void main(String args[]) {
        CountNumberOfTreePreorder cn = new CountNumberOfTreePreorder();
        System.out.println(cn.count(3));
        System.out.println(cn.count(4));
        System.out.println(cn.count(5));
        System.out.println(cn.countRec(3));
        System.out.println(cn.countRec(4));
        System.out.println(cn.countRec(5));
    }
}
