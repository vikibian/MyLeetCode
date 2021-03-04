package com.neu.leetcode.problems.string;

import java.util.Map;

public class 最长公共前缀_0014 {
    //true esay
    public String longestCommonPrefix(String[] strs) {
        int size = strs.length;
        String preStr = "";
        if (size == 0){
            return preStr;
        }
        preStr = strs[0];
        for (int i=1;i<size;i++){
            preStr = compareStr(preStr,strs[i]);
            if ("".equals(preStr)){
                break;
            }
        }
        return preStr;
    }

    public String compareStr(String str1,String str2){
        StringBuilder ans = new StringBuilder();
        int index1=0,index2 = 0;
        int len1 = str1.length(),len2 = str2.length();
        while (index1<len1 && index2<len2){
            String sub1 = str1.substring(index1,index1+1);
            String sub2 = str2.substring(index2,index2+1);
            if ( sub1.equals(sub2) ){
                ans.append(sub1);
                index1++;
                index2++;
            } else {
                break;
            }
        }
        return ans.toString();
    }

    //官方题解 横向扫描
    public String longestCommonPrefix1(String[] str){
        if (str == null || str.length == 0){
            return "";
        }
        String prefix = str[0];
        int count = str.length;
        for (int i=0;i<count;i++){
            if (prefix.length() == 0){
                break;
            }
        }
        return prefix;
    }
    public String longestCommonPrefix(String str1,String str2){
        int length = Math.max(str1.length(),str2.length());
        int index = 0;
        while (index < length && str1.charAt(index) == str2.charAt(index)){
            index++;
        }
        return str1.substring(0,index);
    }

    //官方题解 纵向扫描
    public String longestCommonPrefix2(String[] str){
        if (str == null || str.length == 0){
            return "";
        }

        int length = str[0].length();
        int count = str.length;
        for (int i =0 ;i<length;i++){
            char c = str[0].charAt(i);
            for (int j=1;j<count;j++){
                if (i == str[j].length() || c != str[j].charAt(i)){
                    return str[0].substring(0,i);
                }
            }
        }
        return str[0];
    }

    //官方题解 分治算法
    public String longestCommonPrefix3(String[] str){
        if (str == null || str.length == 0){
            return "";
        } else {
            return longestCommonPrefix(str,0,str.length - 1);
        }
    }

    public String longestCommonPrefix(String[] str, int start, int end){
        if (start == end){
            return str[start];
        } else {
            int mid = (start + end) / 2;
            String lcpleft = longestCommonPrefix(str,start,mid);
            String lcpright = longestCommonPrefix(str,mid+1,end);

            return commonPrefix(lcpleft,lcpright);
        }
    }

    public String commonPrefix(String lcpleft,String lcpright){
        int minLength = Math.min(lcpleft.length(),lcpright.length());
        for (int i=0;i<minLength;i++){
            if (lcpleft.charAt(i) != lcpright.charAt(i)){
                return lcpleft.substring(0,i);
            }
        }
        return lcpleft.substring(0,minLength);
    }

    //官方题解 二分查找
    public String longestCommonPrefix4(String[] strs) {
        if (strs == null || strs.length == 0){
            return "";
        }
        int minLength = Integer.MAX_VALUE;
        for (String str : strs){
            minLength = Math.min(str.length(),minLength);
        }
        int low =0,high = minLength;
        while (low < high){
            int mid = (high - low)/2 +low;
            if (isCommonPrefix(strs,mid)){
                low = mid;
            } else {
                high = mid-1;
            }
        }

        return strs[0].substring(0,low);//注意这里是low
    }

    private boolean isCommonPrefix(String[] strs, int mid) {
        String str0 = strs[0].substring(0,mid);
        int count = strs.length;
        for (int i=1;i<count;i++){
            String str = strs[i];
            for (int j=0;j<mid;j++){
                if (str0.charAt(j) != str.charAt(j)){
                    return false;
                }
            }
        }
        return true;
    }
}
