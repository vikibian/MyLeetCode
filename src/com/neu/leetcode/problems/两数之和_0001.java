package com.neu.leetcode.problems;

import java.util.HashMap;

public class 两数之和_0001 {
    public static void main(String[] args) {

    }
}

class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer ,Integer> hashMap = new HashMap<Integer, Integer>();
        for (int i=0;i<nums.length;i++){
            for (int j=i+1;j<nums.length;j++){
                if (nums[i] + nums[j] == target){
                    hashMap.put(i,target);
                    hashMap.put(j,target);
                }
            }

        }

        int[] result = new int[2];
        int index=0;
        for (int key :
                hashMap.keySet()) {
            result[index++] = key;
        }

        return result;
    }
}
