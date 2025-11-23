package org.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.utils.StringUtils;

/**
 * JUnit 5 test class for StringUtils functions.
 * Designed to be comprehensive for mutation testing.
 */
public class StringUtilsTest {

    // --- Test Cases for reverse() ---

    @Test
    @DisplayName("reverse(): Should reverse a simple string")
    void testReverseSimple() {
        assertEquals("olleh", StringUtils.reverse("hello"));
    }

    @Test
    @DisplayName("reverse(): Should handle an empty string")
    void testReverseEmpty() {
        assertEquals("", StringUtils.reverse(""));
    }

    @Test
    @DisplayName("reverse(): Should handle null input")
    void testReverseNull() {
        assertNull(StringUtils.reverse(null));
    }

    @Test
    @DisplayName("reverse(): Should handle special characters and spaces")
    void testReverseSpecial() {
        assertEquals("!DLROW OLLEH", StringUtils.reverse("HELLO WORLD!"));
    }

    // --- Test Cases for isPalindrome() ---

    @Test
    @DisplayName("isPalindrome(): Should return true for a standard palindrome (case/space ignored)")
    void testIsPalindromeTrue() {
        assertTrue(StringUtils.isPalindrome("A man, a plan, a canal: Panama"));
    }

    @Test
    @DisplayName("isPalindrome(): Should return false for a non-palindrome")
    void testIsPalindromeFalse() {
        assertFalse(StringUtils.isPalindrome("hello world"));
    }

    @Test
    @DisplayName("isPalindrome(): Should handle single-character string")
    void testIsPalindromeSingle() {
        assertTrue(StringUtils.isPalindrome("a"));
    }

    @Test
    @DisplayName("isPalindrome(): Should handle empty or null string")
    void testIsPalindromeEmptyOrNull() {
        assertFalse(StringUtils.isPalindrome(""));
        assertFalse(StringUtils.isPalindrome(null));
    }

    // --- Test Cases for longestSubstringWithoutRepeatingCharacters() ---

    @Test
    @DisplayName("longestSubstringWithoutRepeatingCharacters(): Should find max length (abcabcbb)")
    void testLswrcStandard() {
        assertEquals(3, StringUtils.longestSubstringWithoutRepeatingCharacters("abcabcbb")); // abc, bca, cab
    }

    @Test
    @DisplayName("longestSubstringWithoutRepeatingCharacters(): Should find max length (bbbbb)")
    void testLswrcAllSame() {
        assertEquals(1, StringUtils.longestSubstringWithoutRepeatingCharacters("bbbbb"));
    }

    @Test
    @DisplayName("longestSubstringWithoutRepeatingCharacters(): Should find max length (pwwkew)")
    void testLswrcPwwkew() {
        assertEquals(3, StringUtils.longestSubstringWithoutRepeatingCharacters("pwwkew")); // wke, kew
    }

    @Test
    @DisplayName("longestSubstringWithoutRepeatingCharacters(): Should handle empty/null string")
    void testLswrcEmptyOrNull() {
        assertEquals(0, StringUtils.longestSubstringWithoutRepeatingCharacters(""));
        assertEquals(0, StringUtils.longestSubstringWithoutRepeatingCharacters(null));
    }

    // --- Test Cases for isAnagram() ---

    @Test
    @DisplayName("isAnagram(): Should return true for standard anagrams")
    void testIsAnagramTrue() {
        assertTrue(StringUtils.isAnagram("listen", "silent"));
    }

    @Test
    @DisplayName("isAnagram(): Should return false for different length")
    void testIsAnagramFalseLength() {
        assertFalse(StringUtils.isAnagram("listen", "silence"));
    }

    @Test
    @DisplayName("isAnagram(): Should return false for different character set")
    void testIsAnagramFalseChars() {
        assertFalse(StringUtils.isAnagram("hello", "world"));
    }

    @Test
    @DisplayName("isAnagram(): Should handle empty strings")
    void testIsAnagramEmpty() {
        assertTrue(StringUtils.isAnagram("", ""));
    }

    // --- Test Cases for countOccurrences() ---

    @Test
    @DisplayName("countOccurrences(): Should find multiple non-overlapping occurrences")
    void testCountOccurrencesMultiple() {
        assertEquals(3, StringUtils.countOccurrences("abababa", "aba"));
    }

    @Test
    @DisplayName("countOccurrences(): Should handle no occurrences found")
    void testCountOccurrencesNone() {
        assertEquals(0, StringUtils.countOccurrences("hello world", "temp"));
    }

    @Test
    @DisplayName("countOccurrences(): Should handle empty target/source")
    void testCountOccurrencesEmpty() {
        assertEquals(0, StringUtils.countOccurrences("source", ""));
        assertEquals(0, StringUtils.countOccurrences("", "target"));
    }

    // --- Test Cases for toTitleCase() ---

