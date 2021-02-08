package com.neu.leetcode.problems.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class 组合总和2_0040 {


    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> lists = new LinkedList<>();
        List<Integer>  list = new LinkedList<>();
        Arrays.sort(candidates);
        dfs(candidates,target,lists,list,0); // 可以
//        backtack(candidates,target,lists,list,0,0);  //可以
        return lists;
    }

    private void backtack(int[] candidates, int target, List<List<Integer>> lists, List<Integer> list, int sum, int index) {
        if (sum == target){
            if (lists.contains(list)){

            }else {
                lists.add(new LinkedList<>(list));
            }
            return;
        }

        for (int i=index;i<candidates.length;i++){
            if (i>index&&candidates[i] ==candidates[i-1]){
                continue;
            } else {
                int res = candidates[i] + sum;
                if (res<=target){
                    list.add(candidates[i]);
                    backtack(candidates,target,lists,list,res,i+1);
                    list.remove(list.size() - 1);
                } else {
                    break;
                }
            }
        }
    }

    private void dfs(int[] candidates, int target, List<List<Integer>> lists, List<Integer> list, int i) {

        if (target == 0){
            if (lists.contains(list)){

            }else {
                lists.add(new LinkedList<>(list));
            }

            return;
        }

        if (target < 0){
            return;
        }

        for (int j =i;j<candidates.length;j++){
            if (target - candidates[j] >= 0){
                list.add(candidates[j]);
                dfs(candidates,target-candidates[j],lists,list,j+1);
                list.remove(list.size() - 1);
            }
        }
    }

    //官方题解
    List<int[]> freq = new ArrayList<>();
    List<List<Integer>> ans = new ArrayList<>();
    List<Integer> sequence = new ArrayList<>();

    public List<List<Integer>> combinationSum21(int[] candidates, int target) {
        Arrays.sort(candidates);
        for (int num : candidates){
            int size = freq.size();
            if (freq.isEmpty() || num != freq.get(size-1)[0]){
                freq.add(new int[] {num,1});
            } else {
                ++freq.get(size-1)[1];
            }
        }
        dfs1(0,target);
        return ans;
    }

    private void dfs1(int pos, int rest) {
        if (rest == 0){
            ans.add(new ArrayList<>(sequence));
            return;
        }

        if (pos == freq.size() || rest <freq.get(pos)[0]){
            return;
        }

        dfs1(pos+1,rest);

        int most = Math.min(rest/freq.get(pos)[0],freq.get(pos)[1]);
        for (int i =1;i<=most;i++){
            sequence.add(freq.get(pos)[0]);
            dfs1(pos,rest-i*freq.get(pos)[0]);
        }

        for (int i=1;i<=most;i++){
            sequence.remove(sequence.size() -1);
        }
    }
}
