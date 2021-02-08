package com.neu.leetcode.problems.array;

import java.util.Arrays;

public class 缺失的第一个正数_0041 {
    public int firstMissingPositive(int[] nums) {
        int ans =1;
        if (nums == null){
            return ans;
        }
        Arrays.sort(nums);
        int len = nums.length;
        for (int i=0;i<len;i++){
            if(nums[i] <= 0 && nums[i] < ans){

            } else if (nums[i] == ans){
                ans++;
            } else if (nums[i] >0 && nums[i] > ans){
                break;
            }
        }

        return ans;
    }

    //官方题解 哈希数组  可以把小于0 大于n的全部设置为1
    public int firstMissingPositive1(int[] nums) {
        int n = nums.length;
        for (int i=0;i<n;i++){
            if (nums[i] < 0){
                nums[i] = n+1;
            }
        }
        for (int i=0;i<n;i++){
            int num = Math.abs(nums[i]);
            if (num<n){
                nums[n-1] = -Math.abs(nums[num - 1]);
            }
        }

        for (int i=0;i<n;i++){
            if (nums[i] >0){
                return i+1;
            }
        }

        return n+1;
    }

    //置换
    public int firstMissingPositive2(int[] nums) {
        int n = nums.length;
        for (int i=0;i<n;i++){
            while (nums[i] >0 && nums[i]<=n &&nums[nums[i]-1] != nums[i]){
                int temp = nums[nums[i]-1];
                nums[nums[i]-1] = nums[i];
                nums[i] = temp;
            }
        }

        for (int i=0;i<n;i++){
            if (nums[i] != i){
                return i+1;
            }
        }

        return n+1;
    }
}
