package com.neu.leetcode.problems.tree;


import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class 二叉树的右视图_0199 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode left1 = new TreeNode(2);
        TreeNode right1 = new TreeNode(3);

        root.left = left1;
        root.right = right1;

        TreeNode left12 = new TreeNode(5);
        TreeNode right12 = new TreeNode(4);

        left1.right = left12;
        right1.right = right12;
        System.out.println(rightSideView(root).toString());;
    }

    public static List<Integer> rightSideView(TreeNode root) {
        List<Integer> list = new LinkedList<>();
        if (root == null){
            return list;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        stack.add(null);
        TreeNode cueNode = root;
        while (!stack.isEmpty()){
            TreeNode tempNode = stack.pop();

            //最后一层会有null值
            if (tempNode != null){
                if (tempNode.left != null){
                    stack.add(tempNode.left);
                }

                if (tempNode.right != null){
                    stack.add(tempNode.right);
                }

                if (stack.peek() == null){
                    list.add(tempNode.val);
                    stack.pop();
                    stack.add(null);
                }
            }
        }

        return list;
    }

    //官方题解 深度优先搜索
    public static List<Integer> rightSideView1(TreeNode root) {
        List<Integer> list = new LinkedList<>();
        if (root == null){
            return list;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);

        while (!stack.isEmpty()){

            int size = stack.size();
            for (int i=0;i<size;i++){
                TreeNode tempNode = stack.pop();

                if (tempNode.left != null){
                    stack.add(tempNode.left);
                }

                if (tempNode.right != null){
                    stack.add(tempNode.right);
                }

                if (i == size -1){
                    list.add(tempNode.val);
                }
            }

        }

        return list;
    }

    //官方题解 深度优先搜索  根节点-》右节点-》左节点
    List<Integer> res = new ArrayList<>();
    public List<Integer> rightSideView2(TreeNode root) {
       dfs(root,0);

        return res;
    }

    private void dfs(TreeNode root, int depth) {
        if (root == null){
            return;
        }
        // 先访问 当前节点，再递归地访问 右子树 和 左子树。
        // 如果当前节点所在深度还没有出现在res里，说明在该深度下当前节点是第一个被访问的节点，因此将当前节点加入res中。
        if (depth == res.size()){
            res.add(root.val);
        }
        depth++;
        dfs(root.right,depth);
        dfs(root.left,depth);
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
