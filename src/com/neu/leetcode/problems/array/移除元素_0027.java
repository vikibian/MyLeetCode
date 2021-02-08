package com.neu.leetcode.problems.array;

import java.util.Arrays;

public class 移除元素_0027 {
    public int removeElement(int[] nums, int val) {
        int ans = 0;
        int len = nums.length;
        int count = 0;
        Arrays.sort(nums);
        for (int i=0;i<len;i++){
            if (nums[i] == val){
                count++;
            } else {
                nums[i-count] = nums[i] ;
            }
        }

        return len-count;
    }

    //官方题解 双指针
    public int removeElement1(int[] nums,int val){
        int i=0;
        int len = nums.length;
        for (int j=0;j<len;j++){
            if (nums[j] != val){
                nums[i] = nums[j];
                i++;
            }
        }

        return i;
    }

    //官方题解二
    public int removeElement2(int[] nums,int val){
        int i=0;
        int len = nums.length;
        while (i<len){
            if (nums[i] == val){
                nums[i] = nums[len -1];
                len--;
                //为什么 这里的i 不加加呢？
                //因为你最后的那个元素可能是与val的值相等  i不加的话 下次循环判断会继续判断新的i位置的值
            } else {
                i++;
            }
        }

        return len;
    }

}
