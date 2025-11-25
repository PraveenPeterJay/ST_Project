package org.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.utils.DPUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * JUnit 5 test class for the DPUtils utility class.
 * Tests all static Dynamic Programming methods implemented in DPUtils.
 */
class DPUtilsTest {

    // =========================================================================
    //                    TESTS FOR countSubsetsWithSumK()
    // =========================================================================

    @Test
    @DisplayName("countSubsetsWithSumK: Should return 0 for an empty array")
    void testCountSubsetsWithSumKEmptyArray() {
        assertEquals(0, DPUtils.countSubsetsWithSumK(new int[]{}, 10));
    }

    @Test
    @DisplayName("countSubsetsWithSumK: Should correctly count subsets for a simple case")
    void testCountSubsetsWithSumKSimple() {
        // Subsets for sum 7: {3, 4}, {7}. Count = 2.
        assertEquals(2, DPUtils.countSubsetsWithSumK(new int[]{3, 1, 4, 7}, 7));
    }


    @Test
    @DisplayName("countSubsetsWithSumK: Should return 1 for target 0 (the empty set)")
    void testCountSubsetsWithSumKTargetZero() {
        assertEquals(1, DPUtils.countSubsetsWithSumK(new int[]{1, 2, 3}, 0));
    }

    @Test
    @DisplayName("countSubsetsWithSumK: Should handle duplicate elements correctly")
    void testCountSubsetsWithSumKDuplicates() {
        // Array: [1, 1, 2, 3]. Target 3. Subsets: {1_a, 2}, {1_b, 2}, {3}. Count = 3.
        assertEquals(3, DPUtils.countSubsetsWithSumK(new int[]{1, 1, 2, 3}, 3));
    }

    @Test
    @DisplayName("countSubsetsWithSumK: Should return 0 if target sum is impossible")
    void testCountSubsetsWithSumKImpossible() {
        assertEquals(0, DPUtils.countSubsetsWithSumK(new int[]{1, 2, 5}, 10));
    }

    @Test
    @DisplayName("countSubsetsWithSumK: Should return 0 for null array")
    void testCountSubsetsWithSumKNullArray() {
        assertEquals(0, DPUtils.countSubsetsWithSumK(null, 5));
    }

    // =========================================================================
    //             TESTS FOR countPartitionsWithGivenDifference()
    // =========================================================================

    @Test
    @DisplayName("countPartitionsWithGivenDifference: Should return 0 if the required sum is not an integer (odd totalSum + diff)")
    void testCountPartitionsWithGivenDifferenceOddSum() {
        // totalSum = 10, diff = 3. (10 + 3) / 2 = 6.5 (Impossible)
        assertEquals(0, DPUtils.countPartitionsWithGivenDifference(new int[]{1, 2, 3, 4}, 3));
    }

    @Test
    @DisplayName("countPartitionsWithGivenDifference: Should correctly count partitions for a simple case")
    void testCountPartitionsWithGivenDifferenceSimple() {
        // arr = {1, 1, 2, 3}. totalSum = 7. diff = 1. Target Sum S1 = 4. Count = 3.
        assertEquals(3, DPUtils.countPartitionsWithGivenDifference(new int[]{1, 1, 2, 3}, 1));
    }


    @Test
    @DisplayName("countPartitionsWithGivenDifference: Should return 1 for an empty array and diff 0")
    void testCountPartitionsWithGivenDifferenceEmptyArrayDiffZero() {
        assertEquals(1, DPUtils.countPartitionsWithGivenDifference(new int[]{}, 0));
    }

    @Test
    @DisplayName("countPartitionsWithGivenDifference: Should return 0 if target sum is impossible/too large")
    void testCountPartitionsWithGivenDifferenceImpossible() {
        // totalSum = 10. diff = 12. totalSum < diff. Should return 0.
        assertEquals(0, DPUtils.countPartitionsWithGivenDifference(new int[]{1, 2, 3, 4}, 12));
    }

    @Test
    @DisplayName("countPartitionsWithGivenDifference: Should return 0 for null array and non-zero diff")
    void testCountPartitionsWithGivenDifferenceNullArrayNonZeroDiff() {
        assertEquals(0, DPUtils.countPartitionsWithGivenDifference(null, 5));
    }

    // =========================================================================
    //                        TESTS FOR maxSumNonAdjacent()
    // =========================================================================

    @Test
    @DisplayName("maxSumNonAdjacent: Should return 0 for an empty array")
    void testMaxSumNonAdjacentEmptyArray() {
        assertEquals(0, DPUtils.maxSumNonAdjacent(new int[]{}));
    }

    @Test
    @DisplayName("maxSumNonAdjacent: Should handle a standard sequence correctly")
    void testMaxSumNonAdjacentStandard() {
        // arr: {1, 7, 3, 6, 8, 4}. Max sum is 17 (e.g., {7, 6, 4} if intermediate max logic is complex)
        assertEquals(17, DPUtils.maxSumNonAdjacent(new int[]{1, 7, 3, 6, 8, 4}));
    }

