package com.neu.leetcode.problems.tree;

public class 二叉树中的最大路径和_0124 {

    int maxPath = Integer.MIN_VALUE;

    //官方题解
    public int maxPathSum(TreeNode root) {

        maxPath(root);

        return maxPath;
    }

    private int maxPath(TreeNode root) {
        if (root == null){
            return 0;
        }

        int left = Math.max(maxPath(root.left),0);
        int right = Math.max(maxPath(root.right),0);

        int temp = left + right +root.val;

        maxPath = Math.max(temp,maxPath);

        return root.val + Math.max(left,right);
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
