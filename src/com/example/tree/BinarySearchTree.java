package com.example.tree;

public class BinarySearchTree {
    private TreeNode root;

    public BinarySearchTree() {
        root = null;
    }

    public BinarySearchTree(int[] array) {
        root = createFromSortedArray(array, 0, array.length - 1);
    }

    public boolean find(int value) {
        TreeNode current = root;
        while (current != null) {
            if (current.getValue() == value) {
                return true;
            } else if (current.getValue() > value) {
                current = current.getLeft();
            } else {
                current = current.getRight();
            }
        }
        return false;
    }

    public void insert(int value) {
        root = insert(root, value);
    }

    public void delete(int data) {
        TreeNode current = this.root;
        TreeNode parent = this.root;
        boolean isLeftChild = false;
        if (current == null) return;
        while (current != null && current.getValue() != data) {
            parent = current;
            if (data < current.getValue()) {
                current = current.getLeft();
                isLeftChild = true;
            } else {
                current = current.getRight();
                isLeftChild = false;
            }
        }
        if (current == null) return;
        if (current.getLeft() == null && current.getRight() == null) {
            if (current == this.root) {
                this.root = null;
            } else {
                if (isLeftChild) {
                    parent.setLeft(null);
                } else {
                    parent.setRight(null);
                }
            }
        } else if (current.getRight() == null) {
            if (current == this.root) {
                this.root = current.getLeft();
            } else {
                if (isLeftChild) {
                    parent.setLeft(current.getLeft());
                } else {
                    parent.setRight(current.getLeft());
                }
            }
        } else if (current.getLeft() == null) {
            if (current == this.root) {
                this.root = current.getRight();
            } else {
                if (isLeftChild) {
                    parent.setLeft(current.getRight());
                } else {
                    parent.setRight(current.getRight());
                }
            }
        } else {
            TreeNode successorParent = current.getRight();
            TreeNode successor = current.getRight();
            while (successor.getLeft() != null) {
                successorParent = successor;
                successor = successor.getLeft();
            }
            successorParent.setLeft(successor.getRight());
            if (isLeftChild) {
                parent.setLeft(successor);
            } else {
                parent.setRight(successor);
            }
            successor.setLeft(current.getLeft());
            successor.setRight(current.getRight());
        }
    }

    public void traverseInOrder() {
        traverseInOrder(this.root);
    }

    public int min() {
        TreeNode current = root;
        if (current == null) throw new IllegalStateException("empty tree");
        while (current.getLeft() != null) {
            current = current.getLeft();
        }
        return current.getValue();
    }

    public int max() {
        TreeNode current = root;
        if (current == null) throw new IllegalStateException("empty tree");
        while (current.getRight() != null) {
            current = current.getRight();
        }
        return current.getValue();
    }

    public boolean isBST() {
        return isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public void trimOutsideRange(int min, int max) {
        trimOutsideRange(root, min, max);
    }

    public void printInRange(int min, int max) {
        printInRange(root, min, max);
    }

    public int floor(int value) {
        int floor = Integer.MAX_VALUE;
        TreeNode current = root;
        while (current != null) {
            if (current.getValue() == value) {
                floor = current.getValue();
                break;
            } else if (current.getValue() > value) {
                current = current.getLeft();
            } else {
                floor = current.getValue();
                current = current.getRight();
            }
        }
        return floor;
    }

    public int ceil(int value) {
        int ceil = Integer.MIN_VALUE;
        TreeNode current = root;
        while (current != null) {
            if (current.getValue() == value) {
                ceil = current.getValue();
                break;
            } else if (current.getValue() < value) {
                current = current.getRight();
            } else {
                ceil = current.getValue();
                current =current.getLeft();
            }
        }
        return ceil;
    }

    private void printInRange(TreeNode node, int min, int max) {
        if (node == null) return;
        printInRange(node.getLeft(), min, max);
        if (node.getValue() >= min && node.getValue() <= max) {
            System.out.println(node.getValue());
        }
        printInRange(node.getRight(), min, max);
    }
    private boolean isBST(TreeNode node) {
        if (node == null) {
            return true;
        }
        if (node.getLeft() != null && node.getLeft().max() > node.getValue()) {
            return false;
        }
        if (node.getRight() != null && node.getRight().max() <= node.getValue()) {
            return false;
        }
        return isBST(node.getLeft()) && isBST(node.getRight());
    }

    private boolean isBST(TreeNode node, int min, int max) {
        if (node == null) return true;
        if (node.getValue() < min || node.getValue() > max) return false;
        return isBST(node.getLeft(), min, node.getValue()) && isBST(node.getRight(), node.getValue(), max);
    }

    private void traverseInOrder(TreeNode node) {
        if (node != null) {
            traverseInOrder(node.getLeft());
            System.out.println(node.getValue() + " ");
            traverseInOrder(node.getRight());
        }
    }

    private TreeNode insert(TreeNode node, int value) {
        if (node == null) {
            node = new TreeNode(value);
        } else {
            if (node.getValue() > value) {
                node.setLeft(insert(node.getLeft(), value));
            } else {
                node.setRight(insert(node.getRight(), value));
            }
        }
        return node;
    }

    private TreeNode trimOutsideRange(TreeNode node, int min, int max) {
        if (node == null) return null;
        node.setLeft(trimOutsideRange(node.getLeft(), min, max));
        node.setRight(trimOutsideRange(node.getRight(), min, max));
        if (node.getValue() < min) return node.getRight();
        if (node.getValue() > max) return node.getLeft();
        return node;
    }

    private TreeNode createFromSortedArray(int[] array, int start, int end) {
        if (start > end) return null;
        int middle = (start + end) / 2;
        TreeNode node = new TreeNode(array[middle]);
        node.setLeft(createFromSortedArray(array, start, middle - 1));
        node.setRight(createFromSortedArray(array, middle + 1, end));
        return node;
    }

}