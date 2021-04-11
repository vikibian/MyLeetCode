package com.neu.leetcode.problems.math;

import java.util.*;

public class 丑数2_0264 {
    //超时
    public int nthUglyNumber(int n) {
        int count = 1;
        Deque<Integer> stack = new LinkedList<>();

        while (stack.size() < n){
            if (isUgly(count)){
                stack.push(count);
            }
            count++;
        }

        return stack.pop();

    }

    public boolean isUgly(int num){
        if (num == 0){
            return false;
        }
        if (num == 1){
            return true;
        }

        if (num % 2 == 0){
            return isUgly(num/2);
        }
        if (num%3 == 0){
            return isUgly(num/3);
        }
        if (num % 5 == 0){
            return isUgly(num / 5);
        }

        return false;
    }

    //官方题解 堆
    class Ugly{
        public int[] nums = new int[1690];

        Ugly(){
            HashSet<Long> seen = new HashSet<>();
            PriorityQueue<Long> heap = new PriorityQueue<>();
            seen.add(1L);
            heap.add(1L);
            long curUgly,newUgly;
            int[] primes = new int[]{2,3,5};
            for (int i=0;i<1690;i++){
                curUgly = heap.poll();
                nums[i] = (int)curUgly;
                for (int num : primes){
                    newUgly = curUgly*num;
                    if (!seen.contains(newUgly)){
                        seen.add(newUgly);
                        heap.add(newUgly);
                    }
                }
            }
        }
    }

    public  Ugly ugly = new Ugly();
    public int nthUglyNumber1(int n){
        return ugly.nums[n-1];
    }


    //官方题解 2  动态规划 3指针
    class  Ugly2{
        public int[] nums = new int[1690];
        Ugly2(){
            nums[0]=1;
            int ugly,i2=0,i3=0,i5=0;
            for (int i=1;i<1690;i++){
                ugly = Math.min(Math.min(nums[i2]*2,nums[i3]*3),nums[i5]*5);
                nums[i] = ugly;

                if (ugly == nums[i2]*2){
                    i2++;
                }
                if (ugly == nums[i3]*3){
                    i3++;
                }
                if (ugly == nums[i5]*5){
                    i5++;
                }
            }
        }
    }

    public Ugly2 ugly2 = new Ugly2();
    public int nthUglyNumber2(int n){
        return ugly2.nums[n-1];
    }

    public int nthUglyNumber3(int n){
        int[] factors = {2,3,5};
        Set<Long> seen = new HashSet<>();
        PriorityQueue<Long> heap = new PriorityQueue<>();
        seen.add(1L);
        heap.offer(1L);
        int ugly = 0;
        for (int i=0;i<n;i++){
            long curr = heap.poll();
            ugly = (int) curr;
            for (int num : factors){
                long next = num*ugly;
                if (seen.add(next)){
                    heap.offer(next);
                }
            }
        }
        return ugly;
    }
}
