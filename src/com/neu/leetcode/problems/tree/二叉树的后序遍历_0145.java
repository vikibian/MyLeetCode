package com.neu.leetcode.problems.tree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class 二叉树的后序遍历_0145 {

    public List<Integer> postorderTraversal(TreeNode root) {

        List<Integer> list = new LinkedList<>();
        postOrder(root,list);
        return list;
    }

    private void postOrder(TreeNode root, List<Integer> list) {
        if (root == null){
            return;
        }
        postOrder(root.left,list);
        postOrder(root.right,list);
        list.add(root.val);
    }

    //迭代
    public List<Integer> postorderTraversal1(TreeNode root){
        List<Integer> list = new LinkedList<>();
        if (root == null){
            return list;
        }

        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        TreeNode node = root,preNode = null;
        while ( !stack.isEmpty() || node != null){

//            node = stack.peek();

            while (node.left != null ){
                stack.push(node.left);
                preNode = node;
                node = node.left;
            }

            list.add(node.val);

            if (preNode.right != null){
                node = preNode.right;
            }

            if (node.left == null && node.right == null){
                list.add(node.val);
            } else {
                continue;
            }


            node = stack.pop();
            list.add(node.val);

            node = stack.pop();

            if (node.right != null){
                if (node.right.left != null){
                   node = node.right.left;
                    continue;
                }
                list.add(node.right.val);
            }
            list.add(node.val);
        }

        return list;
    }

    //官方题解 迭代
    public List<Integer> postorderTraversal2(TreeNode root){
        List<Integer> list = new LinkedList<>();
        if (root == null){
            return list;
        }

        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode prev = root;
        while (root != null || !stack.isEmpty()){
            while (root!= null){
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (root.right == null || root.right == prev){
                list.add(root.val);
                prev = root;
                root = null;
            } else {
                stack.push(root);
                root = root.right;
            }
        }

        return list;
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
