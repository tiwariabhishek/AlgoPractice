package algorithms.dp;

        /*
         * Date 14/01/2018
         * @author tiwariabhishek
         *
         * Given an array of non negative numbers and a total, is there subset of numbers in this array which adds up
         * to given total. Another variation is given an array is it possible to split it up into 2 equal
         * sum partitions. Partition need not be equal sized. Just equal sum.
         *
         * Time complexity - O(input.size * total_sum)
         * Space complexity - O(input.size*total_sum)
         *
         */

public class SubsetSum {

    private boolean isSubSetExists(int[] input, int total_sum) {
        boolean[][] table = new boolean[input.length + 1][total_sum + 1];
        for (int i = 0; i < table.length; i++) {
            table[i][0] = true;
        }
        for (int i = 1; i <= input.length; i++) {
            for (int j = 1; j <= total_sum; j++) {
                if (j >= input[i - 1]) {
                    table[i][j] = table[i - 1][j] || table[i][j - input[i - 1]];
                } else {
                    table[i][j] = table[i - 1][j];
                }
            }
        }
        return table[input.length][total_sum];
    }

    private boolean isPartitionPossible(int[] input) {
        int sum = 0;
        for (int i : input) {
            sum += i;
        }
        if (sum % 2 > 0) return false;
        sum >>= 1;
        return isSubSetExists(input, sum);
    }

    public static void main(String args[]) {
        SubsetSum ss = new SubsetSum();
        int arr[] = {1, 3, 5, 5, 2, 1, 1, 6};
        System.out.println(ss.isPartitionPossible(arr));

        int arr1[] = {2, 3, 7, 8};
        System.out.print(ss.isSubSetExists(arr1, 11));
    }
}
