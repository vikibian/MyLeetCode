package com.neu.leetcode.problems;

import java.util.Stack;

public class 两数相加_0002 {
    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(5);
        ListNode listNode2 = new ListNode(4);
        ListNode listNode3 = new ListNode(3);

//        listNode1.next = listNode2;
//        listNode2.next = listNode3;

        ListNode listNode4 = new ListNode(5);
        ListNode listNode5 = new ListNode(6);
        ListNode listNode6 = new ListNode(4);
//        listNode4.next = listNode5;
//        listNode5.next = listNode6;

        ListNode listNode = addTwoNumbers(listNode1, listNode4);
        int index=0;
        while (listNode != null){
            System.out.println("value:"+listNode.val);
            System.out.println("index:"+index);
            index++;
            listNode = listNode.next;
        }
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode listNode1 = l1;
        ListNode listNode2 = l2;



//        Stack<Integer> stack1 = new Stack<>();
//        while (listNode1 != null ){
//            stack1.push(listNode1.val);
//            listNode1 = listNode1.next;
//        }
//
//        Stack<Integer> stack2 = new Stack<>();
//        while (listNode2 != null){
//            stack2.push(listNode2.val);
//            listNode2 = listNode2.next;
//        }
        int rest = 0;
        int index = 0;
        boolean flag = false;
        ListNode resultListNode = new ListNode(0);

        ListNode indexListNode = resultListNode;
        int v1 ;
        int v2 ;


        while (listNode1 != null || listNode2 != null){
            if (listNode1 == null){
                v1 = 0;
            }else {
                v1 = listNode1.val;
            }

            if (listNode2 == null){
                v2 = 0;
            }else {
                v2 = listNode2.val;
            }

            int result = v1 + v2 + rest;
            if ( result  >= 10){
                rest = result / 10;
                flag = true;
            }else {
                flag = false;
            }

            if (index == 0){
                indexListNode.val = (result % 10);
                System.err.println(result%10);
            }else {

                indexListNode.next = new ListNode(result % 10);
                indexListNode = indexListNode.next;
                System.err.println(result%10);

            }
            index ++;


            if (listNode1 != null){
                listNode1 = listNode1.next;
            }

            if (listNode2 != null){
                listNode2 = listNode2.next;
            }

        }
        if (flag){
            indexListNode.next = new ListNode(rest);
        }


        return resultListNode;
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
