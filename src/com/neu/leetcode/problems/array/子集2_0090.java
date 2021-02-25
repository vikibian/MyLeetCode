package com.neu.leetcode.problems.array;

import org.w3c.dom.ls.LSInput;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 子集2_0090 {

    public static void main(String[] args) {
//        List<Integer> list = new ArrayList<>();
//        list.add(1);
//        list.add(2);
//        List<Integer> list1 = new ArrayList<>();
//        list1.add(1);
//        list1.add(2);
//        List<List<Integer>> listList = new ArrayList<>();
//        listList.add(list);
//        System.out.println(listList.contains(list1));
        int[] nums = new int[]{1,2,2};
        subsetsWithDup1(nums);
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        ans.add(new ArrayList<>());
        for (int num : nums){
            List<List<Integer>> listList = new ArrayList<>();
            for (List<Integer> list : ans ){
                List<Integer> tempList = new ArrayList<>(list);
                tempList.add(num);
                if (!ans.contains(tempList)){
                    listList.add(tempList);
                }

            }
            ans.addAll(listList);
        }

        return ans;
    }

    //题解 回溯法
    public static List<List<Integer>> subsetsWithDup1(int[] nums) {
        List<List<Integer>>  ans = new ArrayList<>();
        Arrays.sort(nums);
        getAns(nums,0,new ArrayList<>(),ans);
        return ans;
    }

    private static void getAns(int[] nums, int start, ArrayList<Integer> temp, List<List<Integer>> ans) {
        ans.add(new ArrayList<>(temp));
        for (int i= start;i<nums.length;i++){
            if (i>start && nums[i] == nums[i-1]){
                continue;
            }
            temp.add(nums[i]);
            getAns(nums,i+1,temp,ans);
            temp.remove(temp.size()-1);
        }
    }

    //题解 迭代法
    public List<List<Integer>> subsetsWithDup2(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        ans.add(new ArrayList<>());//初始化空数组
        Arrays.sort(nums);
        int start = 1;//保存新的解的位置
        for (int i=0;i<nums.length;i++){
            List<List<Integer>> ans_tmp = new ArrayList<>();
            //遍历之前的所有结果
            for (int j=0;j<ans_tmp.size();j++){
                List<Integer> list = new ArrayList<>();
                //如果出现重复数字 就跳过所有旧解
                if (i>0 && nums[i] == nums[i-1] && j<start){
                    continue;
                }
                List<Integer> temp = new ArrayList<>(list);
                temp.add(nums[i]);//加入新增数字
                ans_tmp.add(temp);

            }
            start = ans.size();//更新新解的开始位置
            ans.addAll(ans_tmp);
        }
        return ans;
    }

    //题解另一种迭代法
    //当有 n 个重复数字出现，其实就是在出现重复数字之前的所有解中，分别加 1 个重复数字， 2 个重复数字，3 个重复数字
    public List<List<Integer>> subsetsWithDup3(int[] num) {
        List<List<Integer>>  result = new ArrayList<>();
        result.add(new ArrayList<>());
        Arrays.sort(num);
        for (int i=0;i<num.length;i++){
            int dupCount = 0;
            //判断当前是否重复数字，并记录当前的重复次数
            while ((i+1)<num.length && num[i+1] == num[i]){
                dupCount++;
                i++;
            }
            int prenum = result.size();
            //遍历之前几个结果的每个解
            for (int j=0;j<prenum;j++){
                List<Integer> element = new ArrayList<>(result.get(j));
                //每次在上次的结果中多加 1 个重复数字
                for (int t=0;t<prenum;t++){
                    element.add(num[i]);
                    result.add(new ArrayList<>(element));
                }

            }
        }

        return result;
    }

    //位操作
    public List<List<Integer>> subsetsWithDup4(int[] num){
        Arrays.sort(num);
        List<List<Integer>> lists = new ArrayList<>();
        int subsetNum = 1<<num.length;
        for (int i=0;i<subsetNum;i++){
            List<Integer> list = new ArrayList<>();
            boolean illegal =false;
            for (int j=0;j<num.length;j++){
                //当前位为1
                if ((i>>j&1) ==1){
                    //当前是重复数字，并且前一位是0，跳过这种情况
                    if (j>0 && num[j] == num[j-1] && (i>>(j-1)&1)==0){
                        illegal = true;
                        break;
                    } else {
                        list.add(num[i]);
                    }
                }
            }
            if (!illegal){
                lists.add(list);
            }
        }

        return lists;
    }
}
