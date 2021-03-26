package com.neu.leetcode.problems.math;

public class 二的幂_0231 {
    public boolean isPowerOfTwo(int n) {
        if (n == 0){
            return false;
        }
        int num = 0;
        double index = 0;
        while (num<n){
            num = (int) Math.pow(2.0, index);
            index++;
            if (Integer.MAX_VALUE /2 <= num && num != n){
                return false;
            }
        }
        if (num == n){
            return true;
        } else {
            return false;
        }
    }

    //官方题解 暴力解法
    public boolean isPowerOfTwo1(int n) {
        if (n == 0) return false;
        while (n % 2 == 0) n /= 2;
        return n == 1;
    }

    //官方题解 位运算 获取二进制中最右边的1
    public boolean isPowerOfTwo2(int n){
        if (n == 0){
            return false;
        }
        long x = (long) n;
        return (x & (-x)) == x;
    }

    //官方题解 位运算 去除二进制中最右边的1；
    public boolean isPowerOfTwo3(int n){
        if (n==0){
            return false;
        }
        long x= (long)n;
        return (x & (x-1)) == 0;
    }

}
