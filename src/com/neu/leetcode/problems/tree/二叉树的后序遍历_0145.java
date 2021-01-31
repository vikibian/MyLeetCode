package com.neu.leetcode.problems.tree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class 二叉树的后序遍历_0145 {

    public List<Integer> postorderTraversal(TreeNode root) {

        List<Integer> list = new LinkedList<>();
        postOrder(root,list);
        return list;
    }

    private void postOrder(TreeNode root, List<Integer> list) {
        if (root == null){
            return;
        }
        postOrder(root.left,list);
        postOrder(root.right,list);
        list.add(root.val);
    }

    //迭代
    public List<Integer> postorderTraversal1(TreeNode root){
        List<Integer> list = new LinkedList<>();
        if (root == null){
            return list;
        }

        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        TreeNode node = root,preNode = null;
        while ( !stack.isEmpty() || node != null){

//            node = stack.peek();

            while (node.left != null ){
                stack.push(node.left);
                preNode = node;
                node = node.left;
            }

            list.add(node.val);

            if (preNode.right != null){
                node = preNode.right;
            }

            if (node.left == null && node.right == null){
                list.add(node.val);
            } else {
                continue;
            }


            node = stack.pop();
            list.add(node.val);

            node = stack.pop();

            if (node.right != null){
                if (node.right.left != null){
                   node = node.right.left;
                    continue;
                }
                list.add(node.right.val);
            }
            list.add(node.val);
        }

        return list;
    }

    //官方题解 迭代
    public List<Integer> postorderTraversal2(TreeNode root){
        List<Integer> list = new LinkedList<>();
        if (root == null){
            return list;
        }

        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode prev = root;
        while (root != null || !stack.isEmpty()){
            while (root!= null){
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (root.right == null || root.right == prev){
                list.add(root.val);
                prev = root;
                root = null;
            } else {
                stack.push(root);
                root = root.right;
            }
        }

        return list;
    }

    //官方题解 morris
    public List<Integer> postorderTraversalMorris(TreeNode root){
        List<Integer> list = new LinkedList<>();
        if (root == null){
            return list;
        }

        TreeNode curNode = root;
        TreeNode tempNode = null;
        while (curNode != null){
            tempNode = curNode.left;
            if (tempNode != null){
                while (tempNode.right != null && tempNode.right != curNode){
                    tempNode = tempNode.right;
                }

                if (tempNode.right == null){
                    tempNode.right = curNode;
                    curNode = curNode.left;
                    continue;
                }else {
                    tempNode.right = null;
                    addPath(list,curNode.left);
                }
            }

            curNode = curNode.right;

        }
        addPath(list,root);

        return list;
    }

    private void addPath(List<Integer> list, TreeNode node) {
        int count  =0;
        while (node != null){
            count ++;
            list.add(node.val);
            node = node.right;
        }
        int left = list.size() - count,right = list.size() -1;
        while (left <right){
            int temp = list.get(left);
            list.set(left,list.get(right));
            list.set(right,temp);
            left++;
            right--;
        }
    }

    //方法二 翻转二叉树
    public void addEdge(List<Integer> list,TreeNode root){
        TreeNode tail = reverse(root);
        TreeNode cur = tail;
        while (cur != null){
            list.add(cur.val);
            cur = cur.right;
        }
        reverse(tail);
    }

    private TreeNode reverse(TreeNode from) {
        TreeNode pre = null;
        TreeNode next = null;
        while (from != null){
            next = from.right;
            from.right = pre;
            pre = from;
            from = next;
        }
        return null;
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
