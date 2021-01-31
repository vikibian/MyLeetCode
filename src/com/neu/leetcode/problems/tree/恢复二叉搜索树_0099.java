package com.neu.leetcode.problems.tree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class 恢复二叉搜索树_0099 {

    TreeNode left = null;
    TreeNode right = null;
    public void recoverTree(TreeNode root) {
        if(root == null){
            return ;
        }
        findNodes(root,null,null);
        int temp = left.val;
        left.val = right.val;
        right.val = temp;
    }

    private void findNodes(TreeNode root, TreeNode lower, TreeNode highter) {
        if (root == null){
            return;
        }
        int val = root.val;
        if (lower != null && val <= lower.val){
            left = lower;
            right = root;
            return;
        }

        if (highter != null && val >= highter.val){
            left = root;
            right = highter;
            return;
        }

        findNodes(root.left,lower,root);
        findNodes(root.right,root,highter);
    }

    //官方题解一 显式中序遍历  其实就是改进的树的递归遍历方法
    public void recoverTree1(TreeNode root) {
        List<Integer> list = new LinkedList<>();
        inorder(root,list);
        int[] nums = findNumbers(list);
        recover(root,2,nums[0],nums[1]);
    }

    private void inorder(TreeNode root, List<Integer> list) {
        if (root == null){
            return;
        }
        inorder(root.left,list);
        list.add(root.val);
        inorder(root.right,list);
    }

    private int[] findNumbers(List<Integer> list) {
        int size = list.size();
        int x=-1,y=-1;
        for (int i=0;i<size-1;i++){
            if (list.get(i+1)<list.get(i)){
                y = list.get(i+1);
                if (x == -1){
                    x = list.get(i);
                } else {
                    break;
                }
            }
        }

        return new int[] {x,y};
    }


    private void recover(TreeNode root, int k, int num0, int num1) {
        if (root != null){
            if (root.val == num0 || root.val == num1){
                root.val = root.val == num0? num1:num0;
                if (--k == 0){
                    return;
                }
            }
        }

        recover(root.right,k,num0,num1);
        recover(root.left,k,num0,num1);
    }

    //官方题解二 隐式中序遍历  其实就是树的迭代遍历方法
    public void recoverTree2(TreeNode root) {
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode x = null,y=null,pred = null;

        while ( !stack.isEmpty() || root != null){
            while (root != null){
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (pred != null && root.val < pred.val){
                y = root;
                if ( x == null){
                    x = pred;
                } else {
                    break;
                }
            }
            pred = root;
            root = root.right;
        }
        swap(x,y);
    }

    private void swap(TreeNode x, TreeNode y) {
        int temp = x.val;
        y.val = x.val;
        y.val = temp;
    }

    //官方题解三  morris中序遍历
    public void recoverTree3(TreeNode root) {
        TreeNode x = null,y = null,pred = null,currnode = null;

        while (root != null){
            if (root.left != null){
                currnode = root.left;
                while (currnode != null && currnode.right != root){
                    currnode = currnode.right;
                }

                if (currnode == null){
                    currnode.right = root;
                    root = root.left;
                } else {
                    //说明树已经访问完了 需要断开连接
                    if (pred != null && root.val < pred.val){
                        y = root;
                        if (x == null){
                            x = pred;
                        }
                        //注意  上面的方法中这里需要的是 在else中设置break语句 但是树还没有遍历完
                        //不能设置break语句
                    }
                    pred = root;
                    root = root.right;

                    currnode.right = null;
                }

            } else {
                if (pred != null && root.val < pred.val){
                    y = root;
                    if (x == null){
                        x = pred;
                    }
                }
                pred = root;
                root = root.right;
            }
        }
        swap(x,y);
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
