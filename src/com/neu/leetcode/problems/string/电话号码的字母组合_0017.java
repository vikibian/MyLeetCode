package com.neu.leetcode.problems.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 电话号码的字母组合_0017 {

    public static void main(String[] args) {
        System.out.println(letterCombinations("23"));
    }

    //true  midium
    public static List<String> letterCombinations(String digits) {
        List<String> listStr = new ArrayList<>();
        if (digits.equals("") || digits.length() == 0){
            return listStr;
        }
        Map<String,String> map = new HashMap<>();
        map.put("2","abc");
        map.put("3","def");
        map.put("4","ghi");
        map.put("5","jkl");
        map.put("6","mno");
        map.put("7","pqrs");
        map.put("8","tuv");
        map.put("9","wxyz");

        int len = digits.length();
        for (int i=0;i<len;i++){
            String sub = digits.substring(i,i+1);
            int len1 = map.get(sub).length();
            if (i==0){
                List<String> list = new ArrayList<>();
                for (int j=0;j<len1;j++){
                    list.add(map.get(sub).substring(j,j+1));
                }
                listStr.addAll(list);
            } else {
                List<String> stringList = new ArrayList<>();
                for (int j=0;j<len1;j++){
                    List<String> list = new ArrayList<>(listStr);
                    String str1 = map.get(sub).substring(j,j+1);
                    for (int m=0;m<list.size();m++){
                        list.set(m,list.get(m)+str1);
                    }

                    stringList.addAll(list);
                }
                listStr.clear();;
                listStr.addAll(stringList);
            }

        }

        return listStr;
    }

    //官方题解 回溯算法
    public  List<String> letterCombinations1(String digits) {
        List<String> combinations = new ArrayList<String>();
        if (digits.length() == 0){
            return combinations;
        }
        Map<Character, String> phoneMap = new HashMap<Character, String>() {{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};
        backtrack(digits,combinations,phoneMap,0,new StringBuffer());
        return combinations;
    }

    public void backtrack(String digits, List<String> combinations, Map<Character, String> phoneMap, int index, StringBuffer stringBuffer){
        if (index == digits.length()){
            combinations.add(stringBuffer.toString());
        } else {
            char digit = digits.charAt(index);
            String letters = phoneMap.get(digit);
            int count = letters.length();
            for (int i=0;i<count;i++){
                stringBuffer.append(letters.charAt(i));
                backtrack(digits,combinations,phoneMap,index+1,stringBuffer);
                stringBuffer.deleteCharAt(index);
            }
        }
    }
}
