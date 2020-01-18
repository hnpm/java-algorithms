package com.example.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Array {
    public static boolean iterativeBinarySearch(int[] array, int value) {
        int start = 0, end = array.length - 1, middle;
        while (start <= end) {
            middle = start + (end - start) / 2;
            if (array[middle] == value) {
                return true;
            } else if (array[middle] < value){
                start = middle + 1;
            } else {
                end = middle - 1;
            }
        }
        return false;
    }

    public static boolean recursiveBinarySearch(int[] array, int value) {
        return recursiveBinarySearch(array, 0, array.length - 1, value);
    }

    private static boolean recursiveBinarySearch(int[] array, int start, int end, int value) {
        if (start > end) return false;
        int middle = start + (end - start) / 2;
        if (array[middle] == value) {
            return true;
        } else if (array[middle] < value) {
            return recursiveBinarySearch(array, middle + 1, end, value);
        } else {
            return recursiveBinarySearch(array, start, middle - 1, value);
        }
    }

    // Moore's Voting Algorithm
    public static int getMajority(int[] array) {
        int index = 0, count = 1;
        for (int i = 1; i < array.length; i++) {
            if (array[i] == array[index]) {
                count++;
            } else {
                count--;
            }
            if (count == 0) {
                index = i;
                count = 1;
            }
        }
        for (int value : array) {
            if (value == array[index]) {
                count++;
            }
        }
        if (count > array.length / 2) {
            return array[index];
        } else {
            return Integer.MIN_VALUE;
        }
    }

    public static void findPairWithMinimumAbsoluteSum(int[] array) {
        if (array.length < 2) {
            System.out.println("Invalid input");
            return;
        }
        Arrays.sort(array);
        int minFirst = 0, minSecond = array.length - 1, minSum = Math.abs(array[minFirst] + array[minSecond]);
        int i = 0, j = array.length - 1;
        while (i < j) {
            int sum = array[i] + array[j];
            if (Math.abs(sum) < minSum) {
                minSum = Math.abs(sum);
                minFirst = i;
                minSecond = j;
            }
            if (sum < 0) {
                i++;
            } else if (sum > 0) {
                j--;
            } else {
                break;
            }
        }
        System.out.println("The two elements with minimum absolute sum are: " + array[minFirst] + " and " + array[minSecond]);
    }

    public static int findOccurrences(int[] array, int value) {
        Arrays.sort(array);
        int firstIndex = findFirstIndex(array, 0, array.length - 1, value);
        if (firstIndex == -1) return 0;
        int lastIndex = findLastIndex(array, 0, array.length - 1, value);
        return lastIndex - firstIndex + 1;
    }

    public static int[] separateOddAndEven(int[] array) {
        int i = 0, j = array.length - 1;
        while (i < j) {
            if (array[i] % 2 == 0) {
                i++;
            } else if (array[j] % 2 == 1) {
                j--;
            } else {
                swap(array, i, j);
                i++;
                j--;
            }
        }
        return array;
    }

    public static int binarySearch(int[] array, int value) {
        boolean isAscending = array[0] < array[array.length - 1];
        return binarySearch(array, 0, array.length - 1, value, isAscending);
    }

    public static boolean checkPermutation(int[] array1, int[] array2) {
        if (array1.length != array2.length) return false;
        List<Integer> list = new ArrayList<>(array1.length);
        for (int value: array1) {
            list.add(value);
        }
        for (int value: array2) {
            if (!list.contains(value)) {
                return false;
            }
            list.remove((Integer) value);
        }
        return true;
    }

    public static boolean searchSorted2D(int[][] array, int row, int column, int value) {
        int i = 0, j = column - 1;
        while (i < row && j >= 0) {
            if (array[i][j] == value) {
                return true;
            } else if (array[i][j] < value) {
                i++;
            } else {
                j--;
            }
        }
        return false;
    }

    public static int findMaxIndexBitonic(int[] array) {
        int start = 0, end = array.length - 1;
        int maxIndex = -1;
        while (start <= end) {
            int middle = (start + end) / 2;
            if (array[middle - 1] < array[middle] && array[middle] > array[middle + 1]) {
                maxIndex =  middle;
                break;
            } else if (array[middle - 1] < array[middle] && array[middle] < array[middle + 1]) {
                start = middle + 1;
            } else if (array[middle - 1] > array[middle] && array[middle] > array[middle + 1]) {
                end = middle - 1;
            } else {
                break;
            }
        }
        return maxIndex;
    }

    public static int searchBitonic(int[] array, int value) {
        int maxIndex = findMaxIndexBitonic(array);
        int result = Array.binarySearch(array, 0, maxIndex, value, true);
        if (result == -1) {
            result = Array.binarySearch(array, maxIndex + 1, array.length - 1, value, false);
        }
        return result;
    }

    private static int findFirstIndex(int[] array, int start, int end, int value) {
        if (start > end) {
            return -1;
        }
        int middle = (start + end) / 2;
        if (array[middle] == value && (middle == start || array[middle - 1] != value)) {
            return middle;
        } else if (array[middle] >= value) {
            return findFirstIndex(array, start,middle - 1, value);
        } else {
            return findFirstIndex(array, middle + 1, end, value);
        }
    }

    private static int findLastIndex(int[] array, int start, int end, int value) {
        if (start > end) {
            return -1;
        }
        int middle = (start + end) / 2;
        if (array[middle] == value && (middle == end || array[middle + 1] != value)) {
            return middle;
        } else if (array[middle] > value) {
            return findLastIndex(array, start,middle - 1, value);
        } else {
            return findLastIndex(array, middle + 1, end, value);
        }
    }

    private static int binarySearch(int[] array, int start, int end, int value, boolean isAscending) {
        if (start > end) {
            return -1;
        }
        int middle = (start + end) / 2;
        if (array[middle] == value) {
            return middle;
        }
        if (isAscending && array[middle] > value || !isAscending && array[middle] < value) {
            return binarySearch(array, start, middle - 1, value, isAscending);
        } else {
            return binarySearch(array, middle + 1,end, value, isAscending);
        }
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
