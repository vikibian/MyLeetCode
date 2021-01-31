package com.neu.leetcode.problems.tree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class 二叉树的前序遍历_0144 {

    //递归
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new LinkedList<>();
        if (root == null){
            return list;
        }
        preorder(root,list);
        return list;
    }

    private void preorder(TreeNode root, List<Integer> list) {
        list.add(root.val);
        if (root.left != null){
            preorder(root.left,list);
        }

        if (root.right != null ){
            preorder(root.right,list);
        }
    }

    //迭代  官方题解
    public List<Integer> preorderTraversal1(TreeNode root) {
        List<Integer> list = new LinkedList<>();
        if (root == null){
            return list;
        }
        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        TreeNode node = root;
        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                list.add(node.val);
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            node = node.right;
        }

        return list;
    }

    //morris遍历
    public List<Integer> preorderTraversalMorrris(TreeNode root){
        List<Integer> list = new LinkedList<>();
        //感觉不加root是否为null的判断也可以
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
                    list.add(curNode.val);
                    tempNode.right = curNode;
                    curNode = curNode.left;
                    continue;
                } else {
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
