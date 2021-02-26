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

    //官方题解 动态规划
    //dp[i][0] 表示没有股票
    //dp[i][1] 表示有股票
    public int maxProfit1(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i=1;i<n;i++){
            dp[i][0] = Math.max(dp[i-1][0],dp[i-1][1]+prices[i]);
            dp[i][1] = Math.max(dp[i-1][1],dp[i-1][1]-prices[i]);
        }
        return dp[n-1][0];
    }

    //官方题解
    //每天的状态只与前一天的状态有关
    public int maxProfit2(int[] prices){
        int n = prices.length;
        int dp0 = 0,dp1=prices[0];
        for (int i=1;i<n;i++){
            int newDp0 = Math.max(dp0,dp1+prices[i]);
            int newDp1 = Math.max(dp1,dp0-prices[i]);
            dp0 = newDp0;
            dp1 = newDp1;
        }
        return dp0;
    }

    //官方题解 贪心算法
    public int maxProfit3(int[] prices){
        int ans = 0;
        int n = prices.length;
        for (int i=1;i<n;i++){
            ans += Math.max(ans,prices[i]-prices[i-1]);
        }
        return ans;
    }

    //官方视频题解
    public int maxProfit4(int[] prices){
        int ans = 0;
        int n = prices.length;
        for (int i=1;i<n;i++){
            if (prices[i] > prices[i-1]){
                ans += (prices[i]-prices[i-1]);
            }
        }
        return ans;
    }
}
