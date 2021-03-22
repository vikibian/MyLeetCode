package com.neu.leetcode.problems.math;

public class x的平方根_0069 {
    public int mySqrt(int x) {
        return (int)Math.sqrt(x);
    }

    //官方题解 袖珍计算器
    public int mySqrt1(int x){
        if (x == 0){
            return 0;
        }
        int ans = (int) Math.exp(0.5*Math.log(x));
        return (long) (ans+1) * (ans +1) <=x?ans+1:ans;
    }

    //官方题解 二分查找
    public int mySqrt2(int x){
        int l=0,r=x,ans=-1;
        while (l<=r){
            int mid = l+(r-l)/2;
            if ((long) mid* mid <=x){
                ans = mid;
                l=mid+1;
            } else {
                r = mid-1;
            }
        }
        return ans;
    }

    //官方题解  牛顿法
    public int mySqrt3(int x){
        if ( x == 0){
            return 0;
        }

        double c = x,x0=x;
        while (true){
            double x1 = 0.5*(x0+c/x0);
            if (Math.abs(x0-x1)<1e-7){
                break;
            }
            x0 = x1;
        }
        return (int )x0;
    }
}
