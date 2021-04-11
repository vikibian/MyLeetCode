package com.neu.leetcode.problems.array;

public class 移动零_0283 {
    public void moveZeroes(int[] nums) {
        int len = nums.length;
        int index1=0,index2=0;
        while(index2<len){
            if(nums[index2]!=0){
                nums[index1] = nums[index2];
                index1++;
            }
            index2++;
        }

        while(index1 < len){
            nums[index1++] = 0;
        }
    }

    //官方题解 双指针
    public void moveZeroes1(int[] nums) {
        int n=nums.length,left=0,right=0;
        while (right<n){
            if (nums[right] != 0){
                swap(nums,left,right);
                left++;
            }
            right++;
        }
    }

    private void swap(int[] nums,int left,int right){
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }
}
