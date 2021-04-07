package com.neu.leetcode.problems.tree;

import java.util.*;

public class 二叉树的所有路径_0257 {
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
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> list = new ArrayList<>();
        getPath(list,root,"");
        return list;
    }

    private void getPath(List<String> list ,TreeNode root,String str){
        if (root == null){
            return;
        }
        if (root != null){
            str += root.val;
        }
        if (root.left == null && root.right == null){
            list.add(new String(str));
            return;
        }
        if (root.left != null){
            getPath(list,root.left,str+"->");
        }
        if (root.right != null){
            getPath(list,root.right,str+"->");
        }

    }

    //官方题解 深度优先搜索
    public List<String> binaryTreePaths1(TreeNode root) {
        List<String> paths = new ArrayList<String>();
        constructPaths(root, "", paths);
        return paths;
    }

    private void constructPaths(TreeNode root, String s, List<String> paths) {
        if (root == null){
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(Integer.toString(root.val));
            if (root.left != null && root.right != null){
                paths.add(stringBuilder.toString());
            } else {
                stringBuilder.append("->");
                constructPaths(root.left,stringBuilder.toString(),paths);
                constructPaths(root.right,stringBuilder.toString(),paths);
            }
        }
    }

    //官方题解2  广度优先搜索
    public List<String> binaryTreePaths2(TreeNode root) {
        List<String> paths = new ArrayList<>();
        if (root == null){

            return paths;
        }
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<String> pathQueue = new LinkedList<>();

        nodeQueue.offer(root);
        pathQueue.offer(Integer.toString(root.val));
        while (!nodeQueue.isEmpty()){
            TreeNode node = nodeQueue.poll();
            String path = pathQueue.poll();

            if (node.left == null && node.right == null){
                paths.add(path);
            }

            if (root.left != null){
                nodeQueue.offer(node.left);
                pathQueue.offer(new StringBuffer(path).append("->").append(root.left.val).toString());
            }

            if (root.right != null){
                nodeQueue.offer(node.right);
                pathQueue.offer(new StringBuffer(path).append("->").append(root.right.val).toString());
            }
        }
        return paths;
    }

}
