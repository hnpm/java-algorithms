package com.example.queue;

import com.example.list.Node;

import java.util.ArrayDeque;

public class Queue {
    private Node head, tail;
    private int size;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void print() {
        StringBuilder result = new StringBuilder("{ ");
        Node temp = head;
        while (temp != null) {
            result.append(temp.getValue()).append(" ");
            temp = temp.getNext();
        }
        result.append("}");
        System.out.println(result);
    }

    public int peek() {
        if (isEmpty()) throw new IllegalStateException("empty queue");
        return head.getValue();
    }

    public void enqueue(int value) {
        Node newNode = new Node(value);
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.setNext(newNode);
            tail = newNode;
        }
        size++;
    }

    public int dequeue() {
        if (isEmpty()) throw new IllegalStateException("empty queue");
        int value = head.getValue();
        head = head.getNext();
        size--;
        return value;
    }

    public void reverse() {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        while (!isEmpty()) {
            stack.push(dequeue());
        }
        while (!stack.isEmpty()) {
            enqueue(stack.pop());
        }
    }
}
