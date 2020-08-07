package com.neu.leetcode.problems;

import java.util.HashMap;

public class 两数之和_0001 {
    public static void main(String[] args) {

    }
}

class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<int ,int> hashMap = new HashMap<int, int>();
        for (int i=0;i<nums.length;i++){
            for (int j=i+1;j<nums.length;j++){
                if (nums[i] + nums[j] == target){
                    hashMap.put(i,target);
                    hashMap.put(j,target);
                }
            }

        }

        int[] result = new int[];
        int index=0;
        for (int key :
                hashMap.keySet()) {
            result[index++] = key;
        }

        return result;
    }
}
