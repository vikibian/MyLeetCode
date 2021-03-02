package com.neu.leetcode.problems.dynamicprogramming;

public class 二维区域和检索_矩阵不可变_0304 {
    //true  中等
    //官方题解 前缀和
    class NumMatrix {
        private int[][] sums;
        public NumMatrix(int[][] matrix) {

            if (matrix == null){
                sums = matrix;
            } else {
                int m = matrix.length;
                if (m != 0){
                    int n = matrix[0].length;
                    sums = new int[m][n+1];
                    for (int i=0;i<m;i++){
                        for (int j=0;j<n;j++){
                            sums[i][j+1] = sums[i][j]+matrix[i][j];
                        }
                    }
                }else {
                    sums = matrix;
                }

            }

        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            int ans = 0;
            for (int i=row1;i<=row2;i++){
                int temp = (sums[i][col2+1]-sums[i][col1]);
                ans += temp;
            }

            return ans;
        }
    }

    //二维前缀和
    class NumMatrix1 {
        private int[][] sums;
        public NumMatrix1(int[][] matrix) {
            int m = matrix.length;
            if (m > 0 ){
                int n= matrix[0].length;
                sums = new int[m+1][n+1];
                for (int i=0;i<m;i++){
                    for (int j=0;j<n;j++){
                        sums[i+1][j+1] = sums[i][j+1]+sums[i+1][j] -sums[i][j] + matrix[i][j];
                    }
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
           return sums[row2+1][col2+1] - sums[row1][col2+1] - sums[row2+1][col1] + sums[row1][col1];
        }
    }
}
