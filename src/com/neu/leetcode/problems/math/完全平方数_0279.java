package com.neu.leetcode.problems.math;

import java.util.*;

public class 完全平方数_0279 {

    //超时
    public int numSquares(int n) {
        //12 13 6
        int oldNum = n;
        int count = 0;
        List<Integer> square = new LinkedList<>();
        List<Integer> list = new LinkedList<>();
        int num = (int)Math.sqrt(n);
        for (int i=num;i>0;i--){
            square.add(i*i);
        }

        while (oldNum != 0 && num>0){
            int rest = oldNum-(num*num);
//            if (rest<0){
//                num--;
//                continue;
//            }
//            int rest1 = oldNum%(num*num);
//            boolean flag = false;
//            for (int j=(square.size()-num+1);j<square.size();j++){
//                if (rest1%square.get(j)==0 ){
//                    flag = true;
//                    System.out.println("j"+j);
//                }
//            }
            if (square.contains(rest) || (oldNum%(num*num)==0 ) ){
                oldNum = rest;
                count++;
            } else {
                num--;
            }
        }

        System.out.println(square);

        return count;
    }

    //官方题解1 动态规划
    public int numSquares1(int n){
        int[] dp = new int[n+1];
        Arrays.fill(dp,Integer.MAX_VALUE);
        dp[0] = 0;

        int max_square = (int)Math.sqrt(n)+1;
        int[] square_nums = new int[max_square];
        for (int i=1;i<max_square;i++){
            square_nums[i] = i*i;
        }

        for (int i=1;i<=n;i++){
            for (int j=1;j<max_square;j++){
                if (i < square_nums[j]){
                    break;
                }
                dp[i] = Math.min(dp[i],dp[i-square_nums[j]]+1 );
            }
        }

        return dp[n];
    }

    //官方题解2 贪心枚举
    Set<Integer> square_nums = new HashSet<>();
    public int numSquares2(int n){

        for (int i=1;i<=n;i++){
            square_nums.add(i*i);
        }
        int count = 1;
        for (;count<=n;count++){
            if (isDivided(n,count)){
                return count;
            }
        }
        return count;
    }

    private boolean isDivided(int n, int count) {
        if (count == 1){
            return square_nums.contains(n);
        }

        for (int square : square_nums){
            if (isDivided(n-square,count-1)){
                return true;
            }
        }
        return false;
    }

    //官方题解3 贪心加BFS
    public int numSquares3(int n) {
        ArrayList<Integer> square_nums = new ArrayList<>();
        for (int i=1;i*i<=n;i++){
            square_nums.add(i*i);
        }

        Set<Integer> queue = new HashSet<>();
        queue.add(n);
        int level = 0;
        while (queue.size()>0){
            level+=1;
            Set<Integer> next_queue = new HashSet<>();
            for (Integer reminder : queue){
                for (Integer square : square_nums){
                    if (reminder.equals(square)){
                        return level;
                    } else if (reminder < square){
                        break;
                    } else {
                        next_queue.add(reminder-square);
                    }
                }
            }
            queue = next_queue;
        }
        return level;
    }

    public int numSquares4(int n) {
        while (n % 4 == 0){
            n /= 4;
        }
        if (n%8 == 7){
            return 4;
        }
        if (isSqueue(n)){
            return 1;
        }
        for(int i=1;i*i<n;i++){
            if (isSqueue(n - i*i)){
                return 2;
            }
        }
        return 3;
    }

    public boolean isSqueue(int n){
        int sq = (int)Math.sqrt(n);
        return n== sq*sq;
    }
}