    @Test
    @DisplayName("maxSumNonAdjacent: Should return the single element for an array of size 1")
    void testMaxSumNonAdjacentSingleElement() {
        assertEquals(5, DPUtils.maxSumNonAdjacent(new int[]{5}));
    }

    @Test
    @DisplayName("maxSumNonAdjacent: Should handle a case with the last element taken")
    void testMaxSumNonAdjacentLastElementTaken() {
        // arr: [5, 4, 3, 8]. Max sum: 5 + 8 = 13.
        assertEquals(13, DPUtils.maxSumNonAdjacent(new int[]{5, 4, 3, 8}));
    }

    @Test
    @DisplayName("maxSumNonAdjacent: Should handle negative values in array")
    void testMaxSumNonAdjacentNegative() {
        // arr: {5, -4, -3, 8}. Max sum: 5 + 8 = 13.
        assertEquals(13, DPUtils.maxSumNonAdjacent(new int[]{5, -4, -3, 8}));
    }

    // =========================================================================
    //                    TESTS FOR longestCommonSubsequence()
    // =========================================================================

    @Test
    @DisplayName("longestCommonSubsequence: Should return an empty string for empty input strings")
    void testLongestCommonSubsequenceEmptyStrings() {
        assertEquals("", DPUtils.longestCommonSubsequence("", "abc"));
    }

    @Test
    @DisplayName("longestCommonSubsequence: Should find the correct LCS for a standard case")
    void testLongestCommonSubsequenceStandard() {
        // s1="aggtab", s2="gxtxayb". LCS is "gtab" (length 4).
        assertEquals("gtab", DPUtils.longestCommonSubsequence("aggtab", "gxtxayb"));
    }

    @Test
    @DisplayName("longestCommonSubsequence: Should handle a case with no common characters")
    void testLongestCommonSubsequenceNoCommon() {
        assertEquals("", DPUtils.longestCommonSubsequence("abc", "def"));
    }

    @Test
    @DisplayName("longestCommonSubsequence: Should return the shorter string if it's a subsequence of the longer")
    void testLongestCommonSubsequenceSubsequence() {
        assertEquals("ace", DPUtils.longestCommonSubsequence("abcde", "ace"));
    }

    @Test
    @DisplayName("longestCommonSubsequence: Should return an empty string for null inputs")
    void testLongestCommonSubsequenceNullInputs() {
        assertEquals("", DPUtils.longestCommonSubsequence(null, "abc"));
        assertEquals("", DPUtils.longestCommonSubsequence("abc", null));
    }

    // =========================================================================
    //                 TESTS FOR longestPalindromicSubsequence()
    // =========================================================================

    @Test
    @DisplayName("longestPalindromicSubsequence: Should return the correct LPS for a standard case")
    void testLongestPalindromicSubsequenceStandard() {
        // s="bbbab". LPS is "bbbb" (length 4).
        assertEquals("bbbb", DPUtils.longestPalindromicSubsequence("bbbab"));
    }

    @Test
    @DisplayName("longestPalindromicSubsequence: Should return the single character for an already-palindrome string")
    void testLongestPalindromicSubsequenceAlreadyPalindrome() {
        assertEquals("aabbaa", DPUtils.longestPalindromicSubsequence("aabbaa"));
    }


    @Test
    @DisplayName("longestPalindromicSubsequence: Should return an empty string for empty input")
    void testLongestPalindromicSubsequenceEmpty() {
        assertEquals("", DPUtils.longestPalindromicSubsequence(""));
    }


    // =========================================================================
    //                    TESTS FOR longestCommonSubstring()
    // =========================================================================

    @Test
    @DisplayName("longestCommonSubstring: Should find the correct Locus for a standard case")
    void testLongestCommonSubstringStandard() {
        // Locus: "CDEF"
        assertEquals("CDEF", DPUtils.longestCommonSubstring("ABCDEFG", "XYZCDEFPQR"));
    }

    @Test
    @DisplayName("longestCommonSubstring: Should handle a case with multiple common substrings")
    void testLongestCommonSubstringMultiple() {
        // LCS: "CDE" (length 3)
        assertEquals("CDE", DPUtils.longestCommonSubstring("XXABCDEFFG", "CDEYYFGABZZ"));
    }

    @Test
    @DisplayName("longestCommonSubstring: Should return an empty string if no common substring exists")
    void testLongestCommonSubstringNone() {
        assertEquals("", DPUtils.longestCommonSubstring("ABC", "DEF"));
    }

    @Test
    @DisplayName("longestCommonSubstring: Should return an empty string for empty inputs")
    void testLongestCommonSubstringEmpty() {
        assertEquals("", DPUtils.longestCommonSubstring("", "abc"));
        assertEquals("", DPUtils.longestCommonSubstring("abc", ""));
    }

