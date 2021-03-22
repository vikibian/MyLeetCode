package com.neu.leetcode.problems.array;

public class 两数之和2_输入有序数组_0167 {
    public int[] twoSum(int[] numbers, int target) {
        int[] ans = new int[2];
        int len = numbers.length;
        for (int i=0;i<len;i++){
            int index2 = getAnswer(numbers,i,target-numbers[i]);
            if (index2 != -1){

                ans[0] = i+1;
                ans[1] = index2+1;
                return ans;
            }
        }
        return ans;
    }

    public int getAnswer(int[] numbers,int index,int target){
        int left = index+1;
        int len = numbers.length;
        int right = len - 1;
        int res = -1;
        while (left <= right){
            int mid = left+(right-left)/2;
            if (numbers[mid] == target){
                res = mid;
                return res;
            } else if (numbers[mid] < target){
                left = mid+1;
            } else {
                right = mid -1;
            }
        }
        return res;
    }

    //高频题解 双指针
    public int[] twoSum1(int[] numbers,int target){
        if (numbers == null){
            return null;
        }
        int i=0,j=numbers.length-1;
        while (i<j){
            int sum = numbers[i] + numbers[j];
            if (sum == target){
                return new int[]{i+1,j+1};
            } else if (sum < target){
                i++;
            } else {
                j--;
            }
        }
        return null;
    }

    //官方题解 二分查找
    public int[] twoSum2(int[] numbers,int target){
        for (int i = 0;i<numbers.length;i++){
            int low = i+1,high = numbers.length-1;
            while (low<=high){
                int mid = (high-low)/2+low;
                if (numbers[mid] == target-numbers[i]){
                    return new int[]{i+1,mid+1};
                } else if (numbers[mid] > target-numbers[i]){
                    high = mid-1;
                } else {
                    low = mid+1;
                }
            }
        }
        return new int[]{-1,-1};
    }
}
