package com.neu.leetcode.problems.array;

import java.util.LinkedList;
import java.util.List;

public class 螺旋矩阵_0054 {

    public static void main(String[] args) {
        int[][] nums = new int[][]{{1,2,3,4},{5,6,7,8},{9,10,11,12}};
        spiralOrder(nums);
    }

    //其实这就是官方题解的按层遍历
    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new LinkedList<>();
        if (matrix == null){
            return list;
        }

        spiralMatrix(matrix,list,0,0);

        return list;

    }

    private static void spiralMatrix(int[][] matrix, List<Integer> list, int row, int col) {
        int r = matrix.length;
        int l = matrix[0].length;
        //i j大
        for (int i=row;i<l-col;i++){
            list.add(matrix[row][i]);
        }
        if (list.size() == l*r){
            return;
        }
        //i大 j
        for (int i=row+1;i<r-row;i++){
            list.add(matrix[i][l-col-1]);
        }

        if (list.size() == l*r){
            return;
        }
        //i  j小
        for (int i=l-col -1-1;i>=col;i--){
            list.add(matrix[r-row-1][i]);
        }

        if (list.size() == l*r){
            return;
        }
        //i 小 j
        for (int i=r-row -1-1;i>col;i--){
            list.add(matrix[i][col]);
        }
        if (list.size() == l*r){
            return;
        }
        spiralMatrix(matrix,list,row+1,col+1);

    }

    //官方题解  模拟
    public List<Integer> spiralOrder1(int[][] matrix) {
        List<Integer>  order = new LinkedList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return order;
        }
        int rows = matrix.length,col = matrix[0].length;
        boolean[][] visited = new boolean[rows][col];
        int total = rows*col;
        int row=0,column=0;
        int[][] directions = {{0,1},{1,0},{0,-1},{-1,0}};
        int directonIndex = 0;
        for (int i=0;i<total;i++){
            order.add(matrix[row][column]);
            visited[row][column] = true;
            int nextRow =row+ directions[directonIndex][0],nextColumn = column+ directions[directonIndex][1];
            if (nextRow<0 ||nextRow>=rows||nextColumn<0||nextColumn>=col || visited[nextRow][nextColumn]){
                directonIndex = (directonIndex+1)%4;
            }
            row += directions[directonIndex][0];

            column += directions[directonIndex][1];

        }

        return order;
    }

    //按层遍历
    public List<Integer> spiralOrder2(int[][] matrix) {
        List<Integer> order = new LinkedList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return order;
        }
        int rows = matrix.length,colums = matrix[0].length;
        int left=0,right=colums-1,top = 0,bottom=colums-1;
        while (left<=right && top<=bottom){
            for (int column=left;column<=right;column++){
                order.add(matrix[column][top]);
            }
            for (int row = top+1;top<=bottom;top++){
                order.add(matrix[row][right]);
            }
            if (left<right && top < bottom){
                for (int column = right-1;column>left;column--){
                    order.add(matrix[bottom][column]);
                }
                for (int row=bottom;row>top;row--){
                    order.add(matrix[row][left]);
                }
            }
            left++;
            right--;
            top++;
            bottom--;
        }

        return order;
    }
}
