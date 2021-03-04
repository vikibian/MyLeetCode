package com.neu.leetcode.problems.string;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class 有效的括号_0020 {

    public static void main(String[] args) {
//        System.out.println(isValid("(){}}{"));
    }

    //true easy
    public boolean isValid(String s) {
        int len = s.length();
        if (len== 1){
            return false;
        }
        Deque<String> deque = new LinkedList<>();
        for (int i=0;i<len;i++){
            String temp = s.substring(i,i+1);
            if (!deque.isEmpty() && getPair(temp).equals(deque.getLast())){
                if (isPair(deque.getLast(),temp)){
                    deque.removeLast();
                } else {
                    return false;
                }

            } else {
                deque.addLast(temp);
            }
        }

        if (deque.isEmpty()){
            return true;
        } else {
            return false;
        }

    }

    public String getPair(String str){
        if ("(".equals(str)){
            return ")";
        } else if (")".equals(str)){
            return "(";
        } else if ("{".equals(str)){
            return "}";
        } else if ("}".equals(str)){
            return "{";
        } else if ("[".equals(str)){
            return "]";
        } else if ("]".equals(str)){
            return "[";
        }

        return "";
    }

    public boolean isPair(String str1,String str2){
        if ("(".equals(str1) && ")".equals(str2)){
            return true;
        }
        if ("[".equals(str1) && "]".equals(str2)){
            return true;
        }
        if ("{".equals(str1) && "}".equals(str2)){
            return true;
        }

        return false;
    }


    //栈
    public boolean isValid1(String s) {
        int n = s.length();
        if (n % 2 ==1){
            return false;
        }
        Map<Character,Character> pairs = new HashMap<Character,Character>(){
            {
                put(')', '(');
                put(']', '[');
                put('}', '{');
            }
        };
        Deque<Character> stack = new LinkedList<>();
        for (int i=0;i<n;i++){
            char ch = s.charAt(i);
            if (pairs.containsKey(ch)){
                if (stack.isEmpty() || stack.peek() != pairs.get(ch) ){
                    return false;
                }
                stack.pop();
            } else {
                stack.push(ch);
            }
        }

        return stack.isEmpty();
    }
}
