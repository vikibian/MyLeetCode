package com.neu.leetcode.problems.linkedlist;

public class 删除排序链表中的重复元素_0083 {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode preNode = head;
        if(head == null){
            return head;
        }
        ListNode curNode = head.next;
        while(curNode != null){
            if(preNode.val == curNode.val){
                ListNode next = curNode.next;
                preNode.next = next;

            } else{
                preNode = curNode;

            }
            curNode = curNode.next;
        }

        return head;
    }

    public ListNode deleteDuplicates1(ListNode head){
        ListNode current = head;
        while (current != null && current.next != null){
            if (current.next.val == current.val){
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }
        return head;
    }

public class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
}
