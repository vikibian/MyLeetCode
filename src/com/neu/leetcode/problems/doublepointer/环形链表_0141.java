package com.neu.leetcode.problems.doublepointer;

import java.util.HashSet;
import java.util.Set;

public class 环形链表_0141 {

 class ListNode {
     int val;
     ListNode next;
     ListNode(int x) {
         val = x;
         next = null;
     }
 }

    public boolean hasCycle(ListNode head) {
//        System.out.println(pos);
        if (head == null || head.next == null ){
            return false;
        }
        ListNode pre1 = head;
        ListNode pre2 = head.next;

        while (pre2!=null && pre2.next != null){
            if (pre1 == pre2){
                return true;
            }
            pre1 = pre1.next;
            pre2 = pre2.next.next;
        }
        return false;

    }

    //官方题解 哈希表
    public boolean hasCycle1(ListNode head){
        Set<ListNode> set = new HashSet<>();
        while (head != null){
            if (!set.add(head)){
                return true;
            }
            head = head.next;
        }

        return false;
    }

    //官方题解 快慢指针
    public boolean hasCycle2(ListNode head){
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != slow){
            if (fast == null || fast.next == null){
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }

}
