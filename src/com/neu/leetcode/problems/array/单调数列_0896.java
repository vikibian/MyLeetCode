package com.neu.leetcode.problems.array;

public class 单调数列_0896 {

    //ture  简单
    public boolean isMonotonic(int[] A) {
        int n = A.length;
        int num = A[0];
        int up = 0;
        int down = 0;
        int mid =0;
        for (int i=1;i<n;i++){
            if (num<A[i]){
                up++;
            } else if (num>A[i]){
                down++;
            }
            num = A[i];
//           else {
//               mid++;
//           }
        }
        if (up!=0 && down!= 0){
            return false;
        }


        return true;
    }

    //官方题解  两次遍历
    public boolean isMonotonic1(int[] A) {
        return isSorted(A,true) || isSorted(A,false);
    }

    private boolean isSorted(int[] nums, boolean b) {
        int n= nums.length;
        if (b){
            for (int i=0;i<n-1;i++){
                if (nums[i] > nums[i+1]){
                    return false;
                }
            }
        } else {
            for (int i=0;i<n-1;i++){
                if (nums[i]<nums[i+1]){
                    return false;
                }
            }
        }
        return false;
    }

    //官方题解
    public boolean isMonotonic2(int[] A){
        boolean inc = true,dec = true;

        int n = A.length;
        for (int i=0;i<n-1;i++){
            if (A[i] > A[i+1]){
                inc = false;
            }
            if (A[i]<A[i+1]){
                dec = false;
            }
        }

        return inc || dec;
    }
}
