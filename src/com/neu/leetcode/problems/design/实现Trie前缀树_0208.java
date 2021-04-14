package com.neu.leetcode.problems.design;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class 实现Trie前缀树_0208 {
    class Trie {
        private Set<String> set ;
        private List<String> list ;
        /** Initialize your data structure here. */
        public Trie() {
            set = new HashSet<>();
            list = new ArrayList<>();
        }

        /** Inserts a word into the trie. */
        public void insert(String word) {
            list.add(word);
            set.add(word);
        }

        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            return set.contains(word);
        }

        /** Returns if there is any word in the trie that starts with the given prefix. */
        public boolean startsWith(String prefix) {
            if (search(prefix)){
                return true;
            } else {
                int n = prefix.length();
                for (String str : list){
                    if (str.length() >= n){
                        String sub = str.substring(0,n);
                        if (sub.equals(prefix)){
                            return true;
                        }
                    }
                }
                return false;
            }
        }
    }

    //官方题解 字典树
    class Trie1{
        private Trie1[] children;
        private boolean isEnd;

        public Trie1(){
            children = new Trie1[26];
            isEnd = false;
        }

        public void insert(String word){
            Trie1 node = this;
            for (int i=0;i<word.length();i++){
                char ch = word.charAt(i);
                int index = ch-'a';
                if (node.children[index] == null){
                    node.children[index] = new Trie1();
                }
                node = node.children[index];
            }
            node.isEnd = true;
        }

        public boolean search(String word){
            Trie1 node = searchPrefix(word);
            return node != null && node.isEnd;
        }

        public boolean startsWith(String prefix){
            return searchPrefix(prefix).isEnd;
        }

        private Trie1 searchPrefix(String prefix){
            Trie1 node = this;
            for (int i=0;i<prefix.length();i++){
                char ch = prefix.charAt(i);
                int index = ch-'a';
                if (node.children[index] == null){
                    return null;
                }
                node = node.children[index];
            }
            return node;
        }
    }
}
