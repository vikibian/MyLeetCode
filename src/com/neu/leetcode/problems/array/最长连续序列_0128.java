package com.neu.leetcode.problems.array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class 最长连续序列_0128 {

    //true 困难  不满足o(n)
    public int longestConsecutive(int[] nums) {
        if (nums==null || nums.length ==0){
            return 0;
        }
        int len = nums.length;
        Arrays.sort(nums);
        int max = Integer.MIN_VALUE;
        int count = 1;
        int left = nums[0];
        for (int i=1;i<len;i++){
            if (nums[i] == (left+1)){
                count++;
                left = nums[i];
            } else if (nums[i] == left){
                continue;
            } else {
                max = Math.max(max,count);
                count=1;
                left = nums[i];
            }
        }

        max = Math.max(max,count);
        return max;
    }

    //官方题解 哈希表
    public int longestConsecutive1(int[] nums) {
        Set<Integer> num_set = new HashSet<>();
        for (int num : nums){
            num_set.add(num);
        }

        int longestStreak = 0;
        for (int num : num_set){
            if (!num_set.contains(num)){
                int currentNum = num;
                int currentStreak = 1;
                while (num_set.contains(currentNum+1)){
                    currentNum++;
                    currentStreak++;
                }
                longestStreak = Math.max(longestStreak,currentStreak);
            }
        }

        return longestStreak;
    }
}
