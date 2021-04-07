package com.neu.leetcode.problems.math;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

public class 快乐数_0202 {
    public boolean isHappy(int n) {
        TreeMap<Integer,Integer> treeMap1 = new TreeMap<>();
        TreeMap<Integer,Integer> treeMap2 = new TreeMap<>();

        while (n != 1){
            int res=0;
            int currNum = n;
            while (currNum > 0){
                int num1 = currNum %10;
                if (!treeMap1.containsKey(num1)){
                    treeMap1.put(num1,num1 * num1);
                }
                res += treeMap1.get(num1);
                currNum /= 10;
            }
            n = res;
            if (treeMap2.containsKey(n)){
                return false;
            } else {
                treeMap2.put(n,n);
            }
        }

        return true;
    }

    //官方题解一 用哈希集合检测循环
    public boolean isHappy1(int n){
        Set<Integer> seen = new HashSet<>();
        while (n!=1 && !seen.contains(n)){
            seen.add(n);
            n = getNext(n);
        }
        return n == 1;
    }

    private int getNext(int n) {
        int totalSum = 0;
        while (n>0){
            int d = n%10;
            n = n/10;
            totalSum += d*d;
        }
        return totalSum;
    }

    //官方题解二 快慢指针
    public boolean isHaapy2(int n){
        int slowRunner = n;
        int fastRunner = getNext(n);
        while (fastRunner!=1 && fastRunner != slowRunner){
            slowRunner = getNext(n);
            fastRunner = getNext(getNext(fastRunner));
        }
        return fastRunner == 1;
    }

    //官方题解三 数学

    private Set<Integer> cycleNumber = new HashSet<>(Arrays.asList(4,16,37,58,89,145,42,20));

    public boolean isHappy3(int n){
        while (n != 1 && !cycleNumber.contains(n)){
            n = getNext(n);
        }
        return n ==1;
    }


}
