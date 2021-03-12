package com.neu.leetcode.problems.hashtable;

import java.util.Arrays;

public class 只出现一次的数字_0136 {
    public int singleNumber(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length;
        int count = 0;
        boolean flag = true;
        for (int i=0;i<len-1;){
            if (nums[i] == nums[i+1]){
                i= i+2;
            } else {
                count = nums[i];
                flag = false;
                break;
            }
        }
        if (flag){
            count = nums[len-1];
        }

        return count;
    }
}
