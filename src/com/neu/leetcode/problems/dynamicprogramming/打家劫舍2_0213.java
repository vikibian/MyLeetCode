package com.neu.leetcode.problems.dynamicprogramming;

import java.util.Arrays;

public class 打家劫舍2_0213 {
    //官方题解
    public int rob(int[] nums){
        int len = nums.length;
        if (len == 1){
            return nums[0];
        } else if (len == 2){
            return Math.max(nums[0],nums[1]);
        }
        return Math.max(robrange(nums,0,len-2),robrange(nums,1,len-1));
    }

    private int robrange(int[] nums, int start, int end) {
        int first = nums[start],second = Math.max(nums[start],nums[start+1]);
        for (int i=start+2;i<=end;i++){
            int temp = second;
            second = Math.max(first+nums[i],second);
            first = temp;
        }

        return second;
    }

    //精选题解
    public int rob1(int[] nums){
        int len = nums.length;
        if (len == 1){
            return nums[0];
        } else if (len == 2){
            return Math.max(nums[0],nums[1]);
        }
        return Math.max(myRob(Arrays.copyOfRange(nums,0,len-1)),myRob(Arrays.copyOfRange(nums,1,len)));
    }

    private int myRob(int[] nums) {
        int pre=0,cur=0,temp=0;
        for (int num : nums){
            temp = cur;
            cur = Math.max(pre+num,cur);
            pre = temp;
        }
        return cur;
    }
}
