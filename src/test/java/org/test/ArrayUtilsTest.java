package org.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.utils.ArrayUtils;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * JUnit 5 test class for ArrayUtils functions.
 * Designed to be comprehensive for mutation testing.
 */
public class ArrayUtilsTest {

    // =========================================================================
    //                            TESTS FOR min()
    // =========================================================================

    @Test
    @DisplayName("min(): Should find the minimum value in an array of positive integers")
    void testMinPositive() {
        assertEquals(1, ArrayUtils.min(new int[]{5, 2, 8, 1, 9}));
    }

    @Test
    @DisplayName("min(): Should find the minimum value in an array of negative integers")
    void testMinNegative() {
        assertEquals(-10, ArrayUtils.min(new int[]{-5, -2, -10, -1, -9}));
    }

    @Test
    @DisplayName("min(): Should find the minimum value in an array with mixed signs")
    void testMinMixed() {
        assertEquals(-15, ArrayUtils.min(new int[]{10, -5, 0, 1, -15, 5}));
    }

    @Test
    @DisplayName("min(): Should find the minimum value in an array with duplicates")
    void testMinDuplicates() {
        assertEquals(3, ArrayUtils.min(new int[]{5, 3, 8, 3, 9}));
    }

    @Test
    @DisplayName("min(): Should handle single element array")
    void testMinSingleElement() {
        assertEquals(42, ArrayUtils.min(new int[]{42}));
    }

    @Test
    @DisplayName("min(): Should throw exception for empty array")
    void testMinEmptyArray() {
        assertThrows(IllegalArgumentException.class, () -> ArrayUtils.min(new int[]{}));
    }

    @Test
    @DisplayName("min(): Should throw exception for null array")
    void testMinNullArray() {
        assertThrows(IllegalArgumentException.class, () -> ArrayUtils.min(null));
    }

    // =========================================================================
    //                            TESTS FOR max()
    // =========================================================================

    @Test
    @DisplayName("max(): Should find the maximum value in an array of positive integers")
    void testMaxPositive() {
        assertEquals(9, ArrayUtils.max(new int[]{5, 2, 8, 1, 9}));
    }

    @Test
    @DisplayName("max(): Should find the maximum value in an array of negative integers")
    void testMaxNegative() {
        assertEquals(-1, ArrayUtils.max(new int[]{-5, -2, -10, -1, -9}));
    }

    @Test
    @DisplayName("max(): Should find the maximum value in an array with mixed signs")
    void testMaxMixed() {
        assertEquals(10, ArrayUtils.max(new int[]{10, -5, 0, 1, -15, 5}));
    }

    @Test
    @DisplayName("max(): Should handle single element array")
    void testMaxSingleElement() {
        assertEquals(42, ArrayUtils.max(new int[]{42}));
    }

    @Test
    @DisplayName("max(): Should throw exception for empty array")
    void testMaxEmptyArray() {
        assertThrows(IllegalArgumentException.class, () -> ArrayUtils.max(new int[]{}));
    }

    @Test
    @DisplayName("max(): Should throw exception for null array")
    void testMaxNullArray() {
        assertThrows(IllegalArgumentException.class, () -> ArrayUtils.max(null));
    }

    // =========================================================================
    //                          TESTS FOR mergeSort()
    // =========================================================================

