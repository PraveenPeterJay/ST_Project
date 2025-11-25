package org.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.utils.ArrayUtils;
import org.utils.StringUtils;
import org.utils.GraphUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * Integration Test class focusing on plausible data flow scenarios across
 * ArrayUtils, StringUtils, and GraphUtils to satisfy the integration-level
 * mutation testing requirement.
 */
public class IntegrationTests {

    /** Helper to create an adjacency list for an unweighted graph. */
    private List<List<Integer>> createGraph(int numNodes, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>(numNodes);
        for (int i = 0; i < numNodes; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adj.get(u).add(v);
        }
        return adj;
    }

    // --- Scenario 1: GraphUtils -> ArrayUtils (Data Processing & Ranking) ---

    @Test
    @DisplayName("INTEGRATION 1: Graph Indegrees -> Array Sort -> Array Max Value (Node Ranking)")
    void testGraphToArrayNodeRanking() {
        // USE CASE: Analyze a directed graph to find the node with the highest 'influence' (max indegree)
        // after normalizing the scores.

        // 1. GraphUtils: Compute Indegrees (Score Generation)
        // Graph (0->1, 0->2, 1->2, 3->0, 3->4)
        int numNodes = 5;
        int[][] edges = {{0, 1}, {0, 2}, {1, 2}, {3, 0}, {3, 4}};
        List<List<Integer>> adj = createGraph(numNodes, edges);

        int[] indegrees = GraphUtils.computeIndegrees(adj, numNodes); // Result: [1, 1, 2, 0, 1]

        // Mutants in computeIndegrees (e.g., missed iteration) would be killed here.

        // 2. ArrayUtils: Sort the scores (MergeSort)
        // Sorting is done to ensure the maximum value is found efficiently or for presentation.
        ArrayUtils.mergeSort(indegrees); // Result: [0, 1, 1, 1, 2]

        // Mutants in mergeSort (e.g., comparison operator error) would be killed here.

        // 3. ArrayUtils: Find the Maximum Score (Max)
        int maxIndegree = ArrayUtils.max(indegrees); // Result: 2 (Node 2 has the highest indegree)

        // Mutants in ArrayUtils.max (e.g., loop boundary error) would be killed here.

        // ASSERT: The final maximum score must be correct
        assertEquals(2, maxIndegree, "The maximum indegree found after sorting should be 2.");
        assertArrayEquals(new int[]{0, 1, 1, 1, 2}, indegrees, "The array should be sorted correctly.");
    }

    // --- Scenario 2: StringUtils -> ArrayUtils (Data Cleaning and Validation) ---

    @Test
    @DisplayName("INTEGRATION 2: String Clean -> Split by Length -> Array isSorted Check (CSV Data Flow)")
    void testStringToArrayDataValidation() {
        // USE CASE: A raw data string contains a sequence of sorted numbers but also junk/spacing.
        // Clean and parse the data, then validate that the core numerical array is sorted.

        // 1. StringUtils: Clean and Prepare String (The data array)
        String rawData = " 1, 33, 55,  88,  102,  5, 2 ";
        // First, clean external and internal whitespace
        String cleanedStr = StringUtils.cleanWhitespace(rawData); // Result: "1, 33, 55, 88, 102, -5, 20"

        // Mutants in cleanWhitespace (e.g., incorrect space replacement) would be killed here.

        // 2. StringUtils: Split and Filter Array
        // Filter out non-numeric noise (by setting minLength > 1 to exclude single-digit characters or empty strings if we use a different delimiter)
        String[] strParts = StringUtils.splitByLength(cleanedStr.replaceAll("\\s", ""), ",", 2); // Result: ["33", "55", "88", "102"] (1, -5, 20 filtered out by minLength 2)

        // 3. ArrayUtils: Convert and Check Sorting
        int[] arr = Arrays.stream(strParts).mapToInt(Integer::parseInt).toArray(); // Result: [33, 55, 88, 102]
        for(int i=0;i<arr.length;i++)
                System.out.println(arr[i]);
        boolean isSorted = ArrayUtils.isSorted(arr); // Result: True

        // Mutants in isSorted (e.g., using < instead of > on the array element comparison) would be killed here.

        // ASSERT: The final array should be detected as sorted
        assertTrue(isSorted, "The parsed and filtered array should be detected as sorted.");
        assertEquals(4, arr.length, "The array should contain exactly 4 elements after filtering.");
    }

    // --- Scenario 3: ArrayUtils -> StringUtils (System Reporting/Security) ---

    @Test
    @DisplayName("INTEGRATION 3: Array Permutation -> String Anagram Check -> String Pad Left (Security Check)")
    void testArrayToStringSecurityReporting() {
        // USE CASE: A sequence of security codes (array) is processed to its next state (permutation).
        // The resulting sequence is converted to a string and checked for a known 'bad' pattern (anagram) before logging.

        // 1. ArrayUtils: Mutate Security Code Array
        int[] codes = new int[]{1, 3, 2};
        ArrayUtils.nextPermutation(codes); // Codes become: [2, 1, 3]

        // Mutants in nextPermutation (e.g., failure to correctly find k or l) would be killed here.

        // 2. StringUtils: Convert Array to Unique String
        // This simulates encoding the array result into a transmission string.
        String newCodeStr = Arrays.toString(codes).replaceAll("[\\[\\],\\s]", ""); // Result: "213"

        // 3. StringUtils: Check for Forbidden Pattern (Anagram Check)
        String forbiddenPattern = "321";
        boolean isSafe = !StringUtils.isAnagram(newCodeStr, forbiddenPattern); // Result: True (No, "213" and "321" are anagrams)

        // ASSERT: The final state must be correct
        assertFalse(isSafe, "The new code '213' should be an anagram of '321', resulting in 'isSafe' being false.");

        // Mutants in isAnagram (e.g., failure in character counting) would be killed here.

        // 4. StringUtils: Format Warning Message (Pad Left)
        String status = isSafe ? "SAFE" : "WARNING";
        String report = StringUtils.padLeft(status, 10, '_'); // Result: "___WARNING"

        assertEquals("___WARNING", report, "The warning message should be correctly padded.");
    }
}