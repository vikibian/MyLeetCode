package com.neu.leetcode.problems.string;

import java.util.ArrayDeque;
import java.util.Deque;

public class 去除重复字母_0316 {
    //官方题解 贪心+单调栈
    public String removeDuplicateLetters(String s){
        boolean[] vis = new boolean[26];
        int[] num = new int[26];
        for (int i=0;i<s.length();i++){
            num[s.charAt(i)-'a']++;
        }
        StringBuffer sb = new StringBuffer();
        for (int i=0;i<s.length();i++){
            char ch = s.charAt(i);
            if (!vis[ch-'a']){
                while (sb.length()>0 && sb.charAt(s.length()-1) > ch){
                    if (num[sb.charAt(s.length()-1)-'a'] > 0){
                        vis[sb.charAt(sb.length()-1)-'a'] = false;
                        sb.deleteCharAt(sb.length()-1);
                    } else {
                        break;
                    }
                }
                vis[ch-'a'] = true;
                sb.append(ch);
            }
            num[ch-'a'] -= 1;
        }

        return sb.toString();
    }

    //官方题解 视频题解
    public String removeDuplicateLetters1(String s){
        int len = s.length();
        char[] chars = s.toCharArray();
        int[] lastIndex = new int[26];
        for (int i=0;i<len;i++){
            lastIndex[chars[i]-'a'] = i;
        }

        Deque<Character> stack = new ArrayDeque<>();
        boolean[] visited = new boolean[26];
        for (int i=0;i<len;i++){
            if (visited[chars[i]-'a']){
                continue;
            }
            while (!stack.isEmpty() && stack.peekLast()>chars[i] && lastIndex[stack.peekLast()-'a'] != i){
                Character top = stack.removeLast();
                visited[top-'a'] = false;
            }
            stack.addLast(chars[i]);
            visited[chars[i]-'a'] = true;
        }
        StringBuffer sb = new StringBuffer();
        for (char c : stack){
            sb.append(c);
        }

        return sb.toString();
    }
}
