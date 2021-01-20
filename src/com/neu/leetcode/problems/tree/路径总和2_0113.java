package com.neu.leetcode.problems.tree;

import java.util.*;

public class 路径总和2_0113 {

    public static void main(String[] args) {
//        List<List<Integer>> lists = new LinkedList<>();
//        List<Integer> list = new LinkedList<>();
//        list.add(1);
//        lists.add(list);
//        List<Integer> list2 = new LinkedList<>(list);
//        list2.add(2);
//        lists.add(list2);
        TreeNode root = new TreeNode(5);
        TreeNode root1 = new TreeNode(4);
        TreeNode root2 = new TreeNode(8);

        root.left = root1;
        root.right = root2;

        TreeNode root11 = new TreeNode(11);
        TreeNode root21 = new TreeNode(13);
        TreeNode root22 = new TreeNode(4);

        root1.left = root11;

        root2.left = root21;
        root2.right = root22;

        TreeNode root111 = new TreeNode(7);
        TreeNode root112 = new TreeNode(2);
        TreeNode root221 = new TreeNode(5);
        TreeNode root222 = new TreeNode(1);

        root11.left = root111;
        root11.right = root112;

        root22.left = root221;
        root22.right = root222;


        System.out.println(pathSum(root,22));

    }

    //错误的解法
    //debug之后发现不是思路有问题 二是迭代时的targetSum写错了  写成targetSum - root.left.val
    static List<List<Integer>>  lists = new LinkedList<>();
    public static List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        if (root == null){
            return  lists;
        }
        List<Integer> list = new ArrayList<>();
        list.add(root.val);
        getPathSum(root,targetSum ,list);
        return lists;
    }

    private static void getPathSum(TreeNode root, int targetSum, List<Integer> list) {
        if (root.left == null && root.right == null && root.val == targetSum){
            List<Integer> list1 = new ArrayList<Integer>(list);

            lists.add(list1);
        }

        if (root.left != null){
            list.add(root.left.val);
            getPathSum(root.left,targetSum - root.val,list);
            list.remove(list.size() -1);
        }

        if (root.right != null){
            list.add(root.right.val);
            getPathSum(root.right,targetSum - root.val,list);
            list.remove(list.size() - 1);
        }
    }

    //这是看题解之后自己修改的自己解法
//    List<List<Integer>> lists = new LinkedList<>();
    List<Integer> list = new ArrayList<>();
    public List<List<Integer>> pathSum1(TreeNode root, int targetSum) {
        if (root == null){
            return  lists;
        }


        getPathSum(root,targetSum );
        return lists;
    }

    private void getPathSum(TreeNode root, int targetSum) {
        if (root == null){
            return;
        }
        list.add(root.val);
        int rest = targetSum - root.val;
        if (root.left == null && root.right == null && rest == 0){
            List<Integer> list1 = new ArrayList<Integer>(list);

            lists.add(list1);
        }

        getPathSum(root.left,rest);

        getPathSum(root.right,rest);

        list.remove(list.size()-1);
    }

    //官方题解 深度优先搜索
    List<List<Integer>>  ret = new LinkedList<>();
    Deque<Integer> path = new LinkedList<>();

    public List<List<Integer>> getSum1(TreeNode root,int sum){
        dfs(root,sum);
        return ret;
    }

    private void dfs(TreeNode root, int sum) {
        if (root == null){
            return;
        }
        path.offerLast(root.val);
        sum -= root.val;
        if (root.left == null && root.right == null && sum == 0){
            ret.add(new LinkedList<Integer>(path));
        }
        dfs(root.left,sum);
        dfs(root.right,sum);
        path.pollLast();
    }

    //官方题解二 广度优先搜索
    List<List<Integer>> ret1 = new LinkedList<>();
    Map<TreeNode,TreeNode> map = new HashMap<>();

    public List<List<Integer>> getSum2(TreeNode root,int sum){
        if (root == null){
            return ret1;
        }

        Queue<TreeNode> queueNode = new LinkedList<>();
        Queue<Integer> queueIntger = new LinkedList<>();
        queueNode.offer(root);
        queueIntger.offer(0);
        while (!queueNode.isEmpty()){
            TreeNode node = queueNode.poll();
            int sec = queueIntger.poll() + node.val;
            if (node.left == null && node.right == null){
                if (sec == sum){
                    getPath(node);
                }
            }

            if (node.left != null){
                queueNode.offer(node.left);
                queueIntger.offer(sec);
                map.put(node.left,node);
            }

            if (node.right != null){
                queueNode.offer(node.right);
                queueIntger.offer(sec);
                map.put(node.right,node);
            }
        }

        return ret1;
    }

    private void getPath(TreeNode node) {
        List<Integer> list = new LinkedList<>();
        while (node != null){
            list.add(node.val);
            node = map.get(node);
        }

        Collections.reverse(list);
        ret1.add(new LinkedList<Integer>(list));

    }


    public static class TreeNode {
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
