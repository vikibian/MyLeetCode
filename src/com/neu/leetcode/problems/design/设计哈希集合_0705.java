package com.neu.leetcode.problems.design;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class 设计哈希集合_0705 {
    class MyHashSet1 {

        private List<Integer> list ;
        /** Initialize your data structure here. */
        public MyHashSet1() {
            list = new LinkedList<>();
        }

        public void add(int key) {
            if(!list.contains(key)){
                list.add(key);
            }
        }

        public void remove(int key) {
            if(list.contains(key)){
                list.remove(Integer.valueOf(key));
            }
        }

        /** Returns true if this set contains the specified element */
        public boolean contains(int key) {
            return list.contains(key);
        }
    }

    class MyHashSet {
        private final int BASE = 769;
        private LinkedList[] data ;
        /** Initialize your data structure here. */
        public MyHashSet() {
            data = new LinkedList[BASE];
            for (int i=0;i<BASE;i++){
                data[i] = new LinkedList<Integer>();
            }
        }

        public void add(int key) {
            int h = hash(key);
            Iterator<Integer> iterator = data[h].iterator();
            while (iterator.hasNext()){
                Integer element = iterator.next();
                if (element == key){
                    return;
                }
            }
            data[h].offerLast(key);
        }

        public void remove(int key) {
            int h = hash(key);
            Iterator<Integer> iterator = data[h].iterator();
            while (iterator.hasNext()){
                Integer element = iterator.next();
                if (element == key){
                    data[h].remove(element);
                }
            }
        }

        /** Returns true if this set contains the specified element */
        public boolean contains(int key) {
            int h = hash(key);
            Iterator<Integer> iterator = data[h].iterator();
            while (iterator.hasNext()){
                Integer element = iterator.next();
                if (element == key){
                    return true;
                }
            }
            return false;
        }

        private  int hash(int key){
            return key % BASE;
        }
    }
}
