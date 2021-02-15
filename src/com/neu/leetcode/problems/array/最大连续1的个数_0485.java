package com.neu.leetcode.problems.array;

public class 最大连续1的个数_0485 {
    //跟官方题解一样
    public int findMaxConsecutiveOnes(int[] nums) {
        int max = 0;
        if ( nums == null || nums.length == 0){
            return max;
        }
        int len = nums.length;
        int count =0;
        for (int i=0;i<len;i++){
            if (nums[i] == 1){
                count++;
            } else {
                max = Math.max(max,count);
                count = 0;
            }
        }
        max = Math.max(max,count);
        return max;
    }
}
