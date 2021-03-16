package com.neu.leetcode.problems.doublepointer;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class 删除链表的倒数第N个结点_0019 {

public class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
    //mid ture
    public ListNode removeNthFromEnd(ListNode head, int n) {
        List<ListNode> list = new LinkedList<>();
        ListNode cur = head;
        while (cur != null){
            list.add(cur);
            cur = cur.next;
        }

        int size = list.size();
        int index = size-n;
        if (index == 0){
            list.remove(0);
        } else if (index == size-1){
            list.get(index-1).next = null;
            list.remove(index);
        } else {
            ListNode pre = list.get(index-1);
            ListNode post = list.get(index+1);
            pre.next = post;
        }
        return list.size() == 0 ? null:list.get(0);
    }

    //官方题解 计算链表长度
    public ListNode removeNthFromEnd1(ListNode head, int n) {
        ListNode dumy = new ListNode(0,head);
        int len = getLength(head);
        ListNode cur = dumy;
        for (int i=0;i<len-n+1;i++){
            cur = cur.next;
        }
        cur.next = cur.next.next;
        ListNode ans = dumy.next;
        return ans;
    }

    public int getLength(ListNode head){
        int length = 0;
        while (head != null){
            head = head.next;
            length++;
        }
        return length;
    }

    //官方题解 栈
    public ListNode removeNthFromEnd2(ListNode head,int n){
        ListNode dumy = new ListNode(0,head);
        Deque<ListNode> deque = new LinkedList<>();
        ListNode cur = dumy;
        while (cur != null){
            deque.push(cur);
            cur = cur.next;
        }

        for (int i=0;i<n;i++){
            deque.pop();
        }
        ListNode pre = deque.peek();
        pre.next = pre.next.next;
        ListNode ans = dumy.next;
        return ans;
    }

    //官方题解 快慢指针
    public ListNode removeNthFromEnd3(ListNode head,int n){
        ListNode dumy = new ListNode(0,head);
        ListNode first = head;
        ListNode second = dumy;

        for (int i=0;i<n;i++){
            first = first.next;
        }

        while (first!= null){
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        ListNode ans = dumy.next;
        return ans;
    }
}
