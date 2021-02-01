package com.neu.leetcode.problems.array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class 公平的糖果棒交换_0888 {
    public int[] fairCandySwap(int[] A, int[] B) {
        int sumA = 0;
        int sumB = 0;
        for (int num:A){
            sumA = sumA + num;
        }

        for (int num:B){
            sumB = sumB + num;
        }

        Arrays.sort(A);
        Arrays.sort(B);

        int indexA = 0,indexB = 0;
        while (indexA<A.length && indexB <B.length){
            int sA = sumA;
            int sB = sumB;
            sA = sA - A[indexA] +B[indexB];
            sB = sB - B[indexB] + A[indexA];

            if (sA == sB){
                break;
            } else if (sA <sB){
                indexB++;
            } else {
                indexA++;
            }
        }
        return new int[] {A[indexA],B[indexB]};
    }

    //官方题解
    public int[] fairCandySwap1(int[] A, int[] B) {
        int[] ans = new int[2];
        int sumA = Arrays.stream(A).sum();
        int sumB = Arrays.stream(B).sum();
        int delt = (sumA +sumB) /2;
        Set<Integer> rec = new HashSet<>();
        for (int num:A){
            rec.add(num);
        }
        for (int y:B){
            int x = y + delt;
            if (rec.contains(x)){
                ans[0] = x;
                ans[1] = y;
                break;
            }
        }
        return ans;
    }
}
