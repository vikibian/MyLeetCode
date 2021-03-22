package com.neu.leetcode.problems.math;

public class Excel表列名称_0168 {

    public static void main(String[] args) {
        System.out.println(convertToTitle(52));
//        System.out.println(26*26);
//        System.out.println(2147483647%676);
//        System.out.println(647%26);
//        System.out.println((2147483647/26)%26);
//        System.out.println((2147483647/676)%26);

//        System.out.println(701/26);
//        System.out.println(26%26);

//        System.out.println(2147483647%26);
//        System.out.println(2147483647/26);
//        System.out.println((2147483647/26)%26);
//        System.out.println('@'+0);
//        System.out.println('A'+0);
        System.out.println(702%26);
        System.out.println(702/26);
    }

    public static String convertToTitle(int columnNumber) {
        StringBuffer stringBuffer = new StringBuffer();
        int rest = columnNumber;
        int pos = 1;

        if (columnNumber == 702){
            return "ZZ";
        }

        while (rest>0  ){
            int temp = rest%26;
            if (temp == 0){
                stringBuffer.append((char)('Z'));
            } else {
                if (columnNumber%26 == 0){
                    stringBuffer.append((char)((temp-2)+'A'));
                } else {
                    stringBuffer.append((char)((temp-1)+'A'));
                }

            }

            rest = rest / 26;
            if (rest == 1 && temp == 0 ){
                break;
            }
        }

        stringBuffer.reverse();
        return stringBuffer.toString();
    }

    //高分题解
    public String convertToTitle1(int n){
        StringBuilder sb = new StringBuilder();
        while (n>0){
            int c = n%26;
            if (c == 0){
                c = 26;
                n -= 1;
            }
            sb.insert(0,(char)(c-1+'A'));
            n /= 26;
        }
        return sb.toString();
    }
}
