package com.neu.leetcode.problems.array;

import java.util.Arrays;

public class 旋转数组_0189 {
    public void rotate(int[] nums, int k) {
        // -1 -100 3 99
        int len = nums.length;
        if (len == 1 || k % len == 0){
            return;
        }
        //超时
//        for (int i=0;i<(k%len);i++){
//            int temp = nums[len-1];
//            for (int j=0;j<len;j++){
//                int t = nums[j];
//                nums[j] = temp;
//                temp = t;
//            }
//        }
        k = k%len;
        int temp = nums[len-k];
        int index = 0;
        for (int j=0;j<len;j++){
            int t = nums[index];
            nums[index] = temp;
//            if (index + k == len && (index+k)%len == 0){
            if ((index+k)>=len && len %k ==0  ){
                index = ((index+k)%len )+1;
                temp = nums[len-index];
                System.out.println(Arrays.toString(nums));
                System.out.println(index);
                System.out.println(temp);
                continue;
            } else {
                index = (index+k)%len;
            }

            temp = t;
            System.out.println(Arrays.toString(nums));
            System.out.println(index);
            System.out.println(temp);
        }

        nums[index] = temp;
        // 3 -100 3 99

    }

    // 官方题解一  使用额外数组
    public void rotate1(int[] nums, int k) {
        int n = nums.length;
        int[] newArr = new int[n];
        for (int i = 0; i < n; ++i) {
            newArr[(i + k) % n] = nums[i];
        }
        System.arraycopy(newArr, 0, nums, 0, n);
    }

    //官方题解二 环状替换
    public void rotate2(int[] nums, int k){
        int n = nums.length;
        k = k%n;
        int count = gcd(k,n);
        for (int i=0;i<count;i++){
            int current = i;
            int prev = nums[current];
            do {
                int next = (current+k)%n;
                int temp = nums[next];
                nums[next] = prev;
                prev = temp;
                current  = next;
            } while (current != i);
        }

    }

    public int gcd(int x,int y){
        return y>0?gcd(y,x%y):x;
    }

    //官方题解三  数组翻转
    public void rotate3(int[] nums,int k){
        k %= nums.length;
        reverse(nums,0,nums.length-1);
        reverse(nums,0,k-1);
        reverse(nums,k,nums.length-1);
    }

    public void reverse(int[] nums,int start,int end){
        while (start < end){
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start += 1;
            end -= 1;
        }
    }
}
