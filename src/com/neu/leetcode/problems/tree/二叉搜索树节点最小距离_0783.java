package com.neu.leetcode.problems.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class 二叉搜索树节点最小距离_0783 {
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
    public int minDiffInBST(TreeNode root) {

        if (root == null){
            return 0;
        }
        List<Integer> list = new ArrayList<>();
        getAns(root,list);
        Collections.sort(list);
        int ans = Integer.MAX_VALUE;
        for (int i=1;i<list.size();i++){
            ans = Math.min(ans,list.get(i)-list.get(i-1));
        }

        return ans;
    }

    public void getAns(TreeNode root, List<Integer> list){
        if (root != null){
            list.add(root.val);
            getAns(root.left,list);
            getAns(root.right,list);
        }
    }

    //官方题解 中序遍历（二叉搜索树的中序遍历结果时有序的）
    int pre ;
    int ans;
    public int minDiffInBST1(TreeNode root) {
        ans = Integer.MAX_VALUE;
        pre = -1;
        dfs(root);
        return ans;
    }

    private void dfs(TreeNode root) {
        if (root == null){
            return;
        }
        dfs(root.left);
        if (pre == -1){
            pre = root.val;
        } else {
            ans = Math.min(ans,root.val-pre);
            pre = root.val;
        }
        dfs(root.right);
    }
}
