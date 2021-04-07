package com.neu.leetcode.problems.string;

import java.util.Arrays;
import java.util.HashSet;

public class 翻转字符串中的元音字母_0345 {
    public String reverseVowels(String s) {
        int left = 0,right = s.length()-1;
        Integer leftIndex = null;
        Integer rightIndex = null;
        StringBuffer stringBuffer = new StringBuffer(s);
        while (left<= right){
            String leftStr = s.substring(left,left+1);
            String rightStr = s.substring(right,right+1);
            if (isVowels(leftStr)){
                leftIndex = left;
            } else {
                left++;
            }

            if (isVowels(rightStr)){
                rightIndex = right;
            } else {
                right--;
            }
            if (leftIndex!= null && rightIndex!= null){
                char c = stringBuffer.charAt(leftIndex);
                stringBuffer.setCharAt(leftIndex,stringBuffer.charAt(rightIndex));
                stringBuffer.setCharAt(rightIndex,c);
                leftIndex = null;
                rightIndex = null;
                left++;
                right--;
            }
        }
        return stringBuffer.toString();
    }

    public boolean isVowels(String str){
        if ("a".equals(str) || "A".equals(str)||"e".equals(str)||"E".equals(str)||
                "i".equals(str) || "I".equals(str) || "o".equals(str) ||"O".equals(str)||
                "u".equals(str) || "U".equals(str)){
            return true;
        } else {
            return false;
        }
    }

    //高分题解  双指针解法
    private final HashSet<Character> vowels = new HashSet<>(
            Arrays.asList('a','e','i','o','u','A','E','I','O','U')
    );

    public String reverseVowels1(String s) {
        if (s == null){
            return null;
        }
        int low = 0,high =s.length()-1;
        char[] c = new char[s.length()];
        while (low <= high){
            char cl = s.charAt(low);
            char ch = s.charAt(high);
            if (!vowels.contains(cl)){
                c[low++] = cl;
            } else if (!vowels.contains(ch)){
                c[high--] = ch;
            } else {
                c[low++] = ch;
                c[high--] = cl;
            }
        }
        return new String(c);
    }
}
