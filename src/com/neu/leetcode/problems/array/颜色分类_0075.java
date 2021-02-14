package com.neu.leetcode.problems.array;

import java.util.Arrays;

public class 颜色分类_0075 {
    public void sortColors(int[] nums) {
        Arrays.sort(nums);
    }

    //简单题解 就是第一次遍历对颜色进行计数

    //官方题解一 单指针
    public void sortColors1(int[] nums) {
        int n = nums.length;
        int ptr = 0;
        for (int i=0;i<n;i++){
            if (nums[i] == 0){
                int temp = nums[i];
                nums[i] = nums[ptr];
                nums[ptr] = temp;
                ptr++;
            }
        }

        for (int i=0;i<n;i++){
            if (nums[i] == 1){
                int temp = nums[i];
                nums[i] = nums[ptr];
                nums[ptr] = temp;
                ptr++;
            }
        }
    }


    //官方题解二 双指针
    public void sortColors2(int[] nums) {
        int n = nums.length;
        int p0=0,p1=0;

        for (int i=0;i<n;i++){
            if (nums[i] == 1){
                int temp = nums[i];
                nums[i] = nums[p1];
                nums[p1] = temp;
                p1++;
            } else if (nums[i] == 0){
                int temp = nums[i];
                nums[i] = nums[p0];
                nums[p0] = temp;
                if (p0<p1){
                    temp = nums[i];
                    nums[i] = nums[p1];
                    nums[p1] = temp;
                }
                p0++;
                p1++;
            }
        }

    }

    //官方题解三 双指针
    public void sortColors3(int[] nums) {
        int n = nums.length;
        int p0=0,p2=n-1;

        for (int i=0;i<=p2;i++){
            while (i<=p2 && nums[i] == 2){
                int temp = nums[i];
                nums[i] = nums[p2];
                nums[p2] = temp;
                p2--;
            }

            if (nums[i] == 0){
                int temp = nums[i];
                nums[i] = nums[p0];
                nums[p0] = temp;
                p0++;
            }
        }
    }

    //视频题解一
    public void sortColors4(int[] nums) {
        int n = nums.length;
        if (n < 2){
            return;
        }
        // all in [0,p0)==0
        // all in [p0,i)==1
        // all in (p2,n-1]==2
        int p0=0;
        int i = 0;
        int p2 =n-1;

        while (i<=p2){
            if (nums[i] == 0){
                swap(nums,i,p0);
                p0++;
                i++;
            } else if (nums[i] == 1){
                i++;
            } else {
                swap(nums,i,p2);
                p2--;
            }
        }
    }

    public void sortColors5(int[] nums) {
        int n = nums.length;
        if (n < 2){
            return;
        }
        // all in [0,p0]==0
        // all in (p0,i)==1
        // all in [p2,n-1]==2
        int p0=-1;
        int i = 0;
        int p2 =n;

        while (i<=p2){
            if (nums[i] == 0){
                p0++;
                swap(nums,i,p0);
                i++;
            } else if (nums[i] == 1){
                i++;
            } else {
                p2--;
                swap(nums,i,p2);

            }
        }
    }

    private void swap(int[] nums,int index1,int index2){
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }

}
