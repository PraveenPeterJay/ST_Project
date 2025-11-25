package org.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.utils.*;

import java.util.*;
import java.util.stream.Collectors;

@SpringBootApplication(scanBasePackages = {"org.api", "org.utils"})
@RestController
@RequestMapping("/api")
public class UtilityController {

    public static void main(String[] args) {
        SpringApplication.run(UtilityController.class, args);
    }

    // --- Generic Request DTO ---
    // This DTO captures all possible input types we might send from the frontend
    static class RequestDTO {
        public String category;
        public String functionName;
        public String arr;    // For int[] or Stack input
        public String arr2;   // For second array/stack
        public String s1;     // For String inputs
        public String s2;     // For second String or delimiters
        public String val;    // For int values (k, target, numNodes)
        public String val2;   // For second int values
    }

    // --- Generic Response DTO ---
    static class ResponseDTO {
        public String status;
        public String result;
        public ResponseDTO(String status, String result) {
            this.status = status;
            this.result = result;
        }
    }

    @PostMapping("/execute")
    public ResponseDTO execute(@RequestBody RequestDTO req) {
        try {
            String res = switch (req.category) {
                case "array" -> handleArray(req);
                case "string" -> handleString(req);
                case "graph" -> handleGraph(req);
                case "dp" -> handleDP(req);
                case "stack" -> handleStack(req);
                default -> throw new IllegalArgumentException("Unknown category");
            };
            return new ResponseDTO("SUCCESS", res);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseDTO("ERROR", e.getMessage());
        }
    }

    // --- Parsing Helpers ---
    private int[] parseIntArray(String s) {
        if(s==null || s.trim().isEmpty()) return new int[0];
        return Arrays.stream(s.trim().split("[,\\s]+")).mapToInt(Integer::parseInt).toArray();
    }
    private int parseInt(String s) { return Integer.parseInt(s.trim()); }

    // --- Handlers ---

    private String handleArray(RequestDTO r) {
        int[] arr = parseIntArray(r.arr);
        int[] arr2 = (r.arr2 != null) ? parseIntArray(r.arr2) : null;
        int val = (r.val != null) ? parseInt(r.val) : 0;

        return switch (r.functionName) {
            case "min" -> String.valueOf(ArrayUtils.min(arr));
            case "max" -> String.valueOf(ArrayUtils.max(arr));
            case "mergeSort" -> { ArrayUtils.mergeSort(arr); yield Arrays.toString(arr); }
            case "twoSumUniquePairs" -> String.valueOf(ArrayUtils.twoSumUniquePairs(arr, val));
            case "majorityElement" -> String.valueOf(ArrayUtils.majorityElement(arr));
            case "longestSubarrayWithSum" -> String.valueOf(ArrayUtils.longestSubarrayWithSum(arr, val));
            case "maxProductSubarray" -> String.valueOf(ArrayUtils.maxProductSubarray(arr));
            case "nextPermutation" -> { ArrayUtils.nextPermutation(arr); yield Arrays.toString(arr); }
            case "partition" -> { int p = ArrayUtils.partition(arr, 0, arr.length-1); yield "Pivot Idx: " + p + ", Arr: " + Arrays.toString(arr); }
            case "rotate" -> { ArrayUtils.rotate(arr, val); yield Arrays.toString(arr); }
            case "hasIntersection" -> String.valueOf(ArrayUtils.hasIntersection(arr, arr2));
            case "isSorted" -> String.valueOf(ArrayUtils.isSorted(arr));
            case "binarySearchFirstOccurrence" -> String.valueOf(ArrayUtils.binarySearchFirstOccurrence(arr, val));
            default -> "Function not found";
        };
    }

    private String handleString(RequestDTO r) {
        String s1 = r.s1;
        String s2 = r.s2;
        int val = (r.val != null) ? parseInt(r.val) : 0;

        return switch (r.functionName) {
            case "reverse" -> StringUtils.reverse(s1);
            case "isPalindrome" -> StringUtils.isPalindrome(s1) ? "True" : "False";
            case "longestSubstringWithoutRepeatingCharacters" -> String.valueOf(StringUtils.longestSubstringWithoutRepeatingCharacters(s1));
            case "isAnagram" -> StringUtils.isAnagram(s1, s2) ? "True" : "False";
            case "countOccurrences" -> String.valueOf(StringUtils.countOccurrences(s1, s2));
            case "toTitleCase" -> StringUtils.toTitleCase(s1);
            case "truncate" -> StringUtils.truncate(s1, val);
            case "countUniqueWords" -> String.valueOf(StringUtils.countUniqueWords(s1));
            case "cleanWhitespace" -> StringUtils.cleanWhitespace(s1);
            case "padLeft" -> StringUtils.padLeft(s1, val, (s2 != null && !s2.isEmpty()) ? s2.charAt(0) : ' ');
            case "splitByLength" -> Arrays.toString(StringUtils.splitByLength(s1, s2, val));
            case "removeDuplicateChars" -> StringUtils.removeDuplicateChars(s1);
            case "isAlphabetic" -> StringUtils.isAlphabetic(s1) ? "True" : "False";
            default -> "Function not found";
        };
    }

