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

    //二次刷题
    public boolean search2(int[] nums, int target) {
        if (nums == null || nums.length == 0){
            return false;
        }
        int len = nums.length;
        int targetIndex = -1;
        for (int i=1;i<len;i++){
            if (nums[i] < nums[i-1]){
                targetIndex = i-1;
                break;
            }
        }
        System.out.println("targetIndex:"+targetIndex);
        if (targetIndex == -1){
            targetIndex = len-1;
        }
        int ans = 0;
        if (nums[0] <= target){
            ans = binarySearch(nums,targetIndex,target,0);
        } else {
            ans = binarySearch(nums,targetIndex,target,1);
        }

        return ans != -1;
    }

    private int binarySearch(int[] nums,int startIndex,int target,int type){
        int res= -1;
        int low = 0,high = 0;
        if (type == 0){
            low = 0;
            high = startIndex;
        } else {
            low = startIndex+1;
            high = nums.length-1;
        }

        while (low<= high){
            int mid = (low+high)/2;
            if (nums[mid] == target){
                return mid;
            } else if (nums[mid] > target){
                high = mid-1;
            } else {
                low = mid+1;
            }
        }
        return res;
    }

    //官方题解
    public boolean search3(int[] nums, int target) {
        int n = nums.length;
        if (n == 0){
            return false;
        }
        if (n == 1){
            return nums[0] == target;
        }
        int l =0,r = n-1;
        while (l<=r){
            int mid = (l+r)/2;
            if (nums[mid] == target){
                return true;
            }
            if (nums[l] == nums[mid] && nums[r] == nums[mid]){
                r--;
                l++;
            } else if (nums[l] <= nums[mid]){
                if (nums[l] <= nums[target] && target<nums[mid] ){
                    r = mid -1;
                } else {
                    l = mid+1;
                }
            } else {
                if (target>nums[mid] && target <= nums[n]){
                    l = mid +1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return false;
    }
}
