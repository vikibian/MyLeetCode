package com.neu.leetcode.problems.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class 二叉搜索树中第k小的元素_0230 {

    public int kthSmallest(TreeNode root, int k) {
        List<Integer> list = new LinkedList<>();

        return inorder(root,k,list);
    }

    private int inorder(TreeNode root, int k, List<Integer> list) {
        if (root == null){
            return Integer.MIN_VALUE;
        }
        int left =0,right = 0;
        if (root.left != null){
            left = inorder(root.left,k,list);
        }
        list.add(root.val);
        if (list.size() == k){
            return root.val;
        }
        if (root.right != null){
            right = inorder(root.right,k,list);
        }
        return Math.max(left,right);
    }

    //官方题解 迭代
    public ArrayList<Integer> inorder(TreeNode root, ArrayList<Integer> arr) {
        if (root == null) return arr;
        inorder(root.left, arr);
        arr.add(root.val);
        inorder(root.right, arr);
        return arr;
    }

    public int kthSmallest1(TreeNode root, int k) {
        ArrayList<Integer> nums = inorder(root, new ArrayList<Integer>());
        return nums.get(k - 1);
    }

    //官方题解 递归
    public int kthSmallest2(TreeNode root, int k) {
        LinkedList<TreeNode> stack = new LinkedList<TreeNode>();

        while (true){
            while (root != null){
                stack.add(root);
                root = root.left;
            }

            root = stack.removeLast();
            if (--k == 0){
                return root.val;
            }
            root = root.right;
        }
    }

    //简介递归
    int k, res;
    public int kthSmallest3(TreeNode root, int k) {
        this.k = k;
        helper(root);
        return res;
    }

    public void helper(TreeNode root) {
        if(root == null) return;  //越过叶子节点，返回
        helper(root.left);
        if(k == 0) return;  //已找到第k大，剪枝
        k--; //每递归一个节点，k-1
        if(k == 0) res = root.val; //得0时即找到第k大
        helper(root.right);
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
