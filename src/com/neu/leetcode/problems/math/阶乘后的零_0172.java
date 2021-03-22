package com.neu.leetcode.problems.math;

import java.math.BigInteger;

public class 阶乘后的零_0172 {
    public static void main(String[] args) {
        System.out.println(trailingZeroes(7));
    }
    public static int trailingZeroes(int n) {
        int ans = 1;
        for (int i=1;i<=n;i++){
            ans*=i;
        }
        System.out.println(ans);
        int count = 0;
        while (ans!=0){
            if (ans%10 == 0){
                count++;
            }
            ans /= 10;
        }
        return count;
    }


    //高频题解
    public int trailingZeroes1(int n){
        int count =0;
        while (n>0){
            count += n/5;
            n = n/5;
        }
        return count;
    }

    //官方题解 计算阶乘
    public int trailingZeroes2(int n){
        BigInteger bigInteger = BigInteger.ONE;
        for (int i=2;i<=n;i++){
            bigInteger = bigInteger.multiply(BigInteger.valueOf(i));
        }
        int zeroCount =0;
        while (bigInteger.mod(BigInteger.TEN).equals(BigInteger.ZERO)){
            bigInteger = bigInteger.divide(BigInteger.TEN);
            zeroCount++;

        }
        return zeroCount;
    }

    //官方题解 计算因子5
    public int trailingZeroes3(int n){
//        int zeroCount = 0;
//        for (int i=5;i<n;i+=5){
//            int currentFactor = i;
//            while (currentFactor%5==0){
//                zeroCount++;
//                currentFactor /= 5;
//            }
//        }
//        return zeroCount;

        //或者采用下面的形式
        int zeroCount = 0;
        for (int i=5;i<=n;i++){
            int powerOf5 = 5;
            while (i % powerOf5 == 0){
                zeroCount++;
                powerOf5*=5;
            }
        }
        return zeroCount;
    }

    //官方题解 高效计算因子5
    public int trailingZeroes4(int n){
//        int zeroCount =0;
//        long currentMulipy = 5;
//        while (n > currentMulipy){
//            zeroCount += (n/currentMulipy);
//            currentMulipy *= 5;
//        }
//        return zeroCount;

        //或者参考如下形式的代码
        int zeroCount = 0;
        long currentMulipy = 5;
        while (n > 0){
            n /= 5;
            zeroCount += n;
        }
        return zeroCount;
    }
}
