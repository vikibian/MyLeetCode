package com.neu.leetcode.problems.array;

import java.util.Arrays;

public class 寻找数组的中心索引_0724 {
    public int pivotIndex(int[] nums) {
        int length = nums.length;
        if (length == 0){
            return -1;
        }

        int sum = getSum(nums,length);
        int left = 0,right = sum;
        for (int i=0;i<length;i++){
            right = right - nums[i];
            if (left == right){
                return i;
            }
            left = left + nums[i];
        }
        return -1;
    }

    private int getSum(int[] nums, int length) {
        int sum = 0;
        for (int i=0;i<length;i++){
            sum = sum + nums[i];
        }
        return sum;
    }

    //官方题解 前缀和
    public int pivotIndex1(int[] nums) {
        int total = Arrays.stream(nums).sum();
        int sum = 0;
        for (int i=0;i<nums.length;i++){
            if (2*sum + nums[i] == total){
                return i;
            }
            sum = sum + nums[i];
        }
        return -1;
    }
}
