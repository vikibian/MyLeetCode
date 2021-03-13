package com.neu.leetcode.problems.string;

public class 二进制求和_0067 {

    //简单
    public String addBinary(String a, String b) {
        int lena = a.length();
        int lenb = b.length();

        String res = "";
        int post = 0;
        int len = Math.max(lena,lenb);
//        int i = lena>=lenb?lena:lenb;
//        int j = lena<lenb?lenb:lena;
        for (int i=lena-1,j=lenb-1;;){
            String ca = "0";
            String cb = "0";

            if (i<0 && j<0){
                break;
            }

            if (i>=0){
                ca = a.substring(i,i+1);
            }

            if (j>=0){
                cb = b.substring(j,j+1);
            }
            int count = Integer.parseInt(ca)+Integer.parseInt(cb)+post;
            if (count>=2){
                post=1;
                count = count%2;
            } else {
                post = 0;
            }
            res = count+res;
            i--;
            j--;
        }

        if (post ==1){
            res = 1+res;
        }

        return res;
    }

    //利用java原生api中自带的函数  先将二进制的字符串转换为int 求和之后再将int转换为字符串
    public String addBinary1(String a, String b) {
        return Integer.toBinaryString(Integer.parseInt(a,2)+Integer.parseInt(b,2));
    }

    //模拟方法
    public String addBinary2(String a,String b){
        StringBuffer stringBuffer = new StringBuffer();
        int n = Math.max(a.length(),b.length()),carry = 0;
        for (int i=0;i<n;i++){
            carry += i<a.length()?(a.charAt(a.length()-1-i)-'0'):0;
            carry += i<b.length()?(b.charAt(b.length()-1-i)-'0'):0;
            stringBuffer.append((char) (carry%2 +'0'));//将数字根据与0的差值 转换一下
            carry /= 2;

        }
        if (carry > 0){
            stringBuffer.append('1');
        }
        stringBuffer.reverse();
        return stringBuffer.toString();
    }

}
