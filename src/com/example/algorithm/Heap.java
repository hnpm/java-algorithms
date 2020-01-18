package com.example.algorithm;

import java.util.Collections;
import java.util.PriorityQueue;

public class Heap {
    public static int median(int[] array) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int value: array) {
            if (maxHeap.size() == 0 || maxHeap.peek() >= value) {
                maxHeap.add(value);
            } else {
                minHeap.add(value);
            }
            if (maxHeap.size() > minHeap.size() + 1) {
                minHeap.add(maxHeap.remove());
            }
            if (minHeap.size() > maxHeap.size() + 1) {
                maxHeap.add(minHeap.remove());
            }
        }
        if (maxHeap.size() == 0 && minHeap.size() == 0) {
            return Integer.MAX_VALUE;
        }
        if (maxHeap.size() == minHeap.size()) {
            return (maxHeap.peek() + minHeap.peek()) / 2;
        } else if (maxHeap.size() > minHeap.size()) {
            return maxHeap.peek();
        } else {
            return minHeap.peek();
        }
    }

    public static boolean isMinHeap(int[] array) {
        int size = array.length;
        for (int i = 0; i <= (size - 2) / 2; i++) {
            if (2 * i + 1 < size && array[i] > array[2 * i + 1]) return false;
            if (2 * i + 2 < size && array[i] > array[2 * i +2]) return false;
        }
        return true;
    }

    public static boolean isMaxHeap(int[] array) {
        int size = array.length;
        for (int i = 0; i <= (size - 2) / 2; i++) {
            if (2 * i + 1 < size && array[i] < array[2 * i + 1]) return false;
            if (2 * i + 2 < size && array[i] < array[2 * i +2]) return false;
        }
        return true;
    }
}
