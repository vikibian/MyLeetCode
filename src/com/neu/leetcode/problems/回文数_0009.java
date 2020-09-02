package com.neu.leetcode.problems;

public class 回文数_0009 {
    public static void main(String[] args) {
        int t1 = 121;
        int t2 = -121;
        int t3 = 10;
        boolean palindrome = isPalindrome(t3);
        System.out.println("result:"+palindrome);
    }

    public static boolean isPalindrome(int x) {
        String s = String.valueOf(x);
        boolean flag = true;
        int head = 0;
        int end = s.length()-1;
        while (head<= end){
            if (s.substring(head,head+1).equals(s.substring(end,end+1))){
                head++;
                end--;
            } else {
                return false;
            }
        }

        return flag;
    }
}
