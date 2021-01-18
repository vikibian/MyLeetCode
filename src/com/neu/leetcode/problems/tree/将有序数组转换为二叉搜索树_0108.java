package com.neu.leetcode.problems.tree;

import java.util.HashMap;
import java.util.Map;

public class 将有序数组转换为二叉搜索树_0108 {

    public static void main(String[] args) {
        System.out.println((0 +1)/2);
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        if(nums.length == 0){
            return null;
        }
//        int mid = nums.length / 2;
//        TreeNode root = new TreeNode(nums[mid]);
        Map<Integer,Integer> map = new HashMap<>();
        for (int i=0;i<nums.length;i++){
            map.put(i,nums[i]);
        }

        return sortedArrayToBST(map,0,nums.length-1);
    }

    private TreeNode sortedArrayToBST(Map<Integer, Integer> map, int left, int right) {
        if (left>right){
            return null;
        }

        int mid = (left + right) / 2;
        TreeNode root = new TreeNode(map.get(mid));
         root.left = sortedArrayToBST(map,0,mid -1);
         root.right = sortedArrayToBST(map,mid +1,right);

         return root;
    }

    //我的解法是官方解法中的方法一 总是选择中间位置左边的数字作为根节点

    //方法二 选择中间位置元素右边的数字作为根节点
    //只需把上面取位置的代码 换成：
    //        int mid = (left + right + 1) / 2;

    //方法三 选择任意一个中间位置数字作为根节点
    // Random rand = new Random();  //生成介于 【0,1）的整数
    //mid = (left + right + rand.nextInt(2)) / 2;




    class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
 }
}
