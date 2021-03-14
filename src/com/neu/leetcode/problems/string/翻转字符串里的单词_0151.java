package com.neu.leetcode.problems.string;

import java.util.*;

public class 翻转字符串里的单词_0151 {

    public static void main(String[] args) {
//        String str = "the sky is blue";
//        String str = "a good   example";
//        String str = "  Bob    Loves  Alice   ";
        String str = "bob like even not does Alice";
        System.out.println(reverseWords2(str));
    }

    //中等
    public static String reverseWords(String s) {
        int len = s.length();
        String res = "";
        int index = len-1;
        while (index>=0){
            String str = s.substring(index,index+1);
            if (!str.equals(" ")){
                int index2= index;
                while (index2>=0&&!s.substring(index2,index2+1).equals(" ")){
                    index2--;
                }
                index2++;
                res = res+ s.substring(index2,index+1)+" ";
                index = index2-1;
            } else {
                index--;
            }

        }
        return res.substring(0,res.length()-1);
    }

    //官方题解 使用语言特性
    public String reverseWords1(String s){
        //出去开头末尾的空白字符
        s.trim();
        //正则匹配连续的空白字符作为分割字符
        List<String> wordList = Arrays.asList(s.split("\\s+"));
        Collections.reverse(wordList);
        //添加空格
        return String.join(" ",wordList);
    }

    //官方题解 自行编写对应的函数
    public static String reverseWords2(String s){
        StringBuilder sb = trimSpaces(s);

        //翻转字符串
        reverse(sb,0,sb.length()-1);

        //翻转每一个单词
        reverseWord(sb);

        return sb.toString();
    }

    private static void reverseWord(StringBuilder sb) {
        int n = sb.length();
        int start = 0,end=0;
        while (start < n){
            while (end<n && sb.charAt(end) != ' '){
                end++;
            }
            reverse(sb,start,end-1);
            //更新去找下一个单词
            start = end + 1;
            end++;
        }
    }

    private static void reverse(StringBuilder sb, int left, int right) {
        while (left < right){
            char temp = sb.charAt(left);
            sb.setCharAt(left++,sb.charAt(right));
            sb.setCharAt(right--,temp);
        }
    }

    private static StringBuilder trimSpaces(String s) {
        int left = 0,right = s.length()-1;
        //去掉字符串开头空白字符
        while (left<=right&&s.charAt(left) == ' '){
            left++;
        }
        while (left<= right && s.charAt(right) == ' '){
            right--;
        }

        //将字符串中间的多余空白字符去除
        StringBuilder sb = new StringBuilder();

        while (left<=right){
            char c = s.charAt(left);

            if (c != ' '){
                sb.append(c);
            } else if (sb.charAt(sb.length()-1) != ' '){
                sb.append(c);
            }

            left++;
        }

        return sb;
    }

    //官方题解 双端队列
    public String reverseWords4(String s){
        int left=0,right=s.length()-1;
        //去掉字符串开头空白字符
        while (left<=right&&s.charAt(left) == ' '){
            left++;
        }
        while (left<= right && s.charAt(right) == ' '){
            right--;
        }
        Deque<String> d = new ArrayDeque<>();
        StringBuilder word = new StringBuilder();

        while (left<= right){
            char c = s.charAt(left);
            if ((word.length() != 0 && c==' ')){
                d.offerFirst(word.toString());
                word.setLength(0);
            } else if (c != ' '){
                word.append(c);
            }
            left++;
        }
        d.offerFirst(word.toString());
        return String.join(" ",d);
    }

}
