package com.neu.leetcode.problems.design;

import java.util.*;

public class LRU缓存机制_0146 {
    class LRUCache2 {
        private int cap = 0;
        private Map<Integer,Integer> cache;
        //    private Map<Integer,Integer> memo;
        private Deque<Integer> memo ;
        private int count = 0;

        public LRUCache2(int capacity) {
            cap = capacity;
            cache = new LinkedHashMap<>();
            memo = new LinkedList<>();

        }

        public int get(int key) {

            if (cache.containsKey(key)){
                operateMemo(key);
                return cache.get(key);
            } else {
                return -1;
            }
        }

        public void put(int key, int value) {
            if (cache.containsKey(key)){
                cache.remove(key);
                cache.put(key,value);
                operateMemo(key);
            } else {
                if (cache.size()<cap){
                    cache.put(key,value);
                    memo.addLast(key);
                }else {
                    //删除最久没用的
                    int num = memo.getFirst();
                    memo.removeFirst();
                    cache.remove(num);
                    cache.put(key,value);
                    memo.addLast(key);
                }
            }
        }

        private void operateMemo(int key){
            if (memo.contains(key)){
                memo.remove(key);
                memo.addLast(key);
            } else {
                memo.addLast(key);
            }
        }
    }

    //给出使用封装好的数据结构实现的代码，而不多做任何阐述
    class LRUCache1 extends LinkedHashMap<Integer, Integer>{
        private int capacity;

        public LRUCache1(int capacity) {
            super(capacity, 0.75F, true);
            this.capacity = capacity;
        }

        public int get(int key) {
            return super.getOrDefault(key, -1);
        }

        public void put(int key, int value) {
            super.put(key, value);
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
            return size() > capacity;
        }
    }

    //官方题解 哈希表+双向链表
    public class LRUCache {
        class DLinkedNode{
            int key;
            int value;
            DLinkedNode prev;
            DLinkedNode next;
            public DLinkedNode(){};
            public DLinkedNode(int _key,int _value){
                key = _key;
                value = _value;
            }
        }

        private Map<Integer,DLinkedNode> cache = new HashMap<>();
        private int size;
        private int capacity;
        private DLinkedNode head,tail;

        public LRUCache(int capacity){
            this.size = 0;
            this.capacity = capacity;
            head = new DLinkedNode();
            tail = new DLinkedNode();
            head.next = tail;
            tail.prev = head;
        }

        public int get(int key){
            DLinkedNode node = cache.get(key);
            if (node == null){
                return -1;
            }
            moveToHead(node);
            return node.value;
        }

        public void put(int key,int value){
            DLinkedNode node = cache.get(key);
            if (node == null){
                DLinkedNode newNode = new DLinkedNode(key,value);
                cache.put(key,newNode);
                addToHead(newNode);
                size++;
                if (size>capacity){
                    DLinkedNode tail = removeTail();
                    cache.remove(tail,key);
                    size--;
                }

            } else {
                node.value = value;
                moveToHead(node);
            }
        }

        private DLinkedNode removeTail() {
            DLinkedNode res = tail.prev;
            removeNode(res);
            return res;
        }

        private void moveToHead(DLinkedNode node) {
            removeNode(node);
            addToHead(node);
        }

        private void addToHead(DLinkedNode node) {

            head.next.prev = node;
            node.next = head.next;
            head.next = node;
            node.prev = head;

        }

        private void removeNode(DLinkedNode node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
    }

}
