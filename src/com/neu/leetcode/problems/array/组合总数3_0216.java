package com.neu.leetcode.problems.array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class 组合总数3_0216 {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> list = new LinkedList<>();
        if(k==0 || n==0){
            return list;
        }
        List<Integer> path = new LinkedList<>();
        getList(list,k,n,path,1);
        return list;
    }

    public void getList(List<List<Integer>> list,int k,int n,List<Integer> path,int n1){

        if(n==0 && k == 0 ){
            if(!list.contains(path)){
                list.add(new LinkedList<>(path));
                return;
            }

        }
        if(n < n1 || k<=0){
            return;
        }
        if(n<0){
            return;
        }
        if(k == 0 && n!= 0){
            return;
        }


        for(int i=n1;i<=9;i++){
            if(!path.contains(i)){
                path.add(i);
                getList(list,k-1,n-i,path,i+1);
                path.remove(path.size()-1);
            }
        }

    }

    //官方题解  二进制（子集）枚举
    List<Integer> temp = new LinkedList<>();
    List<List<Integer>> ans = new LinkedList<>();
    public List<List<Integer>> combinationSum31(int k, int n) {
        for (int mask =0;mask<(1<<9);++mask){
            if (check(mask,k,n)){
                ans.add(new LinkedList<>(temp));
            }
        }
        return ans;
    }

    public boolean check(int mask,int k,int n){
        temp.clear();
        for (int i=0;i<9;i++){
            if (((1<<i) & mask) != 0){
                temp.add(i+1);
            }
        }

        if (temp.size() != k){
            return false;
        }
        int sum = 0;
        for (int num : temp){
            sum += num;
        }
        return sum == n;
    }

    List<Integer> temp1 = new LinkedList<>();
    List<List<Integer>>  ans1 = new LinkedList<>();
    public List<List<Integer>> combinationSum32(int k, int n) {
        dfs(1,9,k,n);
        return ans1;
    }

    public void  dfs(int cur,int n,int k,int sum){
        if (temp1.size() + (n-cur+1) < k|| temp1.size()<k){
            return;
        }
        if (temp1.size() == k){
            int tempSum = 0;
            for (int num : temp1){
                tempSum += num;
            }
            if (tempSum == sum){
                ans1.add(new ArrayList<>(temp));
                return;
            }
        }
        temp1.add(cur);
        dfs(cur+1,k,n,sum);
        temp1.remove(temp1.size()-1);
        dfs(cur+1,k,n,sum);
    }
}
