package com.neu.leetcode.problems.stack;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class 用栈实现队列_0232 {
    class MyQueue1 {
        private Stack<Integer> stack;
        private Stack<Integer> tempStack;
        /** Initialize your data structure here. */
        public MyQueue1() {
            stack = new Stack<>();
            tempStack = new Stack<>();
        }

        /** Push element x to the back of queue. */
        public void push(int x) {
            stack.push(x);
        }

        /** Removes the element from in front of queue and returns that element. */
        public int pop() {
            int num = 0;
            changeStack();
            num = tempStack.pop();
            rechangeStack();
            return num;
        }

        /** Get the front element. */
        public int peek() {
            changeStack();
            int num = tempStack.peek();
            rechangeStack();
            return num;
        }

        /** Returns whether the queue is empty. */
        public boolean empty() {

            return stack.isEmpty();
        }

        private void changeStack(){

            while (!stack.isEmpty()){
                tempStack.push(stack.pop());
            }
        }

        private void rechangeStack(){
            while (!tempStack.isEmpty()){
                stack.push(tempStack.pop());
            }
        }

    }

    //官方题解1 双栈
    class MyQueue {
        private Deque<Integer> inStack;
        private Deque<Integer> outStack;
        /** Initialize your data structure here. */
        public MyQueue() {
            inStack = new LinkedList<>();
            outStack = new LinkedList<>();
        }

        /** Push element x to the back of queue. */
        public void push(int x) {
            inStack.push(x);
        }

        /** Removes the element from in front of queue and returns that element. */
        public int pop() {
            if (outStack.isEmpty()){
                in2out();
            }
            return outStack.pop();
        }

        /** Get the front element. */
        public int peek() {
            if (outStack.isEmpty()){
                in2out();
            }
            return outStack.peek();
        }

        /** Returns whether the queue is empty. */
        public boolean empty() {
            return inStack.isEmpty() && outStack.isEmpty();

        }

        private void in2out(){
            while (!inStack.isEmpty()){
                outStack.push(inStack.pop());
            }
        }

    }

    //官方题解2
    class MyQueue2 {
        private Deque<Integer> inStack;
        private Deque<Integer> outStack;
        private int front;
        /** Initialize your data structure here. */
        public MyQueue2() {
            inStack = new LinkedList<>();
            outStack = new LinkedList<>();
        }

        /** Push element x to the back of queue. */
        public void push(int x) {
            if (inStack.isEmpty())
                front = x;
            while (!inStack.isEmpty())
                outStack.push(inStack.pop());
            outStack.push(x);
            while (!outStack.isEmpty())
                inStack.push(outStack.pop());

        }

        /** Removes the element from in front of queue and returns that element. */
        public int pop() {
            int num = inStack.pop();
            if (!inStack.isEmpty()){
                front = inStack.peek();
            }
            return num;
        }

        /** Get the front element. */
        public int peek() {

            return front;
        }

        /** Returns whether the queue is empty. */
        public boolean empty() {
            return inStack.isEmpty() ;

        }

        private void in2out(){
            while (!inStack.isEmpty()){
                outStack.push(inStack.pop());
            }
        }

    }

    //使用两个栈 入队 - O(1)，出队 - 摊还复杂度 O(1)
    class MyQueue3 {
        private Stack<Integer> s1 = new Stack<>();
        private Stack<Integer> s2 = new Stack<>();

        private int front;
        /** Initialize your data structure here. */
        public MyQueue3() {

        }

        /** Push element x to the back of queue. */
        public void push(int x) {
            if (s1.empty()){
                front = x;
            }
            s1.push(x);
        }

        /** Removes the element from in front of queue and returns that element. */
        public int pop() {
           if (s2.isEmpty()){
               while (!s1.isEmpty()){
                   s2.push(s1.pop());
               }
           }
            return s2.pop();
        }

        /** Get the front element. */
        public int peek() {
            if (!s2.isEmpty()){
                return s2.peek();
            }
            return front;
        }

        /** Returns whether the queue is empty. */
        public boolean empty() {
            return s1.isEmpty() && s2.isEmpty();

        }
    }
}
