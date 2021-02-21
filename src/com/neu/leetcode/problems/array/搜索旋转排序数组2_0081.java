package com.neu.leetcode.problems.array;

import java.util.Arrays;

public class 搜索旋转排序数组2_0081 {
    public boolean search(int[] nums, int target) {
        Arrays.sort(nums);
        int left = 0,right=nums.length-1;
        while (left<=right){
            int mid = (left+right)/2;
            if (nums[mid] == target){
                return true;
            } else if (nums[mid] < target){
                left = mid+1;
            } else {
                right = mid-1;
            }
        }
        return false;
    }

    //题解 参考
    public boolean search1(int[] nums, int target) {
        if (nums == null || nums.length == 0){
            return false;
        }
        int start =0;
        int end = nums.length-1;
        int mid;
        while (start <= end){
            mid = start + (end-start)/2;
            if (nums[mid] == target){
                return true;
            }

            if (nums[start] == nums[mid]){
                start++;
                continue;
            }

            //前半部分有序
            if (nums[start] < nums[mid]){
                //target 在前半部分
                if (target<nums[mid] && nums[start] <= target){
                    end = mid-1;
                } else {
                    //否则去后半部分查找
                    start = mid + 1;
                }
            } else {
                //后半部分有序
                //target在后半部分
                if (nums[mid] < target && nums[end] >= target){
                    start = mid +1;
                } else {
                    //否则去后半部分查找
                    end = mid-1;
                }
            }
        }
        return false;
    }
}
