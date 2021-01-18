package com.neu.leetcode.problems.tree;

import java.util.*;

public class 从前序和中序遍历序列构造二叉树_0105 {
    public static void main(String[] args) {
        int[] preorder = {3,9,20,15,7};
        int[] inorder = {9,3,15,20,7};
        System.out.println(Arrays.toString(Arrays.copyOfRange(preorder,1,1+1)));
        System.out.println(Arrays.toString(Arrays.copyOfRange(inorder,0,1)));

        System.out.println(Arrays.toString(Arrays.copyOfRange(preorder,1+1,preorder.length)));
        System.out.println(Arrays.toString(Arrays.copyOfRange(inorder,1+1,inorder.length)));

        int num = 3;
        int count = 0;
        for (int i=0;i<inorder.length;i++){
            if (num == inorder[i]){
                count = i;
                break;
            }
            System.out.println("i"+i);
        }

        System.out.println(count);
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length != inorder.length){
            return null;
        }

        if (preorder.length == 0){
            return null;
        }

        TreeNode root = new TreeNode(preorder[0]);

        int num = preorder[0];
        int count =0;
        for (int i=0;i<inorder.length;i++){
            if (num == inorder[i]){
                count = i;
                break;
            }
        }

        int[] left = Arrays.copyOfRange(preorder,1,count+1);
        int[] right = Arrays.copyOfRange(inorder,0,count);
        root.left = buildTree(left,right);

        int[] left1 = Arrays.copyOfRange(preorder,count+1,preorder.length);
        int[] right1 = Arrays.copyOfRange(inorder,count+1,inorder.length);

        root.right = buildTree(left1,right1);

        return root;
    }

    //官方题解 递归
    private Map<Integer,Integer> indexMap;

    public TreeNode buildTree1(int[] preorder,int[] inorder){
        int preLen = preorder.length;
        int inLen = inorder.length;

        if (preLen != inLen){
            throw  new RuntimeException("Incorrect input data");
        }

        for (int i=0;i<preLen;i++){
            indexMap.put(inorder[i],i);
        }

        return myBuildTree(preorder, inorder,0,preLen-1,0,inLen-1);
    }

    private TreeNode myBuildTree(int[] preorder, int[] inorder, int preLeft, int preRight, int inLeft, int inRight) {
        if (preLeft > preRight || inLeft > inRight){
            return null;
        }

        int rootVal = preorder[preLeft];
        int pIndex = indexMap.get(rootVal);

        TreeNode root = new TreeNode(rootVal);

        root.left = myBuildTree(preorder,inorder,preLeft +1,pIndex - inLeft + preLeft,inLeft,pIndex-1);
        root.right = myBuildTree(preorder,inorder,pIndex - inLeft + preLeft+1,preRight,pIndex+1,inRight);

        return root;
    }

    //官方题解 迭代
    public TreeNode buildTree3(int[] preorder,int[] inorder){
        if (preorder == null || preorder.length == 0){
            return null;
        }

        TreeNode root = new TreeNode(preorder[0]);
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        int inorderIndex =0 ;
        for (int i=1;i<preorder.length;i++){
            int preorderVal = preorder[i];
            TreeNode node = stack.peek();
            if (node.val != preorder[i]){
                node.left = new TreeNode(preorderVal);
                stack.push(node);
            } else {
                while (!stack.isEmpty() && stack.peek().val == inorder[inorderIndex]){
                    node = stack.pop();
                    inorderIndex++;
                }
                node.right = new TreeNode(preorderVal);
                stack.push(node.right);
            }
        }
        return root;
    }

    class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
}
