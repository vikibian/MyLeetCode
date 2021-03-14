package com.neu.leetcode.problems.stack;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class 基本计算器2_0227 {

    public static void main(String[] args) {
        System.out.println(calculate("3+2*2"));
        System.out.println(calculate("3/2"));
        System.out.println(calculate(" 3+5 / 2 "));
        System.out.println(calculate("1-1+1"));
    }

    public static int calculate(String s) {
        int len = s.length();
        Stack<Integer> stackNum = new Stack<>();
        Stack<String> stackSymbol = new Stack<>();
        int index = 0;
        while (index < len) {
            String str = s.substring(index, (index + 1 >len?len:(index+1)));
            int start = index;
            if (str.equals("-") ){
                index++;
                str = str+s.substring(index, (index + 1 >len?len:(index+1)));
            }
            String b = str;
            int index2 = index;
            while (index2<len && !b.equals("*")  && !b.equals("/") && !b.equals("-") && !b.equals("+") &&!b.equals(" ")) {
                index2++;
                b = s.substring(index2, (index2 + 1 >len?len:(index2+1)) );

            }

            str = s.substring(start, (index2 == start ? start + 1 : index2));

            if (str.equals("*") || str.equals("/")){
                stackSymbol.push(str);
            } else if (str.equals("+") || str.equals("-")){
                stackSymbol.push(str);
            } else if (str.equals(" ")){

            } else {

                if (stackNum.isEmpty()){
                    stackNum.push(Integer.parseInt(str));
                } else {
                    if (!stackSymbol.isEmpty() && (stackSymbol.peek().equals("*")||stackSymbol.peek().equals("/"))){
                        int post = Integer.parseInt(str);
                        int pre = stackNum.pop();
                        if (stackSymbol.peek().equals("*")){
                            stackNum.push(pre*post);
                        } else {
                            stackNum.push(pre/post);
                        }
                        stackSymbol.pop();
                    } else {
                        int num = Integer.parseInt(str);
                        if (num<0){
                            stackNum.push(num);
                            stackSymbol.push("+");
                        } else {
                            stackNum.push(num);
                        }

                    }
                }
            }

            if (index == (index2-1)){
                index++;
            } else if (index == index2){
                index++;
            } else {
                index = index2;
            }
        }

        while (!stackSymbol.isEmpty()) {
            int post = stackNum.pop();
            int pre = stackNum.pop();
            String s1 = stackSymbol.pop();
            if (s1.equals("-")) {
                stackNum.push(pre - post);
            } else {
                stackNum.push(pre + post);
            }
        }

        return stackNum.peek();
    }

    //官方题解 栈
    public int calculate1(String s) {
        Deque<Integer> deque = new LinkedList<>();
        char preSign = '+';
        int num = 0;
        int n = s.length();
        for (int i=0;i<n;i++){
            if (Character.isDigit(s.charAt(i))){
                num = num*10+s.charAt(i)-'0';
            }
            if (!Character.isDigit(s.charAt(i)) && s.charAt(i)!=' ' || i == n-1){
                switch (preSign){
                    case '+':
                        deque.push(num);
                        break;
                    case '-':
                        deque.push(-num);
                        break;
                    case '*':
                        deque.push(deque.pop() * num);
                        break;
                    case '/':
                        deque.push(deque.pop() / num);
                        break;
                };
                preSign = s.charAt(i);
                num=0;
            }
        }

        int ans = 0;
        while (deque.isEmpty()){
            ans = ans+deque.pop();
        }
        return ans;
    }
}
