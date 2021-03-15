package com.neu.leetcode.problems.string;

import java.util.ArrayList;
import java.util.List;

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
}
