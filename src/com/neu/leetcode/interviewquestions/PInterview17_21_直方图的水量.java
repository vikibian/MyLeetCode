package com.neu.leetcode.interviewquestions;

import java.util.Deque;
import java.util.LinkedList;

public class PInterview17_21_直方图的水量 {
    //这个题与42题相同
    public int trap(int[] height) {
        if (height == null || height.length == 0){
            return 0;
        }
        int len = height.length;
        Deque<Integer> stack = new LinkedList<>();
        stack.push(0);
        int index = 0;
        int memo = 0;
        int ans = 0;
        for (int i=0;i<len;i++){
            int num = height[i];
            if (num >= index){
                int min = Math.min(index,num);
                while (stack.peek() != index){
                    ans += (min - stack.pop());
                }
                stack.pop();
                index = num;
                memo = i;
                stack.push(num);
            } else {
                stack.push(num);
            }
//            System.out.println("i:"+i+"  stack:"+stack.toString());
        }
        //需要判断index和height的大小
        if (memo != len-1){
            System.out.println(false);
            Deque<Integer> stack2 = new LinkedList<>();
            stack2.push(0);
            int index2 = 0;

            while (!stack.isEmpty()){
                int num = stack.pop();
                if (num >= index2){
                    int min = Math.min(index2,num);
                    while (stack2.peek() != index2){
                        ans += (min - stack2.pop());
                    }
                    stack2.pop();
                    index2 = num;
                    stack2.push(num);
                } else {
                    stack2.push(num);
                }
            }
        } else {
            System.out.println(true);
        }
        System.out.println(stack.toString());
        return ans;
    }

    //官方题解1 动态规划
    public int trap1(int[] height){
        int n= height.length;
        if (n == 0){
            return 0;
        }
        int[] leftMax = new int[n];
        leftMax[0] = height[0];
        for (int i=1;i<n;i++){
            leftMax[i] = Math.max(leftMax[i-1],height[i]);
        }
        int[] rightMax = new int[n];
        rightMax[n-1] = height[n-1];
        for (int i=n-2;i>=0;i--){
            rightMax[i] = Math.min(rightMax[i+1],height[i]);
        }
        int ans = 0;
        for (int i=0;i<n;i++){
            ans += Math.min(leftMax[i],rightMax[i] )-height[i];
        }

        return ans;
    }

    //官方题解2  栈
    public int trap2(int[] height){
        int ans = 0;
        Deque<Integer> stack = new LinkedList<>();
        int n = height.length;
        for (int i=0;i<n;i++){
            while (!stack.isEmpty() && height[i] > height[stack.peek()]){
                int top = stack.pop();
                if (stack.isEmpty()){
                    break;
                }
                int left = stack.peek();
                int curWidth = i -left+1;
                int curHeight = Math.min(height[left],height[i]) - height[top];
                ans += curHeight*curWidth;
            }
            stack.push(i);
        }
        return ans;
    }

    //官方题解3 双指针
    public int trap3(int[] height){
        int ans = 0;
        int left =0,right = height.length-1;
        int leftMax =0,rightMax = 0;
        while (left < right){
            leftMax = Math.max(leftMax,height[left]);
            rightMax = Math.max(rightMax,height[right]);
            if (height[left] < height[right]){
                ans += leftMax-height[left];
                left++;
            } else {
                ans += rightMax-height[right];
                right--;
            }
        }

        return ans;
    }
}
