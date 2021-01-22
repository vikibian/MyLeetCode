package com.neu.leetcode.problems.tree;

import java.util.Deque;
import java.util.LinkedList;

public class 填充每一个节点的下一个右侧节点指针2_0117 {

    //解法类似  官方题解的层次遍历
    public Node connect(Node root) {
        if(root == null){
            return null;
        }

        Deque<Node> stack = new LinkedList<>();
        stack.push(root);

        while (!stack.isEmpty()){

            int size = stack.size();
            for (int i=0;i<size;i++){
                Node curNode = stack.pop();
                if (i<size-1){
                    curNode.next = stack.peek();
                }

                if(curNode.left != null){
                    stack.add(curNode.left);
                }

                if (curNode.right != null){
                    stack.add(curNode.right);
                }

            }
        }

        return root;
    }


    //使用已建立的next指针
    Node last = null,nextStart = null;
    public Node connect2(Node root){
        if (root == null){
            return null;
        }
        Node start = root;
        while (start != null){
            last = null;
            nextStart = null;
            for (Node p = start;p != null;p=p.next){

                if(p.left != null){
                    handle(p.left);
                }

                if (p.right != null){
                    handle(p.right);
                }
            }

            start = nextStart;
        }

        return root;
    }

    private void handle(Node p) {
        if (last != null){
            last.next = p;
        }
        if (nextStart == null){
            nextStart = p;
        }
        last = p;
    }


    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    };
}
