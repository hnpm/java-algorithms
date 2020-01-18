package com.example.list;

public class DoublyLinkedList {
    private DNode head;
    private DNode tail;
    private int size;

    public boolean isEmpty() {
        return size == 0;
    }

    public DNode peek() {
        if (isEmpty()) return null;
        else return head;
    }

    public void print() {
        StringBuilder result = new StringBuilder("{ ");
        DNode current = this.head;
        while (current != null) {
            result.append(current.toString()).append(", ");
            current = current.getNext();
        }
        result.append("}");
        System.out.println(result.toString());
    }

    public void addHead(int value) {
        DNode newNode = new DNode(value);
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            head.setPrev(newNode);
            newNode.setNext(head);
            head = newNode;
        }
        size++;
    }

    public void addTail(int value) {
        DNode newNode = new DNode(value);
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            newNode.setPrev(tail);
            tail.setNext(newNode);
            tail = newNode;
        }
        size++;
    }

    public int removeHead() {
        if (isEmpty()) throw new IllegalStateException("empty list");
        int value = head.getValue();
        head = head.getNext();
        if (head == null) {
            tail = null;
        } else {
            head.setPrev(null);
        }
        size--;
        return value;
    }

    public boolean remove(int value) {
        if (isEmpty()) return false;
        DNode current = head;
        if (current.getValue() == value) {
            removeHead();
            return true;
        }
        while (current.getNext() != null) {
            if (current.getNext().getValue() == value) {
                current.setNext(current.getNext().getNext());
                if (current.getNext() == null) {
                    tail = current;
                } else {
                    current.getNext().setPrev(current);
                }
                size--;
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    public boolean isPresent(int value) {
        DNode current = head;
        while (current != null) {
            if (current.getValue() == value) return true;
            current = current.getNext();
        }
        return false;
    }

    public void reverse() {
        DNode current = head;
        while (current != null) {
            DNode temp = current.getNext();
            current.setNext(current.getPrev());
            current.setPrev(temp);
            if (current.getPrev() == null) {
                tail = head;
                head = current;
                return;
            }
            current = current.getPrev();
        }
    }

    public DoublyLinkedList copy() {
        DoublyLinkedList doublyLinkedList = new DoublyLinkedList();
        DNode current = head;
        while (current != null) {
            doublyLinkedList.addTail(current.getValue());
            current = current.getNext();
        }
        return doublyLinkedList;
    }

    public DoublyLinkedList reverseCopy() {
        DoublyLinkedList doublyLinkedList = new DoublyLinkedList();
        DNode current = head;
        while (current != null) {
            doublyLinkedList.addHead(current.getValue());
            current = current.getNext();
        }
        return doublyLinkedList;
    }

    public void sortedInsert(int value) {
        DNode newNode = new DNode(value);
        if (head == null) {
            head = tail = newNode;
            return;
        }
        if (head.getValue() >= value) {
            newNode.setNext(head);
            head.setPrev(newNode);
            head = newNode;
            return;
        }
        DNode current = head;
        while (current.getNext() != null && current.getNext().getValue() < value) {
            current = current.getNext();
        }
        if (current.getNext() == null) {
            tail = newNode;
            current.setNext(newNode);
            newNode.setPrev(current);
        } else {
            newNode.setPrev(current);
            newNode.setNext(current.getNext());
            current.setNext(newNode);
            newNode.getNext().setPrev(newNode);
        }
        size++;
    }

    public void sortedRemoveDuplicate() {
        DNode current = head;
        while (current != null) {
            if (current.getNext() != null && current.getValue() == current.getNext().getValue()) {
                DNode toDelete = current.getNext();
                current.getNext().setPrev(current);
                current.setNext(toDelete.getNext());
                if (toDelete == tail) {
                    tail = current;
                }
                size--;
            } else {
                current = current.getNext();
            }
        }
    }
}
