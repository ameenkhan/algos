package com.akpg._1_strings;

/**
 * Implement a method to perform basic string compression using the counts of repeated characters.
 *
 * Example:
 *
 * input - aabcccccaaa
 * output- a2b1c5a3
 *
 * If the compressed string would not become smaller than the original string, your method should return the original
 * string. You can assume the string has only uppercase and lowercase letters (a-z).
 */
public class _1_6_StringCompression {

    public static void main(String[] args) {
        _1_6_StringCompression testClass = new _1_6_StringCompression();
        System.out.println(testClass.compressString("aabcccccaaa"));
        System.out.println(testClass.compressString("abcd"));
        System.out.println(testClass.compressString("aabb"));
        System.out.println(testClass.compressString("aabb"));
    }

    /**
     * Iterate over string, record characters and frequency, every time next char is different then reset counter and
     * append to string. Finally compare the lengths of strings.
     *
     * O(n) runtime, O(1) space
     */
    private String compressString(String str) {
        int charFreq = 0;
        StringBuilder compressedStr = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            charFreq++;

            // if next char not equal to curr char, append the curr char and its freq or end of string
            if ((i + 1) == str.length() || (str.charAt(i) != str.charAt(i + 1))) {
                compressedStr.append(str.charAt(i));
                compressedStr.append(charFreq);
                charFreq = 0;
            }
        }

        return compressedStr.length() < str.length() ? compressedStr.toString() : str;
    }
}
