package com.neu.leetcode.problems.array;

public class 乘积最大子数组_0152 {
    //true 中等
    public int maxProduct(int[] nums) {
        int n = nums.length;
        int max = Integer.MIN_VALUE;
        for (int i=0;i<n;i++){
            int tmp = 1;
            for (int j=i;j>=0;j--){
                tmp = tmp*nums[j];
                max = Math.max(max,tmp);
            }
        }

        return max;
    }

    //官方题解 动态优化
    public int maxProduct1(int[] nums){
        int length = nums.length;
        int[] maxF = new int[length];
        int[] minF = new int[length];
//        System.arraycopy(nums,0,maxF,0,length);
//        System.arraycopy(nums,0,minF,0,length);
        //其实上面注释掉的代码和下面的代码的作用一样  不需要复制整个数组
        maxF[0] = nums[0];
        minF[0] = nums[0];
        for (int i=1;i<length;i++){
            maxF[i] = Math.max(maxF[i-1]*nums[i],Math.max(minF[i-1]*nums[i],nums[i]));
            minF[i] = Math.min(minF[i-1]*nums[i],Math.min(nums[i],maxF[i-1]*nums[i]));
        }
        int ans = maxF[0];
        for (int i=1;i<length;i++){
            ans = Math.max(ans,maxF[i]);
        }

        return ans;
    }

    //优化空间
    public int maxProduct2(int[] nums){
        int length = nums.length;
        int ans = nums[0],maxF = nums[0],minF=nums[0];
        for (int i=1;i<length;i++){
            int mx = maxF,mn=minF;
            maxF = Math.max(mx*nums[i],Math.max(nums[i],mn*nums[i]));
            minF = Math.min(mn*nums[i],Math.min(nums[i],mx*nums[i]));
            ans = Math.max(maxF,ans);
        }

        return ans;
    }

    //画法题解
    public int maxProduct3(int[] nums){
        int max=Integer.MIN_VALUE,imax=1,imin=1;
        for (int i=0;i<nums.length;i++){
            if (nums[i] < 0){
                int temp = imax;
                imax = imin;
                imin = temp;
            }
            imax = Math.max(imax*nums[i],nums[i]);
            imin = Math.min(imin*nums[i],nums[i]);
            max = Math.max(max,imax);
        }
        return max;
    }
}
