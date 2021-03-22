package com.neu.leetcode.problems.string;

public class 压缩字符串_0443 {
    public int compress(char[] chars) {
        int size = chars.length;
        if (size == 1){
            return 1;
        }
        int len = 0;
        int count = 1;
        int index = 0;
        for (int i=1;i<size;i++){
            char c = chars[i];
            if (c == chars[i-1]){
                count++;
            } else {
                if (count != 1){
                    chars[index++] = chars[i-1];
                    String str = String.valueOf(count);
                    char[] strnums = str.toCharArray();
                    for (int j=0;j<strnums.length;j++){
                        chars[index++] = strnums[j];
                    }

                } else {
                    chars[index++] = chars[i-1];
                }
                count = 1;
            }
        }

        if (count != 1){
            chars[index++] = chars[size-1];
            String str = String.valueOf(count);
            char[] strnums = str.toCharArray();
            for (int i=0;i<strnums.length;i++){
                chars[index++] = strnums[i];
            }
        } else {
            chars[index++] = chars[size-1];
        }

        return index;
    }

    //官方题解 双指针
    public int compose1(char[] chars){
        int anchor=0,write=0;
        for (int read=0;read<chars.length;read++){
            if (read == chars.length || chars[read] != chars[read+1]){
                chars[write++] = chars[anchor];
                if (read > anchor){
                    for (char c : (""+(read-anchor+1)).toCharArray()){
                        chars[write++] = c;
                    }
                }
                anchor = read+1;
            }
        }
        return write;
    }
}
