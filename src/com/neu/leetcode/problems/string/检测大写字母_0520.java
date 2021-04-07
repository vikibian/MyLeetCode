package com.neu.leetcode.problems.string;

public class 检测大写字母_0520 {

    public static void main(String[] args) {
        String s= "mL";
        System.out.println(detectCapitalUse(s));
    }

    public static boolean detectCapitalUse(String word) {
        int len = word.length();
        if (len == 1){
            char c = word.charAt(0);
            if ((c>='A' && c<='Z') || (c>='a' && c<='z') ){
                return true;
            } else {
                return false;
            }
        } else {
            char c1 = word.charAt(0);
            char c2 = word.charAt(1);
            if (c1>='A' && c1<='Z'){
                if (c2 >='A' && c2 <= 'Z'){
                    int index = 1;
                    while (index<len){
                        char c3 = word.charAt(index);
                        if (!(c3>='A' && c3<='Z')){
                            return false;
                        }
                        index++;
                    }
                } else {
                    int index =1;
                    while (index<len){
                        char c3 = word.charAt(index);
                        if (!(c3>='a' && c3<='z')){
                            return false;
                        }
                        index++;
                    }
                }
            } else {
                int index = 1;
                while (index<len){
                    char c3 = word.charAt(index);
                    if (!(c3>='a' && c3<='z' )){
                        return false;
                    }
                    index++;
                }
            }
        }
        return true;
    }

    //高分题解 计算大写字母的个数
    public boolean detectCapitalUse1(String word) {
        int len = word.length();
        int count = 0;
        for (int i=0;i<len;i++){
            char s = word.charAt(i);
            if (s >= 'A' && s<='Z'){
                count++;
            }
        }
        return count == len || count == 0 || (count == 1 && word.charAt(0)>='A' && word.charAt(0)<='Z');
    }
}
