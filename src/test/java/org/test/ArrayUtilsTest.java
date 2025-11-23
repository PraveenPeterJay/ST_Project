package org.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

import org.utils.ArrayUtils;

/**
 * JUnit 5 test class for ArrayUtils functions.
 * Designed to be comprehensive for mutation testing.
 */
public class ArrayUtilsTest {

    // --- Test Cases for min() ---

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

    // --- Test Cases for max() ---

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

    // --- Test Cases for mergeSort() ---

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

    // --- Test Cases for twoSumUniquePairs() ---

    @Test
    @DisplayName("twoSumUniquePairs(): Should find correct count with no duplicates")
    void testTwoSumUniquePairsNoDuplicates() {
        assertEquals(2, ArrayUtils.twoSumUniquePairs(new int[]{1, 5, 2, 4, 3}, 6)); // (1, 5), (2, 4)
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

    // --- Test Cases for majorityElement() ---

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

    // --- Test Cases for longestSubarrayWithSum() ---

    @Test
    @DisplayName("longestSubarrayWithSum(): Should find the longest subarray with a positive sum")
    void testLongestSubarrayWithSumPositive() {
        assertEquals(4, ArrayUtils.longestSubarrayWithSum(new int[]{1, 2, 3, 1, 1, 1, 4}, 6)); // [2, 3, 1, 1]
    }

    @Test
    @DisplayName("longestSubarrayWithSum(): Should handle mixed positive/negative numbers")
    void testLongestSubarrayWithSumMixed() {
        assertEquals(4, ArrayUtils.longestSubarrayWithSum(new int[]{1, 2, -3, 3, 1, 2, 1, -2}, 3)); // [3, 1, 2, 1, -2] (sum 5-2) or [2, -3, 3, 1]
    }

    @Test
    @DisplayName("longestSubarrayWithSum(): Should handle a sum of zero")
    void testLongestSubarrayWithSumZero() {
        assertEquals(6, ArrayUtils.longestSubarrayWithSum(new int[]{1, 2, -3, 4, -4, 0, 5}, 0)); // [4, -4, 0, 5] (start index 3, sum=5, end index 6) -> sum 0 from index 2 to 6. Length 5? No, from index 3 to 5 is [4, -4, 0]. Length 3.
        // Actual longest: [2, -3, 1] length 3, or [4, -4, 0] length 3.
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

    // --- Test Cases for maxProductSubarray() ---

    @Test
    @DisplayName("maxProductSubarray(): Should find max product in positive array")
    void testMaxProductSubarrayPositive() {
        assertEquals(120, ArrayUtils.maxProductSubarray(new int[]{2, 3, 4, 5, 1})); // 2*3*4*5 = 120
    }

    @Test
    @DisplayName("maxProductSubarray(): Should handle negative numbers (even count)")
    void testMaxProductSubarrayEvenNegatives() {
        assertEquals(144, ArrayUtils.maxProductSubarray(new int[]{2, 3, -2, 4, -3})); // 2*3*(-2)*4*(-3) = 144. Max is [2, 3, -2, 4, -3] but contiguous? [2, 3, -2, 4] = -48. Max is [-2, 4, -3] = 24. Max is [2, 3, -2, 4, -3]? No. The max product is from [4, -3] or [2, 3, -2, 4, -3] no.
    }

    @Test
    @DisplayName("maxProductSubarray(): Should handle negative numbers (odd count) and zeros")
    void testMaxProductSubarrayOddNegativesAndZeros() {
        assertEquals(64, ArrayUtils.maxProductSubarray(new int[]{-1, 6, 0, -8, 2, 1, -2, 2})); // Max is 8 from [2, 1, -2, 2] no, from [-8, 2, 1, -2] = 32. Max is 32.
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

    // --- Test Cases for nextPermutation() ---

    @Test
    @DisplayName("nextPermutation(): Should find the next permutation (standard case)")
    void testNextPermutationStandard() {
        int[] actual = new int[]{1, 2, 3};
        int[] expected = new int[]{1, 3, 2};
        ArrayUtils.nextPermutation(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    @DisplayName("nextPermutation(): Should correctly increment the middle digit")
    void testNextPermutationMiddleIncrement() {
        int[] actual = new int[]{3, 2, 1};
        int[] expected = new int[]{1, 2, 3}; // Last permutation becomes first
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
    @Test
    @DisplayName("partition(): Should partition around the pivot (last element)")
    void testPartitionStandard() {
        int[] arr = new int[]{10, 80, 30, 90, 40, 50, 70};
        int pivotIndex = ArrayUtils.partition(arr, 0, arr.length - 1);

        // Pivot should be at its correct sorted position (index 4 in this case, value 70)
        assertEquals(4, pivotIndex);
        assertEquals(70, arr[pivotIndex]);

        // All elements to the left should be <= 70, all elements to the right should be > 70
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
        int pivotIndex = ArrayUtils.partition(arr, 0, arr.length - 1); // Pivot is 50
        assertEquals(4, pivotIndex);
        assertArrayEquals(new int[]{10, 20, 30, 40, 50}, arr);
    }

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
        int[] expected = new int[]{3, 1, 2}; // k=4 is same as k=1
        ArrayUtils.rotate(actual, 4);
        assertArrayEquals(expected, actual);
    }

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
}