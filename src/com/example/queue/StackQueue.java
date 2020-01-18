package com.example.queue;

import java.util.ArrayDeque;

public class StackQueue {
    private ArrayDeque<Integer> stack1;
    private ArrayDeque<Integer> stack2;

    public StackQueue() {
        stack1 = new ArrayDeque<>();
        stack2 = new ArrayDeque<>();
    }

    public void enqueue(int value) {
        stack1.push(value);
    }

    public int dequeue() {
        if (!stack2.isEmpty()) return stack2.pop();
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }
        return stack2.pop();
    }
}
