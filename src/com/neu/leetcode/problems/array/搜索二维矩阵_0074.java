package com.neu.leetcode.problems.array;

public class 搜索二维矩阵_0074 {

    public static void main(String[] args) {
        int[][] nums = new int[][]{{-8,-7,-5,-3,-3,-1,1},{2,2,2,3,3,5,7},{8,9,11,11,13,15,17},{18,18,18,20,20,20,21},{23,24,26,26,26,27,27},{28,29,29,30,32,32,34}};
        System.out.println(searchMatrix(nums,-5));
    }


    public static boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null){
            return false;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;
        int r = 0;
        for (int i=0;i<rows;i++){

            if (matrix[i][0] == target || matrix[i][cols-1] == target){
                return true;
            }

            if (target>matrix[i][0] && target<matrix[i][cols-1]){
                r=i;
                break;
            }
        }

        int left = 0,right=cols-1;
        while (left<=right){
            int mid = (left+right)/2;
            if (matrix[r][mid] == target){
                return true;
            }else if (matrix[r][mid] < target){
                left = mid+1;
            } else if (matrix[r][mid] > target){
                right = mid-1;
            }
        }

        return false;
    }

    //官方题解
    public static boolean searchMatrix1(int[][] matrix, int target) {
        int m = matrix.length;
        if (m == 0){
            return false;
        }
        int n= matrix[0].length;

        int left=0,right= m*n-1;
        int pIndex,pElement;
        while (left<= right){
            pIndex = (left+right)/2;
            pElement = matrix[pIndex/n][pIndex%n];
            if (pElement == target){
                return true;
            } else {
                if (pElement<target){
                    left = pIndex+1;
                } else {
                    right = pIndex-1;
                }
            }

        }

        return false;
    }
}
