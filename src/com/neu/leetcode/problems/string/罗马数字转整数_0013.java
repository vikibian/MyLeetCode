package com.neu.leetcode.problems.string;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class 罗马数字转整数_0013 {

    //true  简单
    public int romanToInt(String s) {
        Map<String,Integer> map = new LinkedHashMap<>();
        map.put("I",1);
        map.put("IV",4);
        map.put("V",5);
        map.put("IX",9);
        map.put("X",10);
        map.put("XL",40);
        map.put("L",50);
        map.put("XC",90);
        map.put("C",100);
        map.put("CD",400);
        map.put("D",500);
        map.put("CM",900);
        map.put("M",1000);
        int res = 0;

        int index=0;
        int len = s.length();
        while ((index+1)<len || (index<len)){
            String str = "";
            if (index+2 > len){
                str = s.substring(index);
            } else {
                str = s.substring(index,index+2);
            }

            if (map.containsKey(str)){
                res = res+map.get(str);
                index += 2;
            } else {
                str = s.substring(index,index+1);
                res = res + map.get(str);
                index++;
            }
        }

        return res;
    }

    //精选题解 思路与我的相同 但是写的比我简洁
    public int romanToInt2(String s) {
        Map<String, Integer> map = new HashMap<>();
        map.put("I", 1);
        map.put("IV", 4);
        map.put("V", 5);
        map.put("IX", 9);
        map.put("X", 10);
        map.put("XL", 40);
        map.put("L", 50);
        map.put("XC", 90);
        map.put("C", 100);
        map.put("CD", 400);
        map.put("D", 500);
        map.put("CM", 900);
        map.put("M", 1000);

        int ans = 0;
        for(int i = 0;i < s.length();) {
            if(i + 1 < s.length() && map.containsKey(s.substring(i, i+2))) {
                ans += map.get(s.substring(i, i+2));
                i += 2;
            } else {
                ans += map.get(s.substring(i, i+1));
                i ++;
            }
        }
        return ans;
    }


    //热门题解
    public int romanToInt1(String s) {
        int sum =0;
        int presum = getValue(s.charAt(0));
        for (int i=1;i<s.length();i++){
            int num = getValue(s.charAt(i));
            if (presum<num){
                sum -= presum;
            } else {
                sum += presum;
            }
            presum = num;
        }
        //最后一个
        sum += presum;
        return sum;
    }

    private int getValue(char ch) {
        switch(ch) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default: return 0;
        }
    }
}
