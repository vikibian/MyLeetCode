package com.neu.leetcode.problems.array;

public class 买卖股票的最佳时机2_0122 {
    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        int len = prices.length;
        int minprice  = prices[0];
        for (int i=1;i<len;i++){
            if (prices[i]<prices[i-1]){
                maxProfit = maxProfit+(prices[i-1]-minprice);
                minprice = prices[i];
            }

            if ((i == len-1)&&(prices[i]>minprice) ){
                maxProfit = maxProfit+(prices[i]-minprice);
            }
        }
        return maxProfit;
    }
}
