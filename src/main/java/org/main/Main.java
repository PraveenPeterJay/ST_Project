package org.main;

import java.util.*;

import org.utils.ArrayUtils;
import org.utils.StringUtils;
import org.utils.GraphUtils;
import org.utils.DPUtils;
import org.utils.StackUtils;


/**
 * Main application class providing a menu-driven interface to test Array, String, and Graph utilities.
 */
public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final int MAX_NODES = 10; // Limit for interactive graph size

    public static void main(String[] args) {
        System.out.println("--- Array, String, and Graph Utilities Testing Menu ---");
        boolean running = true;
        while (running) {
            displayMenu();
            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                System.out.println();

                switch (choice) {
                    // ArrayUtils Functions (1-12)
                    case 1: getMinMax(); break;
                    case 2: getMergeSort(); break;
                    case 3: getTwoSumUniquePairs(); break;
                    case 4: getMajorityElement(); break;
                    case 5: getLongestSubarrayWithSum(); break;
                    case 6: getMaxProductSubarray(); break;
                    case 7: getNextPermutation(); break;
                    case 9: getRotate(); break;
                    case 10: getHasIntersection(); break;
                    case 11: getIsSorted(); break;
                    case 12: getBinarySearchFirstOccurrence(); break;

                    // StringUtils Functions (13-25)
                    case 13: getReverseString(); break;
                    case 14: getIsPalindrome(); break;
                    case 15: getLongestSubstringWithoutRepeatingCharacters(); break;
                    case 16: getIsAnagram(); break;
                    case 17: getCountOccurrences(); break;
                    case 18: getToTitleCase(); break;
                    case 20: getCountUniqueWords(); break;
                    case 21: getCleanWhitespace(); break;
                    case 22: getPadLeft(); break;
                    case 23: getSplitByLength(); break;
                    case 24: getRemoveDuplicateChars(); break;
                    case 25: getIsAlphabetic(); break;

                    // GraphUtils Functions (26-35)
                    case 26: getShortestPathBFS(); break;
                    case 27: getTraverseDFS(); break;
                    case 28: getContainsCycleUndirected(); break;
                    case 29: getMaxDegree(); break;
                    case 30: getTopologicalSortKahn(); break;
                    case 31: getIsTree(); break;
                    case 32: getCountConnectedComponents(); break;
                    case 33: getShortestPathDijkstra(); break;
                    case 34: getIsSinkNode(); break;
                    case 35: getComputeIndegrees(); break;

                    // DPUtils Functions (36-43)
                    case 36: getCountSubsetsWithSumK(); break;
                    case 37: getCountPartitionsWithGivenDifference(); break;
                    case 38: getMaxSumNonAdjacent(); break;
                    case 39: getLongestCommonSubsequence(); break;
                    case 40: getLongestPalindromicSubsequence(); break;
                    case 41: getLongestCommonSubstring(); break;
                    case 42: getMinInsertionsToMakePalindrome(); break;
                    case 43: getLongestIncreasingSubsequence(); break;
                    case 44: getMinPathSum(); break;

                    // StackUtils Functions (45-54)
                    case 45: getReverseStack(); break;
                    case 46: getIsBalanced(); break;
                    case 47: getSortStack(); break;
                    case 48: getEvaluatePostfix(); break;
                    case 49: getNextGreaterElement(); break;
                    case 50: getLongestValidParentheses(); break;
                    case 51: getInfixToPostfix(); break;
                    case 52: findMiddleElement(); break;
                    case 53: isPermutation(); break;
                    case 54: removeAllOccurrences(); break;

                    case 0:
                        running = false;
                        System.out.println("Exiting application. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 0 and 35.");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nERROR: Invalid input. Please enter a number.\n");
                scanner.nextLine(); // Clear the invalid input
            } catch (IllegalArgumentException e) {
                System.out.println("\nLOGIC ERROR: " + e.getMessage() + "\n");
            } catch (Exception e) {
                System.out.println("\nAn unexpected error occurred: " + e.getMessage() + "\n");
            }
        }
        scanner.close();
    }

    // --- Graph Input Helpers ---

    /**
     * Reads a simple unweighted adjacency list from the user.
     * Edge format: u v (space separated, one per line). Stop with empty line.
     */
    private static List<List<Integer>> readUnweightedGraph(int numNodes) {
        List<List<Integer>> adj = new ArrayList<>(numNodes);
        for (int i = 0; i < numNodes; i++) {
            adj.add(new ArrayList<>());
        }
        System.out.println("Enter edges as 'u v' (0 to " + (numNodes - 1) + "), one per line. Type 'end' to finish:");
        String line;
        while (!(line = scanner.nextLine().trim()).equalsIgnoreCase("end")) {
            if (line.isEmpty()) continue;
            try {
                String[] parts = line.split("\\s+");
                int u = Integer.parseInt(parts[0]);
                int v = Integer.parseInt(parts[1]);
                if (u < 0 || u >= numNodes || v < 0 || v >= numNodes) {
                    System.out.println("Node index out of bounds. Please re-enter.");
                    continue;
                }
                adj.get(u).add(v);
            } catch (Exception e) {
                System.out.println("Invalid format. Use 'u v'. Please re-enter.");
            }
        }
        return adj;
    }

    /**
     * Reads a weighted adjacency list from the user.
     * Edge format: u v weight.
     */
    private static List<Map<Integer, Integer>> readWeightedGraph(int numNodes) {
        List<Map<Integer, Integer>> adj = new ArrayList<>(numNodes);
        for (int i = 0; i < numNodes; i++) {
            adj.add(new HashMap<>());
        }
        System.out.println("Enter weighted edges as 'u v w' (w>=0). Type 'end' to finish:");
        String line;
        while (!(line = scanner.nextLine().trim()).equalsIgnoreCase("end")) {
            if (line.isEmpty()) continue;
            try {
                String[] parts = line.split("\\s+");
                int u = Integer.parseInt(parts[0]);
                int v = Integer.parseInt(parts[1]);
                int w = Integer.parseInt(parts[2]);
                if (u < 0 || u >= numNodes || v < 0 || v >= numNodes || w < 0) {
                    System.out.println("Input out of range. Please re-enter.");
                    continue;
                }
                adj.get(u).put(v, w);
            } catch (Exception e) {
                System.out.println("Invalid format. Use 'u v w'. Please re-enter.");
            }
        }
        return adj;
    }


    /**
     * Displays the menu options to the user.
     */
    private static void displayMenu() {
        System.out.println("\n---------------------------------------------------");
        System.out.println("Array Utilities (1-12):");
        System.out.println("1. Min and Max | 2. Merge Sort | 3. 2Sum Pairs | 4. Majority Element | 5. Longest Subarray Sum | 6. Max Product Subarray | 7. Next Permutation | 8. Partition | 9. Rotate Right | 10. Check Intersection | 11. Is Sorted | 12. Binary Search First");
        System.out.println("---------------------------------------------------");
        System.out.println("String Utilities (13-25):");
        System.out.println("13. Reverse | 14. Check Palindrome | 15. Longest Unique Substring | 16. Check Anagram | 17. Count Occurrences | 18. To Title Case | 19. Truncate | 20. Count Unique Words | 21. Clean Whitespace | 22. Pad Left | 23. Split by Length | 24. Remove Duplicate Chars | 25. Is Alphabetic");
        System.out.println("---------------------------------------------------");
        System.out.println("Graph Utilities (26-35):");
        System.out.println("26. Shortest Path (BFS) | 27. DFS Traversal | 28. Contains Cycle (Undirected) | 29. Max Degree | 30. Topological Sort (Kahn's) | 31. Is Tree | 32. Count Connected Components | 33. Shortest Path (Dijkstra's) | 34. Is Sink Node | 35. Compute Indegrees");
        System.out.println("---------------------------------------------------");
        System.out.println("DP Utilities (36-44):");
        System.out.println("36. Count Subsets Sum K | 37. Count Partitions Diff | 38. Max Sum Non-Adjacent | 39. Longest Common Subsequence | 40. Longest Palindromic Subsequence | 41. Longest Common Substring | 42. Min Insertions Palindrome | 43. Longest Increasing Subsequence | 44. Min Path Sum (Grid)");
        System.out.println("---------------------------------------------------");
        System.out.println("Stack Utilities (45-54):");
        System.out.println("45. Reverse Stack | 46. Is Balanced | 47. Sort Stack | 48. Evaluate Postfix | 49. Next Greater Element | 50. Longest Valid Parentheses | 51. Infix to Postfix | 52. Find Middle | 53. Is Permutation | 54. Remove All Occurrences");
        System.out.println("0. Exit");
        System.out.println("---------------------------------------------------");
        System.out.print("Enter choice: ");
    }

    /**
     * Helper to read an integer array from user input.
     */
    private static int[] readIntArray() {
        System.out.print("Enter array elements (space-separated integers, e.g., 1 5 -2 8): ");
        String line = scanner.nextLine().trim();
        if (line.isEmpty()) {
            return new int[0];
        }
        try {
            return Arrays.stream(line.split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        } catch (NumberFormatException e) {
            System.out.println("ERROR: Invalid number format in input.");
            return null;
        }
    }
    /**
     * Helper to read a stack from user input.
     * Elements are entered space-separated, top-to-bottom order.
     * The method reverses input order to push correctly (LIFO).
     */
    private static <T> Stack<T> readStack(Class<T> type) {
        System.out.print("Enter stack elements (space-separated, top-to-bottom order): ");
        String line = scanner.nextLine().trim();
        if (line.isEmpty()) return new Stack<>();

        Stack<T> stack = new Stack<>();

        // Split input and reverse the list so we push from the bottom up.
        List<String> elements = new ArrayList<>(Arrays.asList(line.split("\\s+")));
        Collections.reverse(elements);

        for (String element : elements) {
            try {
                if (type == Integer.class) {
                    stack.push(type.cast(Integer.parseInt(element)));
                } else if (type == String.class) {
                    stack.push(type.cast(element));
                } else {
                    System.out.println("Unsupported stack type."); return new Stack<>();
                }
            } catch (Exception e) {
                System.out.println("Invalid element format: " + element);
                return new Stack<>();
            }
        }
        return stack;
    }
    // --- ArrayUtils Method Implementations (1-7 Existing) ---

    private static void getMinMax() {
        int[] arr = readIntArray();
        if (arr == null) return;

        try {
            int minVal = ArrayUtils.min(arr);
            int maxVal = ArrayUtils.max(arr);
            System.out.printf("Original Array: %s\n", Arrays.toString(arr));
            System.out.printf("Minimum Element: %d\n", minVal);
            System.out.printf("Maximum Element: %d\n", maxVal);
        } catch (IllegalArgumentException e) {
            System.out.println("Result: " + e.getMessage());
        }
    }

    private static void getMergeSort() {
        int[] arr = readIntArray();
        if (arr == null) return;
        if (arr.length == 0) {
            System.out.println("Array is empty. Nothing to sort.");
            return;
        }

        System.out.printf("Original Array: %s\n", Arrays.toString(arr));
        ArrayUtils.mergeSort(arr);
        System.out.printf("Sorted Array:   %s\n", Arrays.toString(arr));
    }

    private static void getTwoSumUniquePairs() {
        int[] arr = readIntArray();
        if (arr == null) return;

        System.out.print("Enter target sum (integer): ");
        int target = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        int count = ArrayUtils.twoSumUniquePairs(arr, target);
        System.out.printf("Original Array: %s\n", Arrays.toString(arr));
        System.out.printf("Target Sum: %d\n", target);
        System.out.printf("Number of Unique Pairs: %d\n", count);
    }

    private static void getMajorityElement() {
        int[] arr = readIntArray();
        if (arr == null) return;

        int element = ArrayUtils.majorityElement(arr);
        System.out.printf("Original Array: %s\n", Arrays.toString(arr));
        if (element != Integer.MIN_VALUE) {
            System.out.printf("Majority Element (appears > n/2 times): %d\n", element);
        } else {
            System.out.println("Result: No Majority Element found.");
        }
    }

    private static void getLongestSubarrayWithSum() {
        int[] arr = readIntArray();
        if (arr == null) return;

        System.out.print("Enter target sum (integer): ");
        int target = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        int length = ArrayUtils.longestSubarrayWithSum(arr, target);
        System.out.printf("Original Array: %s\n", Arrays.toString(arr));
        System.out.printf("Target Sum: %d\n", target);
        System.out.printf("Length of Longest Subarray: %d\n", length);
    }

    private static void getMaxProductSubarray() {
        int[] arr = readIntArray();
        if (arr == null) return;

        try {
            long product = ArrayUtils.maxProductSubarray(arr);
            System.out.printf("Original Array: %s\n", Arrays.toString(arr));
            System.out.printf("Maximum Product of Contiguous Subarray: %d\n", product);
        } catch (IllegalArgumentException e) {
            System.out.println("Result: " + e.getMessage());
        }
    }

    private static void getNextPermutation() {
        int[] arr = readIntArray();
        if (arr == null) return;
        if (arr.length <= 1) {
            System.out.println("Array has 0 or 1 element. No next permutation.");
            return;
        }

        System.out.printf("Original Array: %s\n", Arrays.toString(arr));
        ArrayUtils.nextPermutation(arr);
        System.out.printf("Next Permutation: %s\n", Arrays.toString(arr));
    }

    // --- ArrayUtils Method Implementations (8-12 New) ---



    private static void getRotate() {
        int[] arr = readIntArray();
        if (arr == null || arr.length == 0) return;

        System.out.print("Enter steps (k) to rotate right: ");
        int k = scanner.nextInt();
        scanner.nextLine();

        System.out.printf("Original Array: %s\n", Arrays.toString(arr));
        ArrayUtils.rotate(arr, k);
        System.out.printf("Rotated Array (k=%d): %s\n", k, Arrays.toString(arr));
    }

    private static void getHasIntersection() {
        System.out.println("NOTE: Arrays should be sorted for two-pointer check.");
        System.out.print("Array 1: ");
        int[] arr1 = readIntArray();
        if (arr1 == null) return;
        System.out.print("Array 2: ");
        int[] arr2 = readIntArray();
        if (arr2 == null) return;

        boolean result = ArrayUtils.hasIntersection(arr1, arr2);
        System.out.printf("Array 1: %s\n", Arrays.toString(arr1));
        System.out.printf("Array 2: %s\n", Arrays.toString(arr2));
        System.out.printf("Do they have a common element? %s\n", result);
    }

    private static void getIsSorted() {
        int[] arr = readIntArray();
        if (arr == null) return;

        boolean result = ArrayUtils.isSorted(arr);
        System.out.printf("Array: %s\n", Arrays.toString(arr));
        System.out.printf("Is the array sorted? %s\n", result);
    }

    private static void getBinarySearchFirstOccurrence() {
        System.out.println("NOTE: Array must be sorted for this operation.");
        int[] arr = readIntArray();
        if (arr == null) return;

        System.out.print("Enter target value: ");
        int target = scanner.nextInt();
        scanner.nextLine();

        int index = ArrayUtils.binarySearchFirstOccurrence(arr, target);
        System.out.printf("Array: %s\n", Arrays.toString(arr));
        System.out.printf("Target %d found first at index: %d\n", target, index);
    }

    // --- StringUtils Method Implementations (13-20 Existing) ---

    private static void getReverseString() {
        System.out.print("Enter a string to reverse: ");
        String str = scanner.nextLine();
        String reversed = StringUtils.reverse(str);
        System.out.printf("Original String: %s\n", str);
        System.out.printf("Reversed String: %s\n", reversed);
    }

    private static void getIsPalindrome() {
        System.out.print("Enter a string to check for palindrome: ");
        String str = scanner.nextLine();
        boolean isPal = StringUtils.isPalindrome(str);
        System.out.printf("Original String: %s\n", str);
        System.out.printf("Is Palindrome? %s\n", isPal);
    }

    private static void getLongestSubstringWithoutRepeatingCharacters() {
        System.out.print("Enter a string: ");
        String str = scanner.nextLine();
        int length = StringUtils.longestSubstringWithoutRepeatingCharacters(str);
        System.out.printf("Original String: %s\n", str);
        System.out.printf("Length of Longest Substring Without Repeating Characters: %d\n", length);
    }

    private static void getIsAnagram() {
        System.out.print("Enter the first string: ");
        String s1 = scanner.nextLine();
        System.out.print("Enter the second string: ");
        String s2 = scanner.nextLine();
        boolean isA = StringUtils.isAnagram(s1, s2);
        System.out.printf("Strings: '%s' and '%s'\n", s1, s2);
        System.out.printf("Are Anagrams? %s\n", isA);
    }

    private static void getCountOccurrences() {
        System.out.print("Enter the source string: ");
        String source = scanner.nextLine();
        System.out.print("Enter the target substring: ");
        String target = scanner.nextLine();
        int count = StringUtils.countOccurrences(source, target);
        System.out.printf("Source: '%s', Target: '%s'\n", source, target);
        System.out.printf("Occurrences Count: %d\n", count);
    }

    private static void getToTitleCase() {
        System.out.print("Enter a sentence: ");
        String str = scanner.nextLine();
        String titleCase = StringUtils.toTitleCase(str);
        System.out.printf("Original: '%s'\n", str);
        System.out.printf("Title Case: '%s'\n", titleCase);
    }



    private static void getCountUniqueWords() {
        System.out.print("Enter a sentence: ");
        String str = scanner.nextLine();
        int count = StringUtils.countUniqueWords(str);
        System.out.printf("Sentence: '%s'\n", str);
        System.out.printf("Unique Word Count: %d\n", count);
    }

    // --- StringUtils Method Implementations (21-25 New) ---

    private static void getCleanWhitespace() {
        System.out.print("Enter a string with messy spacing: ");
        String str = scanner.nextLine();
        String cleaned = StringUtils.cleanWhitespace(str);
        System.out.printf("Original: '%s'\n", str);
        System.out.printf("Cleaned: '%s'\n", cleaned);
    }

    private static void getPadLeft() {
        System.out.print("Enter a string: ");
        String str = scanner.nextLine();
        System.out.print("Enter total desired length: ");
        int length = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter padding character (e.g., #): ");
        char padChar = scanner.nextLine().charAt(0);

        String padded = StringUtils.padLeft(str, length, padChar);
        System.out.printf("Original: '%s'\n", str);
        System.out.printf("Padded: '%s'\n", padded);
    }

    private static void getSplitByLength() {
        System.out.print("Enter string to split (e.g., apple,bat,banana): ");
        String str = scanner.nextLine();
        System.out.print("Enter delimiter (e.g., ,): ");
        String delimiter = scanner.nextLine();
        System.out.print("Enter minimum required length for parts: ");
        int minLength = scanner.nextInt();
        scanner.nextLine();

        String[] parts = StringUtils.splitByLength(str, delimiter, minLength);
        System.out.printf("Resulting Parts (min length %d): %s\n", minLength, Arrays.toString(parts));
    }

    private static void getRemoveDuplicateChars() {
        System.out.print("Enter a string: ");
        String str = scanner.nextLine();
        String unique = StringUtils.removeDuplicateChars(str);
        System.out.printf("Original: '%s'\n", str);
        System.out.printf("Unique Chars: '%s'\n", unique);
    }

    private static void getIsAlphabetic() {
        System.out.print("Enter a string: ");
        String str = scanner.nextLine();
        boolean result = StringUtils.isAlphabetic(str);
        System.out.printf("String: '%s'\n", str);
        System.out.printf("Contains only letters? %s\n", result);
    }
    // --- GraphUtils Implementations ---

    private static void getShortestPathBFS() {
        System.out.print("Enter number of nodes (max " + MAX_NODES + "): ");
        int numNodes = scanner.nextInt(); scanner.nextLine();
        if (numNodes <= 0 || numNodes > MAX_NODES) { System.out.println("Invalid number of nodes."); return; }
        List<List<Integer>> adj = readUnweightedGraph(numNodes);
        System.out.print("Enter start node: "); int startNode = scanner.nextInt(); scanner.nextLine();
        int[] dist = GraphUtils.shortestPathBFS(adj, numNodes, startNode);
        System.out.printf("Shortest Distances (from %d): %s\n", startNode, Arrays.toString(dist));
    }

    private static void getTraverseDFS() {
        System.out.print("Enter number of nodes (max " + MAX_NODES + "): ");
        int numNodes = scanner.nextInt(); scanner.nextLine();
        if (numNodes <= 0 || numNodes > MAX_NODES) { System.out.println("Invalid number of nodes."); return; }
        List<List<Integer>> adj = readUnweightedGraph(numNodes);
        System.out.print("Enter start node: "); int startNode = scanner.nextInt(); scanner.nextLine();
        List<Integer> result = GraphUtils.traverseDFS(adj, numNodes, startNode);
        System.out.printf("DFS Traversal Order: %s\n", result);
    }

    private static void getContainsCycleUndirected() {
        System.out.print("Enter number of nodes (max " + MAX_NODES + "): ");
        int numNodes = scanner.nextInt(); scanner.nextLine();
        if (numNodes <= 0 || numNodes > MAX_NODES) { System.out.println("Invalid number of nodes."); return; }
        List<List<Integer>> adj = readUnweightedGraph(numNodes);
        System.out.printf("Contains Cycle? %s (Assumes Undirected Edges Entered Bidirectionally)\n", GraphUtils.containsCycleUndirected(adj, numNodes));
    }

    private static void getMaxDegree() {
        System.out.print("Enter number of nodes (max " + MAX_NODES + "): ");
        int numNodes = scanner.nextInt(); scanner.nextLine();
        if (numNodes <= 0 || numNodes > MAX_NODES) { System.out.println("Invalid number of nodes."); return; }
        List<List<Integer>> adj = readUnweightedGraph(numNodes);
        System.out.printf("Maximum Degree: %d\n", GraphUtils.maxDegree(adj, numNodes));
    }

    private static void getTopologicalSortKahn() {
        System.out.print("Enter number of nodes (max " + MAX_NODES + "): ");
        int numNodes = scanner.nextInt(); scanner.nextLine();
        if (numNodes <= 0 || numNodes > MAX_NODES) { System.out.println("Invalid number of nodes."); return; }
        List<List<Integer>> adj = readUnweightedGraph(numNodes);
        List<Integer> result = GraphUtils.topologicalSortKahn(adj, numNodes);
        if (result.isEmpty() && numNodes > 0) {
            System.out.println("Result: Empty list (Cycle detected or input error).");
        } else {
            System.out.printf("Topological Order: %s\n", result);
        }
    }

    private static void getIsTree() {
        System.out.print("Enter number of nodes (max " + MAX_NODES + "): ");
        int numNodes = scanner.nextInt(); scanner.nextLine();
        if (numNodes <= 0 || numNodes > MAX_NODES) { System.out.println("Invalid number of nodes."); return; }
        List<List<Integer>> adj = readUnweightedGraph(numNodes);
        System.out.printf("Is Tree? %s (Checks connectivity and V = E + 1)\n", GraphUtils.isTree(adj, numNodes));
    }

    private static void getCountConnectedComponents() {
        System.out.print("Enter number of nodes (max " + MAX_NODES + "): ");
        int numNodes = scanner.nextInt(); scanner.nextLine();
        if (numNodes <= 0 || numNodes > MAX_NODES) { System.out.println("Invalid number of nodes."); return; }
        List<List<Integer>> adj = readUnweightedGraph(numNodes);
        System.out.printf("Number of Connected Components: %d\n", GraphUtils.countConnectedComponents(adj, numNodes));
    }

    private static void getShortestPathDijkstra() {
        System.out.print("Enter number of nodes (max " + MAX_NODES + "): ");
        int numNodes = scanner.nextInt(); scanner.nextLine();
        if (numNodes <= 0 || numNodes > MAX_NODES) { System.out.println("Invalid number of nodes."); return; }
        List<Map<Integer, Integer>> adj = readWeightedGraph(numNodes);
        System.out.print("Enter start node: "); int startNode = scanner.nextInt(); scanner.nextLine();
        int[] dist = GraphUtils.shortestPathDijkstra(adj, numNodes, startNode);
        System.out.printf("Dijkstra Distances (from %d): %s\n", startNode, Arrays.toString(dist));
    }

    private static void getIsSinkNode() {
        System.out.print("Enter number of nodes (max " + MAX_NODES + "): ");
        int numNodes = scanner.nextInt(); scanner.nextLine();
        if (numNodes <= 0 || numNodes > MAX_NODES) { System.out.println("Invalid number of nodes."); return; }
        List<List<Integer>> adj = readUnweightedGraph(numNodes);
        System.out.print("Enter node to check: "); int node = scanner.nextInt(); scanner.nextLine();
        System.out.printf("Is Node %d a Sink? %s\n", node, GraphUtils.isSinkNode(adj, numNodes, node));
    }

    private static void getComputeIndegrees() {
        System.out.print("Enter number of nodes (max " + MAX_NODES + "): ");
        int numNodes = scanner.nextInt(); scanner.nextLine();
        if (numNodes <= 0 || numNodes > MAX_NODES) { System.out.println("Invalid number of nodes."); return; }
        List<List<Integer>> adj = readUnweightedGraph(numNodes);
        int[] indegrees = GraphUtils.computeIndegrees(adj, numNodes);
        System.out.printf("Indegree Array: %s\n", Arrays.toString(indegrees));
    }

    private static String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private static void getCountSubsetsWithSumK() {
        int[] arr = readIntArray();
        System.out.print("Enter target sum K: ");
        int k = scanner.nextInt();
        scanner.nextLine();
        int count = DPUtils.countSubsetsWithSumK(arr, k);
        System.out.println("Count of subsets with sum " + k + ": " + count);
    }

    private static void getCountPartitionsWithGivenDifference() {
        int[] arr = readIntArray();
        System.out.print("Enter required difference (S1_sum - S2_sum): ");
        int diff = scanner.nextInt();
        scanner.nextLine();
        int count = DPUtils.countPartitionsWithGivenDifference(arr, diff);
        System.out.println("Count of partitions with difference " + diff + ": " + count);
    }

    private static void getMaxSumNonAdjacent() {
        int[] arr = readIntArray();
        int maxSum = DPUtils.maxSumNonAdjacent(arr);
        System.out.println("Maximum Non-Adjacent Sum: " + maxSum);
    }

    private static void getLongestCommonSubsequence() {
        String s1 = readString("Enter string 1: ");
        String s2 = readString("Enter string 2: ");
        String lcs = DPUtils.longestCommonSubsequence(s1, s2);
        System.out.println("Longest Common Subsequence (LCS): " + lcs + " (Length: " + lcs.length() + ")");
    }

    private static void getLongestPalindromicSubsequence() {
        String s = readString("Enter string: ");
        String lps = DPUtils.longestPalindromicSubsequence(s);
        System.out.println("Longest Palindromic Subsequence (LPS): " + lps + " (Length: " + lps.length() + ")");
    }

    private static void getLongestCommonSubstring() {
        String s1 = readString("Enter string 1: ");
        String s2 = readString("Enter string 2: ");
        String lcs = DPUtils.longestCommonSubstring(s1, s2);
        System.out.println("Longest Common Substring: " + lcs + " (Length: " + lcs.length() + ")");
    }

    private static void getMinInsertionsToMakePalindrome() {
        String s = readString("Enter string: ");
        int minInsertions = DPUtils.minInsertionsToMakePalindrome(s);
        System.out.println("Minimum insertions to make '" + s + "' a palindrome: " + minInsertions);
    }

    private static void getLongestIncreasingSubsequence() {
        int[] arr = readIntArray();
        List<Integer> lis = DPUtils.longestIncreasingSubsequence(arr);
        System.out.println("Longest Increasing Subsequence (LIS): " + lis + " (Length: " + lis.size() + ")");
    }

    // Helper to read a 2D grid from user input
    private static int[][] readGrid() {
        System.out.print("Enter number of rows: ");
        int rows = scanner.nextInt();
        System.out.print("Enter number of columns: ");
        int cols = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        int[][] grid = new int[rows][cols];
        System.out.println("Enter grid values row by row (comma-separated):");
        for (int i = 0; i < rows; i++) {
            System.out.print("Row " + i + ": ");
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) {
                System.out.println("Skipping empty row.");
                continue;
            }
            String[] tokens = line.split(",");
            if (tokens.length != cols) {
                System.err.println("Error: Row " + i + " must have " + cols + " columns. Using default 0s.");
                continue;
            }
            for (int j = 0; j < cols; j++) {
                try {
                    grid[i][j] = Integer.parseInt(tokens[j].trim());
                } catch (NumberFormatException e) {
                    System.err.println("Error: Invalid number format in cell (" + i + ", " + j + "). Using 0.");
                    grid[i][j] = 0;
                }
            }
        }
        return grid;
    }

    private static void getMinPathSum() {
        int[][] grid = readGrid();
        // Create a copy because minPathSum modifies the grid in-place
        int[][] gridCopy = Arrays.stream(grid).map(a -> Arrays.copyOf(a, a.length)).toArray(int[][]::new);

        int minSum = DPUtils.minPathSum(gridCopy);
        System.out.println("Minimum Path Sum (Top-Left to Bottom-Right): " + minSum);
    }
    // --- StackUtils Implementations (45-54) ---

    private static void getReverseStack() {
        Stack<Integer> stack = readStack(Integer.class);
        if (stack.isEmpty()) return;
        System.out.printf("Original (Bottom-to-Top): %s\n", stack);
        StackUtils.reverseStack(stack);
        System.out.printf("Reversed (Bottom-to-Top): %s\n", stack);
    }

    private static void getIsBalanced() {
        System.out.print("Enter expression: ");
        String expression = scanner.nextLine();
        System.out.printf("Is Balanced? %s\n", StackUtils.isBalanced(expression));
    }

    private static void getSortStack() {
        Stack<Integer> stack = readStack(Integer.class);
        if (stack.isEmpty()) return;
        System.out.printf("Original (Bottom-to-Top): %s\n", stack);
        StackUtils.sortStack(stack);
        System.out.printf("Sorted (Smallest at Bottom): %s\n", stack);
    }

    private static void getEvaluatePostfix() {
        System.out.print("Enter postfix expression (e.g., 2 3 * 4 +): ");
        String postfix = scanner.nextLine();
        try {
            System.out.printf("Result: %d\n", StackUtils.evaluatePostfix(postfix));
        } catch (IllegalArgumentException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    private static void getNextGreaterElement() {
        int[] arr = readIntArray();
        if (arr == null || arr.length == 0) return;
        int[] result = StackUtils.nextGreaterElement(arr);
        System.out.printf("Array: %s\n", Arrays.toString(arr));
        System.out.printf("NGE: %s\n", Arrays.toString(result));
    }

    private static void getLongestValidParentheses() {
        System.out.print("Enter string of parentheses: ");
        String s = scanner.nextLine();
        System.out.printf("Longest Valid Length: %d\n", StackUtils.longestValidParentheses(s));
    }

    private static void getInfixToPostfix() {
        System.out.print("Enter infix expression (e.g., A + B * C): ");
        String infix = scanner.nextLine();
        System.out.printf("Postfix: %s\n", StackUtils.infixToPostfix(infix));
    }

    private static void findMiddleElement() {
        Stack<Integer> stack = readStack(Integer.class);
        if (stack.isEmpty()) return;
        System.out.printf("Original (Bottom-to-Top): %s\n", stack);
        Integer middle = StackUtils.findMiddleElement(stack);
        System.out.printf("Middle Element: %d\n", middle);
        System.out.printf("Stack Restored: %s\n", stack);
    }

    private static void isPermutation() {
        System.out.println("Stack 1 (Top-to-Bottom order):");
        Stack<String> s1 = readStack(String.class);
        System.out.println("Stack 2 (Top-to-Bottom order):");
        Stack<String> s2 = readStack(String.class);

        // Since isPermutation destroys the stacks, we show the result once.
        System.out.printf("Are Permutations? %s\n", StackUtils.isPermutation(s1, s2));
        System.out.println("NOTE: Both stacks are destroyed in the process.");
    }

    private static void removeAllOccurrences() {
        Stack<String> stack = readStack(String.class);
        if (stack.isEmpty()) return;
        System.out.print("Enter item to remove: ");
        String item = scanner.nextLine();

        System.out.printf("Original (Bottom-to-Top): %s\n", stack);
        StackUtils.removeAllOccurrences(stack, item);
        System.out.printf("After Removal (Bottom-to-Top): %s\n", stack);
    }
}