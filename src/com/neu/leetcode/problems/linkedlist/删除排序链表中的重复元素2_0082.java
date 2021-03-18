package com.neu.leetcode.problems.linkedlist;

import java.util.*;

public class 删除排序链表中的重复元素2_0082 {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode curNode = head;
        if(head == null){
            return head;
        }
        ListNode tempNode = new ListNode(-1);
        ListNode curNode2 = tempNode;
        List<Integer> list = new LinkedList<>();
        while(curNode!= null && curNode.next != null){
            if(curNode.val == curNode.next.val){
                if(!list.contains(curNode.val)){
                    list.add(curNode.val);
                }
            } else{
                if(!list.contains(curNode.val)){
                    ListNode temp = new ListNode(curNode.val);
                    curNode2.next = temp;
                    curNode2 = curNode2.next;
                }

            }
            curNode = curNode.next;

        }
        if(!list.contains(curNode.val)){
            ListNode temp = new ListNode(curNode.val);
            curNode2.next = temp;
            curNode2 = curNode2.next;
        }

        return tempNode.next;
    }

    //高分题解  哈希表
    public ListNode deleteDuplicates1(ListNode head) {
        if(head==null || head.next==null) {
            return head;
        }
        //用哈希表记录每个节点值的出现频率
        HashMap<Integer,Integer> cache = new HashMap<Integer,Integer>();
        ArrayList<Integer> arr = new ArrayList<Integer>();
        ListNode p = head;
        while(p!=null) {
            int val = p.val;
            if(cache.containsKey(val)) {
                cache.put(val,cache.get(val)+1);
            } else {
                cache.put(val,1);
            }
            p = p.next;
        }
        //将所有只出现一次的值放到arr中，之后再对这个arr排序
        for(Integer k : cache.keySet()) {
            if(cache.get(k)==1) {
                arr.add(k);
            }
        }
        Collections.sort(arr);
        ListNode dummy = new ListNode(-1);
        p = dummy;
        //创建长度为arr.length长度的链表，依次将arr中的值赋给每个链表节点
        for(Integer i : arr) {
            ListNode tmp = new ListNode(i);
            p.next = tmp;
            p = p.next;
        }
        return dummy.next;
    }

    //高分题解 双指针法
    //核心 a.next.val == b.next.val
    public ListNode deleteDuplicates2(ListNode head){
        if (head == null || head.next == null){
            return head;
        }
        ListNode dumy = new ListNode(-1);
        dumy.next = head;
        ListNode a = dumy;
        ListNode b = head;
        while (b!=null && b.next != null){
            if (a.next.val != b.next.val){
                a = a.next;
                b = b.next;
            } else {
                //如果a、b指向的节点值相等，就不断移动b，直到a、b指向的值不相等
                while (b!=null && b.next !=null && a.next.val == b.next.val){
                    b = b.next;
                }
                a.next = b.next;
                b = b.next;
            }
        }
        return dumy.next;
    }

    //跟上面的解法相近
    public ListNode deleteDuplicates3(ListNode head) {
        if (head == null || head.next == null){
            return head;
        }
        ListNode dumy = new ListNode(-1);
        dumy.next = head;
        ListNode a = dumy;
        ListNode b = head.next;
        while (b != null){
            if (a.next.val != b.val){
                a = a.next;
                b = b.next;
            } else {
                while (b!= null && a.next.val== b.val){
                    b = b.next;
                }
                a.next = b;
                b = (b==null)?null:b.next;
            }
        }

        return dumy.next;
    }

    //高分解法 递归
    public ListNode deleteDuplicates4(ListNode head) {
        if (head == null || head.next == null){
            return head;
        }
        if (head.val == head.next.val){
            while (head != null && head.next != null && head.val == head.next.val){
                head = head.next;
            }
            return deleteDuplicates4(head.next);
        } else{
            head.next = deleteDuplicates4(head.next);
            return head;
        }
    }



 public class ListNode {
     int val;
     ListNode next;
     ListNode() {}
     ListNode(int val) { this.val = val; }
     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 }
}
