package com.neu.leetcode.problems.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class 组合总和_0039 {
//    public List<List<Integer>> combinationSum3(int[] candidates, int target) {
//        int len = candidates.length;
//        List<List<Integer>> lists = new LinkedList<>();
//        Arrays.sort(candidates);
//
//        for (int i=0;i<len;i++){
//            List<Integer> list = new LinkedList<>();
//            addResutlt(candidates,lists,list,target ,i);
//        }
//
//
//        return lists;
//    }
//
//    private void addResutlt(int[] candidates, List<List<Integer>> lists, List<Integer> list, int target, int num) {
//        int res = target - candidates[num];
//
//        if (res<0){
//            return;
//        }
//
//        if (res == 0){
//            lists.add(new LinkedList<>(list));
//            return;
//        }
//
//        for (int i=num;i<candidates.length;i++){
//            list.add(candidates[num]);
//            addResutlt(candidates,lists,list,target,i);
//            list.remove(list.size()-1);
//        }
//    }

    //官方题解
    //对于这类寻找所有可行解的题，我们都可以尝试用「搜索回溯」的方法来解决。
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> lists = new LinkedList<>();
        List<Integer> list = new LinkedList<>();
        dfs(candidates,target,lists,list,0);
        return lists;
    }

    private void dfs(int[] candidates, int target, List<List<Integer>> lists, List<Integer> list, int i) {
        if (i == candidates.length){
            return;
        }

        if (target == 0){
            lists.add(new LinkedList<>(list));
            return;
        }
        //跳过这个
        dfs(candidates,target,lists,list,i+1);
        if (target - candidates[i] >= 0){
            list.add(candidates[i]);
            dfs(candidates,target- candidates[i],lists,list,i);
            list.remove(list.size()-1);
        }
    }

    private List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combinationSum1(int[] candidates, int target) {
        List<Integer> path = new ArrayList<>();
        Arrays.sort(candidates);
        backtrack(path,candidates,target,0,0);
        return res;
    }

    private void backtrack(List<Integer> path,int[] candidates,int target,int sum,int begin) {
        if(sum == target) {
            res.add(new ArrayList<>(path));
            return;
        }
        for(int i = begin;i < candidates.length;i++) {
            int rs = candidates[i] + sum;
            if(rs <= target) {
                path.add(candidates[i]);
                backtrack(path,candidates,target,rs,i);
                path.remove(path.size()-1);
            } else {
                break;
            }
        }
    }

}
