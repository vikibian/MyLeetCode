package com.neu.leetcode.problems.tree;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class 不同二叉搜索树2_0095 {

    public static void main(String[] args) {
        List<TreeNode> treeNodes = generateTrees(3);
        System.out.println(treeNodes.toString());
//        List<Integer> list = new ArrayList<>();
//        for (int i=0;i<treeNodes.size();i++){
//            list.add(treeNodes.get(i).val);
//        }
    }

    public static   List<TreeNode> generateTrees(int n) {
        //自己的思路
//        List<TreeNode> treeNodes = new ArrayList<>();
//        if (n == 0){
//            return treeNodes;
//        }
//
//        if (n > 0){
//            for (int i=1;i<=n;i++){
//                // 外层遍历  依次为root节点
//
//                boolean[] isVisited = new boolean[n];
//                TreeNode treeNode = new TreeNode(i);
//                isVisited[i-1] = true;
////                treeNodes.add(treeNode);
//                treeNodes.addAll(binarySearchTree(treeNodes,n,i,treeNode,isVisited,treeNode));
//            }
//
//        }
//
//
//        return treeNodes;
        if (n == 0){
            return new LinkedList<TreeNode>();
        }

        return generateTrees(1,n);
    }

    public static List<TreeNode> generateTrees(int start,int end){
        List<TreeNode> allNodes = new LinkedList<>();
        if (start>end){
            allNodes.add(null);
            return allNodes;
        }

        // 枚举可行根节点
        for (int i = start; i <= end; i++) {
            // 获得所有可行的左子树集合
            List<TreeNode> leftTrees = generateTrees(start, i - 1);

            // 获得所有可行的右子树集合
            List<TreeNode> rightTrees = generateTrees(i + 1, end);

            // 从左子树集合中选出一棵左子树，从右子树集合中选出一棵右子树，拼接到根节点上
            for (TreeNode left : leftTrees) {
                for (TreeNode right : rightTrees) {
                    TreeNode currTree = new TreeNode(i);
                    currTree.left = left;
                    currTree.right = right;
                    allNodes.add(currTree);
                }
            }
        }


        return allNodes;
    }

//    public static List<TreeNode>  binarySearchTree(List<TreeNode> treeNodes, int n, int i, TreeNode node, boolean[] isVisited, TreeNode treeNode){
//
//        if (i == n){
//            TreeNode treeNode1 = treeNode;
//            treeNodes.add(treeNode1);
//            return treeNodes;
//        }
//
//        for (int j=1;j<=n;j++){
//            if (j<i && (!isVisited[i-1])){
//                TreeNode leftNode = new TreeNode(j);
//                isVisited[j-1] = true;
//                node.left = leftNode;
//                binarySearchTree(treeNodes, n,j,leftNode, isVisited, treeNode);
//                isVisited[j-1] = false;
//                node.left = null;
//
//            } else if (j>i && (!isVisited[i-1])){
//                TreeNode rightNode = new TreeNode(j);
//                isVisited[j-1] = true;
//                node.right = rightNode;
//                binarySearchTree(treeNodes, n,j,rightNode, isVisited, treeNode);
//                isVisited[j-1] = false;
//                node.right = null;
//            }
//        }
//
//        return treeNodes;
//    }


   static class TreeNode {
        TreeNode left ;
        TreeNode right ;
        int val ;

        TreeNode(){};
        TreeNode(int val){
            this.val = val;
        }


    }

}
