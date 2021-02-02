package com.neu.leetcode.problems.array;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class 下一个排列_0031 {
    public void nextPermutation(int[] nums) {
        int len = nums.length;
        boolean isOrder = true;
        boolean isOrder1= true;
        int index = 0;
        for (int i=0;i<len-1;i++){
            if (nums[i]>nums[i+1]){

                isOrder = false;
                index = i+1;
            } else {
                if (!isOrder){
                    isOrder1 = false;
                }
            }
        }

        if (isOrder){
            ;
        }else {

        }
    }

    //官方题解
    public void nextPermutation1(int[] nums) {
        int i= nums.length -2;
        while (i >= 0 && nums[i] > nums[i + 1]){
            i--;
        }
        if (i >= 0){
            int j = nums.length - 1;
            while (j>=0 && nums[i] < nums[j]){
                j--;
            }
            swap(nums,i,j);
        }

        reverse(nums,i+1);
    }

    private void reverse(int[] nums, int i) {
        int left = i,right= nums.length -1;
        while (left < right){
            swap(nums,left,right);
            left++;
            right--;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[j] = nums[i];
        nums[j] = temp;
    }
}
