package com.neu.leetcode.problems.design;

import java.util.*;

public class 扁平化嵌套列表迭代器_0341 {

// This is the interface that allows for creating nested lists.
// You should not implement it, or speculate about its implementation
public interface NestedInteger {

    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    public boolean isInteger();

    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    public Integer getInteger();

    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return null if this NestedInteger holds a single integer
    public List<NestedInteger> getList();
}


    public class NestedIterator implements Iterator<Integer> {
        private List<Integer> nestedIntegers;
        private int index = 0;
        public int len = 0;
        public NestedIterator(List<NestedInteger> nestedList) {
            this.nestedIntegers = new LinkedList<>();
            add(nestedList);
            this.len = nestedIntegers.size();
        }

        public void add(List<NestedInteger> list){
            for (NestedInteger nested : list){
                if (nested.isInteger()){
                    nestedIntegers.add(nested.getInteger());
                } else {
                    add(nested.getList());
                }
            }
        }

        @Override
        public Integer next() {
            return nestedIntegers.get(index++);
        }

        @Override
        public boolean hasNext() {
            return index<len;
        }
    }

    //官方题解 深度优先遍历
    public class NestedIterator1 implements Iterator<Integer>{
        private List<Integer> vals;
        private Iterator<Integer> cur;

        public NestedIterator1 (List<NestedInteger> nestedList){
            vals = new ArrayList<>();
            dfs(nestedList);
            cur = vals.iterator();
        }

        private void dfs(List<NestedInteger> nestedList) {
            for (NestedInteger nest : nestedList){
                if (nest.isInteger()){
                    vals.add(nest.getInteger());
                } else {
                    dfs(nest.getList());
                }
            }
        }

        @Override
        public boolean hasNext() {
            return cur.hasNext();
        }

        @Override
        public Integer next() {
            return cur.next();
        }
    }

    //官方题解 栈
    public class NestedIterator2 implements Iterator<Integer>{
        //存储当前遍历位置
        private Deque<Iterator<NestedInteger>> stack;

        public NestedIterator2(List<NestedInteger> nestedList){
            stack = new LinkedList<>();
            stack.push(nestedList.iterator());
        }

        @Override
        public boolean hasNext() {
            while (!stack.isEmpty()){
                Iterator<NestedInteger> it = stack.peek();
                if (!it.hasNext()){
                    stack.pop();
                    continue;
                }
                NestedInteger nest = it.next();
                if (nest.isInteger()){
                    List<NestedInteger> list = new ArrayList<>();
                    list.add(nest);
                    stack.push(list.iterator());
                    return true;
                }
                stack.push(nest.getList().iterator());
            }
            return false;
        }

        @Override
        public Integer next() {
            // 由于保证调用 next 之前会调用 hasNext，直接返回栈顶列表的当前元素
            return stack.peek().next().getInteger();
        }
    }
}
