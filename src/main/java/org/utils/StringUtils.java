package org.utils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Utility class containing various string manipulation and algorithm functions.
 * All methods are implemented to be static for easy access.
 */
public final class StringUtils {

    // Private constructor to prevent instantiation
    private StringUtils() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    // --- Utility Functions ---

    /**
     * Reverses the input string.
     *
     * @param str The input string.
     * @return The reversed string.
     */
    public static String reverse(String str) {
        if (str == null) {
            return null;
        }
        return new StringBuilder(str).reverse().toString();
    }

    /**
     * Checks if a string is a palindrome (reads the same forwards and backwards).
     * Case and space insensitive.
     *
     * @param str The input string.
     * @return true if the string is a palindrome, false otherwise.
     */
    public static boolean isPalindrome(String str) {
        if (str == null) {
            return false;
        }
        if(str.isEmpty())
                return false;
        String cleanStr = str.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        return cleanStr.equals(reverse(cleanStr));
    }

    // --- Algorithm Implementations ---

    /**
     * Finds the length of the longest substring without repeating characters.
     * Uses a sliding window and a set (O(N) time complexity).
     *
     * @param s The input string.
     * @return The length of the longest substring without repeating characters.
     */
    public static int longestSubstringWithoutRepeatingCharacters(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }

        Set<Character> charSet = new HashSet<>();
        int left = 0;
        int maxLength = 0;

        for (int right = 0; right < s.length(); right++) {
            while (charSet.contains(s.charAt(right))) {
                charSet.remove(s.charAt(left));
                left++;
            }
            charSet.add(s.charAt(right));
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }

    /**
     * Checks if one string is an anagram of another.
     * Two strings are anagrams if they are composed of the same characters with the same counts.
     * Case-sensitive and space-sensitive. (O(N) time complexity with fixed alphabet size)
     *
     * @param s1 The first string.
     * @param s2 The second string.
     * @return true if s2 is an anagram of s1, false otherwise.
     */
    public static boolean isAnagram(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() != s2.length()) {
            return false;
        }

        int[] charCounts = new int[256]; // Assuming ASCII characters

        for (char c : s1.toCharArray()) {
            charCounts[c]++;
        }

        for (char c : s2.toCharArray()) {
            charCounts[c]--;
            if (charCounts[c] < 0) {
                return false;
            }
        }

