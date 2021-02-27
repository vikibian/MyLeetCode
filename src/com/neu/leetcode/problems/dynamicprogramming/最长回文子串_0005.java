package com.neu.leetcode.problems.dynamicprogramming;

import java.util.LinkedHashMap;
import java.util.Map;

public class 最长回文子串_0005 {
    public static void main(String[] args) {
        String s = "babad";
        String s1 = "cbaabd";
        String s2 = "ccc";
//        System.err.println(s.substring(2,3));
//        System.err.println(s1.substring(3,5));
        String res = longestPalindrome(s2);
        System.out.println(res);
    }

    public static String longestPalindrome(String s) {
        String subString = "";

        if (s.length() == 1){
            subString = s;
        }else {
            int p = 0;
            while (p < s.length() ){
                if ( (p+1)<s.length() && s.charAt(p) == s.charAt(p+1)){
                    int temp1 = p;
                    int temp2 = (p+1);
                    while (temp1>=0 && temp2<s.length() && s.charAt(temp1)== s.charAt(temp2)){
                        temp1--;
                        temp2++;
                    }
                    String s1 = s.substring(temp1+1,temp2);
                    if (s1.length() > subString.length()){
                        subString = s1;
                    }
                }

                if ( (p-1)>=0 && (p+1)<s.length() && s.charAt((p+1))==s.charAt(p-1) ){
                    int temp1 = p-1;
                    int temp2 = (p+1);
                    while (temp1>=0 && temp2<s.length() && s.charAt(temp1) ==s.charAt(temp2)){
                        temp1--;
                        temp2++;
                    }
                    String s1 = s.substring(temp1+1,temp2);
                    if (s1.length() > subString.length()){
                        subString = s1;
                    }
                }

                p++;

                if (subString == ""){
                    subString = String.valueOf(s.charAt(p));
                }
            }
        }


//        while (point2<s.length()){
//            //找到相同的char之后 point1 ++
//            if (s.charAt(point1) == s.charAt(point2)){
//                if (((point2-point1) % 2 == 0)&&
//                s.substring(point1,(point2+point1)/2) == s.substring((point1+point2)/2 + 1,point2+1)){
////                    map.put(s.substring(point1,point2+1),point2 - point1 +1);
//                    if (subString.length() < s.substring(point1,point2+1).length()){
//                        subString = s.substring(point1,point2+1);
//                    }
//                }
//
//                if (((point2-point1) % 2 == 1)&&
//                        s.substring(point1,(point2+point1)/2+1) == s.substring((point1+point2)/2 + 1,point2+1)){
////                    map.put(s.substring(point1,point2+1),point2 - point1 +1);
//                    if (subString.length() < s.substring(point1,point2+1).length()){
//                        subString = s.substring(point1,point2+1);
//                    }
//
//                }
//                point1++;
//            }
//            point2++;
//        }

        return subString;
    }

    /**
     * 动态规划
     * @param s
     * @return
     */
    public static String longestPalindrome2(String s) {
        int len = s.length();
        if(len < 2){
            return s;
        }

        int maxlen = 1;
        int begin = 0;
        boolean[][] dp = new boolean[len][len];
        for (int i=0;i<len;i++){
            dp[i][i] = true;
        }

        char[] chaArray = s.toCharArray();
        for (int j=0;j<len;j++){
            for (int i=0;i<j;i++){
                if (chaArray[i] != chaArray[j]){
                    dp[i][j] = false;
                }else {
                    if (j-i<3){
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i+1][j-1];
                    }
                }

                if (dp[i][j] ==true && j - i+1 >maxlen){
                    maxlen = j-i+1;
                    begin = i;
                }
            }
        }
        return s.substring(begin,begin+maxlen);
    }

    /**
     * 中心扩散
     * @param s
     * @return
     */
    public static String longestPalindrome3(String s) {
        if (s == null || s.length() < 1) return "";

        int start =0,end = 0;
        for (int i=0;i<s.length();i++){
            int len1 = expandAroundCenter(s,i,i);
            int len2 = expandAroundCenter(s,i,i+1);
            int len = Math.max(len1,len2);
            if (len > end - start){
                start = i - (len - 1)/2;
                end = i + len /2;
            }
        }

        return s.substring(start,end+1);
    }

    private static int expandAroundCenter(String s, int left, int right) {
        int L= left,R=right;
        if (L>=0 && R<s.length() && s.charAt(L) == s.charAt(R)){
            L--;
            R++;
        }
        return R-L-1;
    }

    /**
     * 马拉车算法
     * @param s
     * @return
     */
    public static String longestPalindrome4(String s) {
        int len = s.length();
        if (len<2){
            return s;
        }
        String str = addDividers(s,'#');
        int sLen = 2*len +1;

        //p[i]：以预处理字符串下表i为中心的回文半径（奇数长度时不包括中心）
        //p是palindromic的首字符
        int[] p = new int[sLen];

        //通过中心扩散方式能够扩散的最右边的下标
        int maxRight = 0;
        //与maxRight对应的中心字符的下标
        int center = 0;

        int maxLen = 1;
        int begin = 0;
        char[] charArray = str.toCharArray();

        for (int i=0;i<sLen;i++){
            if (i < maxRight){
                int mirror = 2 * center - i;
                //状态转移方程
                p[i] = Math.min(maxRight - i,p[mirror]);
            }

            //尝试使用中心扩散法，更新p[i]的值
            int left = i - (p[i] + 1);
            int right = i +(1 + p[i]);
            while (left >= 0 && right < sLen && charArray[left] == charArray[right]){
                p[i]++;
                left--;
                right++;
            }

            //更新maxRight,它是遍历的i的i+p[i] 的最大者
            if (p[i] + i > maxRight){
                //maxRight 和 center 同时更新
                maxRight = p[i] + i;
                center = i;
            }

            //记录最长回文子串的长度和相应它的原始字符串的起点
            if (p[i] > maxLen){
                maxLen = p[i];
                begin = (i - maxLen) / 2;
            }
        }


        return s.substring(begin,begin+maxLen);
    }

    /**
     * 创建预处理字符串
     * @param s
     * @param divider 分割字符
     * @return
     */
    private static String addDividers(String s,char divider){
        if (s.indexOf(divider) != -1){
            throw new IllegalArgumentException("参数错误，您传递的分割字符，在输入字符串中存在");
        }
        char[] charArray = s.toCharArray();
        int len = s.length();

        StringBuilder stringBuilder = new StringBuilder();
        for (int i=0;i<len;i++){
            stringBuilder.append(divider);
            stringBuilder.append(charArray[i]);
        }
        stringBuilder.append(divider);
        return stringBuilder.toString();
    }
}
