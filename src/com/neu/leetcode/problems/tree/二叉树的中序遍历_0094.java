package com.neu.leetcode.problems.tree;


import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class 二叉树的中序遍历_0094 {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null){
            return list;
        }

//        if (root.left == null && root.right ==null){
//
//        }

        list.addAll(inorderTraversal(root.left));
        list.add(root.val);
        list.addAll(inorderTraversal(root.right));
        return list;
    }

    //官方题解 迭代
    public List<Integer> inorderTraversal1(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null){
            return list;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode node = root;
        while (node != null || !stack.isEmpty()){
            while (node!= null){
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            list.add(node.val);
            node = node.right;
        }
        return list;
    }

    //morris遍历
    public List<Integer> inorderTraversalMorris(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null){
            return list;
        }
        TreeNode curNode = root;
        TreeNode tempNode = null;
        while (curNode!= null){
            tempNode = curNode.left;
            if (tempNode != null){
                while (tempNode.right != null && tempNode.right != curNode){
                    tempNode = tempNode.right;
                }
                if (tempNode.right == null){
                    tempNode.right = curNode;
                    curNode = curNode.left;
                    continue;
                    //把 curNode = curNode.right 加到下面两个else中则不需要continue
                } else {
                    list.add(curNode.val);
                    tempNode.right = null;
                }
            } else {
                list.add(curNode.val);
            }

            curNode = curNode.right;
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