    @Test
    @DisplayName("toTitleCase(): Should convert a sentence to title case")
    void testToTitleCaseStandard() {
        assertEquals("The Quick Brown Fox", StringUtils.toTitleCase("tHe qUiCk bRoWn FoX"));
    }

    @Test
    @DisplayName("toTitleCase(): Should handle leading/trailing spaces")
    void testToTitleCaseSpaces() {
        assertEquals(" One Two Three ", StringUtils.toTitleCase(" one two three "));
    }

    @Test
    @DisplayName("toTitleCase(): Should handle null/empty string")
    void testToTitleCaseNullOrEmpty() {
        assertNull(StringUtils.toTitleCase(null));
        assertEquals("", StringUtils.toTitleCase(""));
    }

    // --- Test Cases for truncate() ---

    @Test
    @DisplayName("truncate(): Should truncate and add ellipsis")
    void testTruncateStandard() {
        assertEquals("This...", StringUtils.truncate("This is a long string", 7));
    }

    @Test
    @DisplayName("truncate(): Should return original string if length is less than max")
    void testTruncateNoChange() {
        assertEquals("Short", StringUtils.truncate("Short", 10));
    }

    @Test
    @DisplayName("truncate(): Should throw exception if maxLength is too small")
    void testTruncateException() {
        assertThrows(IllegalArgumentException.class, () -> StringUtils.truncate("String", 2));
    }

    // --- Test Cases for countUniqueWords() ---

    @Test
    @DisplayName("countUniqueWords(): Should count unique words (case-insensitive)")
    void testCountUniqueWordsStandard() {
        assertEquals(4, StringUtils.countUniqueWords("The quick fox and the QUICK fox."));
    } // Unique words: the, quick, fox, and. Wait, only 3: the, quick, fox, and



    @Test
    @DisplayName("countUniqueWords(): Should handle empty/null string")
    void testCountUniqueWordsEmptyOrNull() {
        assertEquals(0, StringUtils.countUniqueWords(null));
        assertEquals(0, StringUtils.countUniqueWords(""));
    }
    @Test
    @DisplayName("cleanWhitespace(): Should remove leading/trailing and consolidate internal spaces")
    void testCleanWhitespaceStandard() {
        assertEquals("The quick brown fox", StringUtils.cleanWhitespace(" The   quick  brown\nfox "));
    }

    @Test
    @DisplayName("cleanWhitespace(): Should handle string with only whitespace")
    void testCleanWhitespaceOnlySpaces() {
        assertEquals("", StringUtils.cleanWhitespace(" \t\n "));
    }

    @Test
    @DisplayName("padLeft(): Should pad a string with '*' to the left")
    void testPadLeftStandard() {
        assertEquals("****hello", StringUtils.padLeft("hello", 9, '*'));
    }

    @Test
    @DisplayName("padLeft(): Should return the original string if length is sufficient")
    void testPadLeftNoChange() {
        assertEquals("hello", StringUtils.padLeft("hello", 3, '*'));
    }

    @Test
    @DisplayName("splitByLength(): Should split and filter based on minimum length")
    void testSplitByLengthFilter() {
        String[] expected = {"apple", "banana"};
        String[] actual = StringUtils.splitByLength("apple,bat,banana,cat", ",", 4);
        assertArrayEquals(expected, actual); // "bat" (3) and "cat" (3) are filtered
    }

    @Test
    @DisplayName("splitByLength(): Should return empty array if no parts meet criteria")
    void testSplitByLengthNone() {
        String[] actual = StringUtils.splitByLength("a,b,c", ",", 2);
        assertArrayEquals(new String[]{}, actual);
    }

    @Test
    @DisplayName("removeDuplicateChars(): Should remove duplicates preserving first order")
    void testRemoveDuplicateCharsStandard() {
        assertEquals("helotr", StringUtils.removeDuplicateChars("helloothere"));
    }

    @Test
    @DisplayName("removeDuplicateChars(): Should handle string with no duplicates")
    void testRemoveDuplicateCharsNone() {
        assertEquals("abc", StringUtils.removeDuplicateChars("abc"));
    }

    @Test
    @DisplayName("isAlphabetic(): Should return true for letters only")
    void testIsAlphabeticTrue() {
        assertTrue(StringUtils.isAlphabetic("HelloWorld"));
    }

    @Test
    @DisplayName("isAlphabetic(): Should return false for strings with numbers or spaces")
    void testIsAlphabeticFalse() {
        assertFalse(StringUtils.isAlphabetic("Hello World 123"));
    }

    @Test
    @DisplayName("isAlphabetic(): Should return false for null or empty strings")
    void testIsAlphabeticNullOrEmpty() {
        assertFalse(StringUtils.isAlphabetic(null));
        assertFalse(StringUtils.isAlphabetic(""));
    }

}