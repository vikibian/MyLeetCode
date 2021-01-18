package com.neu.leetcode.problems.tree;

import java.util.LinkedList;
import java.util.Queue;

public class 二叉树的最小深度_0111 {

    public int minDepth(TreeNode root) {
        if (root == null){
            return 0;
        }

        return getMinDepth(root,1);
    }

    private int getMinDepth(TreeNode root, int depNum) {
        int num = 0;

        if (root.left != null && root.right == null){
            num = getMinDepth(root.left,depNum+1);
        } else if (root.left == null && root.right != null){
            num = getMinDepth(root.right,depNum + 1);
        } else if (root.left == null && root.right == null){
            return depNum;
        } else {
            num = Math.min(getMinDepth(root.left, depNum+1),getMinDepth(root.right, depNum+1));
        }

        return num;
    }

    //官方题解一  深度优先搜索
    public int minDepth1(TreeNode root){
        if (root == null){
            return 0;
        }

        if (root.left == null && root.right == null){
            return 1;
        }

        int min_depth = Integer.MIN_VALUE;
        if (root.left != null){
            min_depth = Math.min(minDepth1(root.left),min_depth);
        }

        if (root.right != null){
            min_depth = Math.min(minDepth1(root.right),min_depth);
        }
        return min_depth+1;
    }

    //官方题解二 广度优先遍历
    public int minDepth2(TreeNode root){
        if (root == null){
            return 0;
        }
        Queue<QueueNode> queue = new LinkedList<>();
        queue.offer(new QueueNode(root,1));
        while (!queue.isEmpty()){
            QueueNode queueNode = queue.poll();
            TreeNode node = queueNode.node;
            int depth = queueNode.depth;
            if (node.left == null && node.right == null){
                return depth;
            }

            if (node.left != null){
                queue.offer(new QueueNode(root.left,depth+1));
            }

            if (node.right != null){
                queue.offer(new QueueNode(root.right,depth+1));
            }
        }

        return 0;
    }

    class QueueNode{
        TreeNode node;
        int depth;

        public QueueNode(TreeNode node,int depth){
            this.node = node;
            this.depth = depth;
        }
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
