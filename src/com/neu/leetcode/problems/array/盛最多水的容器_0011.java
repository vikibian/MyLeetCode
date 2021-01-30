package com.neu.leetcode.problems.array;

public class 盛最多水的容器_0011 {
    public int maxArea(int[] height) {
        int max = 0;
        int nums = height.length;
        for (int i=0;i<nums;i++){
            for (int j=0;j<i;j++){
                int h = Math.min(height[j],height[i]);
                int width = i-j;
                max = Math.max(max,h*width);
            }
        }

        return max;
    }

    //官方题解 双指针 解法
    public int maxArea1(int[] height){
        int l=0,r = height.length -1;
        int ans = 0;
        while (l<r){
            int area = Math.min(height[l],height[r])*(r-l);
            ans = Math.max(area,ans);
            if (height[l] < height[r]){
                l++;
            } else {
                r--;
            }
        }

        return ans;
    }
}
