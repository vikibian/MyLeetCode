package com.neu.leetcode.problems;

import sun.text.SupplementaryCharacterData;

import java.util.*;

public class 验证二叉搜索树_0098 {

    public static void main(String[] args) {
        //[5,1,4,null,null,3,6]
//        TreeNode root = new TreeNode(5);
//        TreeNode left1 = new TreeNode(1);
//        TreeNode right1 = new TreeNode(4);
//
//        root.left = left1;
//        root.right = right1;
//
//        TreeNode left1right1 = new TreeNode(3);
//        TreeNode right1right1 = new TreeNode(6);
//
//        right1.left = left1right1;
//        right1.right = right1right1;

        //[0,-1]
//        TreeNode root = new TreeNode(0);
//        TreeNode left = new TreeNode(-1);
//        root.left = left;

        //[5,4,6,null,null,3,7]
//        TreeNode root = new TreeNode(5);
//        TreeNode left = new TreeNode(4);
//        TreeNode right = new TreeNode(6);
//
//
//        root.left = left;
//        root.right = right;
//
//        TreeNode left2 = new TreeNode(3);
//        TreeNode right2 = new TreeNode(7);
//
//        right.left = left2;
//        right.right = right2;

        //[3,1,5,0,2,4,6]
        TreeNode root = new TreeNode(3);
        TreeNode left = new TreeNode(1);
        TreeNode right = new TreeNode(5);


        root.left = left;
        root.right = right;

        TreeNode left1 = new TreeNode(0);
        TreeNode right1 = new TreeNode(2);

        left.left = left1;
        left.right = right1;

        TreeNode left2 = new TreeNode(4);
        TreeNode right2 = new TreeNode(6);

        right.left = left2;
        right.right = right2;

        //[32,26,47,19,null,null,56,null,27]

        System.out.println(isValidBST3(root));


    }

    //官方题解1  迭代
    public static boolean isValidBST1(TreeNode root) {
        return helper(root, null, null);
    }

    public static boolean helper(TreeNode root,Integer lower,Integer hight){

        if (root == null){
            return true;
        }

        int val = root.val;
        if (lower != null && val <= lower){
            return false;
        }

        if (hight != null && val >= hight){
            return false;
        }

        if (!(helper(root.right,root.val,hight))){
            return false;
        }

        if (!helper(root.left,lower,root.val)){
            return false;
        }




        return true;
    }

    //官方题解2  中序遍历
    public static boolean isValidBST2(TreeNode root) {

        Deque<TreeNode> stack = new LinkedList<>();
        Stack<TreeNode> stack1 = new Stack<>();

        Integer inorder = null;

        while ((!stack.isEmpty()) || root != null){
            //找到二叉树的最左边的点
            while (root != null){
                stack.push(root);
                root = root.left;
            }
            //遍历完之后 root  可能为空
            root = stack.pop();
            if (inorder != null && root.val <= inorder){
                return false;
            }

            inorder = root.val;
            root = root.right;
        }

        return true;
    }

    //官方题解3  迭代
    //初始化3个栈
    //stack用来保存树节点
    static LinkedList<TreeNode> stack = new LinkedList<>();
    //uppers用来保存树节点对应的上界
    static LinkedList<Integer> uppers = new LinkedList<>();
    //lowers用来保存树节点对应的下界
    static LinkedList<Integer> lowers = new LinkedList<>();
    public static boolean isValidBST3(TreeNode root) {

       Integer lower = null,upper = null,val;
       update(root,lower,upper);

       while (!stack.isEmpty()){
           root = stack.pop();
           upper = uppers.pop();
           lower = lowers.pop();

           if (root == null)
               continue;
            val = root.val;
            if (upper != null && val >= upper){
                return false;
            }

            if (lower != null && val <= lower){
                return false;
            }


            //先弹出左边的值
            update(root.right,val,upper);
            update(root.left,lower,val);
       }


        return true;
    }

