package com.neu.leetcode.problems.tree;

import com.sun.org.apache.xerces.internal.impl.dv.XSSimpleType;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class 翻转二叉树_0226 {

    //错误解法
    public TreeNode invertTree(TreeNode root) {
        if (root == null){
            return root;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        stack.add(root);
        while (!stack.isEmpty()){
            int size = stack.size();
            if (size % 2 ==0){
                Deque<TreeNode> list = new LinkedList<>();
                for (int i=0;i<(size/2);i++){
                    TreeNode left = stack.pollFirst();
                    TreeNode right =stack.pollLast();

                    int temp = left.val;
                    left.val = right.val;
                    right.val = temp;

                    if (left.left != null){
                        list.addFirst(left.left);
                    }

                    if (left.right != null){
                        list.addFirst(left.right);
                    }


                    if (right.right != null){
                        list.addLast(right.right);
                    }

                    if (right.left != null){
                        list.addLast(right.left);
                    }
                }
                stack.addAll(new LinkedList<>(list));
            } else {
                TreeNode curNode = stack.pop();
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

    public TreeNode invertTree1(TreeNode root) {
        if (root == null){
            return root;
        }
        reverseNode(root);

        return root;
    }

    private void reverseNode(TreeNode root) {
        if (root == null){
            return;
        }

        TreeNode temp = null;
        temp = root.left;
        root.left = root.right;
        root.right = temp;

        reverseNode(root.left);
        reverseNode(root.right);
    }

    //官方题解 递归
    public TreeNode invertTree2(TreeNode root){
        if(root == null){
            return null;
        }
        TreeNode left = invertTree2(root.left);
        TreeNode right = invertTree2(root.right);

        root.left = left;
        root.right = right;

        return root;
    }

    public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
}
