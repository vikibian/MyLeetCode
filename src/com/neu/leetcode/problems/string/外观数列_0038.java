package com.neu.leetcode.problems.string;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class 外观数列_0038 {
    public static void main(String[] args) {
        System.out.println(countAndSay(4));
    }
    public static String countAndSay(int n) {
        String s = "";
        if (n ==1 ){
            return "1";
        }

        if ( n ==2){
            return "11";
        }

        s = countAndSay(n-1);
        Map<String,Integer> map = new LinkedHashMap<>();
        List<String> list1 = new LinkedList<>();
        List<String> list2 = new LinkedList<>();
        int index =0;
        int count = 1;
        while (index < s.length()){
            if (index+1<s.length() && s.charAt(index) == s.charAt(index+1)){
                count++;
            }else {
                list1.add(s.charAt(index)+"");
                list2.add(count+"");
                count=1;
            }
            index++;
        }
        String res = "";
        for (int i=0;i<list1.size();i++){
            res = res+list2.get(i)+list1.get(i);
        }
        return res;
    }

    //层层递归
    public String countAndSay1(int n){
        if (n == 1){
            return "1";
        }
        String str= countAndSay(n-1);
        StringBuffer ans = new StringBuffer();
        int len = str.length();
        int start = 0;
        for (int i=0;i<len+1;i++){
            if (i == len){
                ans.append(i-start).append(str.charAt(start));
            } else if (str.charAt(i) != str.charAt(start)){
                ans.append(i-start).append(str.charAt(start));
                start = i;
            }
        }
        return ans.toString();
    }
}
