package com.neu.leetcode.problems.array;

import java.util.*;

public class 矩阵置零_0073 {
    public void setZeroes(int[][] matrix) {
        List<int[]> list = new ArrayList<>();
        if (matrix == null){
            return;
        }
        int rows = matrix.length;
        int cols = matrix[0].length;

        for (int i=0;i<rows;i++){
            for (int j=0;j<cols;j++){
                if (matrix[i][j] == 0){
                    list.add(new int[]{i,j});
                }
            }
        }

        for (int i=0;i<list.size();i++){
            int r = list.get(i)[0];
            int l = list.get(i)[1];
            System.out.println(l);
            Arrays.fill(matrix[r],0);
            for (int j=0;j<matrix.length;j++){
                matrix[j][l] = 0;
            }
        }

    }

    //官方题解 额外存储空间方法
    public void setZeroes1(int[][] matrix) {
        int R = matrix.length;
        int C = matrix[0].length;
        Set<Integer> rows = new HashSet<>();
        Set<Integer> cols = new HashSet<>();
        for (int i=0;i<R;i++){
            for (int j=0;j<C;j++){
                if (matrix[i][j] == 0){
                    rows.add(i);
                    cols.add(j);
                }
            }
        }

        for (int i=0;i<R;i++){
            for (int j=0;j<C;j++){
                if (rows.contains(i) || cols.contains(j)){
                    matrix[i][j] = 0;
                }
            }
        }
    }

    //官方题解 o(1)的空间暴力
    public void setZeroes2(int[][] matrix) {
        int modified = -1000000;
        int R = matrix.length;
        int C = matrix[0].length;

        for (int i=0;i<R;i++){
            for (int j=0;j<C;j++){
                if (matrix[i][j] == 0){
                   for (int c =0;c<C;c++){
                       if (matrix[i][c] != 0){
                           matrix[i][c] = modified;
                       }
                   }

                   for (int r = 0;r<R;r++){
                       if (matrix[r][j] != 0){
                           matrix[r][j] = modified;
                       }
                   }
                }
            }
        }

        for (int r=0;r<R;r++){
            for (int c=0;c<C;c++){
                if (matrix[r][c] == modified){
                    matrix[r][c] = 0;
                }
            }
        }
    }

    //官方题解 改进上面的方法
    public void setZeroes3(int[][] matrix) {
        boolean isCol = false;
        int R= matrix.length;
        int C = matrix[0].length;

        for (int i=0;i<R;i++){
            if (matrix[i][0] == 0){
                isCol = true;
            }

            for (int j=1;i<C;j++){
                if (matrix[i][j] == 0){
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }

        for (int i=1;i<R;i++){
            for (int j=1;j<C;j++){
                if (matrix[i][0] ==0 || matrix[0][j] == 0){
                    matrix[i][j] = 0;
                }
            }
        }

        if(matrix[0][0] == 0){
            for (int i=0;i<C;i++){
                matrix[0][i] = 0;
            }
        }

        if (isCol){
            for (int i=0;i<R;i++){
                matrix[i][0] = 0;
            }
        }
    }
}
