package com.neu.leetcode.problems;

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
}