    @Test
    @DisplayName("mergeSort(): Should correctly sort a mixed array")
    void testMergeSortMixed() {
        int[] actual = new int[]{3, 1, 4, 1, 5, 9, 2, 6};
        int[] expected = new int[]{1, 1, 2, 3, 4, 5, 6, 9};
        ArrayUtils.mergeSort(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    @DisplayName("mergeSort(): Should correctly sort an array with negative numbers")
    void testMergeSortNegative() {
        int[] actual = new int[]{-5, 0, -10, 3, -1};
        int[] expected = new int[]{-10, -5, -1, 0, 3};
        ArrayUtils.mergeSort(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    @DisplayName("mergeSort(): Should handle an already sorted array")
    void testMergeSortAlreadySorted() {
        int[] actual = new int[]{1, 2, 3, 4, 5};
        int[] expected = new int[]{1, 2, 3, 4, 5};
        ArrayUtils.mergeSort(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    @DisplayName("mergeSort(): Should handle a reverse sorted array")
    void testMergeSortReverseSorted() {
        int[] actual = new int[]{5, 4, 3, 2, 1};
        int[] expected = new int[]{1, 2, 3, 4, 5};
        ArrayUtils.mergeSort(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    @DisplayName("mergeSort(): Should handle duplicate elements")
    void testMergeSortDuplicates() {
        int[] actual = new int[]{5, 1, 5, 1, 2, 2, 8};
        int[] expected = new int[]{1, 1, 2, 2, 5, 5, 8};
        ArrayUtils.mergeSort(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    @DisplayName("mergeSort(): Should handle an empty array")
    void testMergeSortEmpty() {
        int[] actual = new int[]{};
        ArrayUtils.mergeSort(actual);
        assertArrayEquals(new int[]{}, actual);
    }

    @Test
    @DisplayName("mergeSort(): Should handle a single element array")
    void testMergeSortSingle() {
        int[] actual = new int[]{42};
        ArrayUtils.mergeSort(actual);
        assertArrayEquals(new int[]{42}, actual);
    }

    @Test
    @DisplayName("mergeSort(): Should handle null array gracefully and not throw exception")
    void testMergeSortNullArray() {
        // Expected behavior: Method should handle null array without throwing a NullPointerException.
        ArrayUtils.mergeSort(null);
    }

    // =========================================================================
    //                      TESTS FOR twoSumUniquePairs()
    // =========================================================================

    @Test
    @DisplayName("twoSumUniquePairs(): Should find correct count with no duplicates")
    void testTwoSumUniquePairsNoDuplicates() {
        assertEquals(2, ArrayUtils.twoSumUniquePairs(new int[]{1, 5, 2, 4, 3}, 6)); // (1, 5), (2, 4)
    }

    @Test
    @DisplayName("twoSumUniquePairs(): Should correctly count one unique pair with many duplicates on both sides")
    void testTwoSumUniquePairsHeavyDuplicates() {
        assertEquals(1, ArrayUtils.twoSumUniquePairs(new int[]{2, 2, 2, 5, 5, 5}, 7)); // Only (2, 5)
    }

    @Test
    @DisplayName("twoSumUniquePairs(): Should correctly handle duplicates in the array")
    void testTwoSumUniquePairsWithDuplicates() {
        assertEquals(1, ArrayUtils.twoSumUniquePairs(new int[]{1, 1, 1, 5, 5, 5}, 6)); // Only (1, 5) is one unique pair
    }

    @Test
    @DisplayName("twoSumUniquePairs(): Should find pairs with negative numbers")
    void testTwoSumUniquePairsNegatives() {
        assertEquals(2, ArrayUtils.twoSumUniquePairs(new int[]{-2, 4, -4, 6, 8, 2}, 2)); // (-2, 4), (-4, 6)
    }

    @Test
    @DisplayName("twoSumUniquePairs(): Should return 0 when no pair is found")
    void testTwoSumUniquePairsNotFound() {
        assertEquals(0, ArrayUtils.twoSumUniquePairs(new int[]{1, 2, 3, 4}, 10));
    }

    @Test
    @DisplayName("twoSumUniquePairs(): Should handle pairs that sum to zero")
    void testTwoSumUniquePairsSumZero() {
        assertEquals(2, ArrayUtils.twoSumUniquePairs(new int[]{-3, 0, 3, 1, -1}, 0)); // (-3, 3), (-1, 1)
    }

    @Test
    @DisplayName("twoSumUniquePairs(): Should handle an empty array")
    void testTwoSumUniquePairsEmpty() {
        assertEquals(0, ArrayUtils.twoSumUniquePairs(new int[]{}, 5));
    }

    @Test
    @DisplayName("twoSumUniquePairs(): Should handle a single element array")
    void testTwoSumUniquePairsSingle() {
        assertEquals(0, ArrayUtils.twoSumUniquePairs(new int[]{5}, 10));
    }

    @Test
    @DisplayName("twoSumUniquePairs(): Should handle array length exactly 2 with a matching pair")
    void testTwoSumUniquePairsSizeTwoMatch() {
        assertEquals(1, ArrayUtils.twoSumUniquePairs(new int[]{1, 5}, 6));
    }

    @Test
    @DisplayName("twoSumUniquePairs(): Should return 0 for array length exactly 1")
    void testTwoSumUniquePairsSizeOne() {
        assertEquals(0, ArrayUtils.twoSumUniquePairs(new int[]{1}, 2));
    }

    @Test
    @DisplayName("twoSumUniquePairs(): Should return 0 when the input array is null")
    void testTwoSumUniquePairsNullArray() {
        assertEquals(0, ArrayUtils.twoSumUniquePairs(null, 10));
    }

    @Test
    @DisplayName("twoSumUniquePairs(): Should correctly skip duplicates from right up to the next unique number")
    void testTwoSumUniquePairsComplexDuplicates() {
        int[] arr = new int[]{1, 1, 3, 3, 3, 5, 5, 5, 7, 7, 7, 9};
        // Target 10. Pairs: (1, 9), (3, 7), (5, 5). Total 3.
        assertEquals(3, ArrayUtils.twoSumUniquePairs(arr, 10));
    }

    @Test
    @DisplayName("twoSumUniquePairs(): Should correctly move left pointer when sum is too small")
    void testTwoSumUniquePairsSumTooSmall() {
        int[] arr = new int[]{1, 2, 8, 9};
        // Target 11. Initial (1, 9) sum 10. Should move left++ (1 < 11).
        assertEquals(1, ArrayUtils.twoSumUniquePairs(arr, 11)); // Only (2, 9) is not possible as 9 is skipped from right.
    }

    @Test
    @DisplayName("twoSumUniquePairs(): Should correctly enter match block when sum equals target")
    void testTwoSumUniquePairsBoundaryMatch() {
        int[] arr = new int[]{1, 9};
        assertEquals(1, ArrayUtils.twoSumUniquePairs(arr, 10));
    }

    // =========================================================================
    //                        TESTS FOR majorityElement()
    // =========================================================================

    @Test
    @DisplayName("majorityElement(): Should find the majority element (over 1/2)")
    void testMajorityElementFound() {
        assertEquals(4, ArrayUtils.majorityElement(new int[]{3, 3, 4, 2, 4, 4, 2, 4, 4})); // 4 appears 5/9
    }

    @Test
    @DisplayName("majorityElement(): Should handle a very large majority")
    void testMajorityElementMax() {
        assertEquals(1, ArrayUtils.majorityElement(new int[]{1, 1, 1, 1, 1, 2, 3, 4})); // 1 appears 5/8
    }

    @Test
    @DisplayName("majorityElement(): Should return MIN_VALUE when no majority element exists (n=8, max count is 4)")
    void testMajorityElementNotFound() {
        assertEquals(Integer.MIN_VALUE, ArrayUtils.majorityElement(new int[]{1, 2, 3, 4, 1, 2, 3, 4})); // No element > 4 times
    }

    @Test
    @DisplayName("majorityElement(): Should handle an empty array")
    void testMajorityElementEmpty() {
        assertEquals(Integer.MIN_VALUE, ArrayUtils.majorityElement(new int[]{}));
    }

    @Test
    @DisplayName("majorityElement(): Should handle a single element array")
    void testMajorityElementSingle() {
        assertEquals(42, ArrayUtils.majorityElement(new int[]{42}));
    }

    // =========================================================================
    //                   TESTS FOR longestSubarrayWithSum()
    // =========================================================================

    @Test
    @DisplayName("longestSubarrayWithSum(): Should find the longest subarray with a positive sum")
    void testLongestSubarrayWithSumPositive() {
        assertEquals(4, ArrayUtils.longestSubarrayWithSum(new int[]{1, 2, 3, 1, 1, 1, 4}, 6)); // [2, 3, 1, 1]
    }

    @Test
    @DisplayName("longestSubarrayWithSum(): Should handle mixed positive/negative numbers")
    void testLongestSubarrayWithSumMixed() {
        // Longest for sum 3 is [2, -3, 3, 1] (length 4) or [3, 1, 2, 1, -2] (length 5, sum 5-2 = 3).
        assertEquals(4, ArrayUtils.longestSubarrayWithSum(new int[]{1, 2, -3, 3, 1, 2, 1, -2}, 3));
    }

    @Test
    @DisplayName("longestSubarrayWithSum(): Should handle a sum of zero")
    void testLongestSubarrayWithSumZero() {
        // [2, -3, 1] length 3, or [4, -4, 0] length 3, or [1, 2, -3] length 3
        assertEquals(6, ArrayUtils.longestSubarrayWithSum(new int[]{1, 2, -3, 4, -4, 0, 5}, 0));
    }

    @Test
    @DisplayName("longestSubarrayWithSum(): Should return 0 when target sum is not found")
    void testLongestSubarrayWithSumNotFound() {
        assertEquals(0, ArrayUtils.longestSubarrayWithSum(new int[]{1, 2, 3, 4}, 100));
    }

    @Test
    @DisplayName("longestSubarrayWithSum(): Should handle an empty array")
    void testLongestSubarrayWithSumEmpty() {
        assertEquals(0, ArrayUtils.longestSubarrayWithSum(new int[]{}, 5));
    }

    @Test
    @DisplayName("longestSubarrayWithSum(): Should return 0 when the input array is null")
    void testLongestSubarrayWithSumNullArray() {
        assertEquals(0, ArrayUtils.longestSubarrayWithSum(null, 10));
    }

    // =========================================================================
    //                    TESTS FOR maxProductSubarray()
    // =========================================================================

    @Test
    @DisplayName("maxProductSubarray(): Should find max product in positive array")
    void testMaxProductSubarrayPositive() {
        assertEquals(120, ArrayUtils.maxProductSubarray(new int[]{2, 3, 4, 5, 1})); // 2*3*4*5 = 120
    }

    @Test
    @DisplayName("maxProductSubarray(): Should handle negative numbers (even count)")
    void testMaxProductSubarrayEvenNegatives() {
        assertEquals(144, ArrayUtils.maxProductSubarray(new int[]{2, 3, -2, 4, -3})); // 2*3*(-2)*4*(-3) = 144
    }

    @Test
    @DisplayName("maxProductSubarray(): Should handle negative numbers (odd count) and zeros")
    void testMaxProductSubarrayOddNegativesAndZeros() {
        assertEquals(64, ArrayUtils.maxProductSubarray(new int[]{-1, 6, 0, -8, 2, 1, -2, 2})); // [-8, 2, 1, -2] = 32
    }

    @Test
    @DisplayName("maxProductSubarray(): Should handle single element")
    void testMaxProductSubarraySingleElement() {
        assertEquals(42, ArrayUtils.maxProductSubarray(new int[]{42}));
        assertEquals(-5, ArrayUtils.maxProductSubarray(new int[]{-5}));
    }

    @Test
    @DisplayName("maxProductSubarray(): Should handle all zeros (max product should be 0)")
    void testMaxProductSubarrayAllZeros() {
        assertEquals(0, ArrayUtils.maxProductSubarray(new int[]{0, 0, 0, 0}));
    }

    @Test
    @DisplayName("maxProductSubarray(): Should throw exception for empty array")
    void testMaxProductSubarrayEmpty() {
        assertThrows(IllegalArgumentException.class, () -> ArrayUtils.maxProductSubarray(new int[]{}));
    }

    // =========================================================================
    //                        TESTS FOR nextPermutation()
    // =========================================================================

    @Test
    @DisplayName("nextPermutation(): Should find the next permutation (standard case)")
    void testNextPermutationStandard() {
        int[] actual = new int[]{1, 2, 3};
        int[] expected = new int[]{1, 3, 2};
        ArrayUtils.nextPermutation(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    @DisplayName("nextPermutation(): Should correctly wrap around from last to first permutation")
    void testNextPermutationLastToFirst() {
        int[] actual = new int[]{3, 2, 1};
        int[] expected = new int[]{1, 2, 3};
        ArrayUtils.nextPermutation(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    @DisplayName("nextPermutation(): Should handle long reverse-sorted suffix")
    void testNextPermutationLongSuffix() {
        int[] actual = new int[]{1, 5, 1};
        int[] expected = new int[]{5, 1, 1};
        ArrayUtils.nextPermutation(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    @DisplayName("nextPermutation(): Should handle the case 1, 3, 2 -> 2, 1, 3")
    void testNextPermutationTrickyCase() {
        int[] actual = new int[]{1, 3, 2};
        int[] expected = new int[]{2, 1, 3};
        ArrayUtils.nextPermutation(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    @DisplayName("nextPermutation(): Should handle the case 2, 3, 1 -> 3, 1, 2")
    void testNextPermutationAnotherTrickyCase() {
        int[] actual = new int[]{2, 3, 1};
        int[] expected = new int[]{3, 1, 2};
        ArrayUtils.nextPermutation(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    @DisplayName("nextPermutation(): Should handle duplicates")
    void testNextPermutationDuplicates() {
        int[] actual = new int[]{1, 1, 5};
        int[] expected = new int[]{1, 5, 1};
        ArrayUtils.nextPermutation(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    @DisplayName("nextPermutation(): Should handle empty array")
    void testNextPermutationEmpty() {
        int[] actual = new int[]{};
        ArrayUtils.nextPermutation(actual);
        assertArrayEquals(new int[]{}, actual);
    }

    // =========================================================================
    //                          TESTS FOR partition()
    // =========================================================================

    @Test
    @DisplayName("partition(): Should partition around the pivot (last element)")
    void testPartitionStandard() {
        int[] arr = new int[]{10, 80, 30, 90, 40, 50, 70};
        int pivotIndex = ArrayUtils.partition(arr, 0, arr.length - 1);

        assertEquals(4, pivotIndex);
        assertEquals(70, arr[pivotIndex]);

        for (int i = 0; i < pivotIndex; i++) {
            assertTrue(arr[i] <= 70);
        }
        for (int i = pivotIndex + 1; i < arr.length; i++) {
            assertTrue(arr[i] > 70);
        }
    }

    @Test
    @DisplayName("partition(): Should handle already sorted array")
    void testPartitionSorted() {
        int[] arr = new int[]{10, 20, 30, 40, 50};
        int pivotIndex = ArrayUtils.partition(arr, 0, arr.length - 1);
        assertEquals(4, pivotIndex);
        // The array might be rearranged internally, but for a sorted array,
        // the pivot (50) should still end up at the end.
        // A weaker assertion is used here since the goal is to uncomment.
        // If the implementation is standard QuickSort partition:
        // Expected array after partition: {10, 20, 30, 40, 50}
        assertArrayEquals(new int[]{10, 20, 30, 40, 50}, arr);
    }

    // =========================================================================
    //                            TESTS FOR rotate()
    // =========================================================================

    @Test
    @DisplayName("rotate(): Should rotate array to the right by 3 steps")
    void testRotateRightStandard() {
        int[] actual = new int[]{1, 2, 3, 4, 5, 6, 7};
        int[] expected = new int[]{5, 6, 7, 1, 2, 3, 4};
        ArrayUtils.rotate(actual, 3);
        assertArrayEquals(expected, actual);
    }

    @Test
    @DisplayName("rotate(): Should handle rotation greater than array length")
    void testRotateRightWrapAround() {
        int[] actual = new int[]{1, 2, 3};
        int[] expected = new int[]{3, 1, 2};
        ArrayUtils.rotate(actual, 4);
        assertArrayEquals(expected, actual);
    }

    @Test
    @DisplayName("rotate(): Should handle null array gracefully and not throw exception")
    void testRotateNullArray() {
        ArrayUtils.rotate(null, 5);
    }

    // =========================================================================
    //                        TESTS FOR hasIntersection()
    // =========================================================================

    @Test
    @DisplayName("hasIntersection(): Should return true for intersecting sorted arrays")
    void testHasIntersectionTrue() {
        int[] arr1 = new int[]{1, 3, 5, 7, 9};
        int[] arr2 = new int[]{2, 4, 5, 6, 8};
        assertTrue(ArrayUtils.hasIntersection(arr1, arr2));
    }

    @Test
    @DisplayName("hasIntersection(): Should return false for non-intersecting sorted arrays")
    void testHasIntersectionFalse() {
        int[] arr1 = new int[]{1, 3, 5};
        int[] arr2 = new int[]{2, 4, 6};
        assertFalse(ArrayUtils.hasIntersection(arr1, arr2));
    }

    // =========================================================================
    //                           TESTS FOR isSorted()
    // =========================================================================

    @Test
    @DisplayName("isSorted(): Should return true for an ascending sorted array")
    void testIsSortedTrue() {
        assertTrue(ArrayUtils.isSorted(new int[]{1, 2, 2, 3, 5}));
    }

    @Test
    @DisplayName("isSorted(): Should return false for an unsorted array")
    void testIsSortedFalse() {
        assertFalse(ArrayUtils.isSorted(new int[]{1, 5, 2, 8}));
    }

    // =========================================================================
    //                 TESTS FOR binarySearchFirstOccurrence()
    // =========================================================================

    @Test
    @DisplayName("binarySearchFirstOccurrence(): Should find the first index of duplicates")
    void testBinarySearchFirstOccurrenceDuplicates() {
        int[] arr = new int[]{1, 3, 3, 3, 5, 7};
        assertEquals(1, ArrayUtils.binarySearchFirstOccurrence(arr, 3));
    }

    @Test
    @DisplayName("binarySearchFirstOccurrence(): Should return -1 if target is not found")
    void testBinarySearchFirstOccurrenceNotFound() {
        int[] arr = new int[]{1, 3, 5, 7, 9};
        assertEquals(-1, ArrayUtils.binarySearchFirstOccurrence(arr, 4));
    }

    @Test
    @DisplayName("binarySearchFirstOccurrence(): Should return -1 when the input array is null")
    void testBinarySearchFirstOccurrenceNullArray() {
        assertEquals(-1, ArrayUtils.binarySearchFirstOccurrence(null, 5));
    }
}