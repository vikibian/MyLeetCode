package com.neu.leetcode.problems.array;

import java.util.Arrays;

public class 合并两个有序数组_0088 {

    public static void main(String[] args) {
        int[] nums1 = new int[]{1};
        int[] nums2 = new int[]{};
        merge(nums1,1,nums2,0);
        System.out.println(nums1.toString());
    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        for (int i=m;i<m+n;i++){
            nums1[i] = nums2[i-m];
        }

        Arrays.sort(nums1);
    }

    public static void merge1(int[] nums1, int m, int[] nums2, int n) {
        int[] nums = new int[m];
        for (int i=m;i<m+n;i++){
            nums1[i] = nums2[i-m];
        }

        Arrays.sort(nums1);
    }

    //官方题解一  合并后再排序
    public void  merge2(int[] nums1,int m,int[] nums2,int n){
        System.arraycopy(nums2,0,nums1,m,n);
        Arrays.sort(nums1);
    }

    //官方题解二  双指针/从前往后
    public void  merge3(int[] nums1,int m,int[] nums2,int n){
        int[] nums_copy = new int[m];
        System.arraycopy(nums1,0,nums_copy,0,m);
        int p1=0,p2=0;
        int count =0;

        while (p1<m && p2 <n){
            nums1[count++] = nums_copy[p1] <nums2[p2] ? nums_copy[p1++]:nums2[p2++];
        }

        if (p1<m){
            System.arraycopy(nums_copy,p1,nums1,p1+p2,m+n-p1-p2);
        }

        if (p2<n){
            System.arraycopy(nums2,p2,nums1,p1+p2,m+n-p1-p2);
        }
    }

    //官方题解三  双指针/从后往前
    public void  merge4(int[] nums1,int m,int[] nums2,int n){
        int p1=m-1,p2=n-1;
        int count =m+n-1;

        while (p1>=0 && p2 >=0){
            nums1[count--] = nums1[p1] <nums2[p2] ? nums2[p2--]:nums1[p1--];
        }


        System.arraycopy(nums2,0,nums1,0,p2+1);
    }

}
