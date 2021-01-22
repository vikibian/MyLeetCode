package com.neu.leetcode.problems.tree;

import java.util.LinkedList;
import java.util.Queue;

public class 求根到叶子节点数字之和_0129 {
    public static void main(String[] args) {
        System.out.println(Integer.valueOf("0123"));
    }

    int sum = 0;
    public int sumNumbers(TreeNode root) {
        if (root == null){
            return sum;
        }


        return dfs(root,"0");
    }

    private int dfs(TreeNode root, String curSum) {
        String num = curSum + root.val;

        if (root.left == null && root.right == null){
            sum = sum + Integer.valueOf(num);
            return sum;
        }

        int left = 0,right = 0;
        if (root.left != null){
            dfs(root.left,num);
        }

        if (root.right != null){
            dfs(root.right,num);
        }

        return sum;
    }

    //0ms 教科书级解答  官方题解一 深度优先搜索
    public int sumNumbers1(TreeNode root){
        return helper(root,0);
    }

    private int helper(TreeNode root, int sum) {

        if (root == null){
            return 0;
        }
        int temp = sum * 10 +root.val;
        if (root.left == null && root.right == null){
            return temp;
        }

        return helper(root.left,temp) + helper(root.right,temp);
    }

    //官方题解二 广度优先
    public int sumNumbers2(TreeNode root){
        if (root == null){
            return 0;
        }

        int sum = 0;
        Queue<TreeNode> queueNode = new LinkedList<>();
        Queue<Integer>  queueInteger = new LinkedList<>();
        queueNode.offer(root);
        queueInteger.offer(root.val);
        while (!queueNode.isEmpty()){
            TreeNode node = queueNode.poll();
            int num = queueInteger.poll();
            TreeNode left = node.left,right = node.right;
            if (left == null && right == null){
                sum = sum + num;
            }
            if (left != null){
                queueNode.offer(left);
                queueInteger.offer(num * 10 + left.val);
            }

            if (right != null){
                queueNode.offer(right);
                queueInteger.offer(num * 10 + right.val);
            }
        }

        return sum;
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
