package Algorithms.src.algorithms.arrays;

/**
 * Date 09/12/2019
 *
 * @author tiwariabhishek
 *
 * Find smallest window in array sorting which will make entire array sorted in increasing order.
 *
 * Space complexity is O(1)
 * Time complexity - O(n)
 * References
 * http://www.techiedelight.com/smallest-window-sorting-which-make-array-sorted/
 */

public class FindSmallestWindow {

    private static void printSmallestWindow(int[] a) {
        int right_index, left_index, max_so_far, min_so_far;
        right_index = left_index = max_so_far = -1; min_so_far = Integer.MAX_VALUE;
        for(int i=0;i<a.length;i++) {
            max_so_far = Math.max(a[i], max_so_far);
            if(a[i] < max_so_far) right_index = i;
        }
        for(int i=a.length-1;i>=0;i--) {
            min_so_far = Math.min(min_so_far, a[i]);
            if(a[i] > min_so_far) left_index = i;
        }
        if(left_index == -1) {
            System.out.println("Array is already sorted.");
        }
        System.out.println("Sort array from: " + left_index + " to " + right_index);
    }

    public static void main(String[] args) {
        int[] array = {1, 3, 2, 7, 5, 6, 4, 8};
        printSmallestWindow(array);
    }
}
