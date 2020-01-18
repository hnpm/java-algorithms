package com.example.list;

public class DoublyCircularList {
    private DNode head;
    private DNode tail;
    private int size;

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isPresent(int value) {
        if (head == null) return false;
        DNode current = head;
        do {
            if (current.getValue() == value) return true;
            current = current.getNext();
        } while (current != head);
        return false;
    }

    public void print() {
        if (isEmpty()) return;
        StringBuilder result = new StringBuilder("{ ");
        DNode current = head;
        while (current != tail) {
            result.append(current.getValue()).append(", ");
            current = current.getNext();
        }
        result.append(current.getValue()).append(" }");
        System.out.println(result.toString());
    }

    public void addHead(int value) {
        DNode newNode = new DNode(value);
        if (isEmpty()) {
            head = tail = newNode;
            newNode.setNext(newNode);
            newNode.setPrev(newNode);
        } else {
            newNode.setNext(head);
            newNode.setPrev(head.getPrev());
            head.setPrev(newNode);
            newNode.getPrev().setNext(newNode);
            head = newNode;
        }
        size++;
    }

    public void addTail(int value) {
        DNode newNode = new DNode(value);
        if (isEmpty()) {
            head = tail = newNode;
            newNode.setNext(newNode);
            newNode.setPrev(newNode);
        } else {
            newNode.setNext(tail.getNext());
            newNode.setPrev(tail);
            tail.setNext(newNode);
            newNode.getNext().setPrev(newNode);
            tail = newNode;
        }
        size++;
    }

    public int removeHead() {
        if (isEmpty()) throw new IllegalStateException("empty list");
        int value = head.getValue();
        size--;
        if (isEmpty()) {
            head = tail = null;
        } else {
            DNode next = head.getNext();
            next.setPrev(tail);
            tail.setNext(next);
            head = next;
        }
        return value;
    }

    public int removeTail() {
        if (isEmpty()) throw new IllegalStateException("empty list");
        int value = tail.getValue();
        size--;
        if (isEmpty()) {
            head = tail = null;
        } else {
            DNode prev = tail.getPrev();
            prev.setNext(head);
            head.setPrev(prev);
            tail = prev;
        }
        return value;
    }
}
