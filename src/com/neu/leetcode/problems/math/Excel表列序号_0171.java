package com.neu.leetcode.problems.math;

public class Excel表列序号_0171 {

    public static void main(String[] args) {
        System.out.println(titleToNumber("AB"));
    }

    public static int titleToNumber(String columnTitle) {
        int len = columnTitle.length();
        int res = 0;
        for (int i=len-1;i>=0;i--){

            char c = columnTitle.charAt(i);
            int rest = (c-'A'+1);
            if (i == len-1){
                res = res+rest;
            } else {
                res = res+(int)Math.pow(26.0,(len-1)-i)*rest;
            }

        }
        return res;
    }

    //高频题解
    public int  titleToNumber1(String columnTitle){
        int ans= 0;
        for (int i=0;i<columnTitle.length();i++){
            int num = columnTitle.charAt(i)-'A'+1;
            ans = ans*26 + num;
        }
        return ans;
    }
}
