package algorithms.dynamicprogramming;

import java.util.HashMap;

/**
 * @author tiwariabhishek
 * @Date 25/01/2018
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:
 * 1 -> A
 * 2 -> B
 * 3 -> C
 * 26-> Z
 * Given an encoded message containing digits, determine the total number of ways to decode it.
 * <p>
 * https://leetcode.com/problems/decode-ways/
 */

public class DecodeWays {

    private int countWays(String s) {
        if (s == null || s.isEmpty()) return 0;
        return countWaysUtil(s, 0, new HashMap<Integer, Integer>());
    }

    //TopDown Approach
    private int countWaysUtil(String s, int start, HashMap<Integer, Integer> map) {
        if (start >= s.length()) return 0;
        if (map.containsKey(start)) return map.get(s);
        String curr = s.substring(start, start + 1);
        if (curr.equals("0")) {
            map.put(start, 0);
            return 0;
        }
        int c1 = countWaysUtil(s, start + 1, map) + 1, c2 = 0;
        if (start < s.length() - 1) {
            if (Integer.valueOf(s.substring(start, start + 2)) <= 26) {
                c2 = countWaysUtil(s, start + 2, map);
            }
        }
        map.put(start, c1 + c2);
        return c1 + c2;
    }

    public static void main(String args[]) {
        DecodeWays dw = new DecodeWays();
        System.out.println(dw.countWays("12"));
    }
}
