package com.neu.leetcode.problems.array;

public class 旋转图像_0048 {
    //取巧的解法  先对角线对换再中线对换  官方题解三
    public void rotate(int[][] matrix) {
        if (matrix == null){
            return;
        }
        int len1 = matrix.length;
        int len2 = matrix[0].length;

        for (int i=0;i<len1;i++){
            for (int j =0;j<len2-i;j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[len2-j-1][len1-i-1];
                matrix[len2-j-1][len1-i-1] = temp;
            }
        }

        for (int i=0;i<(len1/2);i++){
            for (int j=0;j<len2;j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[len1-i-1][j];
                matrix[len1-i-1][j] = temp;
            }
        }
    }

    //官方题解 暴力解法  需要使用额外数组

    //方法二
    public void rotate1(int[][] matrix) {
        int n = matrix.length;
        for (int i=0;i<n/2;i++){
            for (int j=0;j<(n+1)/2;j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - j -1][i];
                matrix[n-j-1][i] = matrix[n-i-1][n-j-1];
                matrix[n-i-1][n-j-1] = matrix[j][n-i-1];
                matrix[j][n-i-1] = temp;
            }
        }
    }

}
