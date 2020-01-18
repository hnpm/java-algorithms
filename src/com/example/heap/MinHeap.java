package com.example.heap;

public class MinHeap {
    private static final int CAPACITY = 16;
    private int[] array;
    private int size;
    public MinHeap() {
        array = new int[CAPACITY];
        size = 0;
    }

    public MinHeap(int[] array) {
        size = array.length;
        this.array = new int[size + 1];
        System.arraycopy(array, 0, this.array, 1, array.length);
        for (int i = size / 2; i > 0; i--) {
            percolateDown(i);
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void print() {
        StringBuilder result = new StringBuilder("[ ");
        for (int i = 1; i <= size; i++) {
            result.append(array[i]).append(" ");
        }
        result.append("]");
        System.out.println(result);
    }

    public int peek() {
        if (isEmpty()) throw new IllegalStateException("empty heap");
        return array[1];
    }

    public void add(int value) {
        if (size == array.length - 1) doubleSize();
        array[++size] = value;
        percolateUp(size);
    }

    public int remove() {
        if (isEmpty()) throw new IllegalStateException("empty heap");
        int value = array[1];
        array[1] = array[size];
        size--;
        percolateDown(1);
        return value;
    }

    public static void heapSort(int[] array) {
        MinHeap minHeap = new MinHeap(array);
        for (int i = 0; i < array.length; i++) {
            array[i] = minHeap.remove();
        }
    }

    private void doubleSize() {
        int[] current = array;
        array = new int[array.length * 2];
        System.arraycopy(current, 1, array, 1, size);
    }

    private void percolateDown(int position) {
        int leftChild = 2 * position;
        int rightChild = leftChild + 1;
        int min = -1;
        if (leftChild <= size) min = leftChild;
        if (rightChild <= size && array[rightChild] < array[leftChild]) min = rightChild;
        if (min != -1 && array[min] < array[position]) {
            int temp = array[position];
            array[position] = array[min];
            array[min] = temp;
            percolateDown(min);
        }
    }

    private void percolateUp(int position) {
        int parent = position / 2;
        if (parent == 0) return;
        if (array[parent] < array[position]) {
            int temp = array[position];
            array[position] = array[parent];
            array[parent] = temp;
            percolateUp(parent);
        }
    }
}
