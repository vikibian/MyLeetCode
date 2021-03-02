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
}
