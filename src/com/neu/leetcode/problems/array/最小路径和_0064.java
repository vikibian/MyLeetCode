package com.neu.leetcode.problems.array;

public class 最小路径和_0064 {
    int res = Integer.MAX_VALUE;

    public static void main(String[] args) {

    }

    public  int minPathSum(int[][] grid) {
        getPathSum(grid,0,0,0);
        return res;
    }

    private  void getPathSum(int[][] grid, int row, int col, int ans) {
        int r = grid.length;
        int l = grid[0].length;
        ans = ans + grid[row][col];
        if (r-1==row && l-1 ==col){
            res = Math.min(ans,res);
            return ;
        }


        if (row+1<r){
            getPathSum(grid,row+1,col, ans);

        }

        if (col+1<l){
            getPathSum(grid,row,col+1, ans);
        }
    }

    //官方题解  视频解法
    public  int minPathSum1(int[][] grid) {
        int[][] dp = new int[grid.length][grid[0].length];
        for (int i=grid.length-1;i>=0;i--){
            for (int j=grid[0].length-1;j>=0;j--){
                if (i == grid.length-1 && j!=grid[0].length-1){
                    dp[i][j] = grid[i][j]+dp[i][j+1];
                } else if (j == grid[0].length-1 && i!=grid.length-1 ){
                    dp[i][j] = grid[i][j] + dp[i+1][j];
                } else if (j != grid[0].length -1 && i != grid.length-1){
                    dp[i][j] = grid[i][j] + Math.min(dp[i+1][j],dp[i][j+1]);
                } else {
                    dp[i][j] = grid[i][j];
                }
            }
        }
        return dp[0][0];
    }

    //使用优化滚动数组
    public  int minPathSum2(int[][] grid) {
        int[] dp = new int[grid[0].length];
        for (int i=grid.length-1;i>=0;i--){
            for (int j=grid[0].length-1;j>=0;j--){
                if (i == grid.length-1 && j!=grid[0].length-1){
                    dp[j] = grid[i][j]+dp[j-1];
                } else if (j == grid[0].length-1 && i!=grid.length-1 ){
                    dp[j] = grid[i][j] + dp[j];
                } else if (j != grid[0].length -1 && i != grid.length-1){
                    dp[j] = grid[i][j] + Math.min(dp[j],dp[j+1]);
                } else {
                    dp[j] = grid[i][j];
                }
            }
        }
        return dp[0];
    }

    public  int minPathSum3(int[][] grid) {
        if (grid==null || grid.length == 0 || grid[0].length==0){
            return 0;
        }
        int rows = grid.length,cols = grid[0].length;
        int[][] dp = new int[rows][cols];
        dp[0][0] = grid[0][0];
        for (int i=1;i<rows;i++){
            dp[i][0] = grid[i][0] + dp[i-1][0];
        }

        for (int i=1;i<cols;i++){
            dp[0][i] = grid[0][i]+dp[0][i-1];
        }

        for (int i=1;i<rows;i++){
            for (int j=1;i<cols;j++){
                dp[i][j] = grid[i][j] + Math.min(dp[i-1][j],dp[i][j-1]);
            }
        }
        return dp[rows-1][cols-1];
    }

}
