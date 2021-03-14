package com.neu.leetcode.problems.math;

public class 两数相除_0029 {

    public static void main(String[] args) {
        System.out.println(divide(-2147483648,-1));
    }

    public static int divide(int dividend, int divisor) {
        boolean flag = true;
        if((dividend>0 && divisor<0) || (dividend<0 && divisor>0)){
            flag = false;
        }
        int num = 0;
        if (dividend<0 && Integer.MIN_VALUE == dividend){
            num = Integer.MAX_VALUE-1;
        } else {
            num = Math.abs(dividend);
        }
        int sor = 0;
        if (divisor<0 && Integer.MIN_VALUE == divisor){
            sor = Integer.MAX_VALUE-1;
        } else {
            sor = Math.abs(divisor);
        }

        int count = 0;
        while (num>=sor){
            if (sor == 1){
                count = num;
                break;
            }
            count++;
            num = num-sor;
        }

        if (count<0 && Integer.MIN_VALUE == count){
            count = Integer.MAX_VALUE;
        } else {
            count = Math.abs(count);
        }

        if (flag){
            return count;
        } else {
            return -count;
        }
    }

    //
    public int divide1(int dividend, int divisor) {
        if (divisor == -1 && dividend==Integer.MIN_VALUE) return Integer.MAX_VALUE;
        int sign = 1;
        if ((dividend>0 && divisor<0)||(dividend<0&&divisor>0)){
            sign = -1;
        }

        int a = dividend>0?-dividend:dividend;
        int b = divisor>0 ?-divisor : divisor;
        if (a > b){
            return 0;
        }
        int ans = div(a,b);

        return sign== -1?-ans:ans;
    }

    //二分法
    private int div(int a, int b) {
        if (a>b) return 0;
        int count =1;
        int tb = b;
        while (tb+tb >= a&&tb+tb<0){
            tb += tb;
            count++;
        }
        return count+div(a-tb,b);
    }
}
