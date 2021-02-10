package com.neu.leetcode.problems.array;

public class 不同路径2_0063 {

    public static void main(String[] args) {
        int[][] nums = new int[][]{{0,1,0,0,0},{1,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0}};
        System.out.println(uniquePathsWithObstacles(nums));
    }

    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int ans = 0;
        int m = obstacleGrid.length,n=obstacleGrid[0].length;
        if (m ==1 && n==1){
            if (obstacleGrid[0][0] == 0){
                return 1;
            } else {
                return 0;
            }
        }
        int[][] res = new int[m][n];
        res[0][0] = 1;
        for (int i=1;i<m;i++){
            if (obstacleGrid[i][0] == 0 && obstacleGrid[i-1][0] != 1){
                res[i][0] = res[i-1][0];
            } else {
                res[i][0] = 0;
            }

        }

        for (int i=1;i<n;i++){
            if (obstacleGrid[0][i] == 0 && obstacleGrid[0][i-1] != 1){
                res[0][i] = res[0][i-1];
            } else {
                res[0][i] = 0;
            }

        }

        for (int i=1;i<m;i++){
            for (int j=1;j<n;j++){
                if (obstacleGrid[i][j]==0){
                    res[i][j] = res[i-1][j] + res[i][j-1];
                } else {
                    res[i][j] = 0;
                }
            }
        }

        return res[m-1][n-1];
    }

    //官方题解  动态规划
    public static int uniquePathsWithObstacles1(int[][] obstacleGrid) {

        int m =obstacleGrid.length,n=obstacleGrid[0].length;
        int[] ans = new int[n];
        ans[0] = obstacleGrid[0][0] == 0 ?1:0;

        for (int i=0;i<m;i++){
            for (int j=0;j<n;j++){
                if (obstacleGrid[i][j] == 1){
                    ans[j] = 0;
                    continue;
                }
                if (j-1 >= 0 && obstacleGrid[i][j-1] == 0 ){
                    ans[j] = ans[j] + ans[j-1];
                }
            }
        }

        return ans[n-1];
    }

}
