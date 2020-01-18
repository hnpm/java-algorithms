package com.example.tree;

import java.util.ArrayDeque;

public class BinaryTree {
    private TreeNode root;

    public BinaryTree() {
        root = null;
    }

    public BinaryTree(int[] array) {
        root = createFromArray(array, 0);
    }

    public void breadFirstTraverse() {
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        if (root != null) queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode temp = queue.remove();
            System.out.println(temp.getValue());
            if (temp.getLeft() != null) queue.add(temp.getLeft());
            if (temp.getRight() != null) queue.add(temp.getRight());
        }
    }

    public void depthFirstTraverse() {
        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        if (root != null) stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode temp = stack.pop();
            System.out.println(temp.getValue());
            if (temp.getLeft() != null) stack.push(temp.getLeft());
            if (temp.getRight() != null) stack.push(temp.getRight());
        }
    }

    public int depth() {
        return depth(root);
    }

    public void nthPreOrder(int index) {
        nthPostOrder(root, index, 0);
    }

    public void nthPostOrder(int index) {
        nthPreOrder(root, index, 0);
    }

    public void nthInOrder(int index) {
        nthInOrder(root, index, 0);
    }

    public BinaryTree copy() {
        BinaryTree tree = new BinaryTree();
        tree.root = copy(root);
        return tree;
    }

    public int count() {
        return count(root);
    }

    public int leafCount() {
        return leafCount(root);
    }

    public boolean isIdentical(BinaryTree tree) {
        return isIdentical(root, tree.root);
    }

    public TreeNode toList() {
        return toList(root);
    }

    public void printAllPaths() {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        printAllPaths(root, stack);
    }

    public int lca(int first, int second) {
        TreeNode result = lca(root, first, second);
        if (result != null) return result.getValue();
        else return Integer.MIN_VALUE;
    }

    public int max() {
        return max(root);
    }

    public boolean isPresent(int value) {
        return isPresent(root, value);
    }

    public int fullNodeCount() {
        return fullNodeCount(root);
    }

    public int sum() {
        return sum(root);
    }

    private int sum(TreeNode node) {
        if (node == null) return 0;
        int leftSum = sum(node.getLeft());
        int rightSum = sum(node.getRight());
        return node.getValue() + leftSum + rightSum;
    }

    private int fullNodeCount(TreeNode node) {
        if (node == null) return 0;
        int count = fullNodeCount(node.getLeft()) + fullNodeCount(node.getRight());
        if (node.getLeft() != null &&  node.getRight() != null) {
            count++;
        }
        return count;
    }

    private boolean isPresent(TreeNode node, int value) {
        if (node == null) return false;
        if (node.getValue() == value) return true;
        boolean left = isPresent(node.getLeft(), value);
        if (left) return true;
        return isPresent(node.getRight(), value);
    }

    private int max(TreeNode node) {
        if (node == null) return Integer.MIN_VALUE;
        int max = node.getValue();
        int leftMax = max(node.getLeft());
        int rightMax = max(node.getRight());
        if (leftMax > max) max = leftMax;
        else if (rightMax > max) max = rightMax;
        return max;
    }

    private TreeNode lca(TreeNode node, int first, int second) {
        if (node == null) return null;
        if (node.getValue() == first || node.getValue() == second) return node;
        TreeNode left = lca(node.getLeft(), first, second);
        TreeNode right = lca(node.getRight(), first, second);
        if (left != null && right != null) return node;
        else if (left != null) return left;
        else return right;
    }

    private void printAllPaths(TreeNode node, ArrayDeque<Integer> stack) {
        if (node == null) return;
        stack.push(node.getValue());
        if (node.getLeft() == null && node.getRight() == null) {
            System.out.println(stack);
            stack.pop();
            return;
        }
        printAllPaths(node.getRight(), stack);
        printAllPaths(node.getLeft(), stack);
        stack.pop();
    }

    private TreeNode toList(TreeNode node) {
        TreeNode head = null, tail = null;
        if (node == null) return null;
        if (node.getLeft() == null && node.getRight() == null) {
            node.setLeft(node);
            node.setRight(node);
            return node;
        }
        if (node.getLeft() != null) {
            head = toList(node.getLeft());
            tail = head.getLeft();
            node.setLeft(tail);
            tail.setRight(node);
        } else {
            head = node;
        }
        if (node.getRight() != null) {
            TreeNode temp = toList(node.getRight());
            tail = temp.getLeft();
            node.setRight(temp);
            temp.setLeft(node);
        } else {
            tail = node;
        }
        head.setLeft(tail);
        tail.setRight(head);
        return head;
    }

    private int count(TreeNode node) {
        if (node == null) return 0;
        else return count(node.getLeft()) + count(node.getRight()) + 1;
    }

    private int leafCount(TreeNode node) {
        if (node == null) return 0;
        else if (node.getLeft() == null && node.getRight() == null) return 1;
        else return count(node.getLeft()) + count(node.getRight());
    }

    private boolean isIdentical(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null) return true;
        else if (node1 == null || node2 == null) return false;
        else return node1.getValue() == node2.getValue()
                    && isIdentical(node1.getLeft(), node2.getLeft())
                    && isIdentical(node1.getRight(), node2.getRight());
    }

    private TreeNode copy(TreeNode node) {
        if (node != null) {
            TreeNode newNode = new TreeNode(node.getValue());
            newNode.setLeft(copy(node.getLeft()));
            newNode.setRight(copy(node.getRight()));
            return newNode;
        } else {
            return null;
        }
    }

    private void nthPreOrder(TreeNode node, int index, int count) {
        if (node != null) {
            count++;
            if (count == index) {
                System.out.println(node.getValue());
            }
            nthPreOrder(node.getLeft(), index, count);
            nthPreOrder(node.getRight(), index, count);
        }
    }

    private void nthPostOrder(TreeNode node, int index, int count) {
        if (node != null) {
            nthPreOrder(node.getLeft(), index, count);
            nthPreOrder(node.getRight(), index, count);
            count++;
            if (count == index) {
                System.out.println(node.getValue());
            }
        }
    }

    private void nthInOrder(TreeNode node, int index, int count) {
        if (node != null) {
            nthPreOrder(node.getLeft(), index, count);
            count++;
            if (count == index) {
                System.out.println(node.getValue());
            }
            nthPreOrder(node.getRight(), index, count);
        }
    }

    private int depth(TreeNode node) {
        if (node == null) return 0;
        else {
            int leftDepth = depth(node.getLeft());
            int rightDepth = depth(node.getRight());
            if (leftDepth > rightDepth) {
                return leftDepth + 1;
            } else {
                return rightDepth + 1;
            }
        }
    }

    private TreeNode createFromArray(int[] array, int start) {
        int size = array.length;
        TreeNode current = new TreeNode(array[start]);
        int left = 2 * start + 1;
        int right = 2 * start + 2;
        if (left < size) {
            current.setLeft(createFromArray(array, left));
        }
        if (right < size) {
            current.setRight(createFromArray(array, right));;
        }
        return current;
    }
}
