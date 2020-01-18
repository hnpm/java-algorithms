package com.example.list;

public class DNode {
    private int value;
    private DNode next;
    private DNode prev;

    public DNode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public DNode getNext() {
        return next;
    }

    public void setNext(DNode next) {
        this.next = next;
    }

    public DNode getPrev() {
        return prev;
    }

    public void setPrev(DNode prev) {
        this.prev = prev;
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }
}
