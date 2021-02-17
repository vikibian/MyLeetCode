package com.neu.leetcode.swordpoint;

public class 顺时针打印矩阵_29 {
    //官方题解二
    public int[] spiralOrder(int[][] matrix) {

        if(matrix == null){
            return null;
        }
        if(matrix.length == 0 || matrix[0].length == 0){
            return new int[0];
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int left =0,right=n-1;
        int top = 0,bottom = m-1;

        int[] ans = new int[m*n];
        int count=0;
        while(left <= right && top<=bottom){
            for(int i=left;i<=right;i++){
                ans[count++] = matrix[top][i];
            }

            for(int i = top+1;i<=bottom;i++){
                ans[count++] = matrix[i][right];
            }

            if(left<right && top < bottom){
                for(int i=right-1;i>=left;i--){
                    ans[count++] = matrix[bottom][i];
                }

                for(int i=bottom-1;i>top;i--){
                    ans[count++] = matrix[i][left];
                }
            }
            left++;
            right--;
            top++;
            bottom--;
        }

        return ans;
    }

    //官方题解一
    public int[] spiralOrder1(int[][] matrix) {
        if (matrix == null || matrix.length == 0||matrix[0].length == 0){
            return new int[0];
        }
        int  row = matrix.length,col = matrix[0].length;
        boolean[][] visited = new boolean[row][col];
        int total = row*col;
        int[] order = new int[total];
        int r=0,c=0;
        int[][] directions = {{0,1},{1,0},{0,-1},{-1,0}};
        int directionIndex = 0;
        for (int i=0;i<total;i++){
            order[i] = matrix[r][c];
            visited[r][c] = true;
            int nextRow = r+directions[directionIndex][0],nextCol = c+directions[directionIndex][1];
            if (nextRow <0||nextRow>=row || nextCol<0||nextCol>=col||visited[r][c]){
                directionIndex = (directionIndex+1)%4;
            }
            r += directions[directionIndex][0];
            c += directions[directionIndex][1];
        }

        return order;
    }
}
