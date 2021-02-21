package com.neu.leetcode.problems.array;

import java.util.ArrayList;
import java.util.List;

public class 重塑矩阵_0566 {
    public int[][] matrixReshape(int[][] nums, int r, int c) {
        int row = nums.length;
        int col = nums[0].length;

        if (row*col != r*c){
            return nums;
        }
        int[][] ans = new int[r][c];
        int index = 0;

//        for (int i=0;i<row;i++){
//            for (int j=0;j<col;j++){
//                ans[index/c][index%c] = nums[i][j];
//                index++;
//            }
//        }

        //一维数组
        for (int i=0;i<row*col;i++){
            ans[i/c][i%c]  = nums[i/col][i%col];
        }
        return ans;
    }
}