    public static void  update(TreeNode root,Integer lower,Integer upper){
        stack.add(root);
        uppers.add(upper);
        lowers.add(lower);
    }




//    public static boolean isValidBST(TreeNode root) {
//        if (root == null){
//            return false;
//        }
//
//        return isBST3(root);
//
//    }
//
//    private static boolean isBST3(TreeNode root) {
//        if (root.left == null && root.right == null){
//            return true;
//        }
//
//
//
//        boolean left = true;
//        boolean right = true;
//
//
//        if (root.left != null){
//            TreeNode leftNode = root.left;
//            TreeNode preNode = root;
//            while (leftNode.left != null){
//                if (leftNode.left.val > leftNode.val){
//                    return false;
//                }
//                preNode = leftNode;
//                leftNode = leftNode.left;
//            }
//            while (leftNode.right != null){
//                if (leftNode.left.val > leftNode.val){
//                    return false;
//                }
//                preNode = leftNode;
//                leftNode = leftNode.left;
//            }
//
//
//            isBST4(leftNode);
//        }
//
//
//        if (root.right != null){
//            TreeNode rightNode = root.right;
//            while (rightNode.left != null){
//                if (rightNode.right.val > rightNode.val){
//                    return false;
//                }
//                rightNode = rightNode.left;
//            }
//
//        }
//
//
//
//        return false;
//    }

//    private static boolean isBST4(TreeNode leftNode) {
//
//
//
//        return false;
//    }


//    public static boolean isValidBST(TreeNode root) {
//        if (root == null){
//            return false;
//        }
//
//        if (root.left == null && root.right ==null){
//            return true;
//        } else {
//            boolean left = false;
//            boolean rigth = false;
//            if (root.left != null && root.left.val < root.val ){
//                left = isValidBST(root.left);
//            } else {
//                if (root.left == null){
//                    left = true;
//                }
//            }
//
//            if(root.right != null && root.right.val > root.val){
//                rigth = isValidBST(root.right);
//            }else {
//                if (root.right == null){
//                    rigth = true;
//                }
//            }
//
//            return left && rigth;
//        }
//    }


//    public static boolean isValidBST(TreeNode root) {
//        if (root == null){
//            return false;
//        }
//
//        if (root.left == null &&root.right == null){
//            return true;
//        } else {
//            boolean left = false;
//            boolean rigth = false;
//            List<Integer> listLeft = new ArrayList<>();
//            List<Integer> listRight = new ArrayList<>();
//            listLeft.add(root.val);
//            listRight.add(root.val);
//            int low = 0;
//            int hight = 0;
//            if (root.left != null ){
//
//                left = isBST2(root.left,listLeft,listRight,true,root.val,hight);
//            } else {
//                    left = true;
//            }
//
//            if(root.right != null ){
//
//                rigth = isBST2(root.right,listLeft, listRight, false,low,root.val);
//            }else {
//                    rigth = true;
//            }
//            return left && rigth;
//
//        }
//
////       return isBST(root,root.val,true) && isBST(root,root.val,false);
//    }
//    public static  boolean isBST2(TreeNode root, List<Integer> listLeft, List<Integer> listRight, boolean b, int low, int hight){
//
//        if (b){
////            for (int va : listLeft){
////                if ((root.val>va)){
////                    return false;
////                }
////            }
//            if (root.val >=low){
//                return false;
//            }
//
//            listLeft.add(root.val);
//        } else {
////            for (int va : listRight){
////                if ((root.val<va)){
////                    return false;
////                }
////            }
//            if (root.val <= hight){
//                return false;
//            }
//            listRight.add(root.val);
//        }
//
//        if (root.left == null && root.right ==null){
//            return true;
//        } else {
//            boolean left = false;
//            boolean rigth = false;
//            if (root.left != null && root.left.val < root.val ){
//                left = isBST2(root.left,listLeft, listRight, b, low, hight);
//            } else {
//                if (root.left == null){
//                    left = true;
//                }
//            }
//
//            if(root.right != null && root.right.val > root.val){
//                rigth = isBST2(root.right,listLeft, listRight, b, low, hight);
//            }else {
//                if (root.right == null){
//                    rigth = true;
//                }
//            }
//
//            return left && rigth;
//        }
//    }


//    public static  boolean isBST2(TreeNode root, int val, boolean b){
//
//        if (b){
//            if (!(root.val <val)){
//                return false;
//            }
//        } else {
//            if (!(root.val >val)){
//                return false;
//            }
//        }
//
//        if (root.left == null && root.right ==null){
//            return true;
//        } else {
//            boolean left = false;
//            boolean rigth = false;
//            if (root.left != null && root.left.val < root.val ){
//                left = isBST2(root.left,val,b);
//            } else {
//                if (root.left == null){
//                    left = true;
//                }
//            }
//
//            if(root.right != null && root.right.val > root.val){
//                rigth = isBST2(root.right,val,b);
//            }else {
//                if (root.right == null){
//                    rigth = true;
//                }
//            }
//
//            return left && rigth;
//        }
//    }

//    public static  boolean isBST(TreeNode root, int val, boolean b){
//
//        if (b){
//            if (!(root.val <val)){
//                return false;
//            }
//        } else {
//            if (!(root.val >val)){
//                return false;
//            }
//        }
//
//        if (root.left == null && root.right ==null){
//            return true;
//        } else {
//            boolean left = false;
//            boolean rigth = false;
//            if (root.left != null && root.left.val < root.val ){
//                left = isBST(root.left,val,b);
//            } else {
//                if (root.left == null){
//                    left = true;
//                }
//            }
//
//            if(root.right != null && root.right.val > root.val){
//                rigth = isBST(root.right,val,b);
//            }else {
//                if (root.right == null){
//                    rigth = true;
//                }
//            }
//
//            return left && rigth;
//        }
//    }

 static class TreeNode {
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
