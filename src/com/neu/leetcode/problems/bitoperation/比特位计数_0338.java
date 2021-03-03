package com.neu.leetcode.problems.bitoperation;

public class 比特位计数_0338 {
    public static void main(String[] args) {
        System.out.println(4&3);
    }
    //true  中等
    public int[] countBits(int num) {
        int[] arr = new int[num+1];
        for (int i=0;i<=num;i++){
            int count = 0;
            int n = i;
            while (n > 0){
                if (n%2 == 1){
                    count++;
                }
                n /= 2;
            }
            arr[i] = count;
        }

        return arr;
    }

    //官方题解
    public int[] countBits1(int num) {
        int[] bits = new int[num+1];
        for (int i=0;i<=num;i++){
            bits[i] = countOnes(i);
        }
        return bits;
    }

    public int countOnes(int x){
        int one = 0;
        while (x>0){
            x &= (x-1);
            one++;
        }
        return one;
    }

    //官方题解  动态规划-最高有效位
    public int[] countBits2(int num){
        int[] bits = new int[num+1];
        int hightbits = 0;
        for (int i=1;i<=num;i++){
            if ((i & (i-1))==0){
                hightbits = i;
            }
            bits[i] = bits[i-hightbits]+1;
        }
        return bits;
    }

    //官方题解 动态规划-最低有效位
    public int[] countBits3(int num){
        int[] bits = new int[num+1];
        for (int i=1;i<=num;i++){
            //[x/2]                 x除以2的余数  x是奇数则 加一 否则加0
            bits[i] = bits[i >> 1] + (i&1);
        }
        return bits;
    }

    //方法四 动态规划 - 最低设置位
    public int[] countBits4(int num){
        int[] bits = new int[num+1];
        for (int i=1;i<= num;i++){
            bits[i] = bits[i & (i-1)]+1;
        }
        return bits;
    }

    //方法五 清晰的思路
    /*
    奇数：二进制表示中，奇数一定比前面那个偶数多一个 1，因为多的就是最低位的 1。
          举例：
         0 = 0       1 = 1
         2 = 10      3 = 11
偶数：二进制表示中，偶数中 1 的个数一定和除以 2 之后的那个数一样多。因为最低位是 0，除以 2 就是右移一位，也就是把那个 0 抹掉而已，所以 1 的个数是不变的。
           举例：
          2 = 10       4 = 100       8 = 1000
          3 = 11       6 = 110       12 = 1100


     */
    public int[] countBits5(int num){
        int[] bits = new int[num+1];
        for (int i=1;i<= num;i++){
            if (i%2 == 0){
                bits[i] = bits[i/2];
            } else {
                bits[i] = bits[i-1]+1;
            }
        }
        return bits;
    }
}
