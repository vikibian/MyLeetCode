package com.neu.leetcode.problems.oneday;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class 杨辉三角2_0119 {

    public static void main(String[] args) {
        System.out.println(getRow(4));
    }

    public static List<Integer> getRow(int rowIndex) {
        List<Integer> list = new LinkedList<>();
        list.add(1);

        for (int i=1;i<=rowIndex;i++){
            int temp = 1;
            int num = 1;
            int size = list.size();
            for (int j=1;j< size+1;j++){
                if (j == size){
                    list.add(1);
                    break;
                }
//                temp = list.get(j-1);
                num = list.get(j);
                list.set(j,list.get(j)+temp);
                temp  = num;
            }

        }
        return list;
    }

    //官方题解  递归
    public static List<Integer> getRow1(int rowIndex) {
        List<List<Integer>> listList = new ArrayList<>();
        for (int i=0;i<=rowIndex;i++){
            List<Integer> list = new ArrayList<>();
            for (int j=0;j<=i;j++){
                if (j==0 || j==i){
                    list.add(1);
                } else {
                    list.add(listList.get(i-1).get(j-1)+listList.get(i-1).get(j));
                }
            }
            listList.add(list);
        }

        return listList.get(rowIndex);
    }

    //官方题解  滚动数组优化
    public static List<Integer> getRow2(int rowIndex) {
        List<Integer> listList = new ArrayList<>();
        for (int i=0;i<=rowIndex;i++){
            List<Integer> list = new ArrayList<>();
            for (int j=0;j<=i;j++){
                if (j==0 || j==i){
                    list.add(1);
                } else {
                    list.add(listList.get(j-1)+listList.get(j));
                }
            }
            listList = list;
        }

        return listList;
    }

    //官方题解  进一步优化 只用一个数组
    public static List<Integer> getRow3(int rowIndex) {
        List<Integer> listList = new ArrayList<>();
        listList.add(1);
        for (int i=1;i<=rowIndex;i++){
            listList.add(0);
            for (int j=i;j>0;j--){
                listList.set(j,listList.get(j)+listList.get(j-1));
            }
        }
        return listList;
    }

    //线性递推
    public static List<Integer> getRow4(int rowIndex) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        for (int i=1;i<=rowIndex;i++){
            list.add((int)((long)list.get(i-1)*(rowIndex-i+1)/i));
        }

        return list;
    }
}
