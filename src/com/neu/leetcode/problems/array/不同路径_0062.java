package com.neu.leetcode.problems.array;

import java.util.Arrays;

public class 不同路径_0062 {
    public static void main(String[] args) {
        System.out.println(uniquePaths(3,3));
    }
    public static int uniquePaths(int m, int n) {

        return getPathCount(m,n,0,0);
    }

    private static int getPathCount(int m, int n, int row, int col) {
        if (row == m-1 && col == n-1){
            return 1;
        }

        int down = 0,right = 0;
        if (row+1<m){
            down = getPathCount(m,n,row+1,col);
        }

        if (col +1 <n){
            right = getPathCount(m,n,row,col+1);
        }

        return down+right;
    }

    //动态规划  空间复杂度 m*n
    public int uniquePaths1(int m, int n) {
        int[][] ans = new int[m][n];
        for (int i=0;i<m;i++){
            ans[i][0] = 1;
        }

        for (int i=0;i<n;i++){
            ans[0][i] = 1;
        }
        for (int i=1;i<m;i++){
            for (int j=1;j<n;j++){
                ans[i][j] = ans[i-1][j]+ans[i][j-1];
            }
        }

        return ans[m-1][n-1];
    }

    //动态规划  空间复杂度 2n
    public int uniquePaths2(int m, int n) {
        int[] pre = new int[n];
        int[] cur = new int[n];
        Arrays.fill(pre,1);
        Arrays.fill(cur,1);

        for (int i=1;i<m;i++){
            for (int j=1;j<n;j++){
                cur[j] = cur[j-1] + pre[j-1];
            }
            pre = cur.clone();
        }

        return cur[n-1];
    }

    //动态规划  空间复杂度 n
    public int uniquePaths3(int m, int n) {

        int[] cur = new int[n];

        Arrays.fill(cur,1);

        for (int i=1;i<m;i++){
            for (int j=1;j<n;j++){
                cur[j] = cur[j-1] + cur[j];
            }
        }

        return cur[n-1];
    }


    //纯数学计算
    public int uniquePaths4(int m, int n) {
        long ans =1;
        for (int x=n,y=1;y<m;x++,y++){
            ans = ans *x /y;
        }

        return (int)ans;
    }

}
