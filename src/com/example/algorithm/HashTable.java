package com.example.algorithm;

import com.example.hashTable.CountMap;

import java.util.HashSet;

public class HashTable {
    public static boolean isAnagram(char[] str1, char[] str2) {
        if (str1.length != str2.length) return false;
        CountMap<Character> countMap = new CountMap<>();
        for (char c: str1) {
            countMap.add(c);
        }
        for (char c: str2) {
            countMap.remove(c);
        }
        return countMap.size() == 0;
    }

    public static String removeDuplicate(char[] str) {
        HashSet<Character> set = new HashSet<>();
        int index = 0;
        for (char c: str) {
            if (!set.contains(c)) {
                str[index++] = c;
                set.add(c);
            }
        }
        return new String(str, 0, index);
    }

    public static void printRepeating(int[] array) {
        HashSet<Integer> set = new HashSet<>();
        for (int value: array) {
            if (set.contains(value)) {
                System.out.println(value);
            } else {
                set.add(value);
            }
        }
    }

    public static void printFirstRepeating(int[] array) {
        CountMap<Integer> map = new CountMap<>();
        for (int value: array) {
            map.add(value);
        }
        for (int value: array) {
            map.remove(value);
            if (map.containsKey(value)){
                System.out.println(value);
                return;
            }
        }
    }
}