        // Since lengths are equal, if all counts are >= 0, they must all be 0.
        return true;
    }

    /**
     * Counts the number of occurrences of a target substring within a source string.
     * Uses the KMP-like brute-force approach (O(N*M) worst case, where N is source length, M is target length).
     *
     * @param source The string to search within.
     * @param target The substring to search for.
     * @return The number of times the target appears in the source.
     */
    public static int countOccurrences(String source, String target) {
        if (source == null || target == null || source.isEmpty() || target.isEmpty() || target.length() > source.length()) {
            return 0;
        }

        int count = 0;
        int sourceLen = source.length();
        int targetLen = target.length();

        for (int i = 0; i <= sourceLen - targetLen; i++) {
            boolean match = true;
            for (int j = 0; j < targetLen; j++) {
                if (source.charAt(i + j) != target.charAt(j)) {
                    match = false;
                    break;
                }
            }
            if (match) {
                count++;
                // Simple brute force: continue search from next index
            }
        }
        return count;
    }

    /**
     * Converts a sentence to Title Case (first letter of every word is capitalized).
     *
     * @param str The input sentence.
     * @return The string in Title Case.
     */
    public static String toTitleCase(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }

        StringBuilder titleCase = new StringBuilder();
        boolean nextWord = true;

        for (char c : str.toCharArray()) {
            if (Character.isWhitespace(c)) {
                titleCase.append(c);
                nextWord = true;
            } else if (nextWord) {
                titleCase.append(Character.toTitleCase(c));
                nextWord = false;
            } else {
                titleCase.append(Character.toLowerCase(c));
            }
        }

        return titleCase.toString();
    }

    /**
     * Truncates a string to a maximum length and appends "..." if it was truncated.
     *
     * @param str The input string.
     * @param maxLength The maximum length before truncation (must be >= 3).
     * @return The truncated string with ellipsis, or the original string.
     * @throws IllegalArgumentException if maxLength is less than 3.
     */
    public static String truncate(String str, int maxLength) {
        if (maxLength < 3) {
            throw new IllegalArgumentException("Maximum length must be at least 3 to allow for ellipsis.");
        }

        if (str == null) {
            return null;
        }

        if (str.length() <= maxLength) {
            return str;
        }

        // Truncate to maxLength - 3 to leave room for "..."
        return str.substring(0, maxLength - 3) + "...";
    }

    /**
     * Counts the number of unique words in a string, case-insensitive.
     * Words are separated by whitespace.
     *
     * @param str The input string (sentence).
     * @return The count of unique words.
     */
    public static int countUniqueWords(String str) {
        if (str == null || str.trim().isEmpty()) {
            return 0;
        }

        // Replace all non-word characters with spaces and split by one or more spaces
        String[] words = str.replaceAll("[^a-zA-Z\\s]", "").toLowerCase().split("\\s+");

        Set<String> uniqueWords = new HashSet<>();
        for (String word : words) {
            if (!word.trim().isEmpty()) {
                uniqueWords.add(word);
            }
        }
        return uniqueWords.size();
    }
    /**
     * Removes all leading and trailing whitespace from a string, and replaces
     * sequences of internal whitespace with a single space.
     *
     * @param str The input string.
     * @return The cleaned string.
     */
    public static String cleanWhitespace(String str) {
        if (str == null) {
            return null;
        }
        return str.trim().replaceAll("\\s+", " ");
    }

    /**
     * Pads the string on the left with a specified character until it reaches the desired length.
     * If the string is already longer than the length, it is returned unchanged.
     *
     * @param str The input string.
     * @param length The total desired length of the string.
     * @param padChar The character to use for padding.
     * @return The padded string.
     */
    public static String padLeft(String str, int length, char padChar) {
        if (str == null) {
            str = "";
        }
        if (str.length() >= length) {
            return str;
        }

        int paddingSize = length - str.length();
        char[] padding = new char[paddingSize];
        Arrays.fill(padding, padChar);

        return new String(padding) + str;
    }

    /**
     * Splits a string into an array of substrings based on a delimiter, but only returns
     * substrings with a minimum length.
     *
     * @param str The string to split.
     * @param delimiter The delimiter string (e.g., ",").
     * @param minLength The minimum length a resulting substring must have.
     * @return An array of valid substrings.
     */
    public static String[] splitByLength(String str, String delimiter, int minLength) {
        if (str == null || delimiter == null || str.isEmpty() || delimiter.isEmpty() || minLength < 0) {
            return new String[0];
        }

        String[] parts = str.split(delimiter);
        return Arrays.stream(parts)
                .filter(s -> s.length() >= minLength)
                .toArray(String[]::new);
    }

    /**
     * Removes duplicate characters from a string, preserving the order of the first appearance.
     *
     * @param str The input string.
     * @return The string with unique characters.
     */
    public static String removeDuplicateChars(String str) {
        if (str == null || str.length() <= 1) {
            return str;
        }

        Set<Character> seen = new HashSet<>();
        StringBuilder result = new StringBuilder();

        for (char c : str.toCharArray()) {
            if (!seen.contains(c)) {
                seen.add(c);
                result.append(c);
            }
        }
        return result.toString();
    }

    /**
     * Checks if a string contains only letters (A-Z, a-z). Ignores null and empty checks.
     *
     * @param str The input string.
     * @return true if the string is composed entirely of letters, false otherwise.
     */
    public static boolean isAlphabetic(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }

        for (char c : str.toCharArray()) {
            if (!Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }
}