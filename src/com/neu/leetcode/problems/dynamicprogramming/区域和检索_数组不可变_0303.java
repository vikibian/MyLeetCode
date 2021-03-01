package com.neu.leetcode.problems.dynamicprogramming;

public class 区域和检索_数组不可变_0303 {
    //true 简单
    class NumArray {
        private int[] nums;
        private int len=0;
        public NumArray(int[] nums) {
            this.nums = nums;
            this.len = nums.length;
        }

        public int sumRange(int i, int j) {
            int sum =0;
            for (int k=i;k<=j;k++){
                sum += this.nums[k];
            }

            return sum;
        }
    }

    //前缀和
    class NumArray1{
        int[] sums;

        public NumArray1(int[] nums){
            int n = nums.length;
            sums = new int[n+1];
            for (int i=0;i<n;i++){
                sums[i+1] = sums[i]+nums[i];
            }
        }

        public int sumRange(int i,int j){
            return sums[j+1] - sums[i];
        }

    }
}
