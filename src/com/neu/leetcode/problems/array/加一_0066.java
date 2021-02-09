package com.neu.leetcode.problems.array;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class 加一_0066 {

    public static void main(String[] args) {
//        int[] nums = new int[]{4,3,2,1};
        int[] nums = new int[]{8,9,9,9};
        System.out.println(nums[0]);
        nums = new int[nums.length +1];
        System.out.println(nums[0]);
        System.out.println(nums[1]);
    }

    public static int[] plusOne(int[] digits) {
        int len = digits.length;
        boolean isOver = true;
        List<Integer> list = new ArrayList<>();
        int index = len-1;

        for (int i=len-1;i>=0;i--){

            if (isOver && digits[i] ==9  ){
                list.add(0);
                index--;
            }else {
                if (i == len-1 || isOver){
                    list.add(++digits[i]);
                } else {
                    list.add(digits[i]);
                }
                isOver = false;
            }
        }

        if (index == -1){
            list.add(1);
        }
        System.out.println(list.toString());
        int[] ans = new int[list.size()];
        int j=0;
        for (int i=list.size()-1;i>=0;i--){
            ans[j++] = list.get(i);
        }

        return ans;
    }

    //官方题解
    public static int[] plusOne1(int[] digits) {
        for(int i=digits.length-1;i>=0;i--){
            digits[i]++;
            digits[i] = digits[i] %10;
            if (digits[i] != 0){
                return digits;
            }
        }
        digits = new int[digits.length +1];
        digits[0] =1;
        return digits;
    }
}


