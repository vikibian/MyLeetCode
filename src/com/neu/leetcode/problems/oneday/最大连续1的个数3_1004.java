package com.neu.leetcode.problems.oneday;

public class 最大连续1的个数3_1004 {

    public static void main(String[] args) {
        int[] nums = new int[]{0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1};
//        int[] nums = new int[]{1,1,1,0,0,0,1,1,1,1,0};
        System.out.println(longestOnes(nums,3));
    }

    public static int longestOnes(int[] A, int K) {
        int len = A.length;
        int index=0;
        int count0=0;
        int count1 = 0;
        int temp = 0;
        int tempIndex = 0;
        boolean isFirst = true;
        while (index<len){
            int num = A[index];
            if (isFirst ){
                isFirst = false;
                tempIndex = index;
            }
            if (num==0){
                count0++;
                temp++;
                if (count0>K){
                    temp--;
                    count0=0;
                    count1 = Math.max(count1,temp);
                    temp = 0;
                    isFirst = true;
                    index = tempIndex;
                    continue;
                }
            } else {

                temp++;
            }
            index++;
        }

        return count1;
    }
}
