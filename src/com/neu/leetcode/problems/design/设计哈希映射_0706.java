package com.neu.leetcode.problems.design;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class 设计哈希映射_0706 {
    class MyHashMap1 {
        private List<Integer> listKey;
        private List<Integer> listValue;
        /** Initialize your data structure here. */
        public MyHashMap1() {
            listKey = new LinkedList<>();
            listValue = new LinkedList<>();
        }

        /** value will always be non-negative. */
        public void put(int key, int value) {
            if(listKey.contains(key)){
                int index = listKey.indexOf(key);
                listValue.set(index,value);
            } else {
                listKey.add(key);
                listValue.add(value);
            }
        }

        /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
        public int get(int key) {
            if(listKey.contains(key)){
                return listValue.get(listKey.indexOf(key));
            } else{
                return -1;
            }
        }

        /** Removes the mapping of the specified value key if this map contains a mapping for the key */
        public void remove(int key) {
            if(listKey.contains(key)){
                int index = listKey.indexOf(key);
                listKey.remove(index);
                listValue.remove(index);
            }
        }
    }

}

class MyHashMap {

    private class Pair{
        private int key;
        private int value;

        public Pair(int key, int value) {
            this.key = key;
            this.value = value;
        }


        public int getKey() {
            return key;
        }

        public void setKey(int key) {
            this.key = key;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    private static final int BASE = 769;
    private LinkedList[] data;

    /** Initialize your data structure here. */
    public MyHashMap() {
        data = new LinkedList[BASE];
        for (int i=0;i<BASE;i++){
            data[i] = new LinkedList<Pair>();
        }
    }

    /** value will always be non-negative. */
    public void put(int key, int value) {
        int h = hash(key);
        Iterator<Pair> iterator = data[h].iterator();
        while (iterator.hasNext()){
            Pair pair = iterator.next();
            if (pair.getKey() == key){
                pair.setKey(value);
            }
        }
        data[h].offerLast(new Pair(key,value));
    }

    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        int h = hash(key);
        Iterator<Pair> iterator = data[h].iterator();
        while (iterator.hasNext()){
            Pair pair = iterator.next();
            if (pair.getKey() == key){
                return pair.getValue();
            }
        }
        return -1;
    }

    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        int h = hash(key);
        Iterator<Pair> iterator = data[h].iterator();
        while (iterator.hasNext()){
            Pair pair = iterator.next();
            if (pair.getKey() == key){
                data[h].remove(pair);
                return;
            }
        }
    }

    private static int hash(int key){
        return key % BASE;
    }
}
