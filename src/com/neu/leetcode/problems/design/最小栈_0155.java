package com.neu.leetcode.problems.design;

import java.util.*;

public class 最小栈_0155 {
    class MinStack {
        private Stack<Integer> stack ;
        private List<Integer> list;
        /** initialize your data structure here. */
        public MinStack() {
            stack = new Stack<>();
            list = new LinkedList<>();
        }

        public void push(int x) {
            stack.push(x);
            list.add(x);
        }

        public void pop() {
            Integer num = stack.pop();
            int index = list.indexOf(num);
            list.remove(index);

        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            Collections.sort(list);
            return list.get(0);
        }
    }

    //官方题解  辅助站
    class MinStack1 {
        private Deque<Integer> xStack ;
        private Deque<Integer> minStack;
        /** initialize your data structure here. */
        public MinStack1() {
            xStack = new LinkedList<>();
            minStack = new LinkedList<>();
            minStack.push(Integer.MAX_VALUE);
        }

        public void push(int x) {
           xStack.push(x);
           minStack.push(Math.min(minStack.peek(),x));

           //----------------
            //minStack初始化设置为空
            if (minStack.isEmpty() | minStack.peek() < x){
                minStack.push(x);
            }

        }

        public void pop() {
           xStack.pop();
           minStack.pop();
           //-------------
            int num = xStack.pop();
            if (num == minStack.peek()){
                minStack.pop();
            }
        }

        public int top() {
            return xStack.peek();
        }

        public int getMin() {

            return minStack.peek();
        }
    }

    //官方题解  自定义类
    class MinStack2 {
        private Stack<Node> stack ;
        /** initialize your data structure here. */
        public MinStack2() {
            stack = new Stack<>();
        }

        public void push(int x) {
            if (stack.isEmpty()){
                stack.push(new Node(x,x));
            } else {
                stack.push(new Node(x,Math.min(x,stack.peek().min)));
            }

        }

        public void pop() {
            stack.pop();
        }

        public int top() {
            return stack.peek().val;
        }

        public int getMin() {

            return stack.peek().min;
        }

        private  class Node{
            int val;
            int min;
            public Node(int val,int min){
                this.val = val;
                this.min = min;
            }
        }
    }

    //官方题解 自定栈
    class MinStack3{
        private Node head ;
        /** initialize your data structure here. */
        public MinStack3() {

        }

        public void push(int x) {
            if (head == null){
                head = new Node(x,x);
            } else {
                head = new Node(x,Math.min(x,head.min),head);
            }

        }

        public void pop() {
            head = head.next;
        }

        public int top() {
            return head.val;
        }

        public int getMin() {

            return head.min;
        }

        private  class Node{
            int val;
            int min;
            Node next;
            public Node(int val,int min,Node next){
                this.val = val;
                this.min = min;
                this.next = next;
            }

            public Node(int val,int min){
                this.val = val;
                this.min = min;
            }
        }
    }
}
