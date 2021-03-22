package com.neu.leetcode.problems.string;

public class 字符串中的单词数_0434 {
    public int countSegments(String s) {

        String[] strs = s.split("\\s+");

        int len = strs.length;
        int count = len;
        for (int i=0;i<len;i++){
            if (strs[i].equals("")){
                count--;
            }
        }
        return count;
    }

    //官方题解 使用语言内置函数
    public int countSegments1(String s){
        String trimmed = s.trim();
        if (trimmed.equals("")){
            return 0;
        }
        return trimmed.split("\\s+").length;
    }

    //官方题解 原地法
    public int countSegments2(String s){
        int segmentCount =0;
        for (int i=0;i<s.length();i++){
            if ((i==0 || s.charAt(i-1) == ' ') && s.charAt(i) != ' '){
                segmentCount++;
            }
        }
        return segmentCount;
    }
}
