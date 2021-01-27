package com.neu.leetcode.problems.tree;

import java.util.*;

public class 二叉树的最近公共祖先_0236 {

    List<TreeNode> listP = new LinkedList<>();
    List<TreeNode> listQ = new LinkedList<>();
    boolean pBoolean = true;
    boolean qBoolean = true;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        findNode(root,p,q);

        int psize = listP.size()-1;
        int qsize = listQ.size()-1;
        int size = psize<= qsize ? psize:qsize;
        for (int i=size;i>=0;i--){
            if (psize <= qsize){
                if (listQ.contains(listP.get(i))){
                    return listP.get(i);
                }
            }else {
                if (listP.contains(listQ.get(i))){
                    return listQ.get(i);
                }
            }
        }
        return root;
    }

    private void findNode(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null){
            return;
        }

        if (pBoolean){
            listP.add(root);
        }

        if (root.val == p.val && pBoolean){
            pBoolean = false;
        }

        if (qBoolean){
            listP.add(root);
        }
        if (root.val == q.val && qBoolean){
            qBoolean = false;
        }

        if (!pBoolean && !qBoolean){
            return;
        }

        findNode(root.left,p,q);
        findNode(root.right,p,q);

    }

    //递归
    TreeNode ans = null;
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root,p,q);
        return ans;
    }

    private boolean dfs(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null){
            return false;
        }

        boolean lson = dfs(root.left,p,q);
        boolean rson = dfs(root.right,p,q);

        if ((lson && rson) || ((root.val == p.val || root.val == q.val) && (lson || rson))){
            ans = root;
        }

        return lson || rson || (root.val == p.val || root.val == q.val);
    }

    //存储父节点
    Map<Integer,TreeNode> parent = new HashMap<>();
    Set<Integer> visited = new HashSet<>();
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        dfs1(root);
        while (p != null){
            visited.add(p.val);
            p = parent.get(p.val);
        }

        while (q != null){
            if (visited.contains(q.val)){
                return q;
            }

            q = parent.get(q.val);
        }
        return null;
    }

    private void dfs1(TreeNode root) {
        if (root.left != null){
            parent.put(root.left.val,root);
            dfs1(root.left);
        }

        if (root.right != null){
            parent.put(root.right.val,root);
            dfs1(root.right);
        }
    }



    public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
}
