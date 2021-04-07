package com.neu.leetcode.problems.array;

import java.util.HashMap;
import java.util.Map;

public class 删除排序数组中的重复项2_0080 {
//    public int removeDuplicates(int[] nums) {
//        int num = 0;
//        int index=0;
//
//        int len = nums.length;
//
//        int p1 = nums[0],p2=0;
//        int count = 0;
//        while (index<len){
//            p2 = nums[index];
//
//            if (p1 == p2){
//                num++;
//                count++;
//                index++;
//                p1 = p2;
//                if (count == 2){
//                    count=1;
//                    int index2 = index;
//                    while (index2<len && nums[index2]==p1){
//                        index2++;
//                    }
//                    if (index2==len){
//                        nums[index] = nums[index2-1];
//                    } else {
//                        nums[index] = nums[index2];
//                    }
//
//                    p1 = nums[index];
//                    num++;
//                    index++;
//                }
//            } else if (p1<p2){
//                num++;
//                index++;
//                p1 = p2;
//                count=1;
//            }
//
//        }
//        return num;
//    }

//    public int removeDuplicates(int[] nums) {
//        int num = 0;
//        int index=0;
//
//        int len = nums.length;
//
//        int p1 = nums[0],p2=0;
//        int count = 0;
//        int gap = 0;
//        while (index<len){
//            p2 = nums[index];
//            if (p2 == p1){
//                count++;
//            } else if (p2>p1){
//                p1 = p2;
//                if (count>=2){
//                    int g = count-2;
//                    gap = gap+g;
//                    while (g>0){
//                        num++;
//                        nums[index-gap] = nums[index];
//                        g--;
//                    }
//                }else {
//                    num++;
//                    count = 0;
//                }
//            } else if (p2<p1){
//                nums[index-gap] = nums[index];
//            }
//            index++;
//        }
//        return num;
//    }

//    public int removeDuplicates2(int[] nums) {
//        int num = 0;
//        Map<Integer,Integer> map = new HashMap<>();
//        Map<Integer,Integer> mapForIndex = new HashMap<>();
//        int len = nums.length;
//        for (int i=0;i<len;i++){
//            if (map.containsKey(nums[i])){
//                map.put(nums[i],map.get(nums[i])+1);
//            }else {
//                map.put(nums[i],1);
//                mapForIndex.put(nums[i],i);
//            }
//        }
//        int index = 0;
//        int size = map.size();
//
//        while (index<len){
//            int n = nums[index];
//            int index2 = mapForIndex.get(n);
//            int count = map.get(n);
//        }
//
//        for (Map.Entry<Integer,Integer> entry:map.entrySet()){
//            if (entry.getValue() >= 2){
//                index = index+2;
//            } else {
//                index = index+1;
//            }
//        }
//        return num;
//    }

    //官方题解一  删除多余的重复项
    public int removeDuplicates(int[] nums) {
        int i=1,count=1,length = nums.length;
        while (i<length){
            if (nums[i] == nums[i-1]){
                count++;
                if (count>2){
                    remNums(nums,i);
                    i--;
                    length--;
                } else {
                    count = 1;
                }
                i++;
            }
        }
        return length;
    }

    private int[] remNums(int[] nums, int start) {
        for (int i=start+1;i<nums.length;i++){
            nums[i-1] = nums[i];
        }

        return nums;
    }


    //官方题解二 覆盖多余的重复项
    public int removeDuplicates1(int[] nums) {
        int j=1,count=1;
        for (int i=0;i<nums.length;i++){
            if (nums[i-1] == nums[i]){
                count++;
            } else {
                count = 1;
            }
            if (count>=2){
                nums[j++] = nums[i];
            }
        }

        return j;
    }

    //二次题解 双指针
    public int removeDuplicates2(int[] nums) {
        int len = nums.length;
        int index1=0,index2 = 0;
        int count = 0;
        int ans =0;
        while (index2<len){
            if (nums[index1] == nums[index2]){
                count++;
                if (count == 2){
                    count = 0;
                    while (index2<len && nums[index1] == nums[index2]){
                        index2++;
                    }
                    index1++;
                    nums[index1] = nums[index2-1];
                    count++;
                }else {
                    index2++;
                }
            } else {
                count=0;
                index1++;
                nums[index1] = nums[index2];
                index2++;
                count++;
            }

        }


        return index1+1;
    }

    //二次刷题的官方题解
    public int removeDuplicates3(int[] nums) {
        int n = nums.length;
        if (n<2){
            return n;
        }
        int slow=2,fast = 2;
        while (fast<n){
            if (nums[slow-2] != nums[fast]){
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        return slow;
    }

    //高分题解 宫水三叶
    public int removeDuplicates4(int[] nums){
        return process(nums,2);
    }

    private int process(int[] nums, int k) {
        int u =0;
        for (int x : nums){
            if (u<k || nums[u-k] != x){
                nums[u++] = x;
            }
        }
        return u;
    }

}
