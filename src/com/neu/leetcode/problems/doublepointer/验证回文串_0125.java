package com.neu.leetcode.problems.doublepointer;

public class 验证回文串_0125 {

    public static void main(String[] args) {
        String s = "race a car";
        System.out.println(isPalindrome(s));
        System.out.println('a'+1);
        System.out.println('A'+1);


    }
    public static boolean isPalindrome(String s) {
        int index1 = 0;
        int index2 = s.length()-1;

        while (index1 <= index2){
            char c1 = s.charAt(index1);
            char c2 = s.charAt(index2);
            boolean b1 = isvalid(c1);
            boolean b2 = isvalid(c2);
            if ( b1&& b2){
                if (c1>='a'&&c1<='z' && c2>='A'&&c2<='Z'){
                    if (c1-32 != c2){
                        return false;
                    }
                } else if (c2>='a'&&c2<='z' && c1>='A'&&c1<='Z'){
                    if (c2-32 != c1){
                        return false;
                    }
                } else {
                    if (c1 != c2){
                        return false;
                    }
                }
                index1++;
                index2--;
            } else {
                if (!b1){
                    index1++;
                }

                if (!b2){
                    index2--;
                }
            }

        }

        return true;
    }

    public static boolean isvalid(char c){
        if ((c>='0' && c<='9') || (c>='a' && c<='z')||(c>='A' && c<='Z')){
            return true;
        } else {
            return false;
        }
    }
}
