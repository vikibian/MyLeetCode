package com.neu.leetcode.problems.stack;

import java.util.LinkedList;
import java.util.Queue;

public class 用队列实现栈_0225 {
    class MyStack {
        private Queue<Integer> queue1;
        private Queue<Integer> queue2;
        /** Initialize your data structure here. */
        public MyStack() {
            queue1 = new LinkedList<>();
            queue2 = new LinkedList<>();

        }

        /** Push element x onto stack. */
        public void push(int x) {
            queue1.offer(x);
        }

        /** Removes the element on top of the stack and returns that element. */
        public int pop() {
            int num = changQueue(1);
            rechangeQueue();
            return num;
        }

        /** Get the top element. */
        public int top() {
            int num = changQueue(2);
            rechangeQueue();
            return num;
        }

        /** Returns whether the stack is empty. */
        public boolean empty() {
            return queue1.isEmpty();
        }

        private int changQueue(int flag){
            int index = 0;
            int size = queue1.size();
            int ans = 0;
            while (!queue1.isEmpty()){
                int num = queue1.poll();
                index++;
                if (flag == 1 && index==size){
                    ans =  num;
                } else if (flag == 1 && index<size){
                    queue2.offer(num);
                }
                if (flag == 2  ){
                    queue2.offer(num);
                    ans =  num;
                }
            }


            return ans;

        }

        private void rechangeQueue(){
            while (!queue2.isEmpty()){
                queue1.offer(queue2.poll());
            }
        }
    }

    //官方题解 两个队列
    class MyStack1 {
        private Queue<Integer> queue1;
        private Queue<Integer> queue2;
        /** Initialize your data structure here. */
        public MyStack1() {
            queue1 = new LinkedList<>();
            queue2 = new LinkedList<>();

        }

        /** Push element x onto stack. */
        public void push(int x) {
            queue2.offer(x);
            while (!queue1.isEmpty()){
                queue1.offer(queue2.poll());
            }

            Queue<Integer> temp = queue1;
            queue1 = queue2;
            queue2 = temp;
        }

        /** Removes the element on top of the stack and returns that element. */
        public int pop() {
            return queue1.poll();
        }

        /** Get the top element. */
        public int top() {
            return queue1.peek();
        }

        /** Returns whether the stack is empty. */
        public boolean empty() {
            return queue1.isEmpty();
        }

    }

    //官方题解 一个队列
    class MyStack2 {
        private Queue<Integer> queue1;

        /** Initialize your data structure here. */
        public MyStack2() {
            queue1 = new LinkedList<>();
        }

        /** Push element x onto stack. */
        public void push(int x) {
            int n = queue1.size();
            queue1.offer(x);
            for (int i=0;i<n;i++){
                queue1.offer(queue1.poll());
            }
        }

        /** Removes the element on top of the stack and returns that element. */
        public int pop() {
            return queue1.poll();
        }

        /** Get the top element. */
        public int top() {
            return queue1.peek();
        }

        /** Returns whether the stack is empty. */
        public boolean empty() {
            return queue1.isEmpty();
        }

    }
}
