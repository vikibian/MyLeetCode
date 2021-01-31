package com.neu.leetcode.problems.tree;

public class 完全二叉树的节点个数_0222 {
    public static void main(String[] args) {
        int level = 3;
        int low = 1 << level, hight = (1 << (level + 1)) - 1;
        int bits = 1 << (level - 1);
        int mid = (hight - low + 1) / 2 + low;
        System.out.println(low);
        System.out.println(hight);
        System.out.println(bits);
        System.out.println(bits>>1);
        System.out.println(mid);
        System.out.println((2 & 10));
    }

    int num = 0;
    boolean flag =true;
    int level = 0;
    public int countNodes(TreeNode root) {
        if (root == null){
            return num;
        }
        getNums(root);
        num = num + ((2*(level-1)>0?2*(level-1):1)-1);
        return num;
    }

    private void getNums(TreeNode root) {
        if (root.left != null && root.right != null){
            if (flag){
                level++;
            }
        }
        if (root.left == null && root.right == null){
            num++;
            flag = false;
        }

        if (root.left != null){
            getNums(root.left);
        }

        if (root.right != null){
            getNums(root.right);
        }

    }

    //官方题解
    public int countNodes1(TreeNode root) {
        if (root == null){
            return 0;
        }
        int level = 0;
        TreeNode node = root;
        while (node != null){
            node = node.left;
            level++;
        }
        int low = 1<<level,right = (1<<(level+1))-1;
        while (low<right){
            int mid = ((right - low) / 2 )+low;
            if (exists(root,level,mid)){
                low = mid;
            } else {
                right = mid - 1;
            }
        }

        return low;
    }

    private boolean exists(TreeNode root, int level, int k) {
        int bits = 1<<(level - 1);
        TreeNode node = root;
        while (root != null && bits>0){
            if ((bits & k) == 0){
                node = node.left;
            } else {
                node = node.right;
            }
            bits >>= 1;
        }
        return node != null;
    }

    //迭代 没有优化的
    public int countNodes2(TreeNode root) {
        if (root == null){
            return 0;
        }
        int left = countNodes2(root.left);
        int right = countNodes2(root.right);
        return left + right +1;
    }

    //又一种解法
    public  int  countNodes3(TreeNode root){
        if (root == null){
            return 0;
        }
        int left = countLevel(root.left);
        int right = countLevel(root.right);

        if (left == right){
            return countNodes3(root.right) + (1<<left);
        } else {
            return countNodes3(root.left) + (1<<right);
        }

    }

    private int countLevel(TreeNode node) {
        int level = 0;
        TreeNode root = node;
        while (root != null){
            root = root.left;
            level++;
        }
        return level;
    }

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
}
