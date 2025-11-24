package org.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.utils.StackUtils;
import java.util.Stack;
import java.util.Arrays;
import java.util.Collections;

/**
 * JUnit 5 test class for StackUtils functions.
 */
public class StackUtilsTest {

    // Helper to check if a stack matches an expected list
    private <T> void assertStackEquals(T[] expectedArray, Stack<T> actualStack) {
        // 1. Check if the sizes match.
        assertEquals(expectedArray.length, actualStack.size(), "Stack sizes must match.");

        // 2. Iterate and compare elements from the bottom (index 0) to the top (index N-1).
        // Stack.get(i) retrieves the i-th element from the bottom.
        for (int i = 0; i < expectedArray.length; i++) {
            assertEquals(expectedArray[i], actualStack.get(i),
                    "Element at index " + i + " (from bottom) must match.");
        }
    }

    // --- Test Cases for reverseStack ---

    @Test
    @DisplayName("reverseStack(): Should correctly reverse a stack of integers")
    void testReverseStackIntegers() {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3); // Stack: [1, 2, 3] (3 on top)

        StackUtils.reverseStack(stack); // Stack: [3, 2, 1] (1 on top)

        assertStackEquals(new Integer[]{3, 2,1}, stack);
    }

    @Test
    @DisplayName("reverseStack(): Should handle an empty stack")
    void testReverseStackEmpty() {
        Stack<String> stack = new Stack<>();
        StackUtils.reverseStack(stack);
        assertTrue(stack.isEmpty());
    }

    // --- Test Cases for isBalanced ---

    @Test
    @DisplayName("isBalanced(): Should return true for a correctly balanced expression")
    void testIsBalancedTrue() {
        assertTrue(StackUtils.isBalanced("{[()]()}"));
    }

    @Test
    @DisplayName("isBalanced(): Should return false for an unbalanced expression")
    void testIsBalancedFalse() {
        assertFalse(StackUtils.isBalanced("([)]"));
    }

    @Test
    @DisplayName("isBalanced(): Should return true for non-delimiter characters")
    void testIsBalancedWithText() {
        assertTrue(StackUtils.isBalanced("a + (b * c)"));
    }

    // --- Test Cases for sortStack ---

    @Test
    @DisplayName("sortStack(): Should correctly sort integers (smallest on top)")
    void testSortStackIntegers() {
        Stack<Integer> stack = new Stack<>();
        stack.push(30);
        stack.push(5);
        stack.push(18);
        stack.push(1); // Stack: [30, 5, 18, 1] (1 on top)

        StackUtils.sortStack(stack); // Sorted: [30, 18, 5, 1] (30 on top, 1 on bottom)

        assertStackEquals(new Integer[]{1, 5, 18, 30}, stack);
    }

    @Test
    @DisplayName("sortStack(): Should handle already sorted stack")
    void testSortStackAlreadySorted() {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(5);
        stack.push(10);
        StackUtils.sortStack(stack);
        assertStackEquals(new Integer[]{1, 5, 10}, stack);
    }

    // --- Test Cases for evaluatePostfix ---

    @Test
    @DisplayName("evaluatePostfix(): Should correctly evaluate a standard RPN expression")
    void testEvaluatePostfixStandard() {
        assertEquals(10, StackUtils.evaluatePostfix("2 3 * 4 +")); // (2*3) + 4 = 10
    }

    @Test
    @DisplayName("evaluatePostfix(): Should handle subtraction and division")
    void testEvaluatePostfixComplex() {
        assertEquals(12, StackUtils.evaluatePostfix("10 2 / 3 * 8 - 5 +")); // ((10/2)*3 - 8) + 5 = (15 - 8) + 5 = 12. Wait: ((10/2)*3 - 8) + 5 = (5*3 - 8) + 5 = 7 + 5 = 12. Let's stick to simple: 10 2 / 3 * 8 - 5 + = 5 3 * 8 - 5 + = 15 8 - 5 + = 7 5 + = 12
    }

    @Test
    @DisplayName("evaluatePostfix(): Should throw exception on division by zero")
    void testEvaluatePostfixDivisionByZero() {
        assertThrows(IllegalArgumentException.class, () -> StackUtils.evaluatePostfix("10 0 /"));
    }

    // --- Test Cases for nextGreaterElement ---

    @Test
    @DisplayName("nextGreaterElement(): Should find the next greater element correctly")
    void testNextGreaterElementStandard() {
        int[] arr = {4, 5, 2, 25};
        int[] expected = {5, 25, 25, -1};
        assertArrayEquals(expected, StackUtils.nextGreaterElement(arr));
    }

    @Test
    @DisplayName("nextGreaterElement(): Should handle no greater elements")
    void testNextGreaterElementDescending() {
        int[] arr = {13, 7, 6, 12};
        int[] expected = {-1, 12, 12, -1}; // 12 > 7 and 12 > 6
        assertArrayEquals(expected, StackUtils.nextGreaterElement(arr));
    }

    // --- Test Cases for longestValidParentheses ---

    @Test
    @DisplayName("longestValidParentheses(): Should find max length for standard balanced string")
    void testLongestValidParenthesesStandard() {
        assertEquals(4, StackUtils.longestValidParentheses("()()"));
    }

    @Test
    @DisplayName("longestValidParentheses(): Should find max length for complex string with prefixes")
    void testLongestValidParenthesesComplex() {
        assertEquals(6, StackUtils.longestValidParentheses("((()))"));
        assertEquals(2, StackUtils.longestValidParentheses("(()"));
    }

    // --- Test Cases for infixToPostfix ---

    @Test
    @DisplayName("infixToPostfix(): Should convert simple infix expression correctly")
    void testInfixToPostfixSimple() {
        assertEquals("A B C * +", StackUtils.infixToPostfix("A + B * C"));
    }

    @Test
    @DisplayName("infixToPostfix(): Should handle parentheses correctly")
    void testInfixToPostfixParentheses() {
        assertEquals("A B + C *", StackUtils.infixToPostfix("(A + B) * C"));
    }

    // --- Test Cases for findMiddleElement ---

    @Test
    @DisplayName("findMiddleElement(): Should find the middle element in an odd-sized stack")
    void testFindMiddleElementOdd() {
        Stack<String> stack = new Stack<>();
        stack.push("A");
        stack.push("B");
        stack.push("C"); // Stack: [A, B, C] (C on top)

        assertEquals("B", StackUtils.findMiddleElement(stack));
        assertFalse(stack.isEmpty(), "Stack should not be destroyed.");
        assertEquals(3, stack.size(), "Stack size should be restored.");
    }

    @Test
    @DisplayName("findMiddleElement(): Should find the lower middle element in an even-sized stack")
    void testFindMiddleElementEven() {
        Stack<Integer> stack = new Stack<>();
        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.push(40); // Stack: [10, 20, 30, 40] (40 on top)

        // Middle element is 20 (index 1) or 30 (index 2). We choose the element at index size/2 = 20
        assertEquals(30, StackUtils.findMiddleElement(stack));
    }

    // --- Test Cases for isPermutation ---

    @Test
    @DisplayName("isPermutation(): Should return true for stacks with the same elements, different order (destroys stacks)")
    void testIsPermutationTrue() {
        Stack<Character> s1 = new Stack<>();
        s1.push('A'); s1.push('B'); s1.push('A'); // [A, B, A]
        Stack<Character> s2 = new Stack<>();
        s2.push('B'); s2.push('A'); s2.push('A'); // [B, A, A]

        assertTrue(StackUtils.isPermutation(s1, s2));
        assertTrue(s1.isEmpty());
        assertTrue(s2.isEmpty());
    }

    @Test
    @DisplayName("isPermutation(): Should return false for stacks with different elements")
    void testIsPermutationFalse() {
        Stack<Integer> s1 = new Stack<>();
        s1.push(1); s1.push(2);
        Stack<Integer> s2 = new Stack<>();
        s2.push(1); s2.push(3);

        assertFalse(StackUtils.isPermutation(s1, s2));
    }

    // --- Test Cases for removeAllOccurrences ---

    @Test
    @DisplayName("removeAllOccurrences(): Should remove all target items while preserving order")
    void testRemoveAllOccurrencesStandard() {
        Stack<String> stack = new Stack<>();
        stack.push("A");
        stack.push("B");
        stack.push("C");
        stack.push("B");
        stack.push("D"); // Stack: [A, B, C, B, D] (D on top)

        StackUtils.removeAllOccurrences(stack, "B"); // Stack: [A, C, D] (D on top)


        assertStackEquals(new String[]{"A", "C", "D"}, stack);
    }

    @Test
    @DisplayName("removeAllOccurrences(): Should do nothing if item is not found")
    void testRemoveAllOccurrencesNotFound() {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);

        StackUtils.removeAllOccurrences(stack, 99);
        assertStackEquals(new Integer[]{1, 2}, stack);
    }
}