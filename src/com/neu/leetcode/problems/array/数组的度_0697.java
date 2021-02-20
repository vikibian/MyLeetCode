package com.neu.leetcode.problems.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 数组的度_0697 {

    public static void main(String[] args) {
        int[] nums = new int[]{2,1};
        System.out.println(findShortestSubArray(nums));
    }

    public static int findShortestSubArray(int[] nums) {
        int len = nums.length;
        Map<Integer, List<Integer>> map = new HashMap<>();
        int count = 1;
        int index= len;
        for (int i=0;i<len;i++){
            if (map.containsKey(nums[i])){
                map.get(nums[i]).add(i);

                if (count < map.get(nums[i]).size()){
                    count = map.get(nums[i]).size();

                }
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                map.put(nums[i],list);
            }
        }

        for (Map.Entry<Integer,List<Integer>> entry:map.entrySet()){
            if (entry.getValue().size() == count){
                int size = entry.getValue().size();
                index = Math.min(index,entry.getValue().get(size-1)-entry.getValue().get(0)+1);
            }
        }


        return index;
    }

    //官方题解
    public int findShortestSubArray1(int[] nums) {
        Map<Integer, int[]> map = new HashMap<Integer, int[]>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (map.containsKey(nums[i])) {
                map.get(nums[i])[0]++;
                map.get(nums[i])[2] = i;
            } else {
                map.put(nums[i], new int[]{1, i, i});
            }
        }
        int maxNum = 0, minLen = 0;
        for (Map.Entry<Integer, int[]> entry : map.entrySet()) {
            int[] arr = entry.getValue();
            if (maxNum < arr[0]) {
                maxNum = arr[0];
                minLen = arr[2] - arr[1] + 1;
            } else if (maxNum == arr[0]) {
                if (minLen > arr[2] - arr[1] + 1) {
                    minLen = arr[2] - arr[1] + 1;
                }
            }
        }
        return minLen;
    }

}
