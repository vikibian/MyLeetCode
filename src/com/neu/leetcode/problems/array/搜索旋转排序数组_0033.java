package com.neu.leetcode.problems.array;

public class 搜索旋转排序数组_0033 {
    //暴力解法
    public int search(int[] nums, int target) {
        int len = nums.length;
        for (int i=0 ;i<len;i++){
            if (nums[i] == target){
                return i;
            }
        }
        return -1;
    }

    //改进的二分查找算法   也可以将下面的算法改进成迭代形式的算法 见题解视频
    public int search1(int[] nums,int target){
        int n = nums.length;
        if (n == 0){
            return -1;
        }

        if (n==1){
            return nums[0] == target ? 0: -1;
        }
        int l=0,r=n-1;
        while (l<=r){
            int mid = (l+r) /2;
            if (nums[mid] == target){
                return mid;
            }
            if (nums[0] <= nums[mid]){
                if (nums[0] <= target && nums[mid] >target){
                    r = mid-1;
                } else {
                    l = mid +1;
                }
            } else {
                if (nums[mid] < target && target <= nums[n-1]){
                    l = mid +1;
                } else {
                    r = mid-1;
                }
            }
        }
        return -1;
    }
}
