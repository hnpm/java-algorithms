package com.example.hashTable;

import java.util.HashMap;

public class CountMap<T> {
    private HashMap<T, Integer> map = new HashMap<>();

    public void add(T key) {
        if (map.containsKey(key)) {
            int count = map.get(key);
            map.put(key, ++count);
        } else {
            map.put(key, 1);
        }
    }

    public void remove(T key) {
        if (map.containsKey(key)) {
            if (map.get(key) == 1) {
                map.remove(key);
            } else {
                int count = map.get(key);
                map.put(key, --count);
            }
        }
    }

    public int get(T key) {
        return map.getOrDefault(key, 0);
    }

    public boolean containsKey(T key) {
        return map.containsKey(key);
    }

    public int size() {
        return map.size();
    }
}
