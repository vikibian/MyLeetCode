package com.neu.leetcode.problems.tree;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class 二叉树的层序遍历2_0107 {

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if (root == null){
            return new ArrayList<List<Integer>>();
        }
        List<List<Integer>> linkedLists = new LinkedList<>();
        helper(root,0,linkedLists);


        return linkedLists;
    }

    private void helper(TreeNode root, int level, List<List<Integer>> linkedLists) {
        if (linkedLists.size() == level){
            linkedLists.add(0,new LinkedList<>());
        }
        int size = linkedLists.size();

        linkedLists.get(size - level -1 ).add(root.val);

        if (root.left != null){
            helper(root.left,level+1,linkedLists);
        }
         if (root.right != null){
             helper(root.right,level+1,linkedLists);
         }
    }

    public List<List<Integer>> levelOrderBottom2(TreeNode root) {
        if (root == null){
            return new ArrayList<List<Integer>>();
        }
        List<List<Integer>> linkedLists = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()){
            int size = queue.size();
            List<Integer> list = new LinkedList<>();
            for (int i=0;i<size;i++){
                TreeNode node = queue.poll();
                list.add(node.val);

                if (node.left != null){
                    queue.offer(node.left);
                }

                if (node.right != null){
                    queue.offer(node.right);
                }
            }

            linkedLists.add(0,list);
        }


        return linkedLists;
    }

    public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
}
