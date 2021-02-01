package com.neu.leetcode.problems.dynamicprogramming;


import java.util.stream.StreamSupport;

public class 最大子序和_0053 {

    public static void main(String[] args) {
        int[] nums = new int[]{-1,-2};
//        int[] nums = new int[]{-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(maxSubArray(nums));
    }

    public static int maxSubArray(int[] nums) {
        int ans = Integer.MIN_VALUE;
        int len = nums.length;
//        if (len == 1){
//            return nums[0];
//        }
        int sum= 0;
        for (int i=0;i<len;i++){
//            sum = nums[i];
            for (int j=i;j<len;j++){
                sum = sum + nums[j];
                ans = Math.max(ans,sum);
            }
//            ans = Math.max(ans,sum);
            sum = 0;
        }

        return ans;
    }

    //贪心算法
    //算法核心：若当前指针所指元素之前的和小于0则丢弃当前元素之和
    public int maxSubArray1(int[] nums){
        int cur =0,max = 0;
        cur = max = nums[0];
        int len = nums.length;
        for (int i=1;i<len;i++){
            cur = Math.max(nums[i],cur+nums[i]);
            max = Math.max(cur,max);
        }
        return max;
    }

    //动态规划
    //核心算法：若前一个元素大于0， 则将其加到当前元素上
    public int maxSubArray2(int[] nums){
        int len = nums.length;
        int ans = Integer.MIN_VALUE;
        for (int i=1;i<len;i++){
            if (nums[i-1] >0){
                nums[i] = nums[i] + nums[i-1];
            }
        }
        for (int i=0;i<len;i++){
            ans = Math.max(nums[i],ans);
        }
        return ans;
    }

    //分治算法
    public class  Status{
        public int lSum,rSum,mSum,iSum;

        public Status(int lSum,int rSum,int mSum,int iSum){
            this.lSum = lSum;
            this.rSum = rSum;
            this.mSum = mSum;
            this.iSum = iSum;
        }
    }

    public int maxSubArray3(int[] nums){
        return getInfo(nums,0,nums.length).mSum;
    }

    public Status getInfo(int[] nums,int l,int r){
        if (nums.length == 1){
            return new Status(nums[l],nums[l],nums[l],nums[l]);
        }
        int m  = (l + r) >> 1;
        Status lSub = getInfo(nums,l,m);
        Status rSub = getInfo(nums,m+1,r);
        return pushUp(lSub,rSub);
    }

    public Status pushUp(Status l,Status r){
        int iSum = l.iSum + r.iSum;
        int lSum = Math.max(l.lSum,l.iSum+r.lSum);
        int rSum = Math.max(r.rSum,l.rSum + r.iSum);
        int mSum = Math.max(Math.max(l.mSum,r.mSum),l.rSum + r.lSum);
        return new Status(lSum,rSum,iSum,mSum);
    }
}
