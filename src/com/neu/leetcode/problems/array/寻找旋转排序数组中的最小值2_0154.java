package com.neu.leetcode.problems.array;

import java.util.Arrays;

public class 寻找旋转排序数组中的最小值2_0154 {

    //官方api  me
    public int findMin(int[] nums) {
        Arrays.sort(nums);
        return nums[0];
    }

    //遍历  me
    public int findMin1(int[] nums) {
        int len = nums.length;
        int min = nums[0];
        for (int i=0;i<len;i++){
            if (min > nums[i]){
                min = nums[i];
            }
        }
        return min;
    }

    //二分查找 me
    public int findMin2(int[] nums) {
        int len = nums.length;
        int left = 0,right = len-1;

        while (nums[left] == nums[right] && left<right ){
            left++;
        }

        while (nums[left == 0 ?0:left-1] == nums[right] && left<right && nums[right-1] < nums[right]){
            right--;
        }
        while (left < right){
            int mid = left+(right-left)/2;
            if (nums[mid]>nums[right]){
                left = mid+1;
            } else {
                right = mid;
            }
        }

        return nums[left];
    }

    //官方题解
    public int findMin3(int[] nums) {
        int len = nums.length;
        int low=0,high = len-1;
        while (low < high){
            int mid = low + (high-low)/2;
            if (nums[mid] > nums[high]){
                low = mid+1;
            } else if (nums[mid] < nums[high]){
                high = mid;
            } else {
                high--;
            }
        }

        return nums[low];
    }
}
