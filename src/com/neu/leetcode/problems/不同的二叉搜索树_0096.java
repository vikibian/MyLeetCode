package com.neu.leetcode.problems;

import java.util.LinkedList;
import java.util.List;

public class 不同的二叉搜索树_0096 {

    public int numTrees(int n) {
        int[] G = new int[n+1];
        G[0] = 1;
        G[1] = 1;
        for (int i=2;i<=n;i++){
            for (int j=1;j<=i;j++){
                G[i] += G[j -1] * G[i - j ];
            }
        }
        return G[n];
    }

    public List<TreeNode> generateTrees(int start, int end) {

        List<TreeNode> list = new LinkedList<>();
        if (start > end){
            list.add(null);
            return list;
        }

        for (int i=start;i<=end;i++){
            List<TreeNode> leftNodes = generateTrees(start,i-1);
            List<TreeNode> rightNodes = generateTrees(i+1,end);
//            for (int j=0;j<(leftNodes.size() * rightNodes.size());j++){
//                list.add(new TreeNode());
//            }
            list.addAll(leftNodes);
            list.addAll(rightNodes);
        }


        return list;
    }

    public int generateTrees(int start, int end, int num) {

        List<TreeNode> list = new LinkedList<>();
        if (start > end){

            return 0;
        }

        for (int i=start;i<=end;i++){
            int i1 = generateTrees(start, i - 1, num);
            int i2 = generateTrees(i + 1, end, num);
            num = num + i1*i2;
        }


        return num;
    }

    class TreeNode {
        TreeNode left ;
        TreeNode right ;
        int val ;

        TreeNode(){};
        TreeNode(int val){
            this.val = val;
        }


    }
}
