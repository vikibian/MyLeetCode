package com.neu.leetcode.problems.math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 计数质数_0204 {

    public static void main(String[] args) {
        countPrimes2(10);
    }

    public int countPrimes(int n) {
        if (n == 0 || n==1 || n==2){
            return 0;
        }
        int count = 1;
        for (int i =3;i<n;i++){
            if (isPrime(i)){
                count++;
            }
        }
        return count;
    }

    public boolean isPrime(int n){
        if (n <2){
            return false;
        }
        if (n == 2 || n ==3){
            return true;
        } else {
            int a = (int)Math.sqrt(n);
            for (int i= 2;i<=a;i++){
                if (n %i==0){
                    return false;
                }
            }
            return true;
        }
    }

    //官方题解 枚举
    public int countPrimes1(int n){
        int ans = 0;
        for (int i = 2;i<n;i++){
            ans += isPrime1(i) ? 1:0;
        }
        return ans;
    }

    private boolean isPrime1(int n) {
        for (int i=2;i*i<=n;i++){
            if (n % i == 0){
                return false;
            }
        }
        return true;
    }

    //官方题解 埃氏筛
    public static int countPrimes2(int n){
        int[] isPrime = new int[n];
        Arrays.fill(isPrime,1);
        int ans = 0;
        for (int i=2;i<n;i++){
            if (isPrime[i] == 1){
                ans++;
                if ((long) i*i <n){
                    for (int j =i*i;j<n;j++){
                        isPrime[j] = 0;
                    }
                }
            }
        }

        return ans;
    }

    //官方题解 线性筛
    public static  int countPrimes3(int n){
        List<Integer> primes = new ArrayList<>();
        int[] isPrimes = new int[n];
        Arrays.fill(isPrimes,1);
        for (int i=2;i<n;i++){
            if (isPrimes[i] == 1){
                primes.add(i);
            }
            for (int j=0;j<primes.size() && i * primes.get(j)<n;j++){
                isPrimes[i * primes.get(j)] = 0;
                if (i % primes.get(j) == 0){
                    break;
                }
            }
        }
        return primes.size();
    }
}
