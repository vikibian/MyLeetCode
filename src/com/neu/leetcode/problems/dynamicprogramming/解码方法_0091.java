package com.neu.leetcode.problems.dynamicprogramming;

public class 解码方法_0091 {

    public static void main(String[] args) {
        String s = "27";
        System.out.println(numDecodings(s));
    }

    //10  27  2101
    public static int numDecodings(String s) {
        //大写字母的ascii码的数值范围是65到90
        int len = s.length();

        int[] nums = new int[len+1];
        nums[0] = 0;
        int index = 0;
        if (s.substring(0,1).equals("0")){
            return 0;
        } else {
            while (index<len){
                String b = s.substring(index,index+1);
                String a = "";
                if (index-1 >= 0){
                    a = s.substring(index-1,index+1);
                }
                int nu = Integer.parseInt(b);
                int count = 0;
                if (nu>=1 && (nu)<=26){
                    if (a != ""){
                        if (Integer.parseInt(a)>0 && Integer.parseInt(a)<27){
                            count = nums[index] + 1  ;
                        } else {
                            count = nums[index]   ;
                        }
                    } else {
                        count = nums[index] +1  ;
                    }

                }

                int count1 = 0;
                if (a != ""){
                    char char2 = a.charAt(0);
                    if (char2 != 0){
                        int temp = Integer.parseInt(a);
                        if (temp >0 && temp<27){
                            count1 = nums[index]-1 + 1;
                        }
                    }
                }

                nums[index+1] = Math.max( count1,count);
                index++;

            }
        }

        return nums[len];
    }

    //动态规划
    public static int numDecodings1(String s) {
        int len = s.length();
        int res = 0;
        if (s.charAt(0) == '0'){
            return 0;
        }
        int pre = 1,curr=1;
        for (int i=1;i<len;i++){
            char c = s.charAt(i);
            int temp = curr;
            if (c == '0'){
                if (s.charAt(i-1) == '1' || s.charAt(i-1) == '2'){
                    curr = pre;
                } else {
                    return 0;
                }
            } else if (s.charAt(i-1) == '1' || (s.charAt(i-1) == '2'&& s.charAt(i)>=1 &&s.charAt(i)<='6')){
                curr = curr + pre;
            }
            pre = temp;
        }
        return curr;

    }

    //一维动态规划
    //状态定义：dp[i]为第i个位置解码方法的总数
    public int numDecodings2(String s) {
        char[] chars = s.toCharArray();
        if (chars[0] == '0') return 0;
        int[] dp = new int[chars.length];
        dp[0] = 1;
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] == '0') {
                //如果前一位不是1或者2,显然无法解码
                if (chars[i - 1] != '1' && chars[i - 1] != '2') return 0;
                //如果前一位是1或者2
                dp[i] = i == 1 ? 1 : dp[i - 2];
            } else if (chars[i - 1] == '1' || (chars[i - 1] == '2' && chars[i] >= '1' && chars[i] <= '6')) {
                dp[i] = i == 1 ? dp[i - 1] + 1 : dp[i - 1] + dp[i - 2];
            } else {
                dp[i] = dp[i - 1];
            }
        }
        return dp[chars.length - 1];
    }

}
