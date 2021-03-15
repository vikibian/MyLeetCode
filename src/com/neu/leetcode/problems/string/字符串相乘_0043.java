package com.neu.leetcode.problems.string;

public class 字符串相乘_0043 {
    //官方题解 做加法
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")){
            return "0";
        }
        String ans = "0";
        int m = num1.length(),n=num2.length();
        for (int i = n-1;i>=0;i--){
            int add = 0;
            StringBuffer curr = new StringBuffer();
            for (int j=n-1;j>=0;j--){
                curr.append("0");
            }
            int y = num1.charAt(i) -'0';
            for (int j=m-1;j>=0;j--){
                int x = num1.charAt(j)-'0';
                int product = x*y+add;
                curr.append(product%10);
                add = product/10;
            }
            if (add != 0){
                curr.append(add%10);
            }
            ans = addString(ans,curr.reverse().toString());
        }
        return ans;
    }

    private String addString(String ans, String curr) {
        int i = ans.length(),j = curr.length(),add =0;
        StringBuffer stringBuffer = new StringBuffer();

        while (i>=0 || j>=0 ||add != 0){
            int x = i>=0 ? ans.charAt(i):0;
            int y = j>=0? curr.charAt(j):0;
            int res = x*y+add;
            stringBuffer.append(res%10);
            add = res/10;
            i--;
            j--;
        }

        return stringBuffer.reverse().toString();
    }

    //官方题解 做乘法
    public String multiply2(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")){
            return "0";
        }

        int m=num1.length(),n=num2.length();
        int[] ansArr = new int[m+n];
        for (int i =m-1;i>=0;i--){
            int x=num1.charAt(i)-'0';
            for (int j=n-1;j>=0;j--){
                int y = num2.charAt(j) - '0';
                ansArr[i+y+1] += x*y;
            }
        }

        for (int i= m+n-1;i>=0;i--){
            ansArr[i-1] += ansArr[i]/10;
            ansArr[i] %= 10;
        }
        int index = ansArr[0] == 0 ?1:0;
        StringBuffer stringBuffer = new StringBuffer();

        while (index<m+n){
            stringBuffer.append(ansArr[index]);
            index++;
        }

        return stringBuffer.toString();
    }
}
