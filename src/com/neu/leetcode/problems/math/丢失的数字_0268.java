package com.neu.leetcode.problems.math;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class 丢失的数字_0268 {
    public int missingNumber(int[] nums) {
        int len = nums.length;
        for (int i=0;i<len;i++){
            int n = nums[i];
            if (n != 0){
                int temp = nums[n-1];
                nums[n-1] = n;
                nums[i] = temp;
            }
            if (nums[i] != 0 && nums[i] != i+1){
                i--;
            }
        }

        for (int i=0;i<len;i++){
            if (nums[i] == 0){
                return (i+1);
            }
        }
        return 0;
    }

    //官方题解1  排序
    public int missingNumber1(int[] nums) {
        Arrays.sort(nums);
        //判断n是否出现在末尾
        if (nums[nums.length-1] != nums.length){
            return nums.length;
        }
        //判断0是否出现在首位
        else if (nums[0] != 0){
            return 0;
        }

        for (int i=1;i<nums.length;i++){
            int expectedNum = nums[i-1] + 1;
            if (expectedNum != nums[i]){
                return expectedNum;
            }
        }
        return -1;
    }

    //官方题解2 哈希表
    public int missingNumber2(int[] nums){
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums){
            numSet.add(num);
        }
        int expectedNum = nums.length +1;
        for (int i=0;i<expectedNum;i++){
            if (!numSet.contains(i)){
                return i;
            }
        }
        return -1;
    }

    //官方题解3 位运算
    public int missingNumber3(int[] nums){
        int missing = nums.length;
        for (int i=0;i<nums.length;i++){
            missing ^= i ^ nums[i];
        }
        return missing;
    }

    //官方题解4 数学
    public int missingNumber4(int[] nums){
        int expectedSum = nums.length * (nums.length + 1) /2;
        int actualSum = 0;
        for (int num : nums){
            actualSum += num;
        }
        return expectedSum-actualSum;
    }
}
