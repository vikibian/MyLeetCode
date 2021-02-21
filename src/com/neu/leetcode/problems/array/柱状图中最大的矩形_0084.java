package com.neu.leetcode.problems.array;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Stack;

public class 柱状图中最大的矩形_0084 {

     static int maxarea = 0;

    public static void main(String[] args) {
//        int[] nums = new int[]{2,1,5,6,2,3}; //10
//        int[] nums = new int[]{0,9}; //9
//        int[] nums = new int[]{2,1,2}; //3
//        int[] nums = new int[]{2,0,1,0,1,0}; //2
        int[] nums = new int[]{4,2,0,3,2,5}; //6
        System.out.println(largestRectangleArea(nums));
    }

    public static int largestRectangleArea(int[] heights) {
        int num = 0;
        if (heights == null){
            return 0;
        }
        int len = heights.length;
        int left = 0,right=len-1;
//        if (len % 2==0){
//            getLargestArea(right/2,(right/2 +1),heights,Math.min(heights[right/2],heights[(right/2+1)]));
//        } else {
//
//        }
        getLargestArea(left,left,heights, heights[left]);

//        for (int i=0;i<len;i++){
//            maxarea = Math.max(maxarea,heights[i]);
//        }

        return maxarea;
    }

    private static void getLargestArea(int left, int right, int[] heights, int min) {

        int area = 0;
        area = (right-left+1)*min;
        maxarea= Math.max(area,maxarea);
        int len = heights.length;




        if (right+1<len){
            int small = Math.min(min,heights[right+1]);
            getLargestArea(left,right+1,heights,small);
        }

        if (left -1 >=0){
            int  small = Math.min(min,heights[left-1]);
            getLargestArea(left-1,right,heights,small);
        }

    }

    //暴力遍历 宽
    public  int largestRectangleArea1(int[] heights) {
        int len = heights.length;
        int ans = 0;
        for (int left=0;left<len;left++){
            int minHeight= Integer.MAX_VALUE;
            for (int rigth= left;rigth<len;rigth++){
                minHeight = Math.min(heights[rigth],minHeight);
                ans = Math.max(ans,(rigth - left+1)*minHeight);
            }
        }

        return ans;
    }


    //暴力遍历 高
    public  int largestRectangleArea2(int[] heights) {
        int len = heights.length;
        int ans = 0;
        for (int mid=0;mid<len;mid++){
            int height = heights[mid];
            int left = mid,right=mid;
            //确定左右边界
            while (left-1>=0&&heights[left-1] >= height){
                left--;
            }
            while (right+1<len && heights[height+1] >= height){
                right++;
            }
            //计算面积
            ans = Math.max(ans,(right-left+1)*height);
        }

        return ans;
    }

    //单调栈
    public  int largestRectangleArea3(int[] heights) {
        int n = heights.length;
        int[] left = new int[n];
        int[] right = new int[n];

        Stack<Integer> mono_stack = new Stack<>();
        for (int i=0;i<n;i++){
            while (!mono_stack.isEmpty() && heights[mono_stack.peek()]>=heights[i]){
                mono_stack.pop();
            }
            left[i] = (mono_stack.isEmpty()?-1:mono_stack.peek());
            mono_stack.push(i);
        }

        mono_stack.clear();
        for (int i=n-1;i>=0;i--){
            while (!mono_stack.isEmpty() && heights[mono_stack.peek()] >= heights[i]){
                mono_stack.pop();
            }
            right[i] = (!mono_stack.isEmpty()?n:mono_stack.peek());
            mono_stack.push(i);
        }

        int ans =0;
        for (int i=0;i<n;i++){
            ans = Math.max(ans, (right[i]-left[i]-1)*heights[i]);
        }
        return ans;
    }

    //单调栈 + 常数优化
    public  int largestRectangleArea4(int[] heights) {
        int n = heights.length;
        int[] left = new int[n];
        int[] right= new int[n];
        Arrays.fill(right,n);
        Stack<Integer> mono_stack = new Stack<>();
        for (int i=0;i<n;i++){
            while (!mono_stack.isEmpty() && heights[mono_stack.peek()] >= heights[i]){
                right[mono_stack.peek()] = i;
                mono_stack.pop();
            }
            left[i] = (mono_stack.isEmpty()?-1:mono_stack.peek());
            mono_stack.push(i);
        }
        int ans =0;
        for (int i=0;i<n;i++){
            ans = Math.max(ans,(right[i]-left[i]-1)*heights[i]);
        }
        return ans;
    }

    //视频题解
    public  int largestRectangleArea5(int[] heights) {
        int len = heights.length;
        if (len == 0){
            return 0;
        }
        if (len == 1){
            return heights[0];
        }

        int area = 0;
        int[] newHeights = new int[len+2];
        for (int i=0;i<len;i++){
            newHeights[i+1] = heights[i];
        }
        len += 2;
        heights = newHeights;
        Deque<Integer> stack = new ArrayDeque<>();
        stack.addLast(0);

        for (int i=1;i<len;i++){
            while (heights[stack.peekLast()] > heights[i]){
                int height = heights[stack.removeLast()];
                int width = i - stack.peekLast()-1;
                area = Math.max(area,width*height);
            }
            stack.addLast(i);
        }

        return area;
    }
}
