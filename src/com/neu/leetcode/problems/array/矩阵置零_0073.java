package com.neu.leetcode.problems.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
}
