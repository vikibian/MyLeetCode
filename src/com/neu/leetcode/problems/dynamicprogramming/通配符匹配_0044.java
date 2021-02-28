package com.neu.leetcode.problems.dynamicprogramming;

public class 通配符匹配_0044 {
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean[][] dp =new boolean[m+1][n+1];
        dp[0][0] = true;
        for (int i=0;i<n;i++){
            if (p.charAt(i-1) == '*'){
                dp[0][i] = true;
            } else {
                break;
            }
        }

        for (int i=1;i<=m;i++){
            for (int j=1;j<=n;j++){
                if (p.charAt(j-1) == '*'){
                    //dp[i-1][j]表示 使用星号转移而来
                    //dp[i][j-1]  不是用星号
                    dp[i][j] = dp[i-1][j] ||dp[i][j-1];
                } else if (p.charAt(j-1) == '?' || s.charAt(i-1) == p.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                }
            }
        }

        return dp[m][n];
    }


    //贪心算法

    public boolean isMatch1(String s, String p) {
        int sRight = s.length(),pRight = s.length();
        while (sRight >0 &&pRight>0 && p.charAt(pRight-1)!='*'){
            if (charMatch(s.charAt(sRight-1),p.charAt(pRight-1))){
                pRight--;
                sRight--;
            } else {
                return false;
            }
        }

        if (pRight == 0){
            return sRight==0;
        }

        // 我们用 sIndex 和 pIndex 表示当前遍历到 s 和 p 的位置
        // 此时我们正在 s 中寻找某个 u_i
        // 其在 s 和 p 中的起始位置为 sRecord 和 pRecord

        // sIndex 和 sRecord 的初始值为 0
        // 即我们从字符串 s 的首位开始匹配


        int sIndex = 0,pIndex=0;

        // pIndex 和 pRecord 的初始值为 1
        // 这是因为模式 p 的首位是星号，那么 u_1 的起始位置为 1
        int sRecord=-1,pRecord=-1;

        while (sIndex<sRight && pIndex<pRight){
            if (p.charAt(pIndex) == '*'){
                // 如果遇到星号，说明找到了 u_i，开始寻找 u_i+1
                ++pIndex;
                sRecord = sIndex;
                pRecord = pIndex;
            } else if (charMatch(s.charAt(sIndex),p.charAt(pIndex))){
                // 如果两个字符可以匹配，就继续寻找 u_i 的下一个字符
                ++pIndex;
                ++sIndex;
            } else if (sRecord!=-1 && sRecord +1 <sRight){
                // 如果两个字符不匹配，那么需要重新寻找 u_i
                // 枚举下一个 s 中的起始位置
                ++sRecord;
                sIndex = sRecord;
                pIndex = pRecord;
            } else {
                // 如果不匹配并且下一个起始位置不存在，那么匹配失败
                return false;
            }
        }
        return allStars(p,pIndex,sIndex);
    }

    public boolean allStars(String str,int left,int right){
        for (int i=left;i<right;i++){
            if (str.charAt(i) !='*'){
                return false;
            }
        }

        return true;
    }


    public boolean charMatch(char u,char v){
        return u==v || v=='?';
    }

}
