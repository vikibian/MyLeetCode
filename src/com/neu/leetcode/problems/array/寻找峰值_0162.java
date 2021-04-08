package com.neu.leetcode.problems.array;

public class 寻找峰值_0162 {

    public int findPeakElement(int[] nums) {
        int len = nums.length;
        if (len == 1){
            return 0;
        }
        for (int i=0;i<len;i++){
            int left=Integer.MIN_VALUE;
            int right = Integer.MIN_VALUE;
            if (i-1 != -1){
                left = nums[i-1];
            }
            if (i+1 != len){
                right = nums[i+1];
            }
            int mid = nums[i];
            if (mid>left && right<mid){
                return i;
            }
        }


        return -1;
    }

    //官方题解一 线性扫描
    public int findPeakElement1(int[] nums) {
        for (int i=0;i<nums.length-1;i++){
            if (nums[i] > nums[i+1]){
                return i;
            }
        }
        return nums.length-1;
    }

    //官方题解二 递归二分查找
    public int findPeakElement2(int[] nums){
        return search(nums,0,nums.length-1);
    }

    private int search(int[] nums, int l, int r) {
        if (l == r){
            return l;
        }
        int mid = (l+r)/2;
        if (nums[mid] > nums[mid+1]){
            return search(nums,l,mid);
        } else {
            return search(nums,mid+1,r);
        }
    }

    //官方题解三  迭代二分查找
    public int findPeakElement3(int[] nums){
        int l=0,r = nums.length-1;
        while (l < r){
            int mid = (l+r)/2;
            if (nums[mid] > nums[mid+1]){
                r = mid;
            } else {
                l = mid+1;
            }
        }
        return l;
    }
}
