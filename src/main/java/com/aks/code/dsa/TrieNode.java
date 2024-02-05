package com.aks.code.dsa;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class TrieNode {
    public char ch;
    public boolean isWord;
    public TrieNode[] child;

    public TrieNode(char ch) {
        this.ch = ch;
        this.child = new TrieNode[26];
    }

    @Override
    public String toString() {
        return "\tTrieNode{" +
                "ch=" + ch +
                ", isWord=" + isWord +
                ", child=\n\t" +
                Arrays.stream(child).filter(Objects::nonNull).collect(Collectors.toList())
                +
                '}';
    }
}
