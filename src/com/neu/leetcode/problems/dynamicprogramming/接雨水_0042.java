package com.neu.leetcode.problems.dynamicprogramming;

import java.util.Deque;
import java.util.LinkedList;

public class 接雨水_0042 {

//    public int trap(int[] height) {
//        int sum = 0;
//        int len = height.length;
//        int left,right=0;
//        for (int i=0;i<len;i = right){
//
//            if (i>0 &i<len-1){
//                left = i-1;
//                right = i+1;
//                int preleft = height[i];
//                int preright = height[i];
//                int subSum = 0;
//                while ((left > 0 && height[left] > height[i])
//                        && (right<len && height[right]>=height[i])
//                        && (height[left] >=preleft)
//                        && (height[right] >= preright)
//                ){
//                    subSum = subSum ;
//                    preleft = height[left];
//                    preright = height[right];
//                    left--;
//                    right++;
//                }
//            }
//        }
//
//
//        return sum;
//    }

    public static void main(String[] args) {
//        int[] nums = {0,1,0,2,1,0,1,3,2,1,2,1};
        int[] nums = {1,0,2};
//        int[] nums = {4,2,1,2};
        System.out.println(trap(nums));
    }

    public static int trap(int[] height) {
        int sum = 0;
        int len = height.length;
        int left =0,right=0;
        int preright =0,mid=0;
        int sum1=0,sum2 = 0;
        while (right<len){
            //阶梯状递增
            if (height[right] >=height[left]){
                if (right == len -1){
                    for (int i = left;i<=right;i++){
                        sum1 = sum1 + height[i];
                    }
                } else {
                    sum1 = sum1 + (right-left+1)*height[left];
                    left = right;
                }
            } else if (height[right] <= height[left]){
                if (right == len -1){
                    for (int i = left;i<=right;i++){
                        sum1 = sum1 + height[i];
                    }
                }

                if (height[preright] <height[right]){
                    if ((right+1 < len) && height[right+1]>=height[right]){
                        preright = right;
                        right++;
                        continue;
                    } else if ((right+1 < len) && height[right+1]<height[right]){
                        sum1 = sum1 + (right - left -1)*height[right] +(height[left] - height[right]);
                        left = right;
                    }
                }
            }
            preright = right;
            right++;
        }

        for (int j=0;j<len;j++){
            sum2 = sum2 + height[j];
        }

        System.out.println(sum1);
        System.out.println(sum2);

        return sum1-sum2;
    }


    //官方题解 暴力解法
    public int trap1(int[] height){
        int ans = 0;
        int size = height.length;
        for (int i=1;i<size-1;i++){
            int max_left =0,max_right = 0;
            for (int j=i;j>=0;j--){
                max_left = Math.max(max_left,height[j]);
            }

            for (int m=i;m<size;m++){
                max_right = Math.max(max_right,height[m]);
            }
            ans = ans + Math.min(max_left,max_right)-height[i];
        }

        return ans;
    }

    //解法二  动态规划
    public int trap2(int[] height){
        if (height == null || height.length == 0){
            return 0;
        }
        int ans = 0;
        int len = height.length;
        int[] left_max = new int[len];
        int[] right_max = new int[len];
        left_max[0] = height[0];
        for (int i=1;i<len;i++){
            left_max[i] = Math.max(height[i],left_max[i-1]);
        }
        right_max[len-1] = height[len-1];
        for (int i=len-2;i>=0;i--){
            right_max[i] = Math.max(height[i],right_max[i+1]);
        }

        for (int i=0;i<len;i++){
            ans = ans + Math.min(left_max[i],right_max[i])-height[i];
        }

        return ans;
    }

    //使用栈
    public int trap3(int[] height){
        int ans =0;
        Deque<Integer> deque = new LinkedList<>();
        int curretIndex = 0,len = height.length;
        while (curretIndex<len){
            while (!deque.isEmpty() && height[curretIndex]>height[deque.peek()]){
                int top = deque.pop();
                if (deque.isEmpty()){
                    break;
                }
                int distance = curretIndex - deque.peek()-1;
                int bounded_height = Math.min(height[curretIndex],deque.peek()) - height[top];
                ans = ans + distance* bounded_height;
            }
            deque.push(curretIndex++);
        }


        return ans;
    }

    //双指针
    public int trap4(int[] height){
        int ans = 0;
        int left =0,right = height.length;
        int left_max = 0,right_max=0;
        while (left<right){
            if (height[left] < height[right]){
                if (height[left] >= left_max){
                    left_max = height[left];
                } else {
                    ans = ans + (left_max-height[left]);
                }
                left++;
            }else {
                if (height[right]>=right_max){
                    right_max = height[right];
                } else {
                    ans =ans + (right_max - height[right]);
                }

                right--;
            }
        }

        return ans;
    }

    //我的解法二
    public int trap5(int[] height) {
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
}
