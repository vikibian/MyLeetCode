package com.neu.leetcode.problems.hashtable;

import java.util.Arrays;

public class 只出现一次的数字_0136 {

    public static void main(String[] args) {
//        int[] nums = {2,2,1};
        int[] nums = {4,1,2,1,2};
        System.out.println(singleNumber1(nums));
    }

    public int singleNumber(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length;
        int count = 0;
        boolean flag = true;
        for (int i=0;i<len-1;){
            if (nums[i] == nums[i+1]){
                i= i+2;
            } else {
                count = nums[i];
                flag = false;
                break;
            }
        }
        if (flag){
            count = nums[len-1];
        }

        return count;
    }

    //第一种方法 使用hashmap记录出现的个数 然后对map进行遍历

    //第二种方法 使用list进行遍历 出现一次就加入list中  再出现一次就把list中已经有的数字删除

    //第三种方法  异或
    public static int singleNumber1(int[] nums) {
        int single = 0;
        for (int num : nums){
            single ^= num;
        }

        return single;
    }
}
