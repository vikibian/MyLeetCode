package com.neu.leetcode.problems.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class 二叉树的层序遍历_0102 {

    List<TreeNode> queue = new LinkedList<>();


    public List<List<Integer>> levelOrder(TreeNode root) {
        return getLevelOrder(root);
    }

    public List<List<Integer>>  getLevelOrder( TreeNode root){
        List<List<Integer>> lists = new ArrayList<>();

        if (root == null){
            return lists;
        }

        queue.add(root);
//        lists.add( new ArrayList<>(root.val));

        while (!queue.isEmpty()){
            List<Integer> list = new ArrayList<>();
            List<TreeNode> queue1 = new LinkedList<>();
            for (TreeNode treeNode : queue){
                if (treeNode != null){
                    list.add(treeNode.val);
                    queue1.add(treeNode.left);
                    queue1.add(treeNode.right);
                }
            }

            queue.clear();

            if (list.size()!= 0){
                lists.add(list);
            }


            queue.addAll(queue1);
        }

        return lists;
    }

    //官方题解  广度优先搜索
    public List<List<Integer>> levelOrder1(TreeNode root) {
        List<List<Integer>> ret = new ArrayList<>();
        if (root == null){
            return ret;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            List<Integer> list = new ArrayList<>();
            int levelSize = queue.size();
            for (int i=0;i<levelSize;i++){
                TreeNode treeNode = queue.poll();
                list.add(treeNode.val);
                if (treeNode.left != null){
                    queue.offer(treeNode.left);
                }

                if (treeNode.right != null){
                    queue.offer(treeNode.right);
                }
            }

            ret.add(list);
        }
        return ret;
    }

    //官方题解 迭代

    List<List<Integer>> levels = new ArrayList<>();

    public List<List<Integer>> levelOrder2(TreeNode root){
        if (root == null ){
            return levels;
        }
        helper(root,0);
        return levels;
    }

    public void helper(TreeNode node,int level){
        if (levels.size() == level){
            levels.add(new ArrayList<Integer>());
        }
        levels.get(level).add(node.val);
        if (node.left != null){
            helper(node.left,level+1);
        }
        if (node.right != null){
            helper(node.right,level +1);
        }
    }

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
}
