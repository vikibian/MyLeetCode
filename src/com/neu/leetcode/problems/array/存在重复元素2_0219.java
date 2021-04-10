package com.neu.leetcode.problems.array;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class 存在重复元素2_0219 {

    public static void main(String[] args) {
        int[] nums = new int[] {1,0,1,1};
        containsNearbyDuplicate(nums,1);
    }

    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        int len = nums.length;
        if(len == 0){
            return false;
        }
        for(int i=0;i<len;i++){
            int numi = nums[i];
//            for(int j=0;j<len ;j++){
//                if((Math.abs(i - j)<=k) && (i != j) && nums[j] == numi){
//                    return true;
//                }
//            }
            int left = (i-k <0 ? 0:i-k);
//            int right= (i+k>=len ? len-1:i+k);
            for (int j=left;j<=i;j++){
                if (i !=j && nums[j] == numi){
                    return true;
                }
            }
        }
        return false;
    }

    //官方题解 二叉搜索树 超时
    public boolean containsNearbyDuplicate1(int[] nums, int k) {
        Set<Integer> set = new TreeSet<>();
        for (int i=0;i<nums.length;i++){
            if (set.contains(nums[i])){
                return true;
            }
            set.add(nums[i]);
            if (set.size() > k){
                set.remove(nums[i-k]);
            }
        }
        return false;
    }

    //官方题解  散列表
    public boolean containsNearbyDuplicate2(int[] nums, int k){
        Set<Integer> set = new HashSet<>();
        for (int i=0;i<nums.length;i++){
            if (set.contains(nums[i])){
                return true;
            }
            set.add(nums[i]);
            if (set.size() > k){
                set.remove(nums[i-k]);
            }
        }
        return false;
    }

}
