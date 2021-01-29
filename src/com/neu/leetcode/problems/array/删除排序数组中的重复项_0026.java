package com.neu.leetcode.problems.array;

public class 删除排序数组中的重复项_0026 {
    public int removeDuplicates(int[] nums) {

        int firstIndex = 0;
        int count = 0;
        int length = nums.length;
        if (length == 0){
            return 0;
        }
        if (length == 1){
            return 1;
        }
        for (int i=0;(i+1)<length;i++){
            if (nums[i] != nums[i+1]){
                nums[firstIndex] = nums[i];
                firstIndex = firstIndex +1;
            } else {
                firstIndex = firstIndex+1;
            }

        }

        if (nums[firstIndex] != nums[length-1] ){
            firstIndex = firstIndex +1;
            nums[firstIndex] = nums[length-1];
        }

        return firstIndex+1;
    }


    //官方题解 双指针
    public int removeDuplicates1(int[] nums) {
        int i=0;
        for (int j=1;j<nums.length;j++){
            if (nums[i] != nums[j]){
                i++;
                nums[i] = nums[j];
            }
        }
        return i+1;
    }
}
