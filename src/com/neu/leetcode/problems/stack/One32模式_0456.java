package com.neu.leetcode.problems.stack;

import java.util.Deque;
import java.util.LinkedList;
import java.util.TreeMap;

public class One32模式_0456 {

    public static void main(String[] args) {
//        int[] nums = {1,4,0,-1,-2,-3,-1,-2};
        int[] nums = {-1,0,1,-4,-3};
        System.out.println(find132pattern(nums));
    }

    public static boolean find132pattern(int[] nums) {
        if (nums == null || nums.length <3){
            return false;
        }
        Deque<Integer> stack = new LinkedList<>();
        Deque<Integer> stock = new LinkedList<>();
        boolean flag = false;
        for (int i=0;i< nums.length;i++){
            if (i == nums.length-1){
                flag = true;
            }
            int num = nums[i];
            if (stack.isEmpty()){
                stack.addLast(num);
            }else {
                int top = stack.peekLast();
                if (top < num){
                    stack.addLast(num);
                } else if (top > num) {
                    if (stack.size() < 2){
                        stack.removeFirst();
                        stack.addLast(num);
                    } else {
                        int left = stack.peekFirst();
                        if (left<num){
                            return true;
                        } else {
                            stock.addLast(i);
                        }
                    }
                }
            }

            if (flag && !stock.isEmpty()){
                i = stock.pollFirst()-1;
                flag = false;
                stack.clear();
            }
        }
        return false;
    }

    //官方题解一 枚举3
    public  boolean find132pattern1(int[] nums) {
        int n = nums.length;
        if (nums == null || nums.length <3){
            return false;
        }
        int leftMin = nums[0];
        TreeMap<Integer,Integer> treeMap = new TreeMap<>();
        for (int k=2;k<n;k++){
            treeMap.put(nums[k],treeMap.getOrDefault(nums[k],0)+1);
        }

        for (int j =1;j<n;j++){
            if (leftMin<nums[j]){
                Integer next = treeMap.get(leftMin+1);
                if (next != null && next < nums[j]){
                    return true;
                }
            }
            leftMin = Math.min(leftMin,nums[j]);
            treeMap.put(nums[j+1],treeMap.get(nums[j+1])-1);
            if (treeMap.get(nums[j+1]) == 0){
                treeMap.remove(nums[j+1]);
            }
        }

        return false;
    }

    //官方题解 枚举2
    public  boolean find132pattern2(int[] nums) {
        int n = nums.length;
        Deque<Integer> candidatek = new LinkedList<>();
        candidatek.push(nums[n-1]);
        int maxK = Integer.MIN_VALUE;
        for (int i=n-2;i>=0;i--){
            if (nums[i] < maxK){
                return true;
            }
            while (!candidatek.isEmpty() && nums[i] > maxK){
                maxK = candidatek.poll();
            }
            if (nums[i] > maxK){
                candidatek.push(nums[i]);
            }
        }
        return false;
    }
}
