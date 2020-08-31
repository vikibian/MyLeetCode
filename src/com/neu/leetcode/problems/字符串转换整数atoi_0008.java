package com.neu.leetcode.problems;

public class 字符串转换整数atoi_0008 {
    public static void main(String[] args) {
        String num1 = "-91283472332";
//        char a = 'a';
//        System.out.println("char:"+(int)a);//0 -> 48 9 -> 57
        String testNum1 = "42";
        String testNum2 = "   -42";
        String testNum3 = "4193 with words";
        String testNum4 = "words and 987";
        String testNum5 = "-91283472332";
        System.out.println("test:"+myAtoi(testNum4));
    }

    public static int myAtoi(String str) {
        if (str.length() == 0){
            return 0;
        }

        int index = 0;
        for (int i=0;i<str.length();i++){
            String string = str.substring(i,i+1);
            if (!string.equals(" ")){
                if (string.equals("-") || string.equals("+")){
                    index = i;
                    while (index+1 < str.length() && str.charAt(index+1)>=48 && str.charAt(index+1)<=57){
                        index++;
                    }
                    if (index != i){
                        try {
                            return Integer.valueOf(str.substring(i,index+1));
                        }catch (Exception e){
                            if (string.equals("-")){
                                return Integer.MIN_VALUE;
                            }else {
                                return Integer.MAX_VALUE;
                            }
                        }
                    }else {
                        return 0;
                    }
                }else if (str.charAt(i)>=48 && str.charAt(i)<=57){
                    index = i;
                    while (index+1 <= str.length() && str.charAt(index)>=48 && str.charAt(index)<=57){
                        index++;
                    }
                    if (index != i){
                        try {
                            return Integer.valueOf(str.substring(i,index));
                        }catch (Exception e){
                          return Integer.MAX_VALUE;
                        }
                    }else {
                        return 0;
                    }
                }else {
                    return 0;
                }
            }
        }

        return 0;
    }
}
