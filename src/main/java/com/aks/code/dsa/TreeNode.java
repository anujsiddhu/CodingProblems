package com.aks.code.dsa;

public class TreeNode {
    private int val;
    private TreeNode left;
    private TreeNode right;
    private TreeNode parent;

    public TreeNode(int v) {
        val = v;
    }

    public int val() {
        return val;
    }

    public void val(int val) {
        this.val = val;
    }

    public TreeNode left() {
        return left;
    }

    public void left(TreeNode left) {
        this.left = left;
    }

    public TreeNode right() {
        return right;
    }

    public void right(TreeNode right) {
        this.right = right;
    }

    public TreeNode parent() {
        return parent;
    }

    public void parent(TreeNode parent) {
        this.parent = parent;
    }
}
