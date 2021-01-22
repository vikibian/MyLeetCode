package com.neu.leetcode.problems.tree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class 填充每个节点的下一个右侧节点指针_0116 {

    public static void main(String[] args) {
        Node root = new Node(1);
        Node root1 = new Node(2);
        Node root2 = new Node(3);

        Deque<Node> stack = new LinkedList<>();
        stack.push(root);
        stack.add(root1);
        stack.push(root2);

        System.out.println(stack.poll().val);
        System.out.println(stack.pop().val);
//        System.out.println(stack.pop().val);
    }

    public Node connect(Node root) {
        if (root == null){
            return null;
        }

        Deque<Node> stack = new LinkedList<>();
        stack.addLast(root);
        Node pre = null;
        while (!stack.isEmpty()){
            Node node = stack.pollFirst();
            if (pre != null){
                pre.next = node;
            }

            if (node.left != null){
                stack.addLast(node.left);
            }

            if (node.right != null){
                stack.addLast(node.right);
            }


            pre = node;
        }
        Node cur = root;
        while (cur.right != null){
            cur.next = null;
            cur = cur.right;
        }

        return root;

    }

    //官方题解一 层次遍历
    public Node connect2(Node root){
        if (root == null){
            return null;
        }

        Deque<Node> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()){
            int size = stack.size();

            for (int i=0;i<size;i++){
                Node node = stack.poll();

                if (i<size-1){
                    node.next = stack.peek();
                }

                if (node.left != null){
                    stack.add(node.left);
                }

                if (node.right != null){
                    stack.add(node.right);
                }
            }
        }

        return root;
    }

    //官方题解二 使用已建立的next指针
    public Node connect3(Node root){
        if (root == null){
            return null;
        }
        Node leftmost = root;
        while (leftmost.left != null){
            Node head = leftmost;
            while (head != null){
                head.left.next = head.right;

                if (head.next != null){
                    head.right.next = head.next.left;
                }

                head = head.next;
            }

            leftmost = leftmost.left;
        }

        return root;
    }

    //解法三 这个迭代解法比较好
    public Node connect4(Node root){
        dfs(root);
        return root;

    }

    private void dfs(Node root) {
        if (root == null){
            return;
        }
        Node left = root.left;
        Node right = root.right;

        while (left != null){
            left.next = right;
            left = left.right;
            right = right.left;
        }

        dfs(root.left);
        dfs(root.right);
    }

    static class Node {
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
