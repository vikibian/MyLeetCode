package com.neu.leetcode.problems.linkedlist;

import java.util.List;

public class 反转链表2_0092{
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode head1 = new ListNode(2,head);
        ListNode head2 = new ListNode(3,head1);
        ListNode head3 = new ListNode(4,head2);
        ListNode head4 = new ListNode(5,head3);
        reverseBetween(head4,2,4);
    }
    public static ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode preHead = new ListNode(0,head);
        ListNode curNode = preHead;
        int num = 0;
        ListNode preNode = new ListNode();
        ListNode postNode = null;
        ListNode tempNode = null;
        ListNode tempNode2 = null;
        ListNode tailNode = null;
        while (curNode != null){
            if (num== left-1){
                preNode = curNode;
                tempNode = new ListNode(curNode.val);
            }

            if (num>=left && num<=right){
                ListNode node = new ListNode(curNode.val);
                node.next = tempNode.next;

                tempNode.next = node;
                if (num == left){
                    tempNode2 = tempNode.next;
                }
            }
            if (num == right){
                postNode = curNode;
                tailNode = new ListNode(curNode.val,curNode.next);

            }

            curNode = curNode.next;
            num++;
        }

        preNode.next = tempNode.next;
        tempNode2.next = tailNode.next;

        return preHead.next;
    }

    //官方题解  穿针引线
    public ListNode reverseBetween1(ListNode head,int left,int right){
        // 因为头节点有可能发生变化，使用虚拟头节点可以避免复杂的分类讨论
        ListNode dumyNode = new ListNode(-1);
        dumyNode.next = head;

        ListNode pre = dumyNode;
        // 第 1 步：从虚拟头节点走 left - 1 步，来到 left 节点的前一个节点
        // 建议写在 for 循环里，语义清晰
        for (int i=0;i<left-1;i++){
            pre = pre.next;
        }

        // 第 2 步：从 pre 再走 right - left + 1 步，来到 right 节点
        ListNode rightNode = pre;
        for (int i=0;i<right-left+1;i++){
            rightNode = rightNode.next;
        }

        // 第 3 步：切断出一个子链表（截取链表）
        ListNode leftNode = pre.next;
        ListNode curr = rightNode.next;

        // 注意：切断链接
        pre.next = null;
        rightNode.next = null;

        // 第 4 步：同第 206 题，反转链表的子区间
        reverseListNode(leftNode);

        // 第 5 步：接回到原来的链表中
        pre.next = rightNode;
        leftNode.next = curr;

        return dumyNode.next;
    }

    private void reverseListNode(ListNode head) {
        // 也可以使用递归反转一个链表
        ListNode pre = null;
        ListNode cur = head;
        while (cur!=null){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
    }

    //官方题解 （穿针引线）反转链表（头插法）
    public ListNode reverseBetween2(ListNode head,int left,int right){
        ListNode dumy = new ListNode(-1);
        dumy.next = head;
        ListNode pre = dumy;
        for (int i=0;i<left-1;i++){
            pre = pre.next;
        }
        ListNode cur = pre.next;
        ListNode next = null;
        for (int i=0;i<right-left;i++){
            next = cur.next;
            cur.next = next.next;
            next.next = pre.next;
            pre.next = next;

        }

        return dumy.next;
    }

    public static class ListNode {
     int val;
     ListNode next;
     ListNode() {}
     ListNode(int val) { this.val = val; }
     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 }
}
