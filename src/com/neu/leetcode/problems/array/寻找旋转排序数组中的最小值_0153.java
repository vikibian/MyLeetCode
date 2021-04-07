package com.neu.leetcode.problems.array;

import java.util.Arrays;

public class 寻找旋转排序数组中的最小值_0153 {
    //二分查找
    public int findMin(int[] nums) {
        int len = nums.length;
        int left = 0,right=len-1;

        while (left<=right){
            int mid = (left + right)/2;
            if (nums[mid]>= nums[0]){
                left = mid + 1;
            } else if (nums[mid] <= nums[len-1]){
                right = mid-1;
            }
        }
        if (left >= len){
            return nums[0];
        } else {
            return nums[left];
        }

    }
    //java原始api
    public int findMin1(int[] nums) {
        Arrays.sort(nums);
        return nums[0];
    }

    //官方题解一 二分查找
    public int findMin2(int[] nums){
        int low = 0;
        int high = nums.length-1;
        while (low<high){
            int mid = (low+high)/2;
            if (nums[mid] < nums[high]){
                high = mid;
            } else {
                low = mid+1;
            }
        }

        return nums[low];
    }

    //官方题解2 二分搜索
    public int findMin3(int[] nums ){
        if (nums.length ==1){
            return nums[0];
        }

        int left=0,right = nums.length-1;
        if (nums[0] < nums[right]){
            return nums[0];
        }

        while (left <= right){
            int mid = left +(right-left)/2;
            if (nums[mid] > nums[mid+1]){
                return nums[mid+1];
            }

            if (nums[mid] < nums[mid-1]){
                return nums[mid];
            }

            if (nums[mid] > nums[0]){
                left = mid+1;
            } else {
                right = mid-1;
            }
        }
        return -1;
    }
}
