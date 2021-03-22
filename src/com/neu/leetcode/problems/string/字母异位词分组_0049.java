package com.neu.leetcode.problems.string;

import java.util.*;

public class 字母异位词分组_0049 {

    public static void main(String[] args) {
        String[] strings = {"eat", "tea", "tan", "ate", "nat", "bat"};
//        String[] strings = {"",""};
//        String[] strings = {"","b"};
//        String[] strings = {"ac","c"};
        System.out.println(groupAnagrams(strings).toString());
    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> listList = new ArrayList<>();
        int size = strs.length;
        for (int i=0;i<size;i++){
            //最外层遍历数据源
            String str = strs[i];
            int listsize = listList.size();
            if (listsize == 0){
                List<String> list1 = new ArrayList<>();
                list1.add(str);
                listList.add(list1);
            } else {
                for (int j=0;j<listsize;j++){
                    boolean flag = true;
                    boolean isBreak = false;
                    //遍历已有的数据
                    List<String> list = listList.get(j);
                    StringBuffer str1 = new StringBuffer(list.get(0));
                    int strLen = str.length();
                    if (strLen == 0){
                        if (str.equals(str1.toString())){
                            listList.get(j).add(str);
                            flag = false;
                        } else {
                            List<String> list1 = new ArrayList<>();
                            list1.add(str);
                            listList.add(list1);
                            break;
                        }
                    } else {
                        //遍历新的string的长度
                        for (int m=0;m<strLen;m++){
                            if (str1.indexOf(String.valueOf(str.charAt(m)))==-1){
                                isBreak = true;
                                break;
                            }
                            if (m == strLen -1 && str1.length() == str.length()){
                                listList.get(j).add(str);
                                flag = false;
                            }
                        }
                    }

                    if ((isBreak && j==listsize-1)||(!isBreak && str.length()!= str1.length()) ){
                        List<String> list1 = new ArrayList<>();
                        list1.add(str);
                        listList.add(list1);
                        break;
                    } else if (!flag){
                        break;
                    }
                }
            }


        }
        return listList;
    }

    //官方题解 排序
    public List<List<String>> groupAnagrams1(String[] strs) {
        Map<String ,List<String>> map = new HashMap<>();
        for (String str : strs){
            char[] charArray = str.toCharArray();
            Arrays.sort(charArray);
            String key = new String(charArray);
            List<String> list = map.getOrDefault(key,new ArrayList<>());
            list.add(str);
            map.put(key,list);
        }

        return new ArrayList<List<String>>(map.values());
    }

    //官方题解 计数
    public List<List<String>> groupAnagrams2(String[] strs) {
        Map<String,List<String>> map = new HashMap<>();
        for (String str : strs){
            int[] count = new int[26];
            int len = str.length();
            for (int i=0;i<len;i++){
                count[str.charAt(i)-'a']++;
            }

            StringBuffer sb = new StringBuffer();
            for (int i=0;i<26;i++){
                if (count[i] != 0){
                    sb.append((char)'a'+i);
                    sb.append(count[i]);
                }
            }
            String key = sb.toString();
            List<String> list = map.getOrDefault(key,new ArrayList<>());
            list.add(str);
            map.put(key,list);
        }
        return new ArrayList<>(map.values());
    }
}
