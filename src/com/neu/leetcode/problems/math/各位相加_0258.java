package com.neu.leetcode.problems.math;

public class 各位相加_0258 {
    public int addDigits(int num) {
        int ans = num;
        while (num > 9){
            int tempNum = num;
            int res = 0;
            while (tempNum > 0){
                int num1 = tempNum %10;
                res += num1;
                tempNum /= 10;
            }
            num = res;
            if (num >=0 && num<10){
                ans = num;
                break;
            }
        }

        return ans;
    }

    //高分题解 看不懂
    public int addDigits1(int num){
        return (num-1)%9 +1;
    }
}
