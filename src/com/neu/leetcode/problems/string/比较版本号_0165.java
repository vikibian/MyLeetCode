package com.neu.leetcode.problems.string;

public class 比较版本号_0165 {
    public int compareVersion(String version1, String version2) {
        String[] str1 = version1.split("\\.");
        String[] str2 = version2.split("\\.");
        int len1 = str1.length;
        int len2 = str2.length;

        int size = Math.max(len1,len2);
        for (int i=0;i<size;i++){
            String s1 = (i<len1?str1[i]:"0");
            String s2 = (i<len2?str2[i]:"0");
            int num1 = Integer.valueOf(s1);
            int num2 = Integer.valueOf(s2);
            if (num1<num2){
                return -1;
            } else if (num1>num2){
                return 1;
            }
        }
        return 0;
    }
}
