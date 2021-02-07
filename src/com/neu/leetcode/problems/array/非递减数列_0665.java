package com.neu.leetcode.problems.array;

public class 非递减数列_0665 {

    public static void main(String[] args) {
//        int[] nums = new int[]{3,4,2,3};
        int[] nums = new int[]{4,2,1};
        System.out.println(checkPossibility(nums));
    }

//    public static boolean checkPossibility(int[] nums) {
//        int len = nums.length;
//        int count = 0;
//        int max1 = nums[0];
//        int max2 = 0;
//        for (int i=1;i<len;i++){
//            if (max1 > nums[i] &&  max2 >= nums[i]){
//                count++;
//                if (count >=2){
//                    return false;
//                }
//            } else {
//                if (nums[i] > max1){
//                    max2 = max1;
//                    max1 = nums[i];
//                }
//            }
//        }
//
//        return true;
//    }

    //官方题解

    public static boolean checkPossibility(int[] nums) {
        int num = nums.length;
        for (int i=0;i<num-1;i++){
            int x=nums[i];
            int y = nums[i+1];
            if (x>y){
                nums[i] = y;
                if (isCheck(nums)){
                    return true;
                }
                nums[i] = x;
                nums[i+1] = x;
                return isCheck(nums);
            }
        }

        return true;
    }

    private static boolean isCheck(int[] nums) {
        int n = nums.length;
        for (int i=1;i<n;i++){
            if (nums[i-1] > nums[i]){
                return false;
            }
        }
        return true;
    }

    //进阶
    public static boolean checkPossibility2(int[] nums) {
        int num = nums.length;
        int count = 0;
        for (int i=0;i<num-1;i++){
            int x=nums[i];
            int y = nums[i+1];
            if (x>y){
                count++;
                if (count>1){
                    return false;
                }
                if (i>0 && nums[i-1] > y){
                    nums[i+1] = x;
                }
            }
        }

        return true;
    }

}
