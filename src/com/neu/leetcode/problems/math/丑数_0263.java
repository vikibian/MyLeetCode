package com.neu.leetcode.problems.math;

import java.util.ArrayList;
import java.util.List;

public class 丑数_0263 {
    public boolean isUgly(int n) {
        if (n == 1){
            return true;
        }
        if (n == 0){
            return false;
        }
        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(3);
        list.add(5);
        for (int i=0;i<list.size();i++){
            int num = n;
            int tar = list.get(i);
            while (num != 1){
                if (num % tar != 0 ){
                    break;
                } else {
                    num = num / tar;
                }
            }
            if (num == 1){
                return true;
            } else {
                if (list.contains(num)){
                    return true;
                } else {
                    return false;
                }
            }

        }
        return false;
    }

    //高分题解
    public boolean isUgly1(int n) {
        if (n == 0){
            return false;
        }
        if (n == 1){
            return true;
        }
        if (n %2 == 0){
            return isUgly1(n/2);
        }
        if (n % 3 == 0){
            return isUgly1(n /3);
        }
        if (n % 5 == 0){
            return isUgly1(n/5);
        }

        return false;
    }

    //10行代码 快速题解
    public boolean isUgly2(int n){
        //需特判0
        if (n < 1){
            return false;
        }
        while (n % 2 == 0){
            n /= 2;
        }
        while (n % 3 == 0){
            n /= 3;
        }
        while (n % 5 == 0){
            n /= 5;
        }
        return n == 1;
    }

    //乘法交换律，不断除以三个质因数，丑数结果必然为1
    //任何一个丑叔都可以写成 n = 2i * 3j * 5k
    public boolean isUgly3(int n){
        if (n < 0){
            return false;
        }
        int[] factor = new int[]{2,3,5};
        for (int i : factor){
            while (n % i == 0){
                n /= i;
            }
        }

        if (n == 1){
            return true;
        }
        return false;
    }

    //高分题解
    public boolean isUgly4(int n){
        if (n == 0){
            return false;
        }
        boolean flag = true;
        while (flag && n != 1){
            flag = false;
            if (n % 2 ==0){
                n = n>>1;
                flag = true;
            } else if (n % 3 ==0){
                n /= 3;
                flag = true;
            } else if (n % 5 == 0){
                n /= 5;
                flag = true;
            }
        }

        return n == 1?true:false;
    }
}
