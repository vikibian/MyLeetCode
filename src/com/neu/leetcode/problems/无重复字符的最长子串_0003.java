package com.neu.leetcode.problems;

import java.util.*;

public class 无重复字符的最长子串_0003 {
    public static void main(String[] args) {
        String s = "abcabcbb";

//        System.out.println(":"+s.toCharArray()[0]+":");
        int i = lengthOfLongestSubstring(s);
        System.out.println("最长："+i);

    }

    public static int lengthOfLongestSubstring(String s) {
        Map<String,Integer> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        int index = 0;
        char[] chars = s.toCharArray();

        int pre=0;
        int index1=0;
        for (int i=0;i<chars.length;i++){
            if (map.containsKey(String.valueOf(chars[i]))){
                for (String sub:map.keySet()) {
                    if (sub.equals(String.valueOf(chars[i]))){
                        index1 = map.get(sub);
                    }
                }


                list.add(map.size());
                for (int j=index1;j>=0;j--){
                    if (map.get(String.valueOf(chars[j]))<=index1){
                        map.remove(String.valueOf(chars[j]));
                    }
                }

                map.put(String.valueOf(chars[i]),i);
            }else {
                map.put(String.valueOf(chars[i]),i);
            }
        }
        list.add(map.size());
        System.err.println("map:"+map.toString()+":");

        for (int i=0;i<list.size();i++){
            if (list.get(i) > index){
                index = list.get(i);
            }
        }

        return index;

    }
}
