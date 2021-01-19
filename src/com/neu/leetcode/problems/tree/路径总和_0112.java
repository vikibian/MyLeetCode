package com.neu.leetcode.problems.tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class 路径总和_0112 {

    //官方题解的迭代思路
    //注意重点的地方是  再遍历之前不进行为空的判断  这样即使下次迭代TreeNode为空返回的是
    //false  也可以通过 || 进行消除影响
    // 否则迭代之前进行是否为空的判断  需要设置初始值 left 和right  这两个布尔值 有初始值 会对
    //后面的判断产生影响
    public boolean hasPathSum1(TreeNode root, int sum) {
        if (root == null){
            return false;
        }

        if(root.left == null && root.right == null ){
            if (root.val == sum){
                return true;
            }

        }

        boolean left = true;
        boolean right = true;
        left = hasPathSum(root.left,sum - root.val);

        right = hasPathSum(root.right,sum - root.val);



        return left || right;
    }

    List<Integer> list = new LinkedList<>();
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null){
            return false;
        }


        getPathMap(root,0);

        for (int i : list){
            if (i == sum){
                return true;
            }
        }

        return false;
    }

    private void getPathMap(TreeNode root, int sum) {
        int num = sum + root.val;
        if (root.left == null && root.right == null ){
            list.add(num);
        }

        if (root.left != null){
            getPathMap(root.left,num);
        }

        if (root.right != null){
            getPathMap(root.right,num);
        }

    }

    //官方题解  广度优先遍历
    public boolean hasPathSum2(TreeNode root,int sum){
        if (root == null){
            return false;
        }
        Queue<TreeNode> queueNode = new LinkedList<>();
        Queue<Integer> queueInteger = new LinkedList<>();
        queueNode.offer(root);
        queueInteger.offer(root.val);
        while (!queueNode.isEmpty()){
            TreeNode node = queueNode.poll();
            int tempVal = queueInteger.poll();
            if (node.left == null && node.right == null){
                if (tempVal == sum){
                    return true;
                }
                continue;
            }

            if (node.left != null){
                queueNode.offer(node.left);
                queueInteger.offer(node.left.val+tempVal);
            }

            if (node.right != null){
                queueNode.offer(node.right);
                queueInteger.offer(node.right.val+tempVal);
            }
        }

        return false;
    }

    class TreeNode {
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
