package com.neu.leetcode.problems.string;

import com.neu.leetcode.problems.math.实现pow函数_0050;

import java.util.*;

public class 字符串中的第一个唯一字符_0387{
        public int firstUniqChar(String s) {
            Map<Character, List<Integer>> map = new LinkedHashMap<>();
            int len = s.length();
            for (int i=0;i<len;i++){
                char c = s.charAt(i);
                if (map.containsKey(c)){
                    List<Integer> list = map.get(c);
                    list.add(i);
                    map.put(c,list);
                } else {
                    List<Integer> list = new ArrayList<>();
                    list.add(i);
                    map.put(c,list);
                }
            }

            for (Map.Entry<Character,List<Integer>> entry : map.entrySet()){
                if (entry.getValue().size() == 1){
                    return entry.getValue().get(0);
                }
            }

            return -1;
        }


        //官方题解 使用哈希表存储频数
        public int firstUniqChar1(String s) {
            Map<Character,Integer> map = new HashMap<>();
            for (int i=0;i<s.length();i++){
                char ch = s.charAt(i);
                map.put(ch,map.getOrDefault(ch,0)+1);
            }
            for (int i=0;i<s.length();i++){
                if (map.get(s.charAt(i))==1){
                    return i;
                }
            }
            return -1;
        }

        //官方题解 使用哈希表存储索引
        public int firstUniqChar2(String s){
            Map<Character,Integer> map = new HashMap<>();

            for (int i=0;i<s.length();i++){
                char ch = s.charAt(i);
                if (map.containsKey(ch)){
                    map.put(ch,-1);
                } else {
                    map.put(ch,i);
                }
            }

            int first = s.length();
            for (Map.Entry<Character,Integer> entry : map.entrySet()){
                int pos = entry.getValue();
                if (pos != -1 && pos<first){
                    first = pos;
                }
            }

            if (first == s.length()){
                return -1;
            }
            return first;
        }

        //官方题解 队列
        public int firstUniqChar3(String s){
            Map<Character,Integer> map = new HashMap<>();
            Queue<Pair> queue = new LinkedList<>();
            int n = s.length();
            for (int i=0;i<n;i++){
                char ch = s.charAt(i);
                if (!map.containsKey(ch)){
                    map.put(ch,i);
                    queue.offer(new Pair(ch,i));
                } else {
                    map.put(ch,-1);
                    while (!queue.isEmpty() && map.get(queue.peek().ch)==-1){
                        queue.poll();
                    }
                }
            }

            return queue.isEmpty()?-1:queue.poll().pos;
        }

        class Pair{
            char ch;
            int pos;

            Pair(char ch,int pos){
                this.ch = ch;
                this.pos = pos;
            }
        }
}
