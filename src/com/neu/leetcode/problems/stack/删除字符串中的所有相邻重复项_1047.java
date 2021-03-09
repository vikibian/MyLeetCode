package com.neu.leetcode.problems.stack;

import java.util.Stack;

public class 删除字符串中的所有相邻重复项_1047 {

    public static void main(String[] args) {
        String s = "abbaca";
        System.out.println(removeDuplicates(s));
    }

    public static String removeDuplicates(String S) {
        int len = S.length();
        int index = 0;
//        String s = new String(S);
        StringBuilder stringBuilder = new StringBuilder(S);

        int nums = -1;
        while ((nums = isHuiwen(stringBuilder.toString()))!= -1 ){
            stringBuilder = stringBuilder.delete(nums,nums+2);

        }
        return stringBuilder.toString();
    }

    public static int isHuiwen(String s){
        int len = s.length();
        int index = 0;
        while (index<len-1){
            if (s.charAt(index) == s.charAt(index+1)){
                return index;
            }
            index++;
        }

        return -1;
    }

    //栈
    public static String removeDuplicates1(String S) {
        StringBuffer stringBuffer = new StringBuffer();
        int top = -1;
        for (int i=0;i<S.length();i++){
            char ch = S.charAt(i);
            if (top>=0 && stringBuffer.charAt(top) == ch){
                stringBuffer.deleteCharAt(top);
                top--;
            } else {
                stringBuffer.append(ch);
                top++;
            }
        }
        return stringBuffer.toString();
    }

    //栈
    public String removeDuplicates2(String S){
        char[] s = S.toCharArray();
        int len = S.length();
        Stack<Character> stack = new Stack<>();
        for (int i=0;i<len;i++){
            char ch = s[i];
            if (!stack.isEmpty() && stack.peek() == ch){
                stack.pop();
            } else {
                stack.push(ch);
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Character c : stack){
            stringBuilder.append(c);
        }
        return stringBuilder.toString();
    }

    //纯数字解法
    public String removeDuplicates3(String s){
        char[] cs = s.toCharArray();
        char[] d = new char[s.length()];
        int hh=0,tt=-1;
        for (char ch : cs){
            if (hh<=tt && d[tt] == ch){
                tt--;
            } else {
                tt++;
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        while (hh<=tt){
            stringBuilder.append(d[hh++]);
        }
        return stringBuilder.toString();
    }
}
