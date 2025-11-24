package org.utils;

import java.util.Stack;
import java.util.HashMap;
import java.util.Map;

/**
 * Utility class containing various functions related to stack data structures and
 * LIFO (Last-In, First-Out) algorithms.
 */
public final class StackUtils {

    // Private constructor to prevent instantiation
    private StackUtils() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    // --- Basic Stack Operations ---

    /**
     * 1. Reverses a stack in place using only the stack operations (push, pop, empty).
     * This recursive implementation is a classic way to demonstrate stack manipulation.
     *
     * @param stack The stack to reverse.
     */
    public static <T> void reverseStack(Stack<T> stack) {
        if (stack == null || stack.isEmpty()) {
            return;
        }

        // Hold all elements but the bottom one
        T top = stack.pop();

        // Recursively reverse the rest of the stack
        reverseStack(stack);

        // Insert the held element at the bottom of the reversed stack
        insertAtBottom(stack, top);
    }

    /**
     * Helper function for reverseStack: Inserts an element at the bottom of the stack.
     */
    private static <T> void insertAtBottom(Stack<T> stack, T item) {
        if (stack.isEmpty()) {
            stack.push(item);
            return;
        }

        T top = stack.pop();
        insertAtBottom(stack, item);
        stack.push(top);
    }

    /**
     * 2. Checks if the given expression string has balanced parentheses, brackets, and braces.
     * Uses a stack to keep track of opening delimiters.
     *
     * @param expression The input string containing parentheses.
     * @return true if the expression is balanced, false otherwise.
     */
    public static boolean isBalanced(String expression) {
        if (expression == null) {
            return true;
        }

        Stack<Character> stack = new Stack<>();
        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put('}', '{');
        map.put(']', '[');

        for (char c : expression.toCharArray()) {
            if (map.containsValue(c)) { // It's an opening delimiter
                stack.push(c);
            } else if (map.containsKey(c)) { // It's a closing delimiter
                if (stack.isEmpty() || stack.pop() != map.get(c)) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    /**
     * 3. Sorts a stack in ascending order (smallest element at the top) using an auxiliary stack.
     *
     * @param inputStack The stack to be sorted.
     */
    public static <T extends Comparable<T>> void sortStack(Stack<T> inputStack) {
        if (inputStack == null || inputStack.isEmpty()) {
            return;
        }

        Stack<T> auxiliaryStack = new Stack<>();

        while (!inputStack.isEmpty()) {
            T current = inputStack.pop();

            // Move elements from auxiliaryStack back to inputStack if they are smaller (to maintain ascending order in auxiliary)
            while (!auxiliaryStack.isEmpty() && auxiliaryStack.peek().compareTo(current) < 0) {
                inputStack.push(auxiliaryStack.pop());
            }

            auxiliaryStack.push(current);
        }

        // Transfer sorted elements back to inputStack (smallest is now at the bottom of auxiliaryStack)
        while (!auxiliaryStack.isEmpty()) {
            inputStack.push(auxiliaryStack.pop());
        }
    }

    // --- Expression and Calculation Functions ---

    /**
     * 4. Evaluates a postfix (Reverse Polish Notation) expression.
     * Assumes single-digit operands and simple operators (+, -, *, /).
     *
     * @param postfix The postfix expression string (space-separated).
     * @return The integer result of the evaluation.
     * @throws IllegalArgumentException if the expression is invalid.
     */
    public static int evaluatePostfix(String postfix) {
        if (postfix == null || postfix.isEmpty()) {
            throw new IllegalArgumentException("Expression cannot be null or empty.");
        }

        Stack<Integer> stack = new Stack<>();
        String[] tokens = postfix.split("\\s+");

        for (String token : tokens) {
            if (token.matches("\\d+")) { // Operand
                stack.push(Integer.parseInt(token));
            } else if (token.matches("[+\\-*/]")) { // Operator
                if (stack.size() < 2) {
                    throw new IllegalArgumentException("Invalid postfix expression: not enough operands.");
                }
                int operand2 = stack.pop();
                int operand1 = stack.pop();

                int result = switch (token) {
                    case "+" -> operand1 + operand2;
                    case "-" -> operand1 - operand2;
                    case "*" -> operand1 * operand2;
                    case "/" -> {
                        if (operand2 == 0) throw new IllegalArgumentException("Division by zero.");
                        yield operand1 / operand2;
                    }
                    default -> throw new IllegalArgumentException("Invalid operator: " + token);
                };
                stack.push(result);
            } else if (!token.isEmpty()) {
                throw new IllegalArgumentException("Invalid token in expression: " + token);
            }
        }

        if (stack.size() != 1) {
            throw new IllegalArgumentException("Invalid postfix expression: too many operands.");
        }
        return stack.pop();
    }

    /**
     * 5. Implements the Next Greater Element (NGE) logic for an array.
     * For each element, finds the first element to its right that is greater than it.
     *
     * @param arr The input array.
     * @return An array where result[i] is the NGE of arr[i], or -1 if none exists.
     */
    public static int[] nextGreaterElement(int[] arr) {
        if (arr == null || arr.length == 0) {
            return new int[0];
        }

        int n = arr.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>(); // Stores indices

        for (int i = n - 1; i >= 0; i--) {
            // While the element at the index on the stack is smaller than the current element, pop it
            while (!stack.isEmpty() && arr[stack.peek()] <= arr[i]) {
                stack.pop();
            }

            // The top of the stack is the NGE
            if (stack.isEmpty()) {
                result[i] = -1;
            } else {
                result[i] = arr[stack.peek()];
            }

            // Push the current element's index onto the stack
            stack.push(i);
        }

        return result;
    }

    /**
     * 6. Finds the length of the longest valid (balanced) parenthesis substring.
     *
     * @param s The input string containing '(' and ')'.
     * @return The length of the longest valid substring.
     */
    public static int longestValidParentheses(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }

        Stack<Integer> stack = new Stack<>();
        stack.push(-1); // Base index for the start of potential valid substrings
        int maxLength = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else { // s.charAt(i) == ')'
                stack.pop();
                if (stack.isEmpty()) {
                    // Current ')' closes a segment, but a new segment starts after this index
                    stack.push(i);
                } else {
                    // Valid match found: distance between current index and new top of stack
                    maxLength = Math.max(maxLength, i - stack.peek());
                }
            }
        }
        return maxLength;
    }

    /**
     * 7. Converts an infix expression to a postfix (Reverse Polish Notation) expression.
     * Supports +, -, *, /, and parentheses.
     *
     * @param infix The infix expression string (e.g., "A + B * C").
     * @return The postfix expression string (space-separated).
     */
    public static String infixToPostfix(String infix) {
        if (infix == null) return "";

        Map<Character, Integer> precedence = new HashMap<>();
        precedence.put('+', 1);
        precedence.put('-', 1);
        precedence.put('*', 2);
        precedence.put('/', 2);

        Stack<Character> stack = new Stack<>();
        StringBuilder postfix = new StringBuilder();

        for (char c : infix.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                postfix.append(c).append(" ");
            } else if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    postfix.append(stack.pop()).append(" ");
                }
                if (!stack.isEmpty() && stack.peek() == '(') {
                    stack.pop(); // Pop the '('
                }
            } else if (precedence.containsKey(c)) { // Operator
                while (!stack.isEmpty() && stack.peek() != '(' && precedence.get(stack.peek()) >= precedence.get(c)) {
                    postfix.append(stack.pop()).append(" ");
                }
                stack.push(c);
            }
        }

        while (!stack.isEmpty()) {
            postfix.append(stack.pop()).append(" ");
        }

        return postfix.toString().trim();
    }

    /**
     * 8. Finds the immediate element at the middle of the stack.
     * The stack remains unchanged (using recursion and temporary storage).
     *
     * @param stack The stack to inspect.
     * @return The middle element, or null if the stack is null or empty.
     */
    public static <T> T findMiddleElement(Stack<T> stack) {
        if (stack == null || stack.isEmpty()) {
            return null;
        }

        int size = stack.size();
        if (size == 1) {
            return stack.peek();
        }

        int middleIndex = size / 2; // For even size, this is the element below the exact middle line.

        Stack<T> tempStack = new Stack<>();
        T middleElement = null;

        // Pop elements until the middle element is reached (0-based index)
        for (int i = 0; i < size; i++) {
            T item = stack.pop();
            if (i == size - 1 - middleIndex) { // Check from the bottom up
                middleElement = item;
            }
            tempStack.push(item);
        }

        // Restore the original stack
        while (!tempStack.isEmpty()) {
            stack.push(tempStack.pop());
        }

        return middleElement;
    }

    /**
     * 9. Checks if one stack is a permutation of another stack.
     * Requires both stacks to have the same size and contain the same elements.
     * Destroys both stacks in the process (demonstrative).
     *
     * @param stack1 The first stack.
     * @param stack2 The second stack.
     * @return true if stack1 is a permutation of stack2, false otherwise.
     */
    public static <T> boolean isPermutation(Stack<T> stack1, Stack<T> stack2) {
        if (stack1 == null || stack2 == null) {
            return false;
        }

        if (stack1.size() != stack2.size()) {
            return false;
        }

        if (stack1.isEmpty()) {
            return true;
        }

        Map<T, Integer> counts = new HashMap<>();

        while (!stack1.isEmpty()) {
            T item = stack1.pop();
            counts.put(item, counts.getOrDefault(item, 0) + 1);
        }

        while (!stack2.isEmpty()) {
            T item = stack2.pop();
            int count = counts.getOrDefault(item, 0);
            if (count == 0) {
                return false;
            }
            counts.put(item, count - 1);
        }

        // After processing both, all counts should be zero (implicitly checked)
        // If stack sizes were equal and we haven't returned false, it's a permutation.
        return true;
    }

    /**
     * 10. Removes all occurrences of a specific item from the stack.
     * Preserves the relative order of the remaining elements.
     *
     * @param stack The stack to modify.
     * @param itemToRemove The element to remove.
     */
    public static <T> void removeAllOccurrences(Stack<T> stack, T itemToRemove) {
        if (stack == null || stack.isEmpty()) {
            return;
        }

        Stack<T> tempStack = new Stack<>();

        // Transfer elements to tempStack, skipping the itemToRemove
        while (!stack.isEmpty()) {
            T item = stack.pop();
            if (!item.equals(itemToRemove)) {
                tempStack.push(item);
            }
        }

        // Transfer elements back to the original stack to restore order
        while (!tempStack.isEmpty()) {
            stack.push(tempStack.pop());
        }
    }
}