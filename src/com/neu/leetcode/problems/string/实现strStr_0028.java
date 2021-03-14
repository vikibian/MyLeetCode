package com.neu.leetcode.problems.string;

public class 实现strStr_0028 {
    public int strStr(String haystack, String needle) {
        if(needle == null || needle.length() == 0){
            return 0;
        }
        int len1 = haystack.length();
        int len2 = needle.length();
        if (len1< len2){
            return -1;
        }
        int index1 = 0,index2 = 0;
        int ans = -1;

        while (index1<len1 && index2<len2){
            if (haystack.charAt(index1) == needle.charAt(index2)){
                int i =index1,j=index2;
                while (i<len1&&j<len2&&haystack.charAt(i) == needle.charAt(j)){
                    i++;
                    j++;
                }
                if (j == len2){
                    return index1;
                }else {
                    index1++;
                }
            } else {
                index1++;
            }
        }
        return -1;
    }

    //官方题解 子串逐一比较 - 线性时间复杂度
    public int strStr1(String haystack, String needle){
        int L =needle.length(),n = haystack.length();
        for (int start=0;start<n-L+1;start++){
            if (haystack.substring(start,start+L).equals(needle)){
                return start;
            }
        }
        return -1;
    }

    //官方题解 双指针-线性时间复杂度
    public int strStr2(String haystack, String needle){
        int L = needle.length(),n=haystack.length();
        if (L == 0){
            return 0;
        }
        int pn = 0;
        while (pn<n-L+1){
            while (pn<n-L+1&&haystack.charAt(pn) != needle.charAt(0)){
                pn++;
            }

            int curLen =0,pL=0;
            while (pL<L&&pn<n&&haystack.charAt(pn) == needle.charAt(pL)){
                curLen++;
                pn++;
                pL++;
            }

            if (curLen == L){
                return pn-L;
            }
            //回溯
            pn = n-curLen+1;
        }
        return -1;
    }

    // function to convert character to integer
    public int charToInt(int idx, String s) {
        return (int)s.charAt(idx) - (int)'a';
    }

    public int strStr3(String haystack, String needle) {
        int L = needle.length(), n = haystack.length();
        if (L > n) return -1;

        // base value for the rolling hash function
        int a = 26;
        // modulus value for the rolling hash function to avoid overflow
        long modulus = (long)Math.pow(2, 31);

        // compute the hash of strings haystack[:L], needle[:L]
        long h = 0, ref_h = 0;
        for (int i = 0; i < L; ++i) {
            h = (h * a + charToInt(i, haystack)) % modulus;
            ref_h = (ref_h * a + charToInt(i, needle)) % modulus;
        }
        if (h == ref_h) return 0;

        // const value to be used often : a**L % modulus
        long aL = 1;
        for (int i = 1; i <= L; ++i) aL = (aL * a) % modulus;

        for (int start = 1; start < n - L + 1; ++start) {
            // compute rolling hash in O(1) time
            h = (h * a - charToInt(start - 1, haystack) * aL
                    + charToInt(start + L - 1, haystack)) % modulus;
            if (h == ref_h) return start;
        }
        return -1;
    }

    //KMP 算法
    //获取字符串的部分匹配值
    public int[] KMPNext(String dest){
        //创建一个next数组保存部分匹配值
        int[] next = new int[dest.length()];
        next[0] = 0;//如果字符串的部分匹配值是1 部分匹配值是0
        for (int i=0,j=0;i<dest.length();i++){
            //当dest.charAt(i) != dest.charAt(j) 需要从next[j-1]获取新的j值
            //直到我们发现有 dest.charAt(i) == dest.charAt(j)成立才退出
            //KMP算法的核心
            while (j>0 && dest.charAt(i) != dest.charAt(j)){
                j = next[j-1];
            }

            //当dest.charAt(i) == dest.charAt(j) 时部分匹配值加一
            if (dest.charAt(i) == dest.charAt(j)){
                j++;
            }
            next[i] = j;
        }
        return next;
    }

    public int strStr4(String haystack, String needle) {
        int[] next = KMPNext(needle);
        for (int i=0,j=0;i<haystack.length();i++){
            while (j>0&&haystack.charAt(i) != needle.charAt(j)){
                j = next[j-1];
            }

            if (haystack.charAt(i) == needle.charAt(j)){
                j++;
            }
            if (j == needle.length() ){
                return i-j+1;
            }
        }
        return -1;
    }

}
