package com.neu.leetcode.problems;

import java.util.ArrayList;
import java.util.List;

public class N叉树的前序遍历_589 {
    public List<Integer> preorder(Node root) {
        List<Integer> list = new ArrayList<>();

        if (root == null){
            return list;
        }

        list.add(root.val);
        for (int i=0;i<root.children.size();i++){
            list.addAll(preorder(root.children.get(i)));
        }

        return list;
    }
}
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};

