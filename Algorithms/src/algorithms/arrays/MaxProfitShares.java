package Algorithms.src.algorithms.arrays;

/**
 * Date 10/12/2019
 *
 * @author tiwariabhishek
 *
 * Space complexity is O(1)
 * Time complexity - O(n)
 * References
 * https://www.techiedelight.com/maximum-profit-earned-buying-and-selling-shares/
 */

public class MaxProfitShares {

    private static int getMaxProfit(int prices[]) {
        int j=0, maxProfit = 0;
        for(int i=1;i<prices.length;i++) {
            if(prices[i] < prices[i-1]) j=i;
            if(prices[i-1] <= prices[i] && (i+1 == prices.length || prices[i+1] < prices[i]))
                maxProfit += prices[i] - prices[j];
        }
        return maxProfit;
    }

    public static void main(String args[]) {
        int[] array = {1, 5, 2, 3, 7, 6, 4, 5};
        int maxProfit = getMaxProfit(array);
        System.out.println(maxProfit);
    }
}
