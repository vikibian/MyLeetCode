package com.neu.leetcode.problems;

import java.util.ArrayList;
import java.util.List;

public class Z字形变换_0006 {

    public static void main(String[] args) {
        char[] textChars = new char[2];
        System.out.println();

        String s = "LEETCODEISHIRING";
        convert(s,3);
    }

    public static String convert(String s, int numRows) {
        if (numRows == 1){
            return s;
        }
        int len = s.length();

//        int col = (len / (2 * numRows - 2)) * (numRows - 1);
//        char[] charArray = s.toCharArray();
//
//
//
//        char[][] map = new char[numRows][col];
//        int unit = 2*numRows - 2;
//        for (int i=0;i<len;i++){
//            int row = i / unit;
//            if (i % unit < numRows){
//                map[row][i%unit] = charArray[i];
//            }
//        }

        int unit =2 * numRows - 2;
        int col = len / unit;

        if (len % (unit) != 0){
            col = col + 1;
        }
        System.out.println("col:"+col);

        String[] strings = new String[col];
        for (int i=0;i<col;i++){
            if (i == col - 1){
                strings[i] = s.substring(unit * i,len);
            } else {
                strings[i] = s.substring(unit * i,unit * (i+1));
            }
        }

        StringBuilder stringBuilder = new StringBuilder();

        for (int i=0;i<numRows;i++){
            if (i==0){
                int index = 0;
                while (index<s.length()){
                    stringBuilder.append(s.substring(index,index+1));
                    index = index + unit;
                }
            } else {
                for (int j=0;j<strings.length;j++){
                    int restI = unit - i;
                    if (restI == i && i<strings[j].length()){
                        stringBuilder.append(strings[j].substring(i,i+1));
                    } else {
                        if (i< strings[j].length() ){
                            stringBuilder.append(strings[j].substring(i,i+1));
                        }
                        if ( restI < strings[j].length()){
                            stringBuilder.append(strings[j].substring(restI,restI+1));
                        }
                    }

                }
            }
        }

        for (String t : strings) {
            System.out.println(t.toString());
        }

        System.out.println(stringBuilder.toString());
        return stringBuilder.toString();
    }


    public static String convert1(String s, int numRows) {
        if (numRows == 1){
            return s;
        }

        List<StringBuilder> rows = new ArrayList<>();
        for (int i=0;i<Math.min(numRows,s.length());i++){
            rows.add( new StringBuilder());
        }

        int curRow = 0;
        boolean goingdown = false;
        for (char  c : s.toCharArray()){
            rows.get(curRow).append(c);
            if (curRow == 0 || curRow == numRows - 1 ){
                goingdown = !goingdown;
            }
            curRow = curRow + (goingdown ? 1:-1);
        }

        StringBuilder ret = new StringBuilder();
        for (StringBuilder row : rows){
            ret.append(row);
        }

        return ret.toString();
    }

    public static String convert2(String s, int numRows) {
        if (numRows == 1){
            return s;
        }

       StringBuilder ret = new StringBuilder();
        int n = s.length();
        int cyclen = 2 * numRows - 2;

        for (int i=0;i<numRows;i++){
            for (int j=0;j+i<n;j+=cyclen){
                ret.append(s.charAt(j+i));
                if (i!=0 && i!= numRows - 1 && j+cyclen - i < n){
                    ret.append(s.charAt(j + cyclen - i));
                }
            }
        }

        return ret.toString();
    }
}
