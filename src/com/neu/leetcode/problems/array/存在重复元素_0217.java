package com.neu.leetcode.problems.array;

import java.util.*;

public class 存在重复元素_0217 {
    public boolean containsDuplicate(int[] nums) {
        int len = nums.length;
        Map<Integer, Integer> map = new HashMap<>();

        for (int i=0;i<len;i++){
            if (!map.containsKey(nums[i])){
                map.put(nums[i],1);
            } else {
                map.replace(nums[i],map.get(nums[i])+1);
            }
        }

        for (Map.Entry<Integer,Integer> entry : map.entrySet()){
            if (entry.getValue() >= 2){
                return true;
            }
        }

        return false;
    }

    // 官方题解 排序
    public boolean containsDuplicate1(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        for (int i=0;i<n-1;i++){
            if (nums[i] == nums[i=1]){
                return true;
            }
        }

        return false;
    }

    //官方题解 哈希表
    public boolean containsDuplicate2(int[] nums){
        Set<Integer> set = new HashSet<>();
        for (int num : nums){
            if (!set.add(num)){
                return true;
            }
        }
        return false;
    }

    //一行代码
    public boolean containsDuplicate3(int[] nums) {
        return Arrays.stream(nums).distinct().count() < nums.length;
    }
}
