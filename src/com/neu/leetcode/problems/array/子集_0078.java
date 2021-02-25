package com.neu.leetcode.problems.array;

import sun.plugin.javascript.navig.LinkArray;

import java.util.ArrayList;
import java.util.List;

public class 子集_0078 {

    public static void main(String[] args) {
        int[] nums = new int[]{5,2,9};
        System.out.println(subsets(nums));
        String bitmask = Integer.toBinaryString(8).substring(1);
        System.out.println(bitmask);
        System.out.println(Integer.toBinaryString(8));
        System.out.println(Integer.toBinaryString(1).charAt(1));

    }

    //官方题解  迭代法实现子集枚举
    public static List<List<Integer>> subsets(int[] nums) {
        List<Integer> t = new ArrayList<Integer>();
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        int n = nums.length;


        for (int mask =0;mask<(1<<n);mask++){
            t.clear();
            for (int i=0;i<n;i++){
                if ((mask & (1<<i)) != 0){
                    t.add(nums[i]);
                }
            }
            ans.add(new ArrayList<>(t));
        }
        return ans;
    }

    //官方题解 另一种位运算的方法 视频
    public static List<List<Integer>> subsets1(int[] nums) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        int n = nums.length;


        for (int i = (int)Math.pow(2,n);i<(int)Math.pow(2,n+1);i++){
            String bitmask = Integer.toBinaryString(i).substring(1);
            List<Integer> cur = new ArrayList<>();
            for (int j=0;j<n;j++){
                if (bitmask.charAt(j) == '1'){
                    cur.add(nums[j]);
                }
            }
            ans.add(cur);
        }
        return ans;
    }

    //官方题解 递归法实现子集枚举
    List<Integer> t1 = new ArrayList<Integer>();
    List<List<Integer>> ans1 = new ArrayList<List<Integer>>();

    public  List<List<Integer>> subsets2(int[] nums) {
        dfs(0,nums);
        return ans1;
    }

    private void dfs(int index, int[] nums) {
        if (index == nums.length){
            ans1.add(new ArrayList<>(t1));
            return;
        }
        //若去index位置
        t1.add(nums[index]);
        dfs(index+1,nums);
        //若不取index位置的值
        t1.remove(t1.size()-1);
        dfs(index+1,nums);
    }

    //视频题解 递归法一
    public  List<List<Integer>> subsets3(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<Integer>());

        for (int num:nums){
            List<List<Integer>> newSubsets = new ArrayList<>();
            for (List<Integer> list : res){
                List<Integer> newSubset = new ArrayList<>(list);
                newSubset.add(num);
                newSubsets.add(newSubset);
            }
            res.addAll(newSubsets);
        }

        return res;
    }

    //视频题解 递归法二
    public  List<List<Integer>> subsets4(int[] nums) {

        return recurse(nums.length,nums);
    }

    private List<List<Integer>> recurse(int cur, int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        if (cur == 0){
            list.addAll(new ArrayList<>());
            return list;
        }

        List<List<Integer>> existing = recurse(cur-1,nums);
        list.addAll(existing);
        for (List<Integer> subset:existing){
            List<Integer> newSubset = new ArrayList<>(subset);
            newSubset.add(nums[cur-1]);
            list.add(newSubset);
        }

        return list;
    }

    //回溯算法
    List<List<Integer>> result = new ArrayList<>();
    int n;
    public  List<List<Integer>> subsets5(int[] nums) {
        n = nums.length;
        for (int k=0;k<n;k++){
            backtrack(0,k,new ArrayList<>(),nums);
        }

        return result;
    }

    private void backtrack(int start, int k, ArrayList<Integer> cur, int[] nums) {
        if (k == 0){
            result.add(new ArrayList<Integer>(cur));
            return;
        }

        for (int i=start;i<n;i++){
            cur.add(nums[i]);
            backtrack(start+1,k-1,cur,nums);
            cur.remove(cur.size()-1);
        }
    }
}
