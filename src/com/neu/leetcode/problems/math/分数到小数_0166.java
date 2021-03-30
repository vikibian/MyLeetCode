package com.neu.leetcode.problems.math;

import java.util.HashMap;
import java.util.Map;

public class 分数到小数_0166 {
    //长除法
    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0){
            return "0";
        }
        StringBuilder fraction = new StringBuilder();
        if (numerator<0 ^ denominator<0){
            fraction.append("-");
        }
        long dividend = Math.abs(Long.valueOf(numerator));
        long divisor = Math.abs(Long.valueOf(denominator));
        fraction.append(dividend/divisor);
        long remandier = dividend%divisor;
        if (remandier == 0){
            return fraction.toString();
        }
        Map<Long,Integer> map = new HashMap<>();
        while (remandier!= 0){
            if (map.containsKey(remandier)){
                fraction.insert(map.get(remandier),"(");
                fraction.append(")");
                break;
            }
            remandier *= 10;
            fraction.append(String.valueOf(remandier/divisor));
            remandier = remandier%divisor;
        }
        return fraction.toString();
    }
}
