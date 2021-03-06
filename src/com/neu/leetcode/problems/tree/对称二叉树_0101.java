package com.neu.leetcode.problems.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class 对称二叉树_0101 {

    //递归
    public boolean isSymmetric(TreeNode root) {
        if (root == null){
            return true;
        }

//        return isTrue(root.left,root.right);
        return iteration(root.left,root.right);
    }

    public boolean isTrue(TreeNode left,TreeNode right){
        if (left == null && right == null){
            return true;
        } else if (left == null || right == null){
            return false;
        } else if (left.val == right.val){
            return isTrue(left.left,right.right) && isTrue(left.right,right.left);
        }


        return false;
    }

    //迭代
    public boolean iteration(TreeNode left,TreeNode right){

        if (left == null && right == null){
            return true;
        } else if (left == null || right == null){
            return false;
        }

        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack1.push(left);
        stack2.push(right);

        while (!stack1.isEmpty() && !stack2.isEmpty()){
            TreeNode node1 = stack1.pop();
            TreeNode node2 = stack2.pop();

            if (node1 == null && node2 == null){
                continue;
            } else if (node1 == null || node2 == null){
                return false;
            }

            if (node1.val != node2.val){
                return false;
            }

            stack1.push(node1.left);
            stack1.push(node1.right);

            stack2.push(node2.right);
            stack2.push(node2.left);
        }

        return stack1.isEmpty() && stack2.isEmpty();
    }

    public boolean isSymmetric1(TreeNode root){
        return check(root,root);
    }

    private boolean check(TreeNode p, TreeNode q) {

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(p);
        queue.add(q);

        while (!queue.isEmpty()){
            TreeNode leftNode = queue.poll();
            TreeNode rightNode = queue.poll();

            if (leftNode == null && rightNode == null){
                continue;
            }
            if (leftNode == null || rightNode == null){
                return false;
            }

            if (leftNode.val != rightNode.val){
                return false;
            }
            queue.add(leftNode.left);
            queue.add(rightNode.right);
            queue.add(leftNode.right);
            queue.add(rightNode.left);
        }

        return true;
    }

    class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
 }
}