    // =========================================================================
    //                TESTS FOR minInsertionsToMakePalindrome()
    // =========================================================================

    @Test
    @DisplayName("minInsertionsToMakePalindrome: Should return 0 for an already palindromic string")
    void testMinInsertionsToMakePalindromeAlready() {
        assertEquals(0, DPUtils.minInsertionsToMakePalindrome("racecar"));
    }

    @Test
    @DisplayName("minInsertionsToMakePalindrome: Should return correct insertions for a standard case")
    void testMinInsertionsToMakePalindromeStandard() {
        // String "mbadm". Length 5. LPS length 3. min_insertions = 5 - 3 = 2.
        assertEquals(2, DPUtils.minInsertionsToMakePalindrome("mbadm"));
    }

    @Test
    @DisplayName("minInsertionsToMakePalindrome: Should return length-1 for a unique character string")
    void testMinInsertionsToMakePalindromeUnique() {
        // String "abcde". Length 5. LPS length 1. min_insertions = 5 - 1 = 4.
        assertEquals(4, DPUtils.minInsertionsToMakePalindrome("abcde"));
    }

    @Test
    @DisplayName("minInsertionsToMakePalindrome: Should return 0 for an empty or single character string")
    void testMinInsertionsToMakePalindromeEdgeCases() {
        assertEquals(0, DPUtils.minInsertionsToMakePalindrome("a"));
        assertEquals(0, DPUtils.minInsertionsToMakePalindrome(""));
    }

    // =========================================================================
    //                TESTS FOR longestIncreasingSubsequence()
    // =========================================================================

    @Test
    @DisplayName("longestIncreasingSubsequence: Should return an empty list for an empty array")
    void testLongestIncreasingSubsequenceEmptyArray() {
        assertEquals(Collections.emptyList(), DPUtils.longestIncreasingSubsequence(new int[]{}));
    }

    @Test
    @DisplayName("longestIncreasingSubsequence: Should return the correct LIS for a standard case")
    void testLongestIncreasingSubsequenceStandard() {
        // arr: {10, 9, 2, 5, 3, 7, 101, 18}. LIS is {2, 5, 7, 101} (length 4).
        List<Integer> expected = Arrays.asList(2, 5, 7, 101);
        assertEquals(expected, DPUtils.longestIncreasingSubsequence(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
    }

    @Test
    @DisplayName("longestIncreasingSubsequence: Should return the full array if it is already sorted")
    void testLongestIncreasingSubsequenceSorted() {
        List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5);
        assertEquals(expected, DPUtils.longestIncreasingSubsequence(new int[]{1, 2, 3, 4, 5}));
    }

    @Test
    @DisplayName("longestIncreasingSubsequence: Should handle a descending array (LIS of length 1)")
    void testLongestIncreasingSubsequenceDescending() {
        // The implementation returns one of the LIS (length 1).
        List<Integer> result = DPUtils.longestIncreasingSubsequence(new int[]{5, 4, 3, 2, 1});
        assertEquals(1, result.size());
        assertTrue(Arrays.asList(5, 4, 3, 2, 1).contains(result.get(0)));
    }

    // =========================================================================
    //                            TESTS FOR minPathSum()
    // =========================================================================

    @Test
    @DisplayName("minPathSum: Should return 0 for an empty grid")
    void testMinPathSumEmptyGrid() {
        assertEquals(0, DPUtils.minPathSum(new int[][]{}));
    }

    @Test
    @DisplayName("minPathSum: Should return the correct minimum path sum for a standard grid")
    void testMinPathSumStandard() {
        int[][] grid = new int[][]{
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };
        // Path: 1 -> 3 -> 1 -> 1 -> 1. Sum = 7.
        assertEquals(7, DPUtils.minPathSum(grid));
    }

    @Test
    @DisplayName("minPathSum: Should handle a 1xN grid (only moving right)")
    void testMinPathSumSingleRow() {
        int[][] grid = new int[][]{{1, 2, 3, 4}};
        // Path: 1 + 2 + 3 + 4 = 10
        assertEquals(10, DPUtils.minPathSum(grid));
    }

    @Test
    @DisplayName("minPathSum: Should handle an Nx1 grid (only moving down)")
    void testMinPathSumSingleColumn() {
        int[][] grid = new int[][]{{1}, {2}, {3}, {4}};
        // Path: 1 + 2 + 3 + 4 = 10
        assertEquals(10, DPUtils.minPathSum(grid));
    }

    @Test
    @DisplayName("minPathSum: Should handle a large grid")
    void testMinPathSumLarge() {
        int[][] grid = new int[][]{
                {1, 1, 1, 1},
                {1, 1, 1, 1},
                {1, 1, 1, 1},
                {1, 1, 1, 1}
        };
        // Path: 1 + 1 + 1 + 1 + 1 + 1 + 1 = 7.
        assertEquals(7, DPUtils.minPathSum(grid));
    }
}