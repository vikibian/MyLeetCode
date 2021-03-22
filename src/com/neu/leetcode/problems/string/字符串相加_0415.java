package com.neu.leetcode.problems.string;

public class 字符串相加_0415 {
    public String addStrings(String num1, String num2) {
        int m = num1.length(),n = num2.length();
        int max = Math.max(m,n);
        int[] nums = new int[max+1];
        int i = m-1,j=n-1;
        int index = max;
        int count = 0;
        while (i>=0 || j>=0 ||index>=0){
            int x = i>=0?num1.charAt(i)-'0':0;
            int y = j>=0?num2.charAt(j)-'0':0;
            int ans = count + x+y;
            nums[index] =  ans%10;
            count = ans/10;
            index--;
            i--;
            j--;
        }
        StringBuffer sb = new StringBuffer();
        if (nums[0]!= 0){
            sb.append(nums[0]);
        }
        for (int d=1;d<max+1;d++){
            sb.append(nums[d]);
        }
        return sb.toString();
    }

    //官方题解 模拟方法
    public String addString(String num1,String num2){
        int i = num1.length()-1,j=num2.length()-1,add = 0;
        StringBuffer stringBuffer = new StringBuffer();
        while (i>=0 || j>=0 || add != 0){
            int x = i>=0?num1.charAt(i)-'0':0;
            int y = j>=0?num2.charAt(j)-'0':0;
            int res = x+y+add;
            stringBuffer.append(res%10);
            add = res/10;
            i--;
            j--;
        }
        stringBuffer.reverse();
        return stringBuffer.toString();
    }


}
