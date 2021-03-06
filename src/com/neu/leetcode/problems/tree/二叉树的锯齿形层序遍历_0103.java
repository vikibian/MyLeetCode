package com.neu.leetcode.problems.tree;

import java.util.*;

public class 二叉树的锯齿形层序遍历_0103 {

    public static void main(String[] args) {
//        System.out.printf(""+(1%2));
        TreeNode root = new TreeNode(3);
        TreeNode left1 = new TreeNode(9);
        TreeNode right1 = new TreeNode(20);

        root.left = left1;
        root.right = right1;

        TreeNode right11 = new TreeNode(15);
        TreeNode right12 = new TreeNode(7);

        right1.left = right11;
        right1.right = right12;
        System.out.println(zigzagLevelOrder(root));

    }

    static List<List<Integer>>  lists = new ArrayList<>();
    Queue<TreeNode> queue = new LinkedList<>();

    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {

        if (root == null){
            return lists;
        }

//        getLevelOrder(root,0);
        return getLevelOrder2(root,0);
//        return lists;
    }

//    private void getLevelOrder(TreeNode node, int level) {
//        if (lists.size() == level){
//            lists.add(new ArrayList<>());
//        }
//
//        lists.get(level).add(node.val);
//
//        if ((level) % 2 == 1){
//            if (node.left != null){
//                getLevelOrder(node.left,level+1);
//            }
//
//            if (node.right != null){
//                getLevelOrder(node.right,level+1);
//            }
//        } else {
//
//            if (node.right != null){
//                getLevelOrder(node.right,level+1);
//            }
//
//            if (node.left != null){
//                getLevelOrder(node.left,level+1);
//            }
//        }
//    }

//    private List<List<Integer>> getLevelOrder1(TreeNode node, int level) {
//        Stack<TreeNode> queue = new Stack<>();
//        List<TreeNode> treeNodeList = new LinkedList<>();
//        queue.push(node);
//
//        int num = 0;
//        while (!queue.isEmpty()){
//            List<Integer> list = new ArrayList<>();
//            int count = queue.size();
//            for (int i=0;i<count;i++){
//                TreeNode treeNode = queue.pop();
//                list.add(treeNode.val);
//                if ((num+1) % 2 == 0){
//                    if (treeNode.right != null){
//                        queue.push(treeNode.right);
//                    }
//                    if (treeNode.left != null){
//                        queue.push(treeNode.left);
//                    }
//                } else {
//
//                    if (treeNode.left != null){
//                        queue.push(treeNode.left);
//                    }
//
//                    if (treeNode.right != null){
//                        queue.push(treeNode.right);
//                    }
//                }
//            }
//
//            lists.add(list);
//            num++;
//        }
//
//        return lists;
//    }

    private static List<List<Integer>> getLevelOrder2(TreeNode node, int level) {
        Stack<TreeNode> queue = new Stack<>();
        List<TreeNode> treeNodeList = new LinkedList<>();
        List<Integer> list1 = new ArrayList<>();
        treeNodeList.add(node);
        list1.add(node.val);
        lists.add(list1);

        int num = 0;
        while (!treeNodeList.isEmpty()){
            List<Integer> list = new ArrayList<>();
            List<TreeNode> nodeList = new LinkedList<>();
            int count  =  treeNodeList.size();
//            if ((num) % 2 == 0){
//                for (int i=0;i<count;i++){
//                    TreeNode treeNode = treeNodeList.get(i);
//
//                    if (treeNode.right != null){
//                        nodeList.add(treeNode.right);
//                        list.add(treeNode.right.val);
//                    }
//                    if (treeNode.left != null){
//                        nodeList.add(treeNode.left);
//                        list.add(treeNode.left.val);
//                    }
//                }
//            } else {
//
//            }

            for (int i=(count-1);i>=0;i--){
                TreeNode treeNode = treeNodeList.get(i);
                if ((num) % 2 == 0){
                    if (treeNode.right != null){
                        nodeList.add(treeNode.right);
                        list.add(treeNode.right.val);
                    }
                    if (treeNode.left != null){
                        nodeList.add(treeNode.left);
                        list.add(treeNode.left.val);
                    }
                } else {

                    if (treeNode.left != null){
                        nodeList.add(treeNode.left);
                        list.add(treeNode.left.val);
                    }

                    if (treeNode.right != null){
                        nodeList.add(treeNode.right);
                        list.add(treeNode.right.val);
                    }
                }
            }


            if (list.size() != 0){
                lists.add(list);
            }

            treeNodeList.clear();
            treeNodeList.addAll(nodeList);
            num++;
        }

        return lists;
    }

    //官方题解 迭代
    public  List<List<Integer>> zigzagLevelOrder1(TreeNode root) {
        if (root == null){
            return new ArrayList<List<Integer>>();
        }
        List<List<Integer>> results = new ArrayList<>();
        DFS(root,0,results);
        return results;
    }

    protected void DFS(TreeNode node,int level,List<List<Integer>> results){
        if (level == results.size()){
            LinkedList<Integer>  newLevel = new LinkedList<>();
            newLevel.add(node.val);
            results.add(newLevel);
        } else {
            if (level % 2 ==0){
                results.get(level).add(node.val);
            }else {
                results.get(level).add(0,node.val);
            }
        }

        if (node.left != null){
            DFS(node.left,level+1,results);
        }

        if (node.right != null){
            DFS(node.right,level+1,results);
        }
    }

    //官方题解 迭代
    public List<List<Integer>> zigzagLevelOrder2(TreeNode root){
        if (root == null){
            return new ArrayList<List<Integer>>();
        }

        List<List<Integer>> results = new ArrayList<>();

        LinkedList<TreeNode> nodelist = new LinkedList<>();
        nodelist.addLast(root);
        nodelist.addLast(null);

        LinkedList<Integer> integerslist = new LinkedList<>();
        boolean is_order_left = true;

        while (nodelist.size() > 0){
            TreeNode curNode = nodelist.pollFirst();
            if (curNode != null){
                if (is_order_left){
                    integerslist.addLast(curNode.val);
                } else {
                    integerslist.addFirst(curNode.val);
                }

                if (curNode.left != null){
                    nodelist.addLast(curNode.left);
                }

                if (curNode.right != null){
                    nodelist.addLast(curNode.right);
                }
            } else {
                //如果是分界符
                results.add(integerslist);
                //最好是新建一个清空的话数据可能也会清空
                integerslist = new LinkedList<>();
                if (nodelist.size() > 0){
                    nodelist.addLast(null);
                }
                is_order_left = !is_order_left;
            }


        }

        return results;
    }


    //官方题解 广度优先搜索
    public List<List<Integer>> zigzagLevelOrder3(TreeNode root){
        if (root == null){
            return new ArrayList<List<Integer>>();
        }

        List<List<Integer>> results = new ArrayList<>();

        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.offer(root);
        boolean is_left_order = true;

        while (!nodeQueue.isEmpty()){
            Deque<Integer> levelList = new LinkedList<>();
            int size = queue.size();
            for (int i=0;i<size;i++){
                TreeNode curNode = nodeQueue.poll();
                if (is_left_order){
                    levelList.offerLast(curNode.val);
                } else {
                    levelList.offerFirst(curNode.val);
                }

                if (curNode.left != null){
                    nodeQueue.offer(curNode.left);
                }

                if (curNode.right != null){
                    nodeQueue.offer(curNode.right);
                }
            }

            results.add(new LinkedList<>(levelList));
            is_left_order = !is_left_order;
        }

        return results;
    }


   static class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
 }
}
