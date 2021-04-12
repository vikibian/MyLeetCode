package com.neu.leetcode.problems.sort;

import java.util.Arrays;
import java.util.Comparator;

public class 最大数_0179 {
    //没通过
    public String largestNumber(int[] nums) {
        //3432  34323
        Integer[] nums1 = new Integer[nums.length];
        for (int i=0;i<nums.length;i++){
            nums1[i]  = nums[i];
        }
        Arrays.sort(nums1,new Comparator<Integer>(){
            @Override
            public int compare(Integer o1, Integer o2) {
                String num1 = Integer.toString(o1);
                String num2 = Integer.toString(o2);
                int len1 = num1.length();
                int len2 = num2.length();
                int index1 = 0;
                int index2 = 0;
                while (index1<len1 || index2<len2){
                    char c1 = (index1<len1?num1.charAt(index1++):num1.charAt(len1-1));
                    char c2 = (index2<len2?num2.charAt(index2++):num2.charAt(len2-1));
                    if (c1 != c2){
//                        if (index1<index2){
//                            return 1  ;
//                        } else {
//                            return -1;
//                        }
                        return (int)c2-c1;
                    }
                }

                return 0;
            }
        });
        StringBuffer stringBuffer = new StringBuffer();
        for (int i=0;i<nums1.length;i++){
            stringBuffer.append(nums1[i]);
        }
        System.out.println(Arrays.toString(nums1));
        return stringBuffer.toString();
    }

    //官方题解 我是智障
    public String largestNumber1(int[] nums){
        int n = nums.length;
        Integer[] numsArr = new Integer[n];
        for (int i=0;i<n;i++){
            numsArr[i] = nums[i];
        }

        Arrays.sort(numsArr,(x,y)->{
            long sx=x,sy=y;
            while (sx<=x){
                sx *= 10;
            }

            while (sy <= y){
                sy *= 10;
            }
            return (int) (-sy*x-y+sx*y+x);
        });

        if (numsArr[0] == 0){
            return "0";
        }
        StringBuilder ret = new StringBuilder();
        for (int num : numsArr){
            ret.append(num);
        }

        return ret.toString();
    }

    //宫水三叶
    public String largestNumber2(int[] nums){
        int n = nums.length;
        String[] ss = new String[n];
        for (int i=0;i<n;i++){
            ss[i] = ""+nums[i];
        }
        Arrays.sort(ss,(a,b)->{
            String sa = a+b,sb = b+a;
            return sb.compareTo(sa);
        });
        StringBuilder sb = new StringBuilder();
        for (String s : ss){
            sb.append(s);
        }
        int len = sb.length();
        int k=0;
        while (k<len-1 && sb.charAt(k)=='0'){
            k++;
        }
        return sb.substring(k);
    }
}
