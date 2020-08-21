package com.neu.leetcode.problems;

import java.util.LinkedHashMap;
import java.util.Map;

public class 最长回文子串_0005 {
    public static void main(String[] args) {
        String s = "abc";
        String s1 = "cbaabd";
//        System.err.println(s.substring(2,3));
//        System.err.println(s1.substring(3,5));
        String s2 = longestPalindrome(s1);
        System.err.println(s2);
    }

    public static String longestPalindrome(String s) {
        int point1=0,point2=1;
        int start = 0, end=0;
        String subString = "";

        while (point2<s.length()){
            //找到相同的char之后 point1 ++
            if (s.charAt(point1) == s.charAt(point2)){
                if (((point2-point1) % 2 == 0)&&
                s.substring(point1,(point2+point1)/2) == s.substring((point1+point2)/2 + 1,point2+1)){
//                    map.put(s.substring(point1,point2+1),point2 - point1 +1);
                    if (subString.length() < s.substring(point1,point2+1).length()){
                        subString = s.substring(point1,point2+1);
                    }
                }

                if (((point2-point1) % 2 == 1)&&
                        s.substring(point1,(point2+point1)/2+1) == s.substring((point1+point2)/2 + 1,point2+1)){
//                    map.put(s.substring(point1,point2+1),point2 - point1 +1);
                    if (subString.length() < s.substring(point1,point2+1).length()){
                        subString = s.substring(point1,point2+1);
                    }

                }
                point1++;
            }
            point2++;
        }

        return subString;
    }
}
