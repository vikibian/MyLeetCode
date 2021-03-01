package com.neu.leetcode.problems.array;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class 托普利茨矩阵_0766 {

    public static void main(String[] args) {
//        int[][] nums = new int[][]{{1,2,3,4},{5,1,2,3},{9,5,1,2}};
        int[][] nums = new int[][]{{1,2},{2,2}};
        System.out.println(isToeplitzMatrix(nums));
    }

    public static boolean isToeplitzMatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[] nums = new int[n+(m-1)];
        int index = nums.length-1;
        for(int i=n-1;i>=0;i--){
            nums[index--] = matrix[0][i];
        }
        for(int i=1;i<m;i++){
            int index2 = index;
            for(int j=0;j<n;j++){

                if(j == 0){
                    nums[index--] = matrix[i][j];
                }


                if(matrix[i][j] != nums[index2+j]){
                    return false;
                }
            }
        }
        return true;
    }

    //官方题解
    public static boolean isToeplitzMatrix1(int[][] matrix) {
        int m = matrix.length,n=matrix[0].length;
        for (int i=1;i<m;i++){
            for (int j=1;j<n;j++){
                if (matrix[i][j] != matrix[i-1][j-1]){
                    return false;
                }
            }
        }
        return true;
    }

    //按线遍历
    public static boolean isToeplitzMatrix2(int[][] matrix) {

        int m = matrix.length,n = matrix[0].length;
        int row = m,col = n;
        while (col-->0){
            for (int i=0,j=col,val = matrix[i++][j++];i<m&j<n;i++,j++){
                if (matrix[i][j] != val ){
                    return false;
                }
            }
        }

        while (row-->0){
            for (int i=row,j=0,val = matrix[i++][j++];i<m&j<n;i++,j++){
                if (matrix[i][j] !=val){
                    return false;
                }
            }
        }

        return  true;
    }
}
