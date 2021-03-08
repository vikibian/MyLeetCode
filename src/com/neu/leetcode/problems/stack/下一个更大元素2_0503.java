package com.neu.leetcode.problems.stack;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class 下一个更大元素2_0503 {

    public static void main(String[] args) {
        int[] nums = {5,4,3,2,1};
        nextGreaterElements(nums);
        List<Integer> list = new LinkedList<>();

    }

    public static int[] nextGreaterElements(int[] nums) {
        int len = nums.length;
        int[] ans = new int[len];
        int index = 0;
        int res = -1;
        int count = 0;
        Deque<Integer> stack = new LinkedList<>();
        for (int i=0;i<len;i++){
            for (int j=0;j<len;j++){
                if (j<i){
                    stack.addLast(nums[j]);
                } else if (i == j){
                    index = nums[j];

                } else {
                    if (nums[j]>index && count<1){
                        count++;
                        res = nums[j];
                    }
                }
            }
            while (!stack.isEmpty()){
                int num = stack.removeFirst();
                if (num>index && count<1){
                    count++;
                    res = num;
                    break;
                }
            }
            ans[i] = res;
            index = 0;
            res = -1;
            count = 0;
        }
        return ans;
    }

    //官方题解 单调栈+循环数组
    public  int[] nextGreaterElements1(int[] nums) {
        int n= nums.length;
        int[] ret = new int[n];
        Arrays.fill(ret,-1);
        Deque<Integer> stack = new LinkedList<>();
        for (int i=0;i<n;i++){
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i%n]){
                ret[stack.pop()] = nums[i%n];
            }
            stack.push(i%n);
        }
        return ret;
    }
}
