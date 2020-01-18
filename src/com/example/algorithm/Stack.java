package com.example.algorithm;

import java.util.ArrayDeque;
import java.util.Scanner;

public class Stack {
    public static void getMaxProfitStockTrade(int[] stocks) {
        int currentMinIndex = 0, maxProfit = 0, buyIndex = 0, sellIndex = 0;
        for (int i = 0; i < stocks.length; i++) {
            if (stocks[i] < stocks[currentMinIndex]) {
                currentMinIndex = i;
            }
            int profit = stocks[i] - stocks[currentMinIndex];
            if (profit > maxProfit) {
                buyIndex = currentMinIndex;
                sellIndex = i;
                maxProfit = profit;
            }
        }
        System.out.println("Buying date index: " + buyIndex);
        System.out.println("Selling date index: " + sellIndex);
        System.out.println("Max profit: " + maxProfit);
    }

    public static boolean isBalancedParenthesis(String exp) {
        ArrayDeque<Character> stack = new ArrayDeque<>();
        for (char c : exp.toCharArray()) {
            switch (c) {
                case '{':
                case '[':
                case '(':
                    stack.push(c);
                    break;
                case ')':
                    if (stack.pop() != '(') return false;
                    break;
                case ']':
                    if (stack.pop() != '[') return false;
                    break;
                case '}':
                    if (stack.pop() != '{') return false;
                    break;
            }
        }
        return stack.isEmpty();
    }

    public static String infixToPostfix(String exp) {
        char[] output = infixToPostfix(exp.toCharArray());
        return new String(output);
    }

    public static String infixToPrefix(String exp) {
        char[] array = exp.toCharArray();
        reverseString(array);
        replaceParenthesis(array);
        array = infixToPostfix(array);
        reverseString(array);
        return new String(array);
    }

    public static int postfixEvaluate(String exp) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        Scanner tokens = new Scanner(exp);
        while (tokens.hasNext()) {
            if (tokens.hasNextInt()) {
                stack.push(tokens.nextInt());
            } else {
                int firstNumber = stack.pop();
                int secondNumber = stack.pop();
                char operator = tokens.next().charAt(0);
                switch (operator) {
                    case '+':
                        stack.push(firstNumber + secondNumber);
                        break;
                    case '-':
                        stack.push(firstNumber - secondNumber);
                        break;
                    case '*':
                        stack.push(firstNumber * secondNumber);
                        break;
                    case '/':
                        stack.push(firstNumber / secondNumber);
                        break;
                }
            }
        }
        tokens.close();
        return stack.pop();
    }

    private static char[] infixToPostfix(char[] exp) {
        StringBuilder output = new StringBuilder();
        ArrayDeque<Character> stack = new ArrayDeque<>();
        char out;
        for (char c: exp) {
            if (c <= '9' && c >= '0') {
                output.append(c);
            } else {
                switch (c) {
                    case '+':
                    case '-':
                    case '*':
                    case '/':
                    case '%':
                    case '^':
                        while (!stack.isEmpty() && precedence(c) <= precedence(stack.peek())) {
                            out = stack.pop();
                            output.append(" ").append(out);
                        }
                        stack.push(c);
                        output.append(" ");
                        break;
                    case '(':
                        stack.push(c);
                        break;
                    case ')':
                        while (!stack.isEmpty() && (out = stack.pop()) != '(') {
                            output.append(" ").append(out);
                        }
                        break;
                }
            }
        }
        while (!stack.isEmpty()) {
            out = stack.pop();
            output.append(out).append(" ");
        }
        return output.toString().toCharArray();
    }

    private static int precedence(char operator) {
        switch (operator) {
            case '+':
            case '-':
                return 0;
            case '*':
            case '/':
                return 1;
            case '%':
            case '^':
                return 2;
            default:
                return -1;
        }
    }

    private static void replaceParenthesis(char[] exp) {
        for (int i  = 0; i <= exp.length - 1; i++) {
            if (exp[i] == '(') exp[i] = ')';
            else if (exp[i] == ')') exp[i] = '(';
        }
    }

    private static void reverseString(char[] exp) {
        int start = 0, end = exp.length - 1;
        while (start < end) {
            char temp = exp[start];
            exp[start] = exp[end];
            exp[end] = temp;
            start++;
            end--;
        }
    }
}
