package com.neu.leetcode.problems;

import java.util.Arrays;

public class 寻找两个正序数组的中位数_0004 {
    public static void main(String[] args) {
        int[] nums1 = {1,2};
        int[] nums2 = {3,4};
        double medianSortedArrays = findMedianSortedArrays(nums1, nums2);
        System.out.println("result:"+medianSortedArrays);
//        int a =3;
//        int b=4;
//        System.out.println("test:"+(double)(a+b)/2);

    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        double result = 0;
        int length1 = nums1.length;
        int length2 = nums2.length;
        if (length1 == 0){
            if (length2 % 2 ==1){
                int index = length2 /2;
                result = nums2[index];
            }else {
                result = (double)(nums2[length2/2]+nums2[(length2/2)-1])/2;
            }
        }

        if (length2 == 0){
            if (length1 % 2 ==1){
                int index = length1 /2;
                result = nums1[index];
            }else {
                result =(double) (nums1[length1/2]+nums1[(length1/2)-1])/2;
            }
        }

        int[] sum = new int[length1+length2];
        int pointer1 = 0;
        int pointer2 = 0;
        for (int i=0;i<length1+length2;i++){
            if ((nums1[pointer1] <= nums2[pointer2])){
                if (i -  length1 < pointer2){
                    sum[i] = nums1[pointer1];
                    if (pointer1 != length1 - 1){
                        pointer1++;
                    }

                }else {
                    sum[i] = nums2[pointer2];
                    if (pointer2 != length2-1 ){
                        pointer2++;
                    }

                }

            }else {

                if (i - length2 < pointer1){
                    sum[i] = nums2[pointer2];
                    if (pointer2 != length2 - 1){
                        pointer2++;
                    }

                }else {
                    sum[i] = nums1[pointer1];
                    if (pointer1 != length1 - 1){
                        pointer1++;
                    }

                }

            }
        }

        if ((length1 + length2) % 2 ==1){
            result = sum[(length1 + length2)/2];
        }else {
            result =(double) ((sum[(length1 + length2) / 2] + sum[((length1 + length2) / 2) -1]) / 2);
        }


        return result;
    }
}
