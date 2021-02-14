package com.neu.leetcode.problems.array;

import java.util.Arrays;

public class 螺旋矩阵2_0059 {

    public static void main(String[] args) {
        int n =1;
        System.out.println(generateMatrix(n).length);
//        System.out.println(generateMatrix(n)[1][1]);
        int[][] ints = generateMatrix(n);
        for (int i=0;i<n;i++){
            System.out.println(Arrays.toString(ints[i]));
        }

    }

    public static int[][] generateMatrix(int n) {
        int[][] ans = new int[n][n];
        int cont = 1;
        int left = 0,right = n-1;
        int top = 0,bottom = n-1;
        while (left<=right && top <= bottom){
            for (int i=left;i<=right;i++){
                ans[top][i] = cont++;
            }

            for (int i=top+1;i<bottom;i++){
                ans[i][right] = cont++;
            }

            if (left<right && top < bottom){
                for (int i=right;i>=left;i--){
                    ans[bottom][i] = cont++;
                }

                for (int i=bottom-1;i>top;i--){
                    ans[i][left] = cont++;
                }
            }

            left++;
            right--;
            top++;
            bottom--;
        }

        return ans;
    }

    //官方题解
    public static int[][] generateMatrix1(int n) {
        int left=0,right=n-1,top=0,bottom=n-1;
        int[][] mat = new int[n][n];
        int num =1,tar = n*n;
        while (num<=tar){
            for (int i=left;i<=right;i++){
                mat[top][i] = num++;
            }
            top++;
            for (int i=top;i<=bottom;i++){
                mat[i][right] = num++;
            }
            right--;
            for (int i=right;i>=left;i--){
                mat[bottom][i] = num++;
            }
            bottom--;
            for (int i=bottom;i>=top;i--){
                mat[i][left] = num++;
            }
            left++;
        }
        return mat;
    }
}
