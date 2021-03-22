package com.neu.leetcode.problems.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class 多数元素_0169 {

    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length;
        int count = 1;

        for (int i=1;i<len;i++){
            if (nums[i-1] == nums[i]){
                count++;
            } else {
                if (count > len/2){
                    return nums[i-1];
                }
            }
        }
        return nums[len-1];
    }

    //官方题解 哈希表
    public int majorityElement1(int[] nums){
        Map<Integer,Integer> counts = countNums(nums);
        Map.Entry<Integer,Integer> majorityEntry = null;
        for (Map.Entry<Integer,Integer> entry:counts.entrySet()){
            if (majorityEntry == null || majorityEntry.getValue()<entry.getValue()){
                majorityEntry = entry;
            }
        }
        return majorityEntry.getValue();
    }

    private Map<Integer, Integer> countNums(int[] nums) {
        Map<Integer,Integer> counts = new HashMap<>();
        for (int num : nums){
            if (!counts.containsKey(num)){
                counts.put(num,1);
            } else {
                counts.put(num,counts.get(num)+1);
            }
        }
        return null;
    }

    //官方题解 排序 排序后下标为n/2的元素一定是众数
    public int majorityElement2(int[] nums){
        Arrays.sort(nums);
        return nums[nums.length/2];
    }

    //官方题解 随机化
    public int majorityElement3(int[] nums){
        Random random = new Random();
        int majority = nums.length/2;
        while (true){
            int candidate = nums[randRange(random,0,nums.length)];
            if (countOccurence(nums,candidate) > majority){
                return candidate;
            }
        }
    }

    private int countOccurence(int[] nums, int candidate) {
        int count = 0;
        for (int i=0;i<nums.length;i++){
            if (nums[i] == candidate){
                count++;
            }
        }
        return count;
    }

    private int randRange(Random random, int min, int max) {

        return random.nextInt(max-min)+min;
    }

    //官方题解 分治算法
    public int majorityElement4(int[] nums){
        return majorityElementRec(nums,0,nums.length-1);
        
    }

    private int majorityElementRec(int[] nums, int lo, int hi) {
        if (lo == hi){
            return nums[lo];
        }

        int mid = (hi-lo)+lo;
        int left = majorityElementRec(nums,lo,mid);
        int right = majorityElementRec(nums,mid+1,hi);

        if (left == right){
            return left;
        }

        int leftCount = countInRange(nums,left,lo,hi);
        int rightCount = countInRange(nums,right,lo,hi);


        return leftCount>rightCount ? left:right;
    }

    private int countInRange(int[] nums, int num, int lo, int hi) {
        int count = 0;
        for (int i=0;i<nums.length;i++){
            if (nums[i] == num){
                count++;
            }
        }
        return count;
    }

    //官方题解 Boyer-Moore 投票算法
    public int majorityElement5(int[] nums){
        int count = 0;
        Integer candidate = null;
        for (int num : nums){
            if (count == 0){
                candidate = num;
            }

            count += (num == candidate)?1:-1;
        }
        return candidate;
    }
}
