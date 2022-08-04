package com.akpg.strings;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;

public class _1_1_IsUnique {
    /**
     * Implement an algorithm to determine if a string has all unique characters. What if you cannot use additional
     * data structures
     */

    public static void main(String[] args) {
        _1_1_IsUnique testClass = new _1_1_IsUnique();

        System.out.println(testClass.isUnique(""));
        System.out.println(testClass.isUnique("abc"));
        System.out.println(testClass.isUnique("abca"));

        System.out.println("\n");

        System.out.println(testClass.isUniqueNoDS(""));
        System.out.println(testClass.isUniqueNoDS("abc"));
        System.out.println(testClass.isUniqueNoDS("abca"));

        System.out.println("\n");

        System.out.println(testClass.isUniqueNoDsSorted(""));
        System.out.println(testClass.isUniqueNoDsSorted("abc"));
        System.out.println(testClass.isUniqueNoDsSorted("abca"));
    }

    /**
     * Using hashset
     *
     * Runtime is O(n), at worst case we loop through everything, string to char array is O(n) time and space
     * Space is O(n), at worst we go through entire string
     */
    private boolean isUnique(String str) {

        if (str.isBlank()) {
            return true;
        }

        HashSet<Character> set = new HashSet<>();

        for (Character c : str.toCharArray()) {
            if (set.contains(c)) {
                return false;
            } else {
                set.add(c);
            }
        }
        return true;
    }

    /**
     * naive approach is to go check rest of array per character, which is O(n^2) runtime but O(1) space
     *
     * or sort the string alphabetically, O(n log(n)) and check if any two consecutive letters are the same
     *
     */
    private boolean isUniqueNoDS(String str) {
        if (str.isBlank()) {
            return true;
        }

        for (int i = 0; i < str.length(); i++) {
            Character currChar = str.charAt(i);

            for (int k = i + 1; k < str.length(); k++) {
                if (currChar.equals(str.charAt(k))) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean isUniqueNoDsSorted(String str) {

        if (str.isBlank()) {
            return true;
        }

        char[] charArr = str.toCharArray();
        Arrays.sort(charArr);

        for (int i = 0; i < (charArr.length - 1); i++) {
            if (Objects.equals(charArr[i], charArr[i + 1])) {
                return false;
            }
        }

        return true;
    }
}
