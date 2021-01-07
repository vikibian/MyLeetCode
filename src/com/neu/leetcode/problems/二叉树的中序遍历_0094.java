package com.neu.leetcode.problems;

import java.util.ArrayList;
import java.util.List;

public class 二叉树的中序遍历_0094 {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null){
            return list;
        }

//        if (root.left == null && root.right ==null){
//
//        }

        list.addAll(inorderTraversal(root.left));
        list.add(root.val);
        list.addAll(inorderTraversal(root.right));
        return list;
    }
}
