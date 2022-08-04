package com.akpg.strings;

import java.util.Arrays;

/**
 * Given two strings, write a method to decide if one is a permutation of the other
 */
public class _1_2_CheckPermutation {

    public static void main(String[] args) {
        _1_2_CheckPermutation checkPermutation = new _1_2_CheckPermutation();

        System.out.println(checkPermutation.isPermutation("", "s"));
        System.out.println(checkPermutation.isPermutation("dog", "odg"));
        System.out.println(checkPermutation.isPermutation("abc", "adc"));

        System.out.println("\n");

        System.out.println(checkPermutation.isPermutationNoSorting("", "s"));
        System.out.println(checkPermutation.isPermutationNoSorting("dog", "odg"));
        System.out.println(checkPermutation.isPermutationNoSorting("abc", "adc"));
    }

    /**
     * runtime O(n log(n)), space O(n), where n = length of string1/ string2
     * if lengths not equal then return false instantly
     */
    private boolean isPermutation(String str1, String str2) {
        // check if either are null check thingy..

        // easy check - if str length doesn't match
        if (str1.length() != str2.length()) {
            return false;
        }

        char[] charArrStr1 = str1.toCharArray();
        char[] charArrStr2 = str2.toCharArray();

        Arrays.sort(charArrStr1);
        Arrays.sort(charArrStr2);

        
        // Important: don't compare object equality for arrays! Use content equality instead
        return Arrays.equals(charArrStr1, charArrStr2);
    }


    private boolean isPermutationNoSorting(String str1, String str2) {
        if (str1.length() != str2.length()) {
            return false;
        }

        int[] charCount = new int[128];

        for (char c : str1.toCharArray()) {
            charCount[c]++;
        }

        for (char c : str2.toCharArray()) {
            charCount[c]--;
            if (charCount[c] < 0) {
                return false;
            }
        }
        return true;
    }
}
