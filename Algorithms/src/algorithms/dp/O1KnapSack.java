package algorithms.dp;

import java.util.HashMap;
import java.util.Map;

/**
 * Date: 11/01/2018
 *
 * @author tiwariabhishek
 * <p>
 * 0/1 Knapsack Problem - Given items of certain weights/values and maximum allowed weight
 * how to pick items to pick items from this set to maximize sum of value of items such that
 * sum of weights is less than or equal to maximum allowed weight.
 * <p>
 * Time complexity - O(W*total items)
 * <p>
 * References -
 * http://www.geeksforgeeks.org/dynamic-programming-set-10-0-1-knapsack-problem/
 * https://en.wikipedia.org/wiki/Knapsack_problem
 */

public class O1KnapSack {

    // DP Approach
    private int bottomUpApproach(int[] weights, int[] values, int sackWeight) {
        int[][] table = new int[weights.length + 1][sackWeight + 1];
        for (int i = 1; i <= weights.length; i++) {
            for (int j = 1; j <= sackWeight; j++) {
                if (i == 0 || j == 0) {
                    table[i][j] = 0;
                    continue;
                }
                if (j >= weights[i - 1]) {
                    table[i][j] = Math.max(table[i - 1][j], table[i - 1][j - weights[i - 1]] + values[i - 1]);
                } else {
                    table[i][j] = table[i - 1][j];
                }
            }
        }
        return table[weights.length][sackWeight];
    }

    // Key for Memoization
    class Index {
        private int remainingWeight;
        private int remainingItems;

        public Index(int remainingWeight, int remainingItems) {
            this.remainingWeight = remainingWeight;
            this.remainingItems = remainingItems;
        }

        @Override
        public boolean equals(Object o) {
            if (o == this) return true;
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Index index = (Index) o;
            if (this.remainingWeight != index.remainingWeight) {
                return false;
            }
            return this.remainingItems == index.remainingItems;
        }

        @Override
        public int hashCode() {
            int result = remainingWeight;
            result = 31 * result + remainingItems;
            return result;
        }
    }

    private int topDownApproach(int[] values, int[] weights, int totalItems, int currentItem, int remainingWeight,
                                Map<Index, Integer> map) {
        if (remainingWeight <= 0 || currentItem >= totalItems) {
            return 0;
        }
        Index key = new Index(remainingWeight, totalItems - currentItem - 1);
        if (map.containsKey(key)) {
            return map.get(key);
        }

        int maxValue;
        if (weights[currentItem] > remainingWeight) {
            maxValue = topDownApproach(values, weights, totalItems, currentItem + 1, remainingWeight, map);
        } else {
            maxValue = Math.max(values[currentItem] + topDownApproach(values, weights, totalItems,
                    currentItem + 1, remainingWeight - weights[currentItem], map),
                    topDownApproach(values, weights, totalItems, currentItem + 1, remainingWeight, map));
        }
        map.put(key, maxValue);
        return maxValue;
    }

    public static void main(String[] args) {
        O1KnapSack k = new O1KnapSack();
        int val[] = {22, 20, 15, 30, 24, 54, 21, 32, 18, 25};
        int wt[] = {4, 2, 3, 5, 5, 6, 9, 7, 8, 10};
        Map<Index, Integer> map = new HashMap<>();
        System.out.println(k.topDownApproach(val, wt, wt.length, 0, 30, map));
        System.out.println(k.bottomUpApproach(wt, val, 30));
    }
}
