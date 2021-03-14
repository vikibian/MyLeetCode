package com.neu.leetcode.problems.string;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class 整数转罗马数字_0012 {
    public static void main(String[] args) {
        System.out.println(intToRoman(20));
    }

    //中等 true
    public static String intToRoman(int num) {
        Map<Integer,String> map = new LinkedHashMap<>();

        List<Integer> listNum = new LinkedList<>();
        listNum.add(1);
        listNum.add(4);
        listNum.add(5);
        listNum.add(9);
        listNum.add(10);
        listNum.add(40);
        listNum.add(50);
        listNum.add(90);
        listNum.add(100);
        listNum.add(400);
        listNum.add(500);
        listNum.add(900);
        listNum.add(1000);

        List<String> listStr = new LinkedList<>();
        listStr.add("I");
        listStr.add("IV");
        listStr.add("V");
        listStr.add("IX");
        listStr.add("X");
        listStr.add("XL");
        listStr.add("L");
        listStr.add("XC");
        listStr.add("C");
        listStr.add("CD");
        listStr.add("D");
        listStr.add("CM");
        listStr.add("M");
//        map.put(1,"I");
//        map.put(4,"IV");
//        map.put(5,"V");
//        map.put(9,"IX");
//        map.put(10,"X");
//        map.put(40,"XL");
//        map.put(50,"L");
//        map.put(90,"XC");
//        map.put(100,"C");
//        map.put(400,"CD");
//        map.put(500,"D");
//        map.put(900,"CM");
//        map.put(1000,"M");

//        map.put(1000,"M");
//        map.put(900,"CM");
//        map.put(500,"D");
//        map.put(400,"CD");
//        map.put(100,"C");
//        map.put(90,"XC");
//        map.put(50,"L");
//        map.put(40,"XL");
//        map.put(10,"X");
//        map.put(9,"IX");
//        map.put(5,"V");
//        map.put(4,"IV");
//        map.put(1,"I");

        String res = "";
        int index = listNum.size()-1;
        while (num > 0){
            for (int i = index;i>=0;i--){
                if (num<listNum.get(i)){
                    index--;
                    break;
                } else {
                    res = res + listStr.get(i);
                    num -= listNum.get(i);
                    break;
                }
            }
        }
        return res;
    }

    //贪心算法
    int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    String[] symbols = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
    public  String intToRoman1(int num) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i=0;i<values.length && num>0;i++){
            while (values[i]<=num){
                num -= values[i];
                stringBuilder.append(symbols[i]);
            }
        }
        return stringBuilder.toString();
    }

    //硬编码
    public  String intToRoman2(int num) {
        String[] thousands = {"", "M", "MM", "MMM"};
        String[] hundreds = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String[] tens = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] ones = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};

        return thousands[num/1000]+hundreds[num%1000/100]+tens[num%100/10] + ones[num%10];
    }
}
