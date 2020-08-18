package com.neu.leetcode.problems;

import java.util.Arrays;

public class 寻找两个正序数组的中位数_0004 {
    public static void main(String[] args) {
        int[] nums1 = {};
        int[] nums2 = {2};
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
                return nums2[index];
            }else {
                return  (double)(nums2[length2/2]+nums2[(length2/2)-1])/2;
            }
        }

        if (length2 == 0){
            if (length1 % 2 ==1){
                int index = length1 /2;
                return nums1[index];
            }else {
                return (double) (nums1[length1/2]+nums1[(length1/2)-1])/2;
            }
        }

        int[] sum = new int[length1+length2];

        int pointer1 = 0;
        int pointer2 = 0;
        boolean end1 = false;
        boolean end2 = false;
        for (int i=0;i<length1+length2;i++){
            if ((nums1[pointer1] <= nums2[pointer2])){
//                if (i -  length1 < pointer2){
//                    sum[i] = nums1[pointer1];
//                    if (pointer1 != length1 - 1){
//                        pointer1++;
//                    }
//
//                }else {
//                    sum[i] = nums2[pointer2];
//                    if (pointer2 != length2-1 ){
//                        pointer2++;
//                    }
//
//                }
                if (!end1){
                    sum[i] = nums1[pointer1];
                    if (pointer1 == length1-1){
                        end1 = true;
                    }else {
                        pointer1++;
                    }
                }else {
                    sum[i] = nums2[pointer2];
                    pointer2++;
                }
            }else {

//                if (i - length2 < pointer1){
//                    sum[i] = nums2[pointer2];
//                    if (pointer2 != length2 - 1){
//                        pointer2++;
//                    }
//
//                }else {
//                    sum[i] = nums1[pointer1];
//                    if (pointer1 != length1 - 1){
//                        pointer1++;
//                    }
//
//                }
                if (!end2){
                    sum[i] = nums2[pointer2];
                    if (pointer2 == length2-1){
                        end2 = true;
                    }else {
                        pointer2++;
                    }
                }else {
                    sum[i] = nums1[pointer1];
                    pointer1++;
                }

            }
        }


        if ((length1 + length2) % 2 ==1){
             result = (double)sum[(length1 + length2)/2];
        }else {
            result = ((double)(sum[(length1 + length2) / 2] + sum[((length1 + length2) / 2) -1]) / 2);
        }

//        int[] sum2 = {1,2,3,4};
//        if (sum2.length % 2 ==1){
//            return (double)sum2[sum2.length/2];
//        }else {
//            System.err.println("double1:"+((sum2[sum2.length / 2] + sum2[(sum2.length / 2) -1]) / 2));
//            System.err.println("double2:"+(sum2[sum2.length / 2] + sum2[(sum2.length / 2) -1]));
//            return  ((double)(sum2[sum2.length / 2] + sum2[(sum2.length / 2) -1]) / 2);//注意类型转换的位置
//        }


        return result;
    }

    /**
     * 力扣官方题解
     */
    public double findMedianSortedArrays2(int[] nums1, int[] nums2){
        if (nums1.length >= nums2.length){
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
        }

        int m = nums1.length;
        int n = nums2.length;

        //分割线左边的所有元素需要满足的 m + （n - m + 1） / 2
        int totalLeft = (m + n +1)/2;

        //在nums1的区间[0,m]里查找恰当的分割线
        //使得nums1[i-1] <= nums2[j] && nums2[j - 1] <= nums1[i]
        int left=0;
        int right = m;
        while (left < right){
            int i = left + (right - left) /2;
            int j=totalLeft - i;
            if (nums1[i-1] > nums2[j]){
                //下一轮搜索区间[left, i-1]
                right = i;
            }else {
                //下一轮搜索区间[i, right]
                // [left,right] 当区间只有2个元素时 一旦进入这个分支，左边届成为i的时候 这个区间就不会再缩小了 循坏就如死循环
                left = i;
            }
        }

        int i = left;
        int j = totalLeft - i;

        int nums1LeftMax = i==0?Integer.MIN_VALUE:nums1[i-1];
        int nums1RightMin = i==m?Integer.MAX_VALUE:nums1[i];
        int nums2LeftMax = j==0?Integer.MIN_VALUE:nums2[j-1];
        int nums2RightMin = j==n?Integer.MAX_VALUE:nums2[j];

        if (((m + n)%2)==1){
            return  Math.max(nums1LeftMax,nums2LeftMax);
        }else {
            return (double) ((Math.max(nums1LeftMax,nums2LeftMax))+(Math.max(nums1RightMin,nums2RightMin))) / 2;
        }

    }
}
