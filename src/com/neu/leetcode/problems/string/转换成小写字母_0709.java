package com.neu.leetcode.problems.string;

public class 转换成小写字母_0709 {
    public String toLowerCase(String str) {
        int len = str.length();
        char[] chars = str.toCharArray();
        for (int i=0;i<len;i++){
            char c = chars[i];
            if (c>='A' && c<='Z'){
                int a = c-'A';
                chars[i] = (char)('a'+a);
            }
        }
        return new String(chars);
    }
}
