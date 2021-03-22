package com.neu.leetcode.problems.string;

import javafx.util.Pair;

public class 比较版本号_0165 {

    //自己的答案与官方题解 中的第一个题解 两次遍历 线性空间 相同
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

    //官方题解 双指针 一次遍历 常数空间
    public int compareVersion1(String version1,String version2){
        int p1 =0,p2=0;
        int n1 = version1.length(),n2=version2.length();
        int i1,i2;
        Pair<Integer,Integer> pair ;
        while (p1<n1 || p2<n2){
            pair = getNextChunk(version1,n1,p1);
            i1 = pair.getKey();
            p1 = pair.getValue();

            pair = getNextChunk(version2,n2,p2);
            i2 = pair.getKey();
            p2 = pair.getValue();
            if (i1 != i2){
                return i1>i2?1:-1;
            }
        }

        return 0;
    }

    private Pair<Integer, Integer> getNextChunk(String version1, int n, int p) {
        if (p>n-1){
            return new Pair(0,p);
        }
        int i,pEnd = p;
        while (pEnd<n&&version1.charAt(pEnd) != '.'){
            pEnd++;
        }
        if (pEnd != n-1){
            i = Integer.parseInt(version1.substring(p,pEnd));
        } else {
            i = Integer.parseInt(version1.substring(p,n));
        }
        p = pEnd+1;
        return new Pair(i,p);
    }
}
