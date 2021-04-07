package com.neu.leetcode.problems.design;

import sun.applet.AppletResourceLoader;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class 二叉搜索树迭代器_0173 {
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

    //跟官方题解一类似 扁平化
    class BSTIterator {
        List<Integer> list = new ArrayList<>();
        int maxCount = 0;
        int count = 0;
        public BSTIterator(TreeNode root) {
            TreeNode curNode = root;
            getList(root);
            maxCount = list.size();
        }

        private void getList(TreeNode root){
            if (root == null){
                return;
            }
            getList(root.left);
            list.add(root.val);
            getList(root.right);
        }


        public int next() {
            if (hasNext()){
                return list.get(count++);
            } else {
                return -1;
            }
        }

        public boolean hasNext() {
            if (count<maxCount){
                return true;
            } else {
                return false;
            }
        }
    }

    //官方题解 使用栈
    class BSTIterator1{
      private TreeNode cur;
      private Deque<TreeNode> stack;
      public BSTIterator1(TreeNode root){
          cur = root;
          stack = new LinkedList<>();
      }

      public int next(){
          while (cur != null){
              stack.push(cur);
              cur = cur.left;
          }
          cur = stack.pop();
          int ret = cur.val;
          cur = cur.right;
          return ret;
      }

      public boolean hasNext(){
          return cur!=null || !stack.isEmpty();
      }
    }
}
