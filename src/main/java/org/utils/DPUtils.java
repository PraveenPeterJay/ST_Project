package org.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Utility class containing various Dynamic Programming (DP) algorithms.
 * All methods are implemented to be static for easy access.
 */
public final class DPUtils {

    // Private constructor to prevent instantiation
    private DPUtils() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    // --- Subset/Partition Problems ---

    /**
     * Counts the number of subsets in an array that sum up to a given target k.
     * Uses 2D DP (Tabulation).
     *
     * @param arr The input array of non-negative integers.
     * @param k The target sum.
     * @return The count of subsets with sum k.
     */
    public static int countSubsetsWithSumK(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k < 0) {
            return 0;
        }

        int n = arr.length;
        // dp[i][j] is the number of subsets from arr[0...i-1] that sum to j
        int[][] dp = new int[n + 1][k + 1];

        // Base case: 1 subset (the empty set) sums to 0
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                // Not taking the current element arr[i-1]
                dp[i][j] = dp[i - 1][j];

                // Taking the current element arr[i-1]
                if (arr[i - 1] <= j) {
                    dp[i][j] += dp[i - 1][j - arr[i - 1]];
                }
            }
        }
        return dp[n][k];
    }

    /**
     * Counts the number of ways to partition an array into two subsets, S1 and S2,
     * such that their difference (S1_sum - S2_sum) equals a given value 'diff'.
     * This is equivalent to finding subsets with sum: (totalSum + diff) / 2.
     *
     * @param arr The input array of non-negative integers.
     * @param diff The required difference between the two subset sums.
     * @return The count of partitions with the given difference.
     */
    public static int countPartitionsWithGivenDifference(int[] arr, int diff) {
        if (arr == null || arr.length == 0) {
            return (diff == 0) ? 1 : 0; // Empty set can have a diff of 0
        }

        int totalSum = Arrays.stream(arr).sum();

        // The target sum for S1 must be: S1_sum = (totalSum + diff) / 2
        // Conditions for a valid target:
        // 1. totalSum + diff must be non-negative.
        // 2. totalSum + diff must be even (otherwise S1_sum is not an integer).
        if (totalSum < diff || (totalSum + diff) % 2 != 0) {
            return 0;
        }

        int targetSum = (totalSum + diff) / 2;
        return countSubsetsWithSumK(arr, targetSum);
    }

    // --- House Robber / Max Sum Problems ---

    /**
     * Finds the maximum sum of elements in an array such that no two chosen
     * elements are adjacent (Max Sum of Non-Adjacent Elements).
     * Uses DP with space optimization (O(1) extra space).
     *
     * @param arr The input array of non-negative integers.
     * @return The maximum non-adjacent sum.
     */
    public static int maxSumNonAdjacent(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        if (arr.length == 1) {
            return arr[0];
        }

        // prev2: max sum up to arr[i-2]
        // prev1: max sum up to arr[i-1]
        int prev2 = arr[0];
        int prev1 = Math.max(arr[0], arr[1]);

        for (int i = 2; i < arr.length; i++) {
            // Option 1: Take arr[i] + max sum up to arr[i-2] (prev2)
            int take = arr[i] + prev2;
            // Option 2: Don't take arr[i], use max sum up to arr[i-1] (prev1)
            int notTake = prev1;

            int current = Math.max(take, notTake);

            // Update for the next iteration
            prev2 = prev1;
            prev1 = current;
        }

        return prev1;
    }

    // --- Sequence/String Problems ---

    /**
     * Finds the **Longest Common Subsequence (LCS)** between two strings.
     * Uses 2D DP (Tabulation) and backtracks to reconstruct the string.
     *
     * @param s1 The first string.
     * @param s2 The second string.
     * @return The LCS string.
     */
    public static String longestCommonSubsequence(String s1, String s2) {
        if (s1 == null || s2 == null || s1.isEmpty() || s2.isEmpty()) {
            return "";
        }

        int n = s1.length();
        int m = s2.length();
        // dp[i][j] stores the length of the LCS of s1[0...i-1] and s2[0...j-1]
        int[][] dp = new int[n + 1][m + 1];

        // Fill the DP table
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    // Match: add 1 to the diagonal cell
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    // Mismatch: take the max of up or left cell
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        // Backtrack to reconstruct the string
        StringBuilder lcs = new StringBuilder();
        int i = n, j = m;
        while (i > 0 && j > 0) {
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                // Match: This character is part of the LCS. Move diagonally up-left.
                lcs.append(s1.charAt(i - 1));
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                // Mismatch: Move up (s1 shorter)
                i--;
            } else {
                // Mismatch: Move left (s2 shorter)
                j--;
            }
        }

        // The LCS is built backwards, so reverse it
        return lcs.reverse().toString();
    }

    /**
     * Finds the **Longest Palindromic Subsequence (LPS)** of a string.
     * The length of LPS of a string S is equal to the LCS of S and its reverse.
     * We reuse the LCS logic.
     *
     * @param s The input string.
     * @return The Longest Palindromic Subsequence string.
     */
    public static String longestPalindromicSubsequence(String s) {
        if (s == null || s.isEmpty()) {
            return "";
        }
        String reversedS = new StringBuilder(s).reverse().toString();
        // The LCS of S and reverse(S) is the LPS of S
        return longestCommonSubsequence(s, reversedS);
    }

    /**
     * Finds the **Longest Common Substring** between two strings.
     * Uses 2D DP (Tabulation) and keeps track of the max length and its end position.
     *
     * @param s1 The first string.
     * @param s2 The second string.
     * @return The Longest Common Substring string.
     */
    public static String longestCommonSubstring(String s1, String s2) {
        if (s1 == null || s2 == null || s1.isEmpty() || s2.isEmpty()) {
            return "";
        }

        int n = s1.length();
        int m = s2.length();
        int[][] dp = new int[n + 1][m + 1];
        int maxLength = 0;
        int endIndex = 0; // End index of the LCS in s1

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    // Match: The current length is 1 + the length from the diagonal cell
                    dp[i][j] = 1 + dp[i - 1][j - 1];

                    if (dp[i][j] > maxLength) {
                        maxLength = dp[i][j];
                        endIndex = i - 1; // Current index in s1
                    }
                } else {
                    // Mismatch: Substring breaks, reset count
                    dp[i][j] = 0;
                }
            }
        }

        if (maxLength == 0) {
            return "";
        }

        // Substring is from (endIndex - maxLength + 1) up to (endIndex + 1)
        return s1.substring(endIndex - maxLength + 1, endIndex + 1);
    }

    /**
     * Finds the minimum number of insertions required to make a string a palindrome.
     * This is equal to: string.length() - length(LPS).
     *
     * @param s The input string.
     * @return The minimum number of insertions.
     */
    public static int minInsertionsToMakePalindrome(String s) {
        if (s == null || s.length() <= 1) {
            return 0;
        }
        String reversedS = new StringBuilder(s).reverse().toString();

        // Length of LCS of S and reverse(S) is the length of LPS
        int lpsLength = longestCommonSubsequence(s, reversedS).length();

        return s.length() - lpsLength;
    }

    // --- Sequence Construction Problems ---

    /**
     * Finds one of the **Longest Increasing Subsequences (LIS)** of an array.
     * Uses DP (O(N^2)) and a preceding index array to reconstruct the subsequence.
     *
     * @param arr The input array.
     * @return A List representing one of the LIS.
     */
    public static List<Integer> longestIncreasingSubsequence(int[] arr) {
        if (arr == null || arr.length == 0) {
            return Collections.emptyList();
        }

        int n = arr.length;
        // dp[i]: length of LIS ending at index i
        int[] dp = new int[n];
        // parent[i]: index of the element preceding arr[i] in the LIS ending at i
        int[] parent = new int[n];
        Arrays.fill(dp, 1); // LIS of length at least 1 (the element itself)
        Arrays.fill(parent, -1);

        int maxLength = 1;
        int endIndex = 0; // Index where the LIS ends

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j] && dp[i] < 1 + dp[j]) {
                    dp[i] = 1 + dp[j];
                    parent[i] = j;
                }
            }

            if (dp[i] > maxLength) {
                maxLength = dp[i];
                endIndex = i;
            }
        }

        // Reconstruct the LIS by backtracking using the parent array
        List<Integer> lis = new ArrayList<>();
        int curr = endIndex;
        while (curr != -1) {
            lis.add(arr[curr]);
            curr = parent[curr];
        }

        // The list is built backwards, so reverse it
        Collections.reverse(lis);
        return lis;
    }

    // --- Grid Problems (Bonus) ---

    /**
     * Finds the minimum path sum from the top-left to the bottom-right corner of a grid.
     * You can only move down or right.
     * Uses 2D DP (In-place modification of the grid for space optimization).
     *
     * @param grid The input grid of non-negative integers.
     * @return The minimum path sum.
     */
    public static int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int m = grid.length;
        int n = grid[0].length;

        // Initialize the first row (only move right)
        for (int j = 1; j < n; j++) {
            grid[0][j] += grid[0][j - 1];
        }

        // Initialize the first column (only move down)
        for (int i = 1; i < m; i++) {
            grid[i][0] += grid[i - 1][0];
        }

        // Fill the rest of the grid
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // The minimum path to (i, j) is the current value plus the min path from top or left
                grid[i][j] += Math.min(grid[i - 1][j], grid[i][j - 1]);
            }
        }

        // The result is the value at the bottom-right corner
        return grid[m - 1][n - 1];
    }
}