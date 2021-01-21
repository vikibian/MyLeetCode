package com.neu.leetcode.problems.tree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class 二叉树展开为链表_0114 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode root1 = new TreeNode(2);
//        TreeNode root2 = new TreeNode(5);

        root.left = root1;
//        root.right = root2;

        TreeNode root11 = new TreeNode(3);
        TreeNode root12 = new TreeNode(4);
        TreeNode root22 = new TreeNode(5);

        root1.left = root11;
        root1.right = root12;

        root11.left = root22;
        flatten(root);
    }

    public static void flatten(TreeNode root) {
        if (root == null){
            return;
        }
        flattenNode(root,root);
//        flattenNode(root,root.left);
    }

    private static void flattenNode(TreeNode root, TreeNode node) {
        if (root == null || node == null){
            return;
        }

        if (node.left== null && node.right == null && root != node){
            if (root.right == null){
                root.right = node;
                root.left = null;
            } else{
                if (node != root.right){
                    TreeNode temp = root.right;
                    node.right = temp;
                    root.right = node;

                    node.left = null;
                    root.left = null;
                }

            }
        } else {
            flattenNode(node,node.right);
            flattenNode(node,node.left);
            if (node.left != null){
                TreeNode curNode = node.left;
                while (curNode.right != null){
                    curNode = curNode.right;
                }

                curNode.right = node.right;
                node.right = node.left;
                node.left = null;
            }

        }


    }

    //其实将二叉树转换为链表 就是前序遍历
    //官方题解一  前序遍历 之 递归方法
    public void flatten11(TreeNode root){
        List<TreeNode> list = new LinkedList<>();
        preOrder(root,list);
        int size = list.size();
        for (int i=1;i<size;i++){
            TreeNode preNode = list.get(i-1),curNode = list.get(i);
            preNode.right = curNode;
            preNode.left = null;
        }
    }

    private void preOrder(TreeNode root, List<TreeNode> list) {
        if (root != null){
            list.add(root);
            preOrder(root.left,list);
            preOrder(root.right,list);
        }
    }

    //官方题解一 前序遍历之迭代方法
    public void faltten12(TreeNode root){
        List<TreeNode> list = new LinkedList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode node = root;
        while (node != null || !stack.isEmpty()){
            while (node!= null){
                list.add(node);
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            node = node.right;
        }

        int size = list.size();
        for (int i=1;i<size;i++){
            TreeNode preNode = list.get(i-1),curNode = list.get(i);
            preNode.right = curNode;
            preNode.left = null;
        }
    }

    //官方题解二 前序遍历和展开同步进行
    public void flatten2(TreeNode root){
        if (root == null){
            return;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        TreeNode pre = null;
        while (!stack.isEmpty()){
            TreeNode cur = stack.pop();
            if (pre != null){
                pre.right = cur;
                pre.left = null;
            }
            TreeNode left = cur.left,right = cur.right;
            if (left!= null){
                stack.push(left);
            }

            if (right!= null){
                stack.push(right);
            }
            pre = cur;
        }
    }

    //官方题解三  寻找前驱节点
    public void flatten3(TreeNode root){
        TreeNode curNode = root;
        while (curNode != null){
            if (curNode.left != null){
                TreeNode next = curNode.left;
                TreeNode pre = next;
                while (pre.right != null){
                    pre = pre.right;
                }
                pre.right = curNode.right;
                curNode.right = next;
                curNode.left = null;
            }
            curNode = curNode.right;
        }
    }


    public static class TreeNode {
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
