package com.neu.leetcode.problems.recursion;

public class 合并两个有序链表_0021 {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null){
            return null;
        }
        ListNode root = new ListNode();
        if (l1 == null){
            root.val = l2.val;
            root.next = mergeTwoLists(l1,l2.next);
            return root;
        }

        if (l2 == null){
            root.val = l1.val;
            root.next = mergeTwoLists(l1.next,l2);
            return root;
        }

        if (l1.val <= l2.val){
            root.val = l1.val;
            root.next = mergeTwoLists(l1.next,l2);
        } else {
            root.val = l2.val;
            root.next = mergeTwoLists(l1,l2.next);
        }

        return root;
    }

    //递归
    public ListNode mergeTwoLists1(ListNode l1,ListNode l2){
        if (l1 == null){
            return l2;
        } else if (l2 == null){
            return l1;
        } else if (l1.val < l2.val){
            l1.next = mergeTwoLists1(l1.next,l2);
            return l1;
        } else {
            l2.next = mergeTwoLists1(l1,l2.next);
            return l2;
        }
    }

    //迭代
    public ListNode mergeTwoLissts2(ListNode l1,ListNode l2){
        ListNode prehead = new ListNode(-1);
        ListNode prev = prehead;
        while (l1 != null && l2 != null){
            if (l1.val <= l2.val){
                prev.next = l1;
                l1 = l1.next;
            } else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }
        prev.next = l1 == null ? l2:l1;
        return prehead.next;
    }

 public class ListNode {
     int val;
     ListNode next;
     ListNode() {}
     ListNode(int val) { this.val = val; }
     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 }
}
