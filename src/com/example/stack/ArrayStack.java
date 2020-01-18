package com.example.stack;

public class ArrayStack {
    private static final int MIN_CAPACITY = 1000;
    private int[] data;
    private int top;
    private int capacity;

    public ArrayStack(int capacity) {
        data = new int[capacity];
        top = -1;
        this.capacity = capacity;
    }

    public ArrayStack() {
        data = new int[MIN_CAPACITY];
        top = -1;
        capacity = MIN_CAPACITY;
    }


    public int size() {
        return top + 1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public void print() {
        StringBuilder result = new StringBuilder("[ ");
        for (int i = 0; i <= top; i++) {
            result.append(data[i]).append(" ");
        }
        result.append("]");
        System.out.println(result);
    }

    public void push(int value) {
        if (size() == capacity) {
            int[] newData = new int[capacity * 2];
            System.arraycopy(data, 0, newData, 0, capacity);
            data = newData;
            capacity = capacity * 2;
        }
        top++;
        data[top] = value;
    }

    public int pop() {
        if (isEmpty()) throw new IllegalStateException("empty stack");
        int value = data[top];
        top--;
        if (size() == capacity / 2 && capacity > MIN_CAPACITY) {
            capacity /= 2;
            int[] newData = new int[capacity];
            System.arraycopy(data, 0, newData, 0, capacity);
            data = newData;
        }
        return value;
    }

    public int top() {
        if (isEmpty()) throw new IllegalStateException("empty stack");
        return data[top];
    }
}
