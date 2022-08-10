package com.akpg._1_strings;

/**
 * There are three types of edits that can be performed on strings:
 * 1. Insert a char
 * 2. Remove a char
 * 3. Replace a char
 *
 * Given two strings, write a function to check if they are one edit (or zero edits) away
 *
 * Example:
 *
 * pale, ple --> true
 * pales, pale --> true
 * pale, bale --> true
 * pale, bake --> false
 */
public class _1_5_OneAway {

    public static void main(String[] args) {
        _1_5_OneAway testClass = new _1_5_OneAway();
        System.out.println(testClass.oneAway("pale", "ple"));
        System.out.println(testClass.oneAway("pales", "pale"));
        System.out.println(testClass.oneAway("pale", "bale"));
        System.out.println(testClass.oneAway("pale", "bake"));

    }

    /**
     * One edit could either be a remove/ insert or a replace so two options. Now if string length diff == 1, then
     * check if we only need one insert. If string length diff == 0, then check if we only need one replace
     */
    private boolean oneAway(String str1, String str2) {
        System.out.println("For strings: " + str1 + " | " + str2);

        if (str1.length() - str2.length() == 0) {
            return isOneAwayReplace(str1, str2);
        } else if (str1.length() - str2.length() == 1) {
            return isOneAwayInsert(str1, str2);
        } else if (str2.length() - str1.length() == -1) {
            return isOneAwayInsert(str2, str1);
        }

        return false;
    }

    // Increment short pointer once to emulate an insert, if we have to increment short pointer twice then it seems
    // like we need an insert and a replace which is not one edit.
    private boolean isOneAwayInsert(String longStr, String shortStr) {
        int ptrLong = 0;
        int ptrShort = 0;

        while ((ptrLong < longStr.length()) && (ptrShort < shortStr.length())) {
            if (longStr.charAt(ptrLong) != shortStr.charAt(ptrShort)) {
                // assume here is where the insert happens for the short str
                if (ptrLong != ptrShort) {
                    return false;
                }
                ptrLong++;
            } else {
                ptrLong++;
                ptrShort++;
            }
        }

        return true;
    }

    // there should be <= 1 difference between both strings
    private boolean isOneAwayReplace(String str1, String str2) {
        boolean foundMoreThanOneDifference = false;
        for (int ptr = 0; ptr < str1.length(); ptr++) {
            if (str1.charAt(ptr) != str2.charAt(ptr)) {
                if (foundMoreThanOneDifference) {
                    return false;
                }
                foundMoreThanOneDifference = true;
            }
        }
        return true;
    }
}
