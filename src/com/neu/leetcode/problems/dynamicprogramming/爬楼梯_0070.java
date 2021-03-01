package com.neu.leetcode.problems.dynamicprogramming;

public class 爬楼梯_0070 {
    // true 简单
    public int climbStairs(int n) {
        int[] dp = new int[n+1];
        dp[1] = 1;
        if (n == 1){
            return dp[n];
        }
        dp[2] = 2;

        for (int i=3;i<n+1;i++){
            dp[i] = dp[i-1]+dp[i-2];
        }

        return dp[n];
    }

    //滚动数组
    public int climbStairs1(int n){
        int p=0,q=0,r=1;
        for (int i=1;i<n;i++){
            p = q;
            q = r;
            r = p+q;
        }
        return r;
    }

    //矩阵快速幂
    public int climbStairs2(int n){
        int[][] q = {{1,1},{1,0}};
        int[][] res = pow(q,n);
        return res[0][0];
    }

    public int[][] pow(int[][] a,int n){
        int[][] ret = {{1,0},{0,1}};
        while (n>0){
            if ((n&1) == 1){
                ret = multiply(ret,a);
            }
            n>>=1;
            a = multiply(a,a);
        }

        return ret;
    }

    private int[][] multiply(int[][] ret, int[][] a) {
        int[][] c= new int[2][2];
        for (int i=0;i<2;i++){
            for (int j=0;j<2;j++){
                c[i][j] = ret[i][0] * a[0][j] + ret[i][1]*a[1][j];
            }
        }
        return c;
    }

    //通项公式
    public int climbStairs3(int n){
        double sqrt5 = Math.sqrt(5);
        double fibn = Math.pow((1+sqrt5)/2,n+1)-Math.pow((1-sqrt5)/2,n+1);
        return (int) Math.round(fibn/sqrt5);
    }

    //递归
    public int climbStairs4(int n){
        if (n==1){
            return 1;
        }
        if (n == 2){
            return 2;
        }

        return climbStairs4(n-1) + climbStairs4(n-2);
    }

    //使用记忆化搜索
    public int climbStatir5(int n){
        int memo[] = new int[n+1];
        return climbStairMemo(n,memo);
    }

    public int climbStairMemo(int n,int[] memo){
        if (memo[n] > 0){
            return memo[n];
        }

        if (n == 1){
            memo[n] = 1;
        } else if (n == 2){
            memo[n] =2;
        } else {
            memo[n] = climbStairMemo(n-1,memo) + climbStairMemo(n-2,memo);
        }

        return memo[n];
    }

    //滚动数组 斐波那契数列
    public int climbStair(int n){
        if ( n== 1){
            return 1;
        }

        int first = 1;
        int second = 2;
        for (int i=3;i<n;i++){
            int third = first + second;
            first = second;
            second = third;
        }

        return second;
    }
}
