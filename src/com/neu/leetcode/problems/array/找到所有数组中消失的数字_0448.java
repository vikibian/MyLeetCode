package com.neu.leetcode.problems.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 找到所有数组中消失的数字_0448 {

    public static void main(String[] args) {
//        int[] nums = new int[] {4,3,2,7,8,2,3,1};
        int[] nums = new int[] {1,1};
//        System.out.println(findDisappearedNumbers(nums).toString());
        System.out.println(findDisappearedNumbers1(nums).toString());
    }

//    public static List<Integer> findDisappearedNumbers(int[] nums) {
//        List<Integer> list = new ArrayList<>();
//        if (nums == null || nums.length == 0){
//            return list;
//        }
//        Arrays.sort(nums);
//        int len = nums.length;
//        int temp = nums[0];
//        int num = 0;
//        for (int i=0;i<len;i++){
//            if (nums[i] == (num+1)){
//                num++;
//            } else if (nums[i] == num){
//
//            } else {
//                list.add(num+1);
//                num++;
//            }
//        }
//        return list;
//    }

    public static List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> list = new ArrayList<>();
        if (nums == null || nums.length == 0){
            return list;
        }
        int[] tempNums = new int[nums.length];
        for (int i=0;i<nums.length;i++){
            int index = nums[i];
            tempNums[index-1] = 1;
        }

        for (int i=0;i<tempNums.length;i++){
            if (tempNums[i] == 0){
                list.add(i+1);
            }
        }
        return list;
    }

    //原地修改
    public static List<Integer> findDisappearedNumbers1(int[] nums) {
        int n = nums.length;
        for (int num : nums) {
            int x = (num - 1) % n;
            nums[x] += n;
        }
        List<Integer> ret = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            if (nums[i] <= n) {
                ret.add(i + 1);
            }
        }
        return ret;
    }


}
