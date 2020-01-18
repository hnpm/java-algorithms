package com.example.algorithm;

public class TowerOfHanoi {
    public static void move(int numberOfDiscs, String from, String to, String inter) {
        if (numberOfDiscs == 1) {
            System.out.println("Moving disc " + numberOfDiscs + " from " + from + " to " + to);
        } else {
            move(numberOfDiscs - 1, from, inter, to);
            System.out.println("Moving disc " + numberOfDiscs + " from " + from + " to " + to);
            move(numberOfDiscs - 1, inter, to, from);
        }
    }
}
