package com.neu.leetcode.problems.string;

import java.util.ArrayList;
import java.util.List;

public class 赎金信_0383 {
    public boolean canConstruct(String ransomNote, String magazine) {
        List<String> magList = new ArrayList<>();
        int len2 = magazine.length();
        for (int i=0;i<len2;i++){
            String str = magazine.substring(i,i+1);
            magList.add(str);
        }

        int len1 = ransomNote.length();
        for (int i=0;i<len1;i++){
            String str = ransomNote.substring(i,i+1);
            if (magList.size() != 0 && magList.contains(str)){
                int index = magList.indexOf(str);
                magList.remove(index);
            } else {
                return false;
            }
        }
        return true;
    }

    //java 高分题解
    public boolean canConstruct1(String ransomNote, String magazine) {
        if (magazine.length() < ransomNote.length()) {
            return false;
        }
        int[] caps = new int[26];
        for (char c : ransomNote.toCharArray()) {
            int index = magazine.indexOf(c,caps[c-'a']);
            if (index == -1){
                return false;
            }
            caps[c-97] = index+1;
        }
        return true;
    }
}
