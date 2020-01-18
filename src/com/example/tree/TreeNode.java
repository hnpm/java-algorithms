package com.example.tree;

public class TreeNode {
    private int value;
    private TreeNode left, right;

    public TreeNode(int value) {
        this.value = value;
    }

    public TreeNode find(int data) {
        if (this.value == data) {
            return this;
        }
        if (data < this.value && left != null) {
            return left.find(data);
        }
        if (right != null) {
            return right.find(data);
        }
        return null;
    }

    public void insert(int data) {
         if (data < this.value) {
             if (this.left != null) {
                 this.left.insert(data);
             } else {
                 this.left = new TreeNode(data);
             }
         } else {
             if (this.right != null) {
                 this.right.insert(data);
             } else {
                 this.right = new TreeNode(data);
             }
         }
    }

    public int min() {
        if (this.left == null) {
            return this.value;
        }
        return this.left.min();
    }

    public int max() {
        if (this.right == null) {
            return this.value;
        }
        return this.right.max();
    }

    public int getValue() {
        return value;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }
}
