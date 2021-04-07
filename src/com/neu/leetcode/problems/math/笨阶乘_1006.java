package com.neu.leetcode.problems.math;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class 笨阶乘_1006 {
    public int clumsy(int N) {
        List<Integer> list = new ArrayList<>();
        int count = 0;
        int num = N;
        int ans = 1;
        for (int i=0;i<N-1;i++){
            switch (i % 4){
                case 0:
                    ans = num * (--num);
                    break;
                case 1:
                    ans = ans/(--num);
                    if (i != 1){
                        list.add(-ans);
                    } else {
                        list.add(ans);
                    }
                    ans = 0;
                    break;
                case 2:
                    list.add(--num);
                    break;
                case 3:
                    num--;
                    ans=num;
                    break;
            }
        }
        System.out.println(list.toString());
        System.out.println(ans);

        if (list.size() == 0){
            return ans;
        } else {
            int res = list.get(0);
            for (int i=1;i<list.size();i++){
                res += list.get(i);
            }
            return res-ans;
        }



    }

    //官方题解 使用模拟栈
    public int clumsy1(int N){
        Deque<Integer> stack = new LinkedList<>();
        stack.push(N);
        N--;
        int index = 0;
        while (N>0){
            if (index % 4 == 0){
                stack.push(stack.pop() * N);
            } else if (index % 4 == 1){
                stack.push(stack.pop() / N);
            } else if (index % 4 == 2){
                stack.push(N);
            } else {
                stack.push(-N);
            }
            index++;
            N--;
        }

        int ans = 0;
        while (!stack.isEmpty()){
            ans += stack.pop();
        }
        return ans;
    }

    //官方题解
    public int clumsy2(int N){
        if (N==1){
            return 1;
        } else if (N==2){
            return 2;
        } else if (N==3){
            return 6;
        } else if (N==4){
            return 7;
        }

        if (N%4 == 0){
            return N+1;
        } else if (N%4<=2){
            return N+2;
        } else {
            return N-1;
        }
    }
}
