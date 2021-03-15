package com.neu.leetcode.problems.doublepointer;

public class 反转字符串_0344 {
    public void reverseString(char[] s) {
        int size = s.length;
        int left=0,right=size-1;

        while (left <= right){
            char c = s[left];
            s[left] = s[right];
            s[right] =c;
            left++;
            right--;
        }

    }
}
