package com.neu.leetcode.problems.array;

import java.lang.reflect.Array;
import java.util.Arrays;

public class 数组拆分1_0561 {
    //官方题解
    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int sum = 0;
        int len = nums.length;
        for (int i=0;i<len;i+=2){
            sum = sum + Math.min(nums[i],nums[i+1]);
        }

        return sum;
    }
}
