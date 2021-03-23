package com.neu.leetcode.problems.string;

import java.util.ArrayList;
import java.util.List;

public class 复原IP地址_0093 {

    //官方题解 回溯
    static final  int SEG_COUNT = 4;
    List<String> ans = new ArrayList<>();
    int[] segments = new int[SEG_COUNT];

    public List<String> restoreIpAddresses(String s) {
        segments = new int[SEG_COUNT];
        dfs(s,0,0);
        return ans;
    }

    private void dfs(String s, int segId, int segStart) {
        // 如果找到了 4 段 IP 地址并且遍历完了字符串，那么就是一种答案
        if (segId == SEG_COUNT){
            if (segStart == s.length()){
                StringBuffer stringBuffer = new StringBuffer();
                for (int i=0;i<SEG_COUNT;i++){
                    stringBuffer.append(segments[i]);
                    if (i != SEG_COUNT-1){
                        stringBuffer.append(".");
                    }
                }
                ans.add(stringBuffer.toString());
            }
            return;
        }

        // 如果还没有找到 4 段 IP 地址就已经遍历完了字符串，那么提前回溯
        if (segStart == s.length()){
            return;
        }

        //由于不能有前导0， 如果数字为0，那么这一段ip地址只能为0
        if (s.charAt(segStart) == '0'){
            segments[segId] = 0;
            dfs(s,segId+1,segStart+1);
        }

        //一般情况 枚举每一种可能性
        int addr = 0;
        for (int segEnd = segStart;segEnd<s.length();segEnd++){
            addr = addr*10 +s.charAt(segEnd);
            if (addr>0 && addr <0XFF){
                dfs(s,segId+1,segStart);
            } else {
                break;
            }
        }
    }
}
