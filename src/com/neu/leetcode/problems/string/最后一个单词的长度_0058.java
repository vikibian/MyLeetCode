package com.neu.leetcode.problems.string;

public class 最后一个单词的长度_0058 {
    //true 简单
    public int lengthOfLastWord(String s) {
        String[] strs = s.split(" ");
        int len = strs.length;
        if (len == 0){
            return 0;
        }

        return strs[len-1].length();
    }

    //精选解答
    public int lengthOfLastWord1(String s) {
        int end = s.length()-1;
        while (end>=0&&s.charAt(end) == ' '){
            end--;
        }
        if (end<0){
            return 0;
        }
        int start = end;
        while (start>=0 && s.charAt(start)!= ' '){
            start--;
        }
        return end - start;
    }
}
