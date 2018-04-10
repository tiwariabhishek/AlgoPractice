package algorithms.string;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Date 10/04/2018
 *
 * @author tiwariabhishek
 *         <p>
 *         Z algorithm to pattern matching
 *         <p>
 *         Time complexity - O(n + m)
 *         Space complexity - O(n + m)
 *         <p>
 *         http://www.geeksforgeeks.org/z-algorithm-linear-time-pattern-searching-algorithm/
 *         http://www.utdallas.edu/~besp/demo/John2010/z-algorithm.htm
 */

public class ZAlgorithm {

    private int[] calculateZ(char[] input) {
        int[] z = new int[input.length];
        int left = 0, right = 0;
        for (int k = 1; k < z.length; k++) {
            if (k > right) {
                left = right = k;
                while (right < input.length && input[right] == input[right - left]) right++;
                z[k] = right - left;
                right--;
            } else {
                int k1 = k - left;
                if (z[k1] < right - k + 1) z[k] = z[k1];
                else {
                    left = k;
                    while (right < input.length && input[right] == input[right - left]) right++;
                    z[k] = right - left;
                    right--;
                }
            }
        }
        return z;
    }

    public List<Integer> matchPattern(char[] text, char[] pattern) {
        List<Integer> result = new ArrayList<>();
        char[] input = new char[text.length + pattern.length + 1];
        int index = 0;
        for (int i = 0; i < pattern.length; i++) {
            input[index++] = pattern[i];
        }
        input[index++] = '$';
        for (int i = 0; i < text.length; i++) {
            input[index++] = text[i];
        }
        int[] z = calculateZ(input);
        for (int i = 1; i < z.length; i++) {
            if (z[i] == pattern.length) {
                result.add(i - pattern.length - 1);
            }
        }
        return result;
    }

    public List<Integer> matchPattern(String text, String pattern) {
        if (text == null || pattern == null || text.length() == 0 || pattern.length() == 0) {
            System.out.println("Invalid Input");
            return null;
        }
        return matchPattern(text.toCharArray(), pattern.toCharArray());
    }

    public static void main(String args[]) {
        String text = "aaabcxyzaaaabczaaczabbaaaaaabc";
        String pattern = "aaabc";
        ZAlgorithm zAlgorithm = new ZAlgorithm();
        List<Integer> result = zAlgorithm.matchPattern(text, pattern);
        result.forEach(System.out::println);
    }
}
