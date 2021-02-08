package com.neu.leetcode.problems.array;

public class 搜索插入位置_0035 {

    public static void main(String[] args) {
        int[] nums = new int[] {1,3,5,6};
        System.out.println(searchInsert(nums,5));
    }

    public static int searchInsert(int[] nums, int target) {
        int ans = 0;
        int len = nums.length;
        int left = 0,right= len-1;
        while (left<=right){
            int mid = (left + right) /2;
            if (nums[mid] < target){
                left = mid + 1;
            } else if (nums[mid] == target){
                return mid;
            } else {
                right = mid -1;
            }
        }

        if (left > right){
            ans = left;
        }
        return ans;
    }

    //官方题解[在一个有序数组中找第一个大于等于 \textit{target}target 的下标」
//    int n = nums.length;
//    int left = 0, right = n - 1, ans = n;
//        while (left <= right) {
//        int mid = ((right - left) >> 1) + left;
//        if (target <= nums[mid]) {
//            ans = mid;
//            right = mid - 1;
//        } else {
//            left = mid + 1;
//        }
//    }


}
