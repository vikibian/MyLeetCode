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
}
