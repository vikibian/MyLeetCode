package com.neu.leetcode.problems.tree;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class 从中序和后序遍历序列构造二叉树_0106 {

    public static void main(String[] args) {
        int[] inorder = {9,3,15,20,7};
        int[] postorder = {9,15,7,20,3};
        buildTree(inorder,postorder);
    }

    public static TreeNode buildTree(int[] inorder, int[] postorder) {
        int inLen = inorder.length;
        int postLen = postorder.length;
        if (inLen != postLen){
            throw new RuntimeException("Incorect Input Data");
        }

        Map<Integer,Integer> indexMap = new HashMap<>();
        for (int i =0;i<inLen;i++){
            indexMap.put(inorder[i],i);
        }

        return buildTree(inorder,postorder,indexMap,0,inLen-1,0,postLen - 1);
    }

    private static TreeNode buildTree(int[] inorder, int[] postorder, Map<Integer, Integer> indexMap, int inLeft, int inRight, int postLeft, int postRight) {
        if (inLeft > inRight || postLeft > postRight){
            return null;
        }

        int rootval = postorder[postRight];
        TreeNode root = new TreeNode(rootval);
        int index= indexMap.get(rootval);

        root.left =
                buildTree(inorder,postorder,indexMap,inLeft,index-1,postLeft,postLeft +index-inLeft-1);

        root.right =
                buildTree(inorder,postorder,indexMap,index +1,inRight,postLeft +index-inLeft ,postRight - 1);

        return root;
    }

    //官方题解  通过后序遍历 父节点在最后  方法比较巧妙
    int post_idx;
    int[] postorder;
    int[] inorder;
    Map<Integer,Integer> idx_map = new HashMap<>();

    public TreeNode buildTree1(int[] inorder,int[] postorder){
        this.postorder = postorder;
        this.inorder = inorder;
        post_idx = postorder.length -1;

        for (int i=0;i<postorder.length;i++){
            idx_map.put(inorder[i],i);
        }

        return helper(0,inorder.length - 1);
        

    }

    private TreeNode helper(int int_left, int in_right) {
        if (int_left > in_right){
            return null;
        }

        int rootval = postorder[post_idx];
        TreeNode root = new TreeNode(rootval);

        int index = idx_map.get(rootval);

        post_idx--;

        root.right = helper(index+1,in_right);
        root.left = helper(int_left,index-1);


        return null;
    }

    //官方题解 迭代
    public TreeNode buildTree3(int[] inorder,int[] postorder){

        if (inorder == null || inorder.length == 0){
            return  null;
        }

        TreeNode root = new TreeNode(postorder[postorder.length - 1]);
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        int inorderIndex = inorder.length -1;
        for (int i = postorder.length - 2;i>=0;i--){
            int postorderVal = postorder[i];
            TreeNode node = stack.peek();
            if (node.val != inorder[inorderIndex]){
                node.right = new TreeNode(postorderVal);
                stack.push(node.right);
            } else {
                while (!stack.isEmpty() && stack.peek().val == inorder[inorderIndex]){
                    node = stack.pop();
                    inorderIndex--;
                }
                node.left = new TreeNode(postorderVal);
                stack.push(node.left);
            }
        }

        return root;
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
