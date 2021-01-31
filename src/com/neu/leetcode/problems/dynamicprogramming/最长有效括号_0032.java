package com.neu.leetcode.problems.dynamicprogramming;

import com.sun.org.apache.xalan.internal.res.XSLTErrorResources;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class 最长有效括号_0032 {

    public static void main(String[] args) {
//        String str = ")()())"; //4
//        String str = "()()"; //4
//        String str = "()(())"; //6
        String str = "()(()"; //6
        System.out.println(longestValidParentheses(str));
        // ()  (( )) )(
    }

    public static int longestValidParentheses(String s) {
        int maxlen = 0;
        int len = s.length();
        Deque<Character> stack = new LinkedList<>();
        int count =0;
        for(int i=0;i<len;i++){
            char c = s.charAt(i);

            if (stack.isEmpty()){
                if (c == ')'){
                    maxlen = Math.max(count*2,maxlen);
                    count = 0;
                }
                stack.push(c);
            } else {
                char c1 = stack.peek();
//                if (c1 == '(' && c ==')'){
//                    stack.pop();
//                    count++;
//                } else {
//                    stack.push(c);
////                    maxlen = Math.max(count*2,maxlen);
////                    count = 0;
//                }
                if (c1 == '(' && c ==')'){
                    stack.pop();
                    count++;
                } else {
                    stack.push(c);
                }
            }
        }

        maxlen = Math.max(count*2,maxlen);

        return maxlen;
    }

    //官方题解 暴力解法   主要是思路
    public static int longestValidParentheses1(String s) {
        int len = s.length();
        if (len < 2){
            return 0;
        }
        int n = len;
        if (n %2 !=0){
            n = n-1;
        }
        for (int i=n;i>=0;i = i-2){
            for (int j =0;j<n-i-1;j++){
                if (isValid(s,j,j+i)){
                    return i;
                }
            }
        }
        return 0;
    }

    private static boolean isValid(String s, int j, int i) {
        Stack<Character> stack = new Stack<>();
        for (int m=j;m<i;m++){
            if (s.charAt(m) == '('){
                stack.push(s.charAt(m));
            } else if (!stack.isEmpty() && stack.peek() == ')'){
                stack.pop();
            } else {
                return false;
            }
        }
        return stack.isEmpty();
    }

    //动态规划
    public static int longestValidParentheses2(String s) {
        int maxlen  = 0;
        int[] dp = new int[s.length()];
        for (int i=1;i<s.length();i++){
            if (s.charAt(i) == ')'){
                if (s.charAt(i-1) == '('){
                    dp[i] = ( i>=2?dp[i-2]:0)+2;
                } else if (i - dp[i-1] > 0 && dp[i - dp[i-1]-1] == '('){
                    dp[i] = dp[i-1] +2 +((i-dp[i-1]) >=2 ?dp[i-dp[i-1] -2]:0);
                }
                maxlen = Math.max(dp[i],maxlen);
            }
        }
        return maxlen;
    }

    //栈
    //始终保持栈底元素为当前已经遍历过的元素中「最后一个没有被匹配的右括号的下标」
    public static int longestValidParentheses3(String s) {
        int maxLen = 0;
        Deque<Integer> stack = new LinkedList<>();
        stack.push(-1);
        for (int i=0;i< s.length();i++){
            if (s.charAt(i) == '('){
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()){
                    stack.push(i);
                }else {
                    maxLen = Math.max(maxLen,i-stack.peek());
                }
            }
        }
        return maxLen;
    }

    //不需要额外空间
    public static int longestValidParentheses4(String s) {
        int left = 0,right = 0,maxlen = 0;
        for (int i=0;i<s.length();i++){
            if (s.charAt(i) =='('){
                left++;
            } else {
                right++;
            }
            if (left == right){
                maxlen = Math.max(maxlen,2 * right);
            } else if (right>left){
                left = right = 0;
            }
        }
        left = right = 0;
        for (int j=s.length()-1;j>=0;j--){
            if (s.charAt(j) == '('){
                left++;
            } else {
                right++;
            }

            if (left == right){
                maxlen = Math.max(maxlen,left*2);
            } else if (left>right){
                left = right =0;
            }
        }
        return maxlen;
    }
}
