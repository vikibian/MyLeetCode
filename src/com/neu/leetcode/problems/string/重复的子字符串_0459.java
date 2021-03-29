package com.neu.leetcode.problems.string;

import java.util.Arrays;

public class 重复的子字符串_0459 {
    public boolean repeatedSubstringPattern(String s) {
        int len = s.length();
        for (int i=1;i<=len;i++){
            boolean flag = false;
            if (len%i != 0 || i==len){
                continue;
            } else {
                String substr = s.substring(0,i);
                for (int j=0;j<len;j= j+i){
                    String sub = s.substring(j,j+i);
                    if (!sub.equals(substr)){
                        flag = false;
                        break;
                    } else {
                        flag = true;
                    }
                }
            }
            if (flag){
                return true;
            }
        }
        return false;
    }

    //官方题解 枚举法
    public boolean repeatedSubstringPattern1(String s) {
        int len = s.length();
        for (int i=1;i*2<=len;i++){
            if (len %i==0){
                boolean match = true;
                for (int j=i;j<len;j++){
                    if (s.charAt(j) != s.charAt(j-i)){
                        match = false;
                        break;
                    }
                }
                if (match){
                    return true;
                }
            }
        }
        return false;
    }

    //官方题解二 字符串匹配
    public boolean repeatedSubstringPattern2(String s){
        return (s+s).indexOf(s,1) != s.length();
    }


    public boolean repeatedSubstringPattern3(String s){
        return kmp(s+s,s);
    }

    private boolean kmp(String query, String pattern) {
        int n = query.length();
        int m = pattern.length();
        int[] next = kmpnext(pattern);
        for (int i=1,j=0;i<query.length();i++){
            while (j>0 && query.charAt(i) != query.charAt(j)){
                j = next[j-1];
            }
            if (query.charAt(i) == query.charAt(j)){
                j++;
            }
            if (j == query.length()){
                return true;
            }
        }
        return false;
    }

    private int[] kmpnext(String pattern) {
        int[] next = new int[pattern.length()];
        next[0] = 0;
        for (int i=1,j=0;i<pattern.length();i++){
            while (j>0 && pattern.charAt(j) != pattern.charAt(i)){
                j = next[j-1];
            }
            if (pattern.charAt(j) == pattern.charAt(i)){
                j++;
            }
            next[i] = j;
        }
        return next;
    }

    //官方题解三 kmp算法
    public boolean repeatedSubstringPattern4(String s){
        return kmp1(s+s,s);
    }

    private boolean kmp1(String query, String pattern) {
        int n= query.length();
        int m = pattern.length();
        int[] next = new int[m];
        Arrays.fill(next,-1);
        for (int i=1;i<m;i++){
            int j = next[i-1];
            while (j!=-1 && pattern.charAt(j+1) != pattern.charAt(i)){
                j = next[j];
            }
            if (pattern.charAt(j+1) == pattern.charAt(i)){
                next[i] = j+1;
            }
        }
        int match =-1;
        for (int i=1;i<n-1;i++){
            while (match!=-1&&pattern.charAt(match+1) != query.charAt(i)){
                match = next[match];
            }
            if (pattern.charAt(match+1) == query.charAt(i)){
                match++;
                if (match == m-1){
                    return true;
                }
            }
        }
        return false;
    }

    //官方题解四  优化kmp算法
    public boolean repeatedSubstringPattern5(String s) {
        return kmp2(s);
    }

    private boolean kmp2(String pattern) {
        int n= pattern.length();
        int[] next = new int[n];
        Arrays.fill(next,-1);
        for (int i=1;i<n;i++){
            int j = next[i-1];
            while (j!=-1 && pattern.charAt(j+1) != pattern.charAt(i)){
                j = next[j];
            }
            if (pattern.charAt(j+1) == pattern.charAt(i)){
                next[i] = j+1;
            }
        }
        return next[n-1] != 1 && n %(n-next[n-1]-1) == 0;
    }


}
