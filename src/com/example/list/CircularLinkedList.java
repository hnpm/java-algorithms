package com.example.list;

public class CircularLinkedList {
    private Node tail;
    private int size;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
    public Node peek() {
        if (isEmpty()) return null;
        return tail.getNext();
    }

    public void addHead(int value) {
        Node newNode = new Node(value);
        if (isEmpty()) {
            newNode.setNext(newNode);
            tail = newNode;
        } else {
            newNode.setNext(tail.getNext());
            tail.setNext(newNode);
        }
        size++;
    }

    public void addTail(int value) {
        Node newNode = new Node(value);
        if (isEmpty()) {
            tail = newNode;
            newNode.setNext(newNode);
        } else {
            newNode.setNext(tail.getNext());
            tail.setNext(newNode);
            tail = newNode;
        }
        size++;
    }

    public int removeHead() {
        if (isEmpty()) throw new IllegalStateException("empty list");
        int value = tail.getNext().getValue();
        if (tail == tail.getNext()) {
            tail = null;
        } else {
            tail.setNext(tail.getNext().getNext());
        }
        size--;
        return value;
    }

    public boolean isPresent(int value) {
        Node current = tail;
        for (int i = 0; i < size; i++) {
            if (current.getValue() == value) return true;
            else current = current.getNext();
        }
        return false;
    }

    public void print() {
        if (isEmpty()) return;
        Node current = tail.getNext();
        StringBuilder result = new StringBuilder("{ ");
        while (current != tail) {
            result.append(current.getValue()).append(", ");
            current = current.getNext();
        }
        result.append(current.getValue()).append(" }");
        System.out.println(result.toString());
    }

    public boolean remove(int value) {
        if (isEmpty()) return false;
        Node head = tail.getNext();
        if (head.getValue() == value) {
            if (head.getNext() == null) {
                tail = null;
            } else {
                tail.setNext(head.getNext());
            }
            size--;
            return true;
        }
        Node prev = tail.getNext();
        Node current = prev.getNext();
        while (current != head) {
            if (current.getValue() == value) {
                if (current == tail) {
                    prev.setNext(tail.getNext());
                    tail = prev;
                } else {
                    prev.setNext(current.getNext());
                }
                size--;
                return true;
            }
            prev = current;
            current = current.getNext();
        }
    return false;
    }

    public CircularLinkedList copy() {
        CircularLinkedList circularLinkedList = new CircularLinkedList();
        Node current = tail.getNext(), head = tail.getNext();
        if (current != null) {
            circularLinkedList.addTail(current.getValue());
            current = current.getNext();
        }
        while (current != head) {
            circularLinkedList.addTail(current.getValue());
            current = current.getNext();
        }
        return circularLinkedList;
    }

    public CircularLinkedList reverseCopy() {
        CircularLinkedList circularLinkedList = new CircularLinkedList();
        Node current = tail.getNext(), head = tail.getNext();
        if (current != null) {
            circularLinkedList.addHead(current.getValue());
            current = current.getNext();
        }
        while (current != head) {
            circularLinkedList.addHead(current.getValue());
            current = current.getNext();
        }
        return circularLinkedList;
    }
}
