package com.example.stack;

import com.example.list.Node;

public class Stack {
    private Node head;
    private int size;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int peek() {
        if (isEmpty()) throw new IllegalStateException("empty stack");
        return head.getValue();
    }

    public void push(int value) {
        head = new Node(value, head);
        size++;
    }

    public int pop() {
        if (isEmpty()) throw new IllegalStateException("empty stack");
        int value = head.getValue();
        head = head.getNext();
        size--;
        return value;
    }

    public void print() {
        StringBuilder result = new StringBuilder("[ ");
        Node temp = head;
        while (temp != null) {
            result.append(temp.getValue()).append(" ");
            temp = temp.getNext();
        }
        result.append("]");
        System.out.println(result);
    }

    public void insertAtBottom(int value) {
        if (isEmpty()) push(value);
        else {
            int temp = pop();
            insertAtBottom(value);
            push(temp);
        }
    }

    public void reverse() {
        if (!isEmpty()) {
            int value = pop();
            reverse();
            insertAtBottom(value);
        }
    }
}
