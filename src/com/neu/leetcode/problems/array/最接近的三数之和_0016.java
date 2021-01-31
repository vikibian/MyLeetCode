package com.neu.leetcode.problems.array;

import java.util.Arrays;

public class 最接近的三数之和_0016 {
    public static void main(String[] args) {
        int[] nums = new int[]{1,1,-1,-1,};
        int target = -1;
        System.out.println(threeSumClosest(nums,target));
    }

    public static int threeSumClosest(int[] nums, int target) {
        int len = nums.length;
        Integer res = null;
        Arrays.sort(nums);
        for (int first =0;first<len;first++){

            for (int second = first+1;second<len;second++){
                int third = len-1;
                if (second == third){
                    break;
                }

                while (second<third ){
                    if (res == null){
                        res = (nums[first] + nums[second] + nums[third]);
                    }
                    int res1 = Math.abs(res - target);
                    int res2 = Math.abs( (nums[first] + nums[second] + nums[third]) - target);
                    if (res1 > res2){
                        res = (nums[first] + nums[second] + nums[third]);
                    }
                    third--;
                }
            }
        }

        return res;
    }

    //官方题解
    public static int threeSumClosest1(int[] nums, int target) {
        int len = nums.length;
        int best = 10000000;
        Arrays.sort(nums);
        for (int i=0;i<len;i++){
            if (i>0 && nums[i] == nums[i-1]){
                continue;
            }
            int j = i+1,k = len - 1;
            while (j<k){
                int sum = nums[i] + nums[j] +nums[k];
                if (sum == target){
                    return target;
                }
                if (Math.abs(sum - target) < Math.abs(best - target)){
                    best = sum;
                }

                if (sum > target){
                    int k0 = k-1;
                    while (j<k0 && nums[k] == nums[k0]){
                        k0--;
                    }
                    k = k0;
                } else {
                    int j0 = j+1;
                    while (j0<k&&nums[j] == nums[j0]){
                        j0++;
                    }
                    j = j0;
                }
            }
        }
        return best;
    }
}
