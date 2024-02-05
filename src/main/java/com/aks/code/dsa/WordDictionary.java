package com.aks.code.dsa;

// https://leetcode.com/problems/design-add-and-search-words-data-structure/
public class WordDictionary {

    public static void main(String[] args) {
        WordDictionary wd = new WordDictionary();
        wd.addWord("bad");
        wd.addWord("dad");
        wd.addWord("mad");
//        System.out.println(wd.root);

        System.out.println(wd.search("pad"));
        System.out.println(wd.search("bad"));
        System.out.println(wd.search(".ad"));
        System.out.println(wd.search(".a."));
    }
    private final TrieNode root;
    public WordDictionary() {
        root = new TrieNode('.');
    }

    public void addWord(String word) {
        insert(root, word, 0);
    }

    private void insert(TrieNode parent, String s, int i) {
        if(i >= s.length()) {
            parent.isWord = true;
            return;
        }
        char ch = s.charAt(i);
        TrieNode child = parent.child[index(ch)];
        if(child == null) {
            child = new TrieNode(ch);
            parent.child[index(ch)] = child;
        }
        insert(child, s, i+1);
    }

    private int index(char c) {
        return c - 'a';
    }

    public boolean search(String word) {
        return search(root, word, 0);
    }

    private boolean search(TrieNode parent, String s, int i) {
        if(i >= s.length()) {
            return parent.isWord;
        }
        char ch = s.charAt(i);
        if(ch == '.') {
            boolean res = false;
            for (TrieNode child : parent.child) {
                if(child != null) {
                    res |= search(child, s, i+1);
                }
            }
            return res;
        } else {
            TrieNode child = parent.child[index(ch)];
            if(child == null) {
                return false;
            }
            if( i == s.length()-1 && child.isWord) {
                return true;
            }
            return search(child,s,i+1);
        }
    }

}
