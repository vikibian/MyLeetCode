package com.neu.leetcode.problems.array;

public class 买卖股票的最佳时机_0121 {

    public static void main(String[] args) {
        int[] nums = new int[]{7,6,4,3,1};
        System.out.println(maxProfit(nums));
    }

    public static int maxProfit(int[] prices) {
        int pro = 0;
        int n = prices.length;
        int in =0;
        int out=0;
        int price = prices[0];
        for (int i=0;i<n;i++){
            if (prices[i] < price){
                in = i;
                price = prices[i];

            } else {
                out = i;
            }
            if (out>in){
                pro = Math.max(pro,prices[out]-prices[in]);
            }

        }
        return pro;
    }

    //暴力解法
    public int maxProfit1(int prices[]) {
        int maxprofit = 0;
        for (int i=0;i<prices.length-1;i++){
            for (int j=i+1;j<prices.length;j++){
                int profit = prices[j] - prices[i];
                if (profit > maxprofit){
                    maxprofit = profit;
                }
            }
        }
        return maxprofit;
    }

    //一次遍历
    public int maxProfit2(int prices[]){
        int minprices = Integer.MAX_VALUE;
        int maxProfit =0;
        for (int i=0;i<prices.length;i++){
            if (prices[i] <minprices){
                minprices = prices[i];
            } else if (prices[i] - minprices > maxProfit){
                maxProfit = prices[i]-minprices;
            }
        }
        return maxProfit;
    }
}
