package com.neu.leetcode.problems.math;

import com.sun.prism.shader.Solid_TextureYV12_AlphaTest_Loader;

import java.text.SimpleDateFormat;
import java.util.Date;

public class 整数翻转_0007 {

    public static void main(String[] args) {
        int a = -12;
        String s = String.valueOf(a);

//        System.out.println("test s:"+s);
        System.out.println("test s:"+Integer.valueOf(s));
        System.out.println("test s:"+s.substring(s.length()-1,s.length()));
        int t1 = 123;
        int t2 = -123;
        int t3 = 120;
        int t4 = 1534236469;
        System.out.println("result:"+reverse1(t2));
        System.out.println("result:"+Integer.MAX_VALUE);

    }

    public static  int reverse(int x) {
        String s = String.valueOf(x);
        if (s.length() == 1){
            return x;
        }
        StringBuilder stringBuilder = new StringBuilder(s);

        int p1=0;
        int p2 = s.length() - 1;

        while (s.substring(p2,p2+1).equals("0") && p2 >= 0){
            p2--;
        }
        int last = p2+1;

        while (p1<=p2){


            if (s.substring(p1,p1+1).equals("-") && p1 == 0){
                p1++;
            }

            String temp1 = s.substring(p1,p1+1);
            String temp2 = s.substring(p2,p2+1);

            stringBuilder.replace(p1,p1+1,temp2);
            stringBuilder.replace(p2,p2+1,temp1);
            p1++;
            p2--;
        }
        int result = 0;
        try{
             result = Integer.valueOf(stringBuilder.substring(0,last)) ;
        }catch (Exception e){
            return 0;
        }

        return result;
    }

    public static int reverse1(int x) {
        int rev = 0;
        while (x != 0){
            int pop = x % 10;
            x = x / 10;
            if (rev >= Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE/10 && pop > 7)) return 0;
            if (rev <= Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE/10 && pop < -8)) return 0;
            rev = rev * 10 + pop;
        }
        return rev;
    }
}
