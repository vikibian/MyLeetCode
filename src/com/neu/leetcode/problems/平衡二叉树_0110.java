package com.neu.leetcode.problems;

import java.util.Map;

public class 平衡二叉树_0110 {

    public static void main(String[] args) {
//        TreeNode root = new TreeNode(1);
//        TreeNode treeNode1 = new TreeNode(2);
//        TreeNode treeNode2 = new TreeNode(2);
//        TreeNode treeNode3 = new TreeNode(3);
//        TreeNode treeNode4 = new TreeNode(3);
//        TreeNode treeNode5 = new TreeNode(4);
//        TreeNode treeNode6 = new TreeNode(4);
//
//        treeNode3.left = treeNode5;
//        treeNode3.right = treeNode6;
//
//        treeNode1.left = treeNode3;
//        treeNode1.right = treeNode4;
//
//        root.left = treeNode1;
//        root.right = treeNode2;
//
//        System.out.println(""+isBalanced(root));

        //[1,2,2,3,null,null,3,4,null,null,4]
        TreeNode root = new TreeNode(1);
        TreeNode treeNode1 = new TreeNode(2);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(3);
        TreeNode treeNode5 = new TreeNode(4);
        TreeNode treeNode6 = new TreeNode(4);

        treeNode3.left = treeNode5;
        treeNode4.right = treeNode6;
        treeNode1.left = treeNode3;
        treeNode2.right = treeNode4;
        root.left = treeNode1;
        root.right = treeNode2;

        System.out.println(""+isBalanced(root));

    }

    public static boolean isBalanced(TreeNode root) {
        if(root == null){
            return true;
        }

//        if (root.left == null && root.right == null){
//            return true;
//        } else if (root.right != null && root.left == null ){
//            if ((root.right.left != null || root.right.right != null)  ){
//                return false;
//            }
//        } else if (root.left != null && root.right == null){
//            if ((root.left.left != null || root.left.right != null) ){
//                return false;
//            }
//        }
//        else {
////            if ((root.left.left != null || root.left.right != null) && (root.right.left == null || root.right.right == null )){
////                if (root.left.left.left != null || root.left.left.right != null || root.left.right.left != null || root.left.right.right != null){
////                    return false;
////                }
////
////            }
////            if ((root.right.left != null || root.right.right != null) && (root.left.left == null || root.left.right == null )){
////                if (root.right.left.left != null || root.right.left.right != null || root.right.right.left != null || root.right.right.right != null){
////                    return false;
////                }
////
////            }
//            return isBalanced(root.left) && isBalanced(root.right);
//        }

        if (root.left == null && root.right == null){
            return true;
        } else {
            if (Math.abs(getHeight(root.left)-getHeight(root.right)) <2){
                return isBalanced(root.left) && isBalanced(root.right);
            } else {
                return false;
            }
        }

    }

    public static  int getHeight(TreeNode root){
        if (root == null){
            return 0;
        }

        if (root.left == null && root.right == null){
            return 1;
        }else {
            return Math.max(getHeight(root.left),getHeight(root.right))+1;
        }
    }

    //官方题解  确实比自己的简洁
    public boolean isBalan(TreeNode root) {
        if (root == null) {
            return true;
        } else {
            return Math.abs(height(root.left) - height(root.right)) <= 1 && isBalan(root.left) && isBalan(root.right);
        }
    }

    public int height(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            return Math.max(height(root.left), height(root.right)) + 1;
        }
    }

    //方法二 不需要遍历多次子树
    public boolean isB(TreeNode root){

        return height2(root) >= 0;
    }

    public int height2(TreeNode treeNode){
        if (treeNode == null){
            return  0;
        }

        int leftheight = height2(treeNode.left);
        int rightheight = height2(treeNode.right);
        if (leftheight == -1 || rightheight == -1 || Math.abs(leftheight - rightheight)>1){
            return -1;
        } else {
            return Math.max(leftheight,rightheight)+1;
        }
    }


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


