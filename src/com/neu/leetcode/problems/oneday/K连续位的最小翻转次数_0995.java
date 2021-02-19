package com.neu.leetcode.problems.oneday;

public class K连续位的最小翻转次数_0995 {

    public static void main(String[] args) {
//        int[] nums = new int[]{1,1,1 };
        int[] nums = new int[]{0,0,0,1,0,1,1,0};
        System.out.println(minKBitFlips3(nums,3));
    }

    public static int minKBitFlips(int[] A, int K) {
        int len = A.length;
        int count = 0;
        int index = 0;
        while (index<len){
            if (A[index] == 1){
                if (index+K < len){
                    int test = test(A, index, K);
                    index = index+test;
                } else {
                    index++;
                }

            } else {
                if (index+K>len){
                    return -1;
                }
                int index2 =0;
                index2 = swap(A,index,K);
                index = index+index2;
                count++;

            }
        }
        return count;
    }

    private static int swap(int[] nums,int index,int k){
        int count = 0;
        boolean isFirst = true;
        int index2 = index;
        while (index2<index+k){
            if (nums[index2] == 1){
                nums[index2] = 0;
                isFirst = false;
            } else {
                if (isFirst){
                    count++;
                }
                nums[index2] = 1;
            }
            index2++;
        }
        return count;
    }

    private static int test(int[] nums,int index,int k){
        int count = 0;
        int index2 = index;
        while (index2<index+k){
            if (nums[index2] == 1){
                count++;
            } else {
                return count;
            }
            index2++;
        }
        return count;
    }

    //官方题解  差分数组
    public static int minKBitFlips1(int[] A, int K) {
        int n = A.length;
        int[] diff = new int[n+1];
        int ans=0,revCnt=0;
        for (int i=0;i<n;i++){
            revCnt += diff[i];
            if ((A[i]+revCnt)%2 == 0){
                if (i+K >n){
                    return -1;
                }
                ++ans;
                ++revCnt;
                --diff[i+K];
            }
        }
        return ans;
    }

    public static int minKBitFlips2(int[] A, int K) {
        int n = A.length;
        int[] diff = new int[n+1];
        int ans=0,revCnt=0;
        for (int i=0;i<n;i++){
            revCnt ^= diff[i];
            if ((A[i] == revCnt)){//A[i] ^ revCnt ==0
                if (i+K >n){
                    return -1;
                }
                ++ans;
                ++revCnt;
                --diff[i+K];
            }
        }
        return ans;
    }

    //滑动窗口
    public static int minKBitFlips3(int[] A, int K) {
        int n = A.length;
        int ans=0,revCnt=0;
        for (int i=0;i<n;i++){
            if (i>=K && A[i-K] >1){
                revCnt ^= 1;
                A[i-K] -= 2;//复原数组元素 若允许修改数组 则可以省略
            }
            if (A[i] == revCnt){
                if (i+K>n){
                    return -1;
                }
                ++ans;
                revCnt ^= 1;
                A[i] += 2;
            }
        }
        return ans;
    }


}
