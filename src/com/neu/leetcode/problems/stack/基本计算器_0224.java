package com.neu.leetcode.problems.stack;

import java.util.*;

public class 基本计算器_0224 {

    public static void main(String[] args) {
        System.out.println(Integer.parseInt("-2"));
        System.out.println(calculate("12 + 1"));
        System.out.println(calculate(" 2-1 + 2"));
        System.out.println(calculate1("(1+(4+5+2)-3)+(6+8)"));
        System.out.println(calculate("2147483647"));
        System.out.println(calculate1("-2+1"));

    }

    public static int calculate(String s) {
        int len = s.length();
        Stack<Integer> stackNum = new Stack<>();
        Stack<String> stackSymbol = new Stack<>();
        int index = 0;
        while (index < len) {
            String str = s.substring(index, (index + 1 >len?len:(index+1)));
            int start = index;
            if (str.equals("-") && ((index==0)||(index-1>=0 &&(s.charAt(index-1)=='('|| s.charAt(index-1)=='-' || s.charAt(index-1)=='+')))){
                index++;
                str = str+s.substring(index, (index + 1 >len?len:(index+1)));
            }
            String b = str;
            int index2 = index;
            while (index2<len && !b.equals("(")  && !b.equals(")") && !b.equals("-") && !b.equals("+") && !b.equals(" ")) {
                index2++;
                b = s.substring(index2, (index2 + 1 >len?len:(index2+1)) );

            }

            str = s.substring(start, (index2 == start ? start + 1 : index2));
            if (str.equals("(")) {
                stackSymbol.push(str);
            } else if (str.equals("+") || str.equals("-")) {
                stackSymbol.push(str);
            } else if (str.equals(" ") ) {

            } else if (str.equals(")")) {
                if (stackSymbol.peek().equals("(")) {
                    stackSymbol.pop();
                } else {
                    while (!stackSymbol.peek().equals("(")) {
                        int post = stackNum.pop();
                        int pre = stackNum.pop();
                        String s1 = stackSymbol.pop();
                        if (s1.equals("-")) {
                            stackNum.push(pre - post);
                        } else {
                            stackNum.push(pre + post);
                        }
                    }
                    stackSymbol.pop();
                }
            } else {
                //数字
                if (stackNum.isEmpty() || stackSymbol.peek().equals("(")) {
                    stackNum.push(Integer.parseInt(str));
                } else if (stackSymbol.peek().equals("-") || stackSymbol.peek().equals("+")) {
                    int nowNum = Integer.parseInt(str);
                    int preNum = stackNum.pop();
                    if (stackSymbol.peek().equals("-")){
                        stackNum.push(preNum - nowNum);
                    } else {
                        stackNum.push(preNum + nowNum);
                    }

                    stackSymbol.pop();
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

    //官方题解 括号展开+栈
    public static int calculate1(String s) {
        Deque<Integer> deque = new LinkedList<>();
        int sign = 1;
        deque.push(1);

        int ret = 0;
        int n= s.length();
        int i=0;
        while (i<n){
            if (s.charAt(i) == ' '){
                i++;
            } else if (s.charAt(i) == '+'){
                sign = deque.peek();
                i++;
            } else if (s.charAt(i) == '-'){
                sign = -deque.peek();
                i++;
            } else if (s.charAt(i) == '('){
                deque.push(sign);
                i++;
            } else if (s.charAt(i) == ')'){
                deque.pop();
                i++;
            } else {
                long num = 0;
                while (i<n && Character.isDigit(s.charAt(i))){
                    num = num*10 + s.charAt(i)-'0';
                    i++;
                }
                ret = (int) (ret+ sign*num);
            }
        }
        return ret;
    }

    //双栈解法
    public int calculate2(String s) {
        Deque<Integer> nums = new ArrayDeque<>();
        // 为了防止第一个数为负数，先往 nums 加个 0
        nums.addLast(0);
        // 将所有的空格去掉，并将 (- 替换为 (0-
        s.replace(" ","");
        s.replace("\\(-","(0-");
        Deque<Character> ops = new ArrayDeque<>();
        int n = s.length();
        char[] cs = s.toCharArray();
        for (int i=0;i<n;i++){
            char c= cs[i];
            if (c =='('){
                ops.addLast(c);
            } else if (c == ')'){
                // 计算到最近一个左括号为止
                while (!ops.isEmpty()){
                    char op = ops.peekLast();
                    if (op != '('){
                        clac(nums,ops);
                    } else {
                        ops.pollLast();
                        break;
                    }
                }
            } else {
                if (Character.isDigit(c)){
                    int u=0;
                    int j=i;
                    // 将从 i 位置开始后面的连续数字整体取出，加入 nums
                    while (j<n&&Character.isDigit(cs[j])){
                        u = u*10+(cs[j++] - '0');
                    }
                    nums.addLast(u);
                    i = j-1;
                } else {
                    while (!ops.isEmpty() && ops.peekLast() != '('){
                        clac(nums,ops);
                    }
                    ops.addLast(c);
                }
            }
        }
        while (!ops.isEmpty()){
            clac(nums,ops);
        }
        return nums.peekLast();
    }

    private void clac(Deque<Integer> nums, Deque<Character> ops) {
        if (ops.isEmpty() || nums.size() < 2){
            return;
        }
        if (ops.isEmpty()){
            return;
        }
        int b = nums.pollLast(),a = nums.pollLast();
        char op = ops.pollLast();
        nums.addLast(op == '+'?a+b:a-b);
    }

    // 使用 map 维护一个运算符优先级
    // 这里的优先级划分按照「数学」进行划分即可
    Map<Character, Integer> map = new HashMap<Character,Integer>(){{
        put('-', 1);
        put('+', 1);
        put('*', 2);
        put('/', 2);
        put('%', 2);
        put('^', 3);
    }};


    //加优先级
    public int calculate3(String s) {
        // 将所有的空格去掉，并将 (- 替换为 (0-
        s = s.replaceAll(" ", "");
        s = s.replaceAll("\\(-", "(0-");
        char[] cs = s.toCharArray();

        int n= s.length();
        Deque<Integer> nums = new ArrayDeque();
        nums.addLast(0);
        Deque<Character> ops = new ArrayDeque<>();
        for (int i=0;i<n;i++){
            char c= cs[i];
            if ( c == '(' ){
                ops.push(c);
            } else if (c == ')'){
                while (!ops.isEmpty()){
                    if (ops.peekLast() != '('){
                        calc1(nums,ops);
                    } else {
                        ops.pollLast();
                        break;
                    }
                }
            } else {
                if (Character.isDigit(c)){
                    int u=0;
                    int j=i;
                    while (j<n &&Character.isDigit(cs[j])){
                        u = u*10+(cs[j++] - '0');
                    }
                    nums.addLast(u);
                    i = j-1;
                } else {
                    // 有一个新操作要入栈时，先把栈内可以算的都算了
                    // 只有满足「栈内运算符」比「当前运算符」优先级高/同等，才进行运算

                    while (!ops.isEmpty() && ops.peekLast()!='('){
                        char prev = ops.peekLast();
                        if (map.get(prev) >= map.get(c)){
                            calc1(nums,ops);
                        } else {
                            break;
                        }
                    }
                    ops.addLast(c);
                }
            }
        }
        while (!ops.isEmpty()){
            calc1(nums,ops);
        }

        return nums.peekLast();
    }

    private void calc1(Deque<Integer> nums, Deque<Character> ops) {
        if (nums.isEmpty() || nums.size() < 2) return;
        if (ops.isEmpty()) return;
        int b = nums.pollLast(), a = nums.pollLast();
        char op = ops.pollLast();
        int ans = 0;
        if (op == '+') ans = a + b;
        else if (op == '-') ans = a - b;
        else if (op == '*') ans = a * b;
        else if (op == '/')  ans = a / b;
        else if (op == '^') ans = (int)Math.pow(a, b);
        else if (op == '%') ans = a % b;
        nums.addLast(ans);

    }
}
