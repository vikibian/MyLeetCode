package com.neu.leetcode.problems.stack;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class 逆波兰表达式_0150 {
    public int evalRPN(String[] tokens) {
        Stack<String> stack = new Stack<>();
        int res = 0;
        int len = tokens.length;

        for (int i=0;i<len;i++){
            String str = tokens[i];
            if ("+".equals(str)){
                int num2 = Integer.valueOf(stack.pop());
                int num1 = Integer.valueOf(stack.pop());
                int ans = num1 + num2;
                stack.push(ans+"");
            } else if ("-".equals(str)){
                int num2 = Integer.valueOf(stack.pop());
                int num1 = Integer.valueOf(stack.pop());
                int ans = num1 - num2;
                stack.push(ans+"");
            } else if ("*".equals(str)){
                int num2 = Integer.valueOf(stack.pop());
                int num1 = Integer.valueOf(stack.pop());
                int ans = num1 * num2;
                stack.push(ans+"");
            } else if ("/".equals(str)){
                int num2 = Integer.valueOf(stack.pop());
                int num1 = Integer.valueOf(stack.pop());
                int ans = num1 / num2;
                stack.push(ans+"");
            } else {
                stack.push(str);
            }
        }

        return Integer.valueOf(stack.pop());
    }

    //官方题解 栈
    public int evalRPN1(String[] tokens) {

        Deque<Integer> stack = new LinkedList<Integer>();
        int n = tokens.length;
        for (int i = 0; i < n; i++) {
            String token = tokens[i];
            if (isNumber(token)) {
                stack.push(Integer.parseInt(token));
            } else {
                int num2 = stack.pop();
                int num1 = stack.pop();
                switch (token) {
                    case "+":
                        stack.push(num1 + num2);
                        break;
                    case "-":
                        stack.push(num1 - num2);
                        break;
                    case "*":
                        stack.push(num1 * num2);
                        break;
                    case "/":
                        stack.push(num1 / num2);
                        break;
                    default:
                }
            }
        }
        return stack.pop();

    }

    public boolean isNumber(String token) {
        return !("+".equals(token) || "-".equals(token) || "*".equals(token) || "/".equals(token));
    }

    //官方题解  数组
    public int evalRPN2(String[] tokens) {
        int n = tokens.length;
        int[] stack = new int[(n+1)/2];
        int index= -1;

        for (int i=0;i<n;i++){
            String token = tokens[i];
            switch (token){
                case "+":
                    index--;
                    stack[index] += stack[index+1];
                    break;
                case "-":
                    index--;
                    stack[index] -= stack[index+1];
                    break;
                case "*":
                    index--;
                    stack[index] *= stack[index+1];
                    break;
                case "/":
                    index--;
                    stack[index] /= stack[index+1];
                    break;
                default:
                    index++;
                    stack[index] = Integer.valueOf(token);
                    break;
            }
        }

        return stack[index];
    }
}
