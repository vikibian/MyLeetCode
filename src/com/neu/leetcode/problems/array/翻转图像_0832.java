package com.neu.leetcode.problems.array;

public class 翻转图像_0832 {
    public int[][] flipAndInvertImage(int[][] A) {
        int m = A.length;
        int n = A[0].length;
        int mid = (n+1)/2;
        for (int i=0;i<m;i++){
            for (int j=0;j<mid;j++){
                int temp = A[i][j];
                A[i][j] = (A[i][n-j-1]==0?1:0);
                A[i][n-j-1] = (temp == 0?1:0);
            }
        }
        return A;
    }

    //官方题解
    public int[][] flipAndInvertImage1(int[][] A) {
        int n = A.length;
        for (int i=0;i<n;i++){
            int left=0,right=n-1;
            while (left<right){
                if (A[i][left] == A[i][right]){
                    A[i][left] ^= 1;
                    A[i][right] ^= 1;
                }
                left++;
                right--;
            }
            if (left == right){
                A[i][left] ^= 1;
            }
        }
        return A;
    }
}
