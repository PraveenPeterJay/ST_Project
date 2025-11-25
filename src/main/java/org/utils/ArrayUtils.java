package org.utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Utility class containing various array manipulation and algorithm functions.
 * All methods are implemented to be static for easy access.
 */
public final class ArrayUtils {

    // Private constructor to prevent instantiation
    private ArrayUtils() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    // --- Utility Functions ---

    /**
     * Finds the minimum element in an array of integers.
     *
     * @param arr The input integer array.
     * @return The minimum element in the array.
     * @throws IllegalArgumentException if the array is null or empty.
     */
    public static int min(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty for min operation.");
        }

        int minValue = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < minValue) {
                minValue = arr[i];
            }
        }
        return minValue;
    }

    /**
     * Finds the maximum element in an array of integers.
     *
     * @param arr The input integer array.
     * @return The maximum element in the array.
     * @throws IllegalArgumentException if the array is null or empty.
     */
    public static int max(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty for max operation.");
        }

        int maxValue = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > maxValue) {
                maxValue = arr[i];
            }
        }
        return maxValue;
    }

    // --- Algorithm Implementations ---

    /**
     * Sorts the specified array into ascending order using the Merge Sort algorithm.
     * This implementation is recursive and stable.
     *
     * @param arr The array to be sorted. The array is sorted in-place.
     */
    public static void mergeSort(int[] arr) {
        if (arr == null) {
            return;
        }
        mergeSort(arr, new int[arr.length], 0, arr.length - 1);
    }

    /**
     * Recursive helper for the Merge Sort algorithm.
     *
     * @param arr The array containing the elements to be sorted.
     * @param temp A temporary array used for merging.
     * @param left The starting index of the subarray.
     * @param right The ending index of the subarray.
     */
    private static void mergeSort(int[] arr, int[] temp, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(arr, temp, left, mid);      // Sort first half
            mergeSort(arr, temp, mid + 1, right); // Sort second half
            merge(arr, temp, left, mid, right);   // Merge both halves
        }
    }

    /**
     * Merges two sorted subarrays into one sorted array segment.
     *
     * @param arr The array containing the two subarrays.
     * @param temp A temporary array for copying elements during merging.
     * @param left The starting index of the first subarray.
     * @param mid The ending index of the first subarray.
     * @param right The ending index of the second subarray.
     */
    private static void merge(int[] arr, int[] temp, int left, int mid, int right) {
        // Copy both halves into the temporary array
        for (int i = left; i <= right; i++) {
            temp[i] = arr[i];
        }

        int i = left;      // Current index for the left subarray
        int j = mid + 1;   // Current index for the right subarray
        int k = left;      // Current index for the main array

        // Merge back to the original array
        while (i <= mid && j <= right) {
            if (temp[i] <= temp[j]) { // Stable comparison
                arr[k] = temp[i];
                i++;
            } else {
                arr[k] = temp[j];
                j++;
            }
            k++;
        }

        // Copy remaining elements of the left half (if any)
        while (i <= mid) {
            arr[k] = temp[i];
            i++;
            k++;
        }
        // Right half remaining elements are already in place relative to the rest of the right half
        // No need to copy remaining elements of the right half if they are already in the array
        // because the copy operation at the beginning ensures they are in 'temp' and 'arr' after
        // the while loop finishes.
    }


    /**
     * Finds the number of unique pairs (i, j) such that arr[i] + arr[j] = target,
     * where i != j. Uses sorting and the two-pointer approach for O(N log N) time complexity.
     *
     * @param arr The input integer array.
     * @param target The target sum.
     * @return The count of unique pairs that sum up to the target.
     */
    public static int twoSumUniquePairs(int[] arr, int target) {
        if (arr == null) {
            return 0;
        }

        // 1. Sort the array (O(N log N))
        // Using built-in sort here, but for mutation testing purposes, we could use our own mergeSort.
        Arrays.sort(arr);

        int left = 0;
        int right = arr.length - 1;
        int uniquePairsCount = 0;

        while (left < right) {
            int currentSum = arr[left] + arr[right];

            if (currentSum == target) {
                // Found a pair. Increment count and move both pointers.
                uniquePairsCount++;

                // Skip duplicate elements from the left side
                int leftVal = arr[left];
                while (left < right && arr[left] == leftVal) {
                    left++;
                }

                // Skip duplicate elements from the right side
                int rightVal = arr[right];
                while (left < right && arr[right] == rightVal) {
                    right--;
                }
            } else if (currentSum < target) {
                // Sum is too small, need a larger number, move left pointer right
                left++;
            } else { // currentSum > target
                // Sum is too large, need a smaller number, move right pointer left
                right--;
            }
        }

        return uniquePairsCount;
    }


    /**
     * Finds the Majority Element, which is the element that appears more than
     * floor(n / 2) times. Uses the Boyer-Moore Voting Algorithm (O(N) time, O(1) space).
     *
     * @param arr The input integer array.
     * @return The majority element, or Integer.MIN_VALUE if no majority element exists.
     * Note: The problem statement usually guarantees a majority element exists.
     * We add a check for robustness if no such guarantee is made.
     */
    public static int majorityElement(int[] arr) {
        if (arr == null || arr.length == 0) {
            // Cannot determine majority element in an empty array
            return Integer.MIN_VALUE;
        }

        // Phase 1: Find a candidate
        int candidate = arr[0];
        int count = 1;

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == candidate) {
                count++;
            } else {
                count--;
                if (count == 0) {
                    candidate = arr[i];
                    count = 1;
                }
            }
        }

        // Phase 2: Verify the candidate (Necessary only if guarantee is not given)
        count = 0;
        for (int num : arr) {
            if (num == candidate) {
                count++;
            }
        }

        if (count > arr.length / 2) {
            return candidate;
        } else {
            // No majority element exists
            return Integer.MIN_VALUE;
        }
    }

    /**
     * Finds the length of the longest subarray whose elements sum up to a given target sum.
     * Uses a HashMap to store the prefix sum and its first occurrence index (O(N) time, O(N) space).
     *
     * @param arr The input integer array.
     * @param targetSum The desired sum.
     * @return The length of the longest subarray with the given sum, or 0 if none is found.
     */
    public static int longestSubarrayWithSum(int[] arr, int targetSum) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        // Stores the running prefix sum and the index of its first occurrence.
        // Key: prefixSum, Value: index
        Map<Integer, Integer> sumMap = new HashMap<>();
        sumMap.put(0, -1); // Base case: a sum of 0 exists before the start of the array (at index -1)

        int currentSum = 0;
        int maxLength = 0;

        for (int i = 0; i < arr.length; i++) {
            currentSum += arr[i];

            // Check if (currentSum - targetSum) has been seen before
            if (sumMap.containsKey(currentSum - targetSum)) {
                // The difference between the current index and the index of the required previous sum
                // gives the length of the subarray. We only update maxLength if a longer subarray is found.
                int length = i - sumMap.get(currentSum - targetSum);
                if (length > maxLength) {
                    maxLength = length;
                }
            }

            // Only store the FIRST occurrence of a sum to ensure we find the LONGEST subarray.
            if (!sumMap.containsKey(currentSum)) {
                sumMap.put(currentSum, i);
            }
        }

        return maxLength;
    }

    /**
     * Finds the maximum product of a contiguous subarray.
     * Uses Dynamic Programming to track both the maximum and minimum product ending at the current position.
     * The minimum product is tracked because a large negative number multiplied by a negative number
     * can become a large positive number. (O(N) time, O(1) space beyond input)
     *
     * @param arr The input integer array.
     * @return The maximum product found in any contiguous subarray.
     * @throws IllegalArgumentException if the array is null or empty.
     */
    public static long maxProductSubarray(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty for max product subarray.");
        }

        // Initialize variables based on the first element
        long maxProductSoFar = arr[0]; // The global maximum product result
        long currentMaxProduct = arr[0]; // The maximum product ending at the current index i
        long currentMinProduct = arr[0]; // The minimum product ending at the current index i

        for (int i = 1; i < arr.length; i++) {
            int num = arr[i];

            // If the current number is negative, we swap currentMaxProduct and currentMinProduct
            // because multiplying by a negative number reverses the roles of max and min.
            if (num < 0) {
                long temp = currentMaxProduct;
                currentMaxProduct = currentMinProduct;
                currentMinProduct = temp;
            }

            // The new maximum product ending at i is the max of:
            // 1. The number itself (starting a new subarray)
            // 2. The previous max product multiplied by the number (extending the current max subarray)
            currentMaxProduct = Math.max((long)num, currentMaxProduct * num);

            // The new minimum product ending at i is the min of:
            // 1. The number itself (starting a new subarray)
            // 2. The previous min product multiplied by the number (extending the current min subarray)
            currentMinProduct = Math.min((long)num, currentMinProduct * num);

            // The overall max product is the maximum seen so far
            maxProductSoFar = Math.max(maxProductSoFar, currentMaxProduct);
        }

        return maxProductSoFar;
    }

    /**
     * Finds the lexicographically next greater permutation of numbers in the array.
     * If no greater permutation exists, it rearranges the array to the lowest possible order (sorted ascending).
     * The operation is performed in-place. (O(N) time, O(1) space)
     *
     * @param arr The input integer array, which will be modified in-place.
     */
    public static void nextPermutation(int[] arr) {
        if (arr == null || arr.length < 2) {
            return; // Permutation is itself if array is null, empty, or size 1.
        }

        int n = arr.length;
        int k = -1; // Index of the first number from the right that is smaller than the number to its right

        // 1. Find the largest index k such that arr[k] < arr[k + 1]
        for (int i = n - 2; i >= 0; i--) {
            if (arr[i] < arr[i + 1]) {
                k = i;
                break;
            }
        }

        // If no such index exists, the permutation is the last permutation (descending sorted).
        // In this case, we simply reverse the array to get the lowest order (ascending sorted).
        if (k == -1) {
            reverse(arr, 0, n - 1);
            return;
        }

        int l = -1; // Index of the smallest number to the right of k that is greater than arr[k]

        // 2. Find the largest index l > k such that arr[k] < arr[l]
        for (int i = n - 1; i > k; i--) {
            if (arr[k] < arr[i]) {
                l = i;
                break;
            }
        }

        // 3. Swap arr[k] and arr[l]
        swap(arr, k, l);

        // 4. Reverse the subarray arr[k + 1] to arr[n - 1] (It's currently descending, reversing makes it ascending)
        reverse(arr, k + 1, n - 1);
    }

    /**
     * Helper method to swap two elements in an array.
     *
     * @param arr The array.
     * @param i The index of the first element.
     * @param j The index of the second element.
     */
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * Helper method to reverse a portion of an array in-place.
     *
     * @param arr The array.
     * @param start The starting index (inclusive).
     * @param end The ending index (inclusive).
     */
    private static void reverse(int[] arr, int start, int end) {
        while (start < end) {
            swap(arr, start, end);
            start++;
            end--;
        }
    }
    /**
     * Partitions the array around a pivot using the Lomuto partition scheme (used in QuickSort).
     * Elements less than or equal to the pivot are moved to the left, and greater elements to the right.
     *
     * @param arr The array to partition.
     * @param low The starting index of the subarray.
     * @param high The ending index of the subarray.
     * @return The final index of the pivot element.
     * @throws IllegalArgumentException if array is null or indices are invalid.
     */
    public static int partition(int[] arr, int low, int high) {
        if (arr == null || low > high) {
            throw new IllegalArgumentException("Invalid array or indices for partition.");
        }

        int pivot = arr[high]; // Choose the last element as the pivot
        int i = (low - 1); // Index of the smaller element

        for (int j = low; j < high; j++) {
            // If current element is smaller than or equal to pivot
            if (arr[j] <= pivot) {
                i++;
                // Swap arr[i] and arr[j]
                swap(arr, i, j);
            }
        }

        // Swap arr[i+1] (the first element greater than pivot) and arr[high] (pivot)
        swap(arr, i + 1, high);

        return i + 1;
    }

    /**
     * Rotates the array to the right by k steps. The operation is performed in-place.
     * Uses the three-reverse approach (Reverse all, reverse first k, reverse remaining n-k).
     *
     * @param arr The array to rotate.
     * @param k The number of steps to rotate to the right.
     */
    public static void rotate(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k < 0) {
            return;
        }

        int n = arr.length;
        k = k % n; // Effective rotation steps

        if (k == 0) return;

        // 1. Reverse the entire array
        reverse(arr, 0, n - 1); // e.g., 1,2,3,4,5 -> 5,4,3,2,1

        // 2. Reverse the first k elements
        reverse(arr, 0, k - 1); // e.g., k=2: 5,4,3,2,1 -> 4,5,3,2,1

        // 3. Reverse the remaining n-k elements
        reverse(arr, k, n - 1); // e.g., 4,5,3,2,1 -> 4,5,1,2,3
    }

    /**
     * Checks if two sorted arrays contain any common elements (i.e., finds intersection).
     * Uses the two-pointer technique for O(N+M) time complexity.
     *
     * @param arr1 The first sorted array.
     * @param arr2 The second sorted array.
     * @return true if there is at least one common element, false otherwise.
     */
    public static boolean hasIntersection(int[] arr1, int[] arr2) {
        if (arr1 == null || arr2 == null || arr1.length == 0 || arr2.length == 0) {
            return false;
        }

        int i = 0; // Pointer for arr1
        int j = 0; // Pointer for arr2

        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] == arr2[j]) {
                return true; // Found an intersection
            } else if (arr1[i] < arr2[j]) {
                i++;
            } else {
                j++;
            }
        }
        return false;
    }

    /**
     * Checks if the array is sorted in ascending order.
     *
     * @param arr The input array.
     * @return true if the array is sorted, false otherwise.
     */
    public static boolean isSorted(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return true; // Null, empty, or single element array is considered sorted
        }

        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Finds the index of the first occurrence of a target value in a sorted array
     * using Binary Search. Returns -1 if the target is not found.
     *
     * @param arr The sorted input array.
     * @param target The value to search for.
     * @return The index of the first target occurrence, or -1.
     */
    public static int binarySearchFirstOccurrence(int[] arr, int target) {
        if (arr == null) {
            return -1;
        }

        int low = 0;
        int high = arr.length - 1;
        int result = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] == target) {
                result = mid;       // Potential first occurrence found
                high = mid - 1;     // Continue searching in the left half for an earlier occurrence
            } else if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return result;
    }
}