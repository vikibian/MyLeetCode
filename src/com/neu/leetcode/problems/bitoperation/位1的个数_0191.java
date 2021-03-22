package com.neu.leetcode.problems.bitoperation;

public class 位1的个数_0191 {

    public static void main(String[] args) {
        int n = 00000000000000000000000000001011;
        System.out.println(hammingWeight(n));
    }

    public static int hammingWeight(int n) {
        int count = 0;
        int k = 1;
        for (int i=0;i<32;i++){
            if ((n&1)==1){
                count++;
            }
            n = n>>1;
        }
        return count;
    }

    //官方题解 循环检查二进制位
    public int hammingWeight1(int n){
        int ret = 0;
        for (int i=0;i<32;i++){
            if ((n & (1<<i))!=0){
                ret++;
            }
        }
        return ret;
    }

    //官方题解 位运算优化
    public int hammingWeight2(int n){
        int ret = 0;
        while (n!=0){
            n &= n-1;
            ret++;
        }

        return ret;
    }
}
