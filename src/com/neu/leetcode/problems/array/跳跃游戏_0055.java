package com.neu.leetcode.problems.array;

public class 跳跃游戏_0055 {

    public static void main(String[] args) {
        int[] nums = new int[] {2,0};
        System.out.println(canJump(nums));
    }

    public static boolean canJump(int[] nums) {
        Boolean jump = null;
        if (nums == null){
            return false;
        }


//        return jump;
        return getJump(nums,nums[0],0);
    }

    private static boolean getJump(int[] nums, int num, int count) {
        boolean jump = true;
        int len = nums.length;

        if (len-1 == count){
            return true;
        }

        if (num == 0 && count<len-1){
            return false;
        }
        for (int i=1;i<=num;i++){
            count = count + 1;
            if (count >= len){
                return false;
            }
//            if(nums[count] == 0){
//                jump = false;
//                break;
//            }

            if (getJump(nums,nums[count],count)){
                return true;
            }else {
                jump = false;
            }
        }

        return jump;
    }

    //官方题解  贪婪算法 正向
    public boolean canJump1(int[] nums) {
        int n = nums.length;
        int rightmost = 0;
        for (int i=0;i<n;i++){
            if (i<=rightmost){
                rightmost = Math.max(i+nums[i],rightmost);
                if (rightmost >= n-1){
                    return true;
                }
            }
        }
        return false;
    }

    //官方题解 反方向
    public boolean canJump2(int[] nums) {
        int len = nums.length;
        int last = len-1;
        for (int i= len-2;i>=0;i--){
            if (i+nums[i] >= last){
                last = i;
            }
        }

        return last == 0;

    }
}
