package com.neu.leetcode.problems.tree;

import java.util.LinkedList;
import java.util.Queue;

public class 二叉树的最大深度_0104 {

    public int maxDepth(TreeNode root) {

        if (root == null){
            return 0;
        }



        return getMaxDepth(root,1);
    }

    private int getMaxDepth(TreeNode root, int max) {


        if (root == null){
            return max;
        }

        if (root.left != null || root.right != null){
            return Math.max(getMaxDepth(root.left,max+1),getMaxDepth(root.right,max+1));
        }

        if (root.left == null && root.right == null){
            return max;
        }

        return 0;
    }

    //官方题解 递归
    public int maxDepth2(TreeNode root) {

        if (root == null){
            return 0;
        }



        return 1+Math.max(maxDepth2(root.left),maxDepth2(root.right));
    }

    //官方题解 广度优先搜索

    public int maxDepth3(TreeNode root){

        if (root == null){
            return 0;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        int ans =0 ;
        queue.offer(root);

        while (!queue.isEmpty()){
            int size = queue.size();
            while (size-- >0){
                TreeNode node = queue.poll();
                if (node.left != null){
                    queue.offer(node.left);
                }

                if (node.right != null){
                    queue.offer(node.right);
                }
            }

            ans++;
        }

        return ans;

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