    private String handleGraph(RequestDTO r) {
        int numNodes = parseInt(r.val);
        // r.s1 contains edges like "0 1\n1 2"
        // Parse Adjacency List
        List<List<Integer>> adj = new ArrayList<>();
        for(int i=0; i<numNodes; i++) adj.add(new ArrayList<>());

        // Basic parser for u v
        if(r.s1 != null && !r.s1.isEmpty()) {
            String[] lines = r.s1.split("\n");
            for(String line : lines) {
                String[] parts = line.trim().split("\\s+");
                if(parts.length >= 2) {
                    adj.get(Integer.parseInt(parts[0])).add(Integer.parseInt(parts[1]));
                }
            }
        }

        // Special parser for weighted Dijkstra
        List<Map<Integer, Integer>> weightedAdj = new ArrayList<>();
        if(r.functionName.equals("shortestPathDijkstra")) {
            for(int i=0; i<numNodes; i++) weightedAdj.add(new HashMap<>());
            if(r.s1 != null) {
                for(String line : r.s1.split("\n")) {
                    String[] parts = line.trim().split("\\s+");
                    if(parts.length >= 3) {
                        weightedAdj.get(Integer.parseInt(parts[0])).put(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
                    }
                }
            }
        }

        int startNode = (r.val2 != null) ? parseInt(r.val2) : 0;

        return switch (r.functionName) {
            case "shortestPathBFS" -> Arrays.toString(GraphUtils.shortestPathBFS(adj, numNodes, startNode));
            case "traverseDFS" -> GraphUtils.traverseDFS(adj, numNodes, startNode).toString();
            case "containsCycleUndirected" -> String.valueOf(GraphUtils.containsCycleUndirected(adj, numNodes));
            case "maxDegree" -> String.valueOf(GraphUtils.maxDegree(adj, numNodes));
            case "topologicalSortKahn" -> GraphUtils.topologicalSortKahn(adj, numNodes).toString();
            case "isTree" -> String.valueOf(GraphUtils.isTree(adj, numNodes));
            case "countConnectedComponents" -> String.valueOf(GraphUtils.countConnectedComponents(adj, numNodes));
            case "shortestPathDijkstra" -> Arrays.toString(GraphUtils.shortestPathDijkstra(weightedAdj, numNodes, startNode));
            case "isSinkNode" -> String.valueOf(GraphUtils.isSinkNode(adj, numNodes, startNode)); // reusing val2 as check node
            case "computeIndegrees" -> Arrays.toString(GraphUtils.computeIndegrees(adj, numNodes));
            default -> "Function not found";
        };
    }

    private String handleDP(RequestDTO r) {
        int[] arr = parseIntArray(r.arr);
        int val = (r.val != null) ? parseInt(r.val) : 0;

        return switch (r.functionName) {
            case "countSubsetsWithSumK" -> String.valueOf(DPUtils.countSubsetsWithSumK(arr, val));
            case "countPartitionsWithGivenDifference" -> String.valueOf(DPUtils.countPartitionsWithGivenDifference(arr, val));
            case "maxSumNonAdjacent" -> String.valueOf(DPUtils.maxSumNonAdjacent(arr));
            case "longestCommonSubsequence" -> DPUtils.longestCommonSubsequence(r.s1, r.s2);
            case "longestPalindromicSubsequence" -> DPUtils.longestPalindromicSubsequence(r.s1);
            case "longestCommonSubstring" -> DPUtils.longestCommonSubstring(r.s1, r.s2);
            case "minInsertionsToMakePalindrome" -> String.valueOf(DPUtils.minInsertionsToMakePalindrome(r.s1));
            case "longestIncreasingSubsequence" -> DPUtils.longestIncreasingSubsequence(arr).toString();
            case "minPathSum" -> {
                // Parse Grid from s1 ("1,2;3,4")
                String[] rows = r.s1.split("\n");
                int R = parseInt(r.val);
                int C = parseInt(r.val2);
                int[][] grid = new int[R][C];
                for(int i=0; i<R; i++) {
                    String[] cols = rows[i].trim().split("[,\\s]+");
                    for(int j=0; j<C; j++) grid[i][j] = Integer.parseInt(cols[j]);
                }
                yield String.valueOf(DPUtils.minPathSum(grid));
            }
            default -> "Function not found";
        };
    }

    private String handleStack(RequestDTO r) {
        // Parse Stack from String (space sep)
        Stack<Integer> stackInt = new Stack<>();
        Stack<String> stackStr = new Stack<>();

        if (r.arr != null && !r.arr.isEmpty()) {
            String[] parts = r.arr.trim().split("\\s+");
            // Input usually Top-to-Bottom, we need to push in reverse to maintain order
            List<String> list = Arrays.asList(parts);
            Collections.reverse(list);
            for(String p : list) {
                try { stackInt.push(Integer.parseInt(p)); } catch(Exception e){}
                stackStr.push(p);
            }
        }

        return switch (r.functionName) {
            case "reverseStack" -> { StackUtils.reverseStack(stackInt); yield stackInt.toString(); }
            case "isBalanced" -> String.valueOf(StackUtils.isBalanced(r.s1));
            case "sortStack" -> { StackUtils.sortStack(stackInt); yield stackInt.toString(); }
            case "evaluatePostfix" -> String.valueOf(StackUtils.evaluatePostfix(r.s1));
            case "nextGreaterElement" -> Arrays.toString(StackUtils.nextGreaterElement(parseIntArray(r.arr)));
            case "longestValidParentheses" -> String.valueOf(StackUtils.longestValidParentheses(r.s1));
            case "infixToPostfix" -> StackUtils.infixToPostfix(r.s1);
            case "findMiddleElement" -> String.valueOf(StackUtils.findMiddleElement(stackInt));
            case "isPermutation" -> {
                // Need second stack
                Stack<String> stackStr2 = new Stack<>();
                if(r.arr2 != null) {
                    List<String> list2 = Arrays.asList(r.arr2.trim().split("\\s+"));
                    Collections.reverse(list2);
                    for(String p : list2) stackStr2.push(p);
                }
                yield String.valueOf(StackUtils.isPermutation(stackStr, stackStr2));
            }
            case "removeAllOccurrences" -> { StackUtils.removeAllOccurrences(stackStr, r.s1); yield stackStr.toString(); }
            default -> "Function not found";
        };
    }
}