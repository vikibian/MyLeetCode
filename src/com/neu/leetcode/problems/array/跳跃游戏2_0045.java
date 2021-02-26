package com.neu.leetcode.problems.array;

import java.util.ArrayList;
import java.util.List;

public class 跳跃游戏2_0045 {

    public static void main(String[] args) {
        int[] nums = new int[]{2,3,1,1,4};
        System.out.println(jump(nums));
    }

    public static int jump(int[] nums) {
        if (nums == null || nums.length==0){
            return 0;
        }

        List<List<Integer>> lists = new ArrayList<>();
        int step = nums[0];

        for (int i=1;i<=step;i++){
            List<Integer> list = new ArrayList<>();
            list.add(i);
            getMinPath(lists,list,nums,i);
        }

        int max = Integer.MAX_VALUE;
        for (List<Integer> list : lists){
            max = Math.min(max,list.size());
        }

        return max;

//        int len = nums.length;
//        for (int i=0;i<len;i++){
//
//        }
    }

    public static void getMinPath(List<List<Integer>> lists,List<Integer> list,int[] nums,int index){
        if (index>= nums.length-1){
            lists.add(new ArrayList<>(list));
            return;
        }

        int step = nums[index];
        for (int i=1;i<=step;i++){
            if (index+i<nums.length){
                list.add(index+i);
                getMinPath(lists,list,nums,index+i);
                list.remove(list.size()-1);
            }

        }

    }

    //官方题解 反向查找出发位置
    public int jump1(int[] nums){
        int position = nums.length-1;
        int steps = 0;
        while (position>0){
            for (int i=0;i<position;i++){
                if (i+nums[i] >= position){
                    position=i;
                    steps++;
                    break;
                }
            }
        }
        return steps;
    }

    //贪心算法 正向查找可到达的最大位置
    public int jump2(int[] nums){
        int n = nums.length;
        int end = 0;
        int maxPosition =0;
        int steps =0;
        for (int i=0;i<n-1;i++){
            maxPosition = Math.max(maxPosition,i+nums[i]);
            if (end == i){
                end = maxPosition;
                steps++;
            }
        }

        return steps;

    }
}
