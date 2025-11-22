package org.main;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.utils.ArrayUtils;

/**
 * Main application class providing a menu-driven interface to test the ArrayUtils functions.
 */
public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("--- Array Algorithms and Utilities Testing Menu ---");
        boolean running = true;
        while (running) {
            displayMenu();
            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        getMinMax();
                        break;
                    case 2:
                        getMergeSort();
                        break;
                    case 3:
                        getTwoSumUniquePairs();
                        break;
                    case 4:
                        getMajorityElement();
                        break;
                    case 5:
                        getLongestSubarrayWithSum();
                        break;
                    case 6:
                        getMaxProductSubarray();
                        break;
                    case 7:
                        getNextPermutation();
                        break;
                    case 0:
                        running = false;
                        System.out.println("Exiting application. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 0 and 7.");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nERROR: Invalid input. Please enter a number.\n");
                scanner.nextLine(); // Clear the invalid input
            } catch (Exception e) {
                System.out.println("\nAn unexpected error occurred: " + e.getMessage() + "\n");
            }
        }
        scanner.close();
    }

    /**
     * Displays the menu options to the user.
     */
    private static void displayMenu() {
        System.out.println("\n---------------------------------------------------");
        System.out.println("Select an option:");
        System.out.println("1. Min and Max");
        System.out.println("2. Merge Sort (In-place)");
        System.out.println("3. 2Sum - Unique Pairs Count");
        System.out.println("4. Majority Element");
        System.out.println("5. Longest Subarray with Given Sum");
        System.out.println("6. Maximum Product Subarray");
        System.out.println("7. Next Permutation (In-place)");
        System.out.println("0. Exit");
        System.out.print("Enter choice: ");
    }

    /**
     * Helper to read an integer array from user input.
     *
     * @return The array parsed from the input string, or null on error/empty input.
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

    // --- Method Implementations ---

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
}
