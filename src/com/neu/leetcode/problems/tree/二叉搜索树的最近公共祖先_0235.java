package com.neu.leetcode.problems.tree;

import com.sun.org.apache.xml.internal.serialize.LineSeparator;

import java.util.ArrayList;
import java.util.List;

public class 二叉搜索树的最近公共祖先_0235 {

    TreeNode ans = null;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root,p,q);
        return ans;
    }

    private void dfs(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null){
            return;
        }

        if (p.val == root.val || q.val == root.val){
            ans = root;
            return;
        }

        if ((p.val <root.val && q.val <root.val)||((p.val >root.val && q.val >root.val))){
            if (root.left != null){
                dfs(root.left,p,q);
            }
            if (root.right != null){
                dfs(root.right,p,q);
            }
        } else {
            ans = root;
            return;
        }
    }

    //官方题解 两次遍历
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> path_p = getPath(root,p);
        List<TreeNode> path_Q = getPath(root, q);
        TreeNode ans1 = null;
        for (int i=0;i<path_p.size() && i<path_Q.size();i++){
            if (path_p.get(i) == path_Q.get(i)){
                ans1 = path_p.get(i);
            } else {
                break;
            }
        }
        return ans1;
    }

    private List<TreeNode> getPath(TreeNode root, TreeNode p) {
        List<TreeNode>  list = new ArrayList<>();
        TreeNode node = root;
        while (node != p){
            list.add(node);
            if (node.val > p.val){
                node = node.left;
            } else {
                node = node.right;
            }
        }
        list.add(node);
        return list;
    }

    //一次遍历
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode ans2= root;
        while (true){
            if (p.val < root.val && q.val < root.val){
                ans2 = ans2.left;
            } else if (p.val > root.val && q.val > root.val){
                ans2 = ans2.right;
            } else {
                break;
            }
        }

        return ans2;
    }

    //迭代三行代码 解决问题
    public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode p, TreeNode q) {
        if(p.val < root.val && q.val < root.val ){
            return lowestCommonAncestor3(root.left,p,q);
        }
        if (q.val > root.val && q.val > root.val){
            return  lowestCommonAncestor3(root.right,p,q);
        }
        return root;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
