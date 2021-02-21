package com.neu.leetcode.problems.oneday;

public class 最大连续1的个数3_1004 {

    public static void main(String[] args) {
        int[] nums = new int[]{0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1}; //3
//        int[] nums = new int[]{1,1,1,0,0,0,1,1,1,1,0}; //2
//        int[] nums = new int[]{0,0,0,1}; //4
        System.out.println(longestOnes(nums,3));
    }

    public static int longestOnes(int[] A, int K) {
        int len = A.length;
        int index=0;
        int count0=0;
        int count1 = 0;
        int temp = 0;
        int tempIndex = 0;
        boolean isFirst = true;
        while (index<len){
            int num = A[index];
            if (isFirst ){
                isFirst = false;
                tempIndex = index+1;
            }
            if (num==0){
                count0++;
                temp++;
                if (count0>K){
                    temp--;
                    count0=0;
                    count1 = Math.max(count1,temp);
                    temp = 0;
                    isFirst = true;
                    index = tempIndex;
                    continue;
                }
            } else {

                temp++;
            }
            index++;
        }
        count1  = Math.max(count1,temp);
        return count1;
    }

    //官方题解 二分查找
    public int longestOnes1(int[] A, int K) {
        int n = A.length;
        int[] P = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            P[i] = P[i - 1] + (1 - A[i - 1]);
        }

        int ans = 0;
        for (int right = 0; right < n; ++right) {
            int left = binarySearch(P, P[right + 1] - K);
            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }

    public int binarySearch(int[] P, int target) {
        int low = 0, high = P.length - 1;
        while (low < high) {
            int mid = (high - low) / 2 + low;
            if (P[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    //滑动窗口
    public int longestOnes2(int[] A, int K) {
        int n = A.length;
        int left=0,lsum=0,rsum=0;
        int ans =0;
        for(int right=0;right<n;right++){
            rsum += (1-A[right]);
            while (lsum <rsum-K){
                lsum += 1-A[left];
                left++;
            }
            ans = Math.max(ans,right-left+1);
        }
        return ans;
    }
}
