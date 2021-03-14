package com.neu.leetcode.problems.string;

import java.util.*;

public class 括号生成_0022 {

    public static void main(String[] args) {
        System.out.println(generateParenthesis(3).toString());
//        StringBuilder stringBuilder = new StringBuilder("()");
//        stringBuilder.insert(1,"()");
//        System.out.println(stringBuilder.toString());
//        stringBuilder.insert(3,"12");
//        System.out.println(stringBuilder.toString());
    }

    public static List<String> generateParenthesis(int n) {
        List<String> list = new LinkedList<>();
        for (int i=1;i<=n;i++){
            if (i == 1){
                String s = "()";
                list.add(s);
            } else {
                int size = list.size();
                List<String> list1 = new LinkedList<>();
                for (int m=0;m<size;m++){
                    StringBuilder stringBuilder = new StringBuilder(list.get(m));
                    int len = stringBuilder.length();

                    for (int j=1;j<=len;j++){
                        StringBuilder stringBuilder1 = new StringBuilder(stringBuilder.toString());
                        stringBuilder1.insert(j,"()");
                        if (!list1.contains(stringBuilder1.toString())){
                            String str1 = stringBuilder1.toString();
                            list1.add(str1);
                        }
                    }
                }
                list.clear();
                list.addAll(list1);
            }
        }
        return list;
    }

    //暴力解法
    public List<String> generateParenthesis1(int n) {
        List<String> list = new ArrayList<>();
        generateAll(new char[2*n],0,list);
        return list;
    }

    private void generateAll(char[] chars, int pos, List<String> list) {
        if (pos == chars.length){
            if (isVaild(chars)){
                list.add(new String(chars));
            }
        } else {
            chars[pos] = '(';
            generateAll(chars,pos+1,list);
            chars[pos] = ')';
            generateAll(chars,pos+1,list);
        }

    }

    private boolean isVaild(char[] chars) {
        int balance = 0;
        for (char ch: chars){
            if (ch == '('){
                balance++;
            } else {
                balance--;
            }
            if (balance<0){
                return false;
            }
        }
        return balance == 0;
    }

    //回溯算法
    public List<String> generateParenthesis2(int n){
        List<String> list = new ArrayList<>();
        backtrack(list,new StringBuilder(),0,0,n);
        return list;
    }

    private void backtrack(List<String> list, StringBuilder stringBuilder, int open, int close, int max) {
        if (stringBuilder.length() == max*2){
            list.add(stringBuilder.toString());
        }
        if (open<max){
            stringBuilder.append("(");
            backtrack(list,stringBuilder,open+1,close,max);
            stringBuilder.deleteCharAt(stringBuilder.length()-1);
        }

        if (close<open){
            stringBuilder.append(")");
            backtrack(list,stringBuilder,open,close+1,max);
            stringBuilder.deleteCharAt(stringBuilder.length()-1);
        }
    }

    //按括号序列的长度递归
    ArrayList[] lists = new ArrayList[100];
    public List<String> generte(int n){
        if (lists[n] != null){
            return lists[n];
        }
        ArrayList<String> ans = new ArrayList<>();
        if (n == 0){
            ans.add("");
        } else {
            for (int c=0;c<n;c++){
                for (String left : generte(n)) {
                    for (String right : generte(n - 1 -c)){
                        ans.add("("+left+")"+right);
                    }
                }
            }
        }
        lists[n] = ans;
        return ans;
    }

    public List<String> generateParenthesis3(int n){
        return generte(n);
    }

    //动态规划
    public List<String> generateParenthesis4(int n){
        LinkedList<LinkedList<String>> result = new LinkedList<>();
        if (n == 0){
            return result.get(0);
        }
        LinkedList<String> list0 = new LinkedList<>();
        list0.add("");
        result.add(list0);
        LinkedList<String> list1 = new LinkedList<>();
        list1.add("");
        result.add(list1);

        for (int i=2;i<=n;i++){
            LinkedList<String> temp = new LinkedList<>();
            for (int j=0;j<i;j++){
                List<String> str1 = result.get(i);
                List<String> str2 = result.get(i-1-j);
                for (String s1 : str1){
                    for (String s2 : str2){
                        String el = "("+s1+")"+s2;
                        temp.add(el);
                    }
                }
            }
            result.add(temp);
        }

        return result.get(n);
    }

    //深度优先遍历
    public List<String> generateParenthesis5(int n){
        List<String> res = new ArrayList<>();
        if ( n == 0){
            return res;
        }
        dfs("",n,n,res);//第一种
//        dfs1("",0,0,n,res);//第二种
        return res;
    }

    private void dfs1(String s, int left, int right, int n,List<String> res) {
        if (left == n && right == n){
            res.add(s);
            return;
        }
        if (right>left){
            return;
        }

        if (left<n){
            dfs1(s+"(",left+1,right,n,res);
        }

        if (right<n){
            dfs1(s+")",left,right,n,res);
        }

    }

    private void dfs(String s, int left, int right, List<String> res) {
        if (left == 0 && right == 0){
            res.add(s);
            return;
        }

        if (left>right){
            return;
        }
        if (left>0){
            dfs(s+"(",left-1,right,res);
        }
        if (right>0){
            dfs(s+")",left,right-1,res);
        }
    }

    //广度优先遍历
    class Node{
        private String res;
        private int left;
        private int right;
        public Node(String str, int left,int right){
            this.res = str;
            this.left = left;
            this.right = right;
        }
    }

    public List<String> generateParenthesis6(int n){
        List<String> res = new ArrayList<>();
        if (n == 0){
            return res;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node("",n,n));
        while (!queue.isEmpty()){
            Node node = queue.poll();
            if (node.left == 0 && node.right == 0){
                res.add(node.res);
            }
            if (node.left > 0){
                queue.offer(new Node(node.res+"(", node.left-1, node.right ));
            }
            if (node.right>0){
                queue.offer(new Node(node.res+")",node.left, node.right-1));
            }
        }
        return res;
    }


}
