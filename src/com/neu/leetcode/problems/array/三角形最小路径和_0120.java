package com.neu.leetcode.problems.array;

import java.util.List;

/*
给定一个三角形 triangle ，找出自顶向下的最小路径和。

每一步只能移动到下一行中相邻的结点上。相邻的结点
在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。
也就是说，如果正位于当前行的下标 i ，那么下一步可以移动到下一行的下标 i 或 i + 1 。


 */
public class 三角形最小路径和_0120 {

    private int sum= Integer.MAX_VALUE;

    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle.size() == 1){
            return triangle.get(0).get(0);
        }
        int n = triangle.size();
        updateSum(triangle,n,0,0,triangle.get(0).get(0));

        return sum;
    }

    private void updateSum(List<List<Integer>> triangle, int n, int index, int row, Integer integer) {
        if ( n-1 == row){
            sum = Math.min(integer,sum);
            return;
        }
//        int left = triangle.get(row+1).get(index);
//        int right = triangle.get(row+1).get(index+1);
//
//
//
//        int temp = integer+Math.min(left,right);
//        updateSum(triangle,n,index, row+1, temp);
//        temp = integer+Math.max(right,left);
//        updateSum(triangle,n,index+1,row+1,temp);

        int temp = integer+triangle.get(row+1).get(index);
        updateSum(triangle,n,index, row+1, temp);
        temp = integer+triangle.get(row+1).get(index+1);
        updateSum(triangle,n,index+1,row+1,temp);

    }

    //官方题解 动态规划
    public int minimumTotal1(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] f = new int[n][n];
        f[0][0] = triangle.get(0).get(0);
        for (int i=1;i<n;i++){
            f[i][0] = f[i-1][0]+triangle.get(i).get(0);
            for (int j=1;j<n;j++){
                f[i][j] = Math.min(f[i-1][j-1],f[i-1][j])+triangle.get(i).get(j);
            }
            f[i][i] = f[i-1][i-1]+triangle.get(i).get(i);
        }
        int minTotal = f[n-1][0];
        for (int i=1;i<n;i++){
            minTotal = Math.min(minTotal,f[n-1][i]);
        }
        return minTotal;
    }

    //动态规划+空间优化
    public int minimumTotal2(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] f = new int[2][n];
        f[0][0] = triangle.get(0).get(0);
        for (int i=1;i<n;i++){
            int curr = i%2;
            int prev = 1-curr;
            f[curr][0] = f[prev][0] + triangle.get(i).get(0);
            for (int j=1;i<n;j++){
                f[curr][j] = Math.min(f[prev][j-1],f[prev][j])+triangle.get(i).get(j);
            }
            f[curr][i] = f[prev][i-1]+triangle.get(i).get(i);
        }
        int minTotal = f[(n-1)%2][0];
        for (int i=1;i<n;i++){
            minTotal = Math.min(minTotal,f[(n-1)%2][i]);
        }
        return minTotal;
    }

    //动态规划+空间优化
    public int minimumTotal3(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] f = new int[n];
        f[0] = triangle.get(0).get(0);
        for (int i=1;i<n;i++){
            f[i] = f[i-1]+triangle.get(i).get(i);
            for (int j=i-1;j>=0;j--){
                f[j] = Math.min(f[j-1],f[j])+triangle.get(i).get(j);
            }
            f[0] = f[0]+triangle.get(i).get(0);
        }
        int minTotal = f[0];
        for (int i=1;i<n;i++){
            minTotal = Math.min(minTotal,f[i]);
        }
        return minTotal;
    }
}
