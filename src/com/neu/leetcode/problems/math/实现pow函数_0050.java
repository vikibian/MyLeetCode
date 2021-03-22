package com.neu.leetcode.problems.math;

public class 实现pow函数_0050 {

    public double myPow(double x, int n) {
        if (x == 1.0){
            return 1;
        }
        if (x == -1.0){
            if (n == Integer.MAX_VALUE){
                return -1;
            } else if (n == Integer.MIN_VALUE){
                return 1;
            }
        }
        if (n == Integer.MIN_VALUE){
            return 0;
        }
        double ans = 1;
        if (n>=0){
            for (int i=0;i<n;i++){
                ans = ans*x;
            }
        } else {
            int abs = Math.abs(n);
            for (int i=0;i<abs;i++){
                ans = ans/x;
            }
        }



        return ans;
    }

    //暴力解法
    public double myPow1(double x, int n) {
        long N = n;
        if (N < 0){
            x = 1/x;
            N = -N;
        }
        double ans = 1;
        for (long i =0;i<N;i++){
            ans = ans * x;
        }
        return ans;
    }

    //官方题解 快速幂+递归
    public double myPow2(double x,int n){
        long N = n;
        return N>=0?quickMul(x,N) : 1.0/quickMul(x,-N);
    }

    public double quickMul(double x,long N){
        if (N == 0){
            return 1.0;
        }
        double y = quickMul(x,N/2);
        return N%2==0?y*y:y*y*x;
    }

    //官方题解 快速幂+迭代
    public double myPow3(double x,int n){
        long N = n;
        return N>=0? quickMul2(x,N):1.0/quickMul2(x,-N);
    }

    public double quickMul2(double x,long N){
        double ans = 1.0;
        // 贡献的初始值为 x
        double x_contribute = x;
        // 在对 N 进行二进制拆分的同时计算答案
        while (N >0){
            if (N%2 ==1){
                ans *= x_contribute;
            }
            x_contribute *= x_contribute;
            N/=2;
        }

        return ans;
    }

}
