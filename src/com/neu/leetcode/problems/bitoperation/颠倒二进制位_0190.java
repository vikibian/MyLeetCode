package com.neu.leetcode.problems.bitoperation;

import java.util.Arrays;

public class 颠倒二进制位_0190 {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int[] bits = new int[32];
        for (int i=0;i<32;i++){
            bits[i] = ((n>>i) & 1);
        }
        long ans = 0;
        for (int i=31;i>=0;i--){
            int bit = bits[i];
            if (bit == 1){
//                ans = ans + (int)Math.pow(2,31-i);
//                System.out.println(bit+"=="+(31-i));
//                System.out.println((int)Math.pow(2,31-i));
                if (31-i == 31){
                    ans = ans + (int)Math.pow(2,31-i)+1;
                } else {
                    ans = ans + (int)Math.pow(2,31-i);
                }
            }
        }
        System.out.println(Arrays.toString(bits));
        System.out.println(ans);
        System.out.println(Integer.MAX_VALUE);
//        System.out.println(Integer.MAX_VALUE <ans);
//        System.out.println(Integer.MAX_VALUE == ans);
//        System.out.println(Math.pow(2,0));
//        StringBuffer stringBuffer = new StringBuffer(n);
//        System.out.println(stringBuffer.toString());
//        return Integer.parseInt(stringBuffer.toString());
//        if (Integer.MAX_VALUE<ans){
//            return (int)ans+1;
//        } else {
//            return (int)ans;
//        }
        return (int)ans;
    }

    //官方题解 逐位颠倒
    public int reverseBits1(int n){
        int rev = 0;
        for (int i=0;i<32 && n!=0;i++){
            rev |= (n & 1) << (31 - i);
            n >>>= 1;
        }
        return rev;
    }

    //官方题解二 位运算分治
    private final int M1 = 0x55555555;
    private final int M2 = 0x33333333;
    private final int M3 = 0x0f0f0f0f;
    private final int M4 = 0x00ff00ff;
    public int reverseBits2(int n){
        n = n>>>1 & M1 | (M1 & n) <<1;
        n = n>>>2 & M2 | (M2 & n) << 2;
        n = n>>>4 & M3 | (M3 & n) <<4;
        n = n>>>8 & M4 | (M4 & n) << 8;
        return n >>> 16 | n<<16;
    }
}
