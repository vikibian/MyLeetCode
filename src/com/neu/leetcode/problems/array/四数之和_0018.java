package com.neu.leetcode.problems.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class 四数之和_0018 {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        int len = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> lists = new LinkedList<>();
        for (int i=0;i<len;i++){
            if (i>0 && nums[i] == nums[i-1]){
                continue;
            }
            for (int j=i+1;j<len;j++){
                if (j>i+1 && nums[j] == nums[j-1]){
                    continue;
                }

                int four = len -1;

                for (int m = j+1;m<len;m++){
                    if (m >j+1&&nums[m] == nums[m-1]){
                        continue;
                    }
                    //后面那个 是>target  而不是 !=  肯定要大于  小于target的话 不需要操作
                    while (i<j && j<m && m<four && nums[i] + nums[j] + nums[m] +nums[four] >target ){
                        four--;
                    }

                    if (m == four){
                        break;
                    }

                    if (nums[i]+nums[j]+nums[m]+nums[four] == target){
                        List<Integer> list = new LinkedList<>();
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[m]);
                        list.add(nums[four]);
                        lists.add(list);
                    }
                }
            }
        }

        return lists;
    }

    //官方题解
    public List<List<Integer>> fourSum1(int[] nums, int target) {
        List<List<Integer>>  lists = new ArrayList<>();
        if (nums == null || nums.length < 4){
            return lists;
        }
        Arrays.sort(nums);
        int len = nums.length;
        for (int i=0;i<len -3;i++){
            if (i>0 && nums[i] == nums[i-1]){
                continue;
            }

            if (nums[i] + nums[i+1]+nums[2]+nums[3] > target){
                break;
            }

            if (nums[i] + nums[len-3] + nums[len -2] + nums[len-1] < target){
                continue;
            }

            for (int j=i+1;j<len-2;j++){
                if (j>i+1 && nums[j] == nums[j-1]){
                    continue;
                }

                if (nums[i] + nums[j] + nums[j+1] + nums[j+2] > target){
                    break;
                }

                if (nums[i] + nums[j] + nums[len-2]+nums[len-1] < target){
                    continue;
                }

                int left = j+1,right= len-1;
                while (left<right){
                    int sum = nums[i]+nums[j]+nums[left]+nums[right];

                    if (sum == target){
                        lists.add(Arrays.asList(nums[i],nums[j],nums[left],nums[right]));
                        while (left < right && nums[left] == nums[left+1]){
                            left++;
                        }
                        left++;
                        while (left < right && nums[right] == nums[right - 1] ){
                            right--;
                        }
                        right--;
                    } else if (sum > target){
                        right--;
                    } else {
                        left++;
                    }
                }
            }
        }
        return lists;
    }
}
