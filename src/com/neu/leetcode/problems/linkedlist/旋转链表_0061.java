package com.neu.leetcode.problems.linkedlist;

public class 旋转链表_0061 {
 public class ListNode {
     int val;
     ListNode next;
     ListNode() {}
     ListNode(int val) { this.val = val; }
     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 }
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || k == 0){
            return head;
        }
        int count = 0;
        ListNode post = head;
        ListNode prepost = null;
        while (post != null){
            prepost = post;
            post = post.next;
            count++;
        }
        k = k % count;
        if (k == 0){
            return head;
        }
        int index = 0;
        ListNode pre = head;
        ListNode curNode = null;
        int rest = count-k;

        while (index < rest){
            curNode = pre;
            pre = pre.next;
            index++;
        }

        curNode.next = null;
        prepost.next = head;
        return pre;
    }

    //官方题解  闭合为环
    public ListNode rotateRight1(ListNode head, int k) {
        if (k == 0 || head == null || head.next == null){
            return head;
        }
        int n=1;
        ListNode iter = head;
        while (iter.next != null){
            iter = iter.next;
            n++;
        }
        int add = n - k%n;
        if (add == n){
            return head;
        }
        iter.next = head;
        while (add-- > 0){
            iter = iter.next;
        }
        ListNode ret = iter.next;
        iter.next = null;
        return ret;
    }


    //高分题解
    /*
    1:两个指针 slow 和 fast 值距离是 k，先让 fast 指向链表的第 k + 1个节点，slow 指向第 1 个节点；
    2:然后 slow 和 fast 同时向后移动，当 fast 移动到链表的最后一个节点的时候，
    那么 slow 指向链表的倒数第 k+1 个节点。

     */
}
