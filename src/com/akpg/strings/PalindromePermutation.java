package com.akpg.strings;

/**
 * Given a string, write a function to check if it is a permutation of a palindrome. A palindrome is a word or phrase
 * that is the same forwards and backwards. A permutation is a rearrangement of letters. The palindrom does not need
 * to be limited to just dictionary words
 *
 * Example:
 *
 * Input: Tact Coa
 * Output: True (permutations: "taco cat", "atco cta", etc.)
 */
public class PalindromePermutation {

    public static void main(String[] args) {
        PalindromePermutation testClass = new PalindromePermutation();

        System.out.println(testClass.isPalindromePermutation("Tact Coa"));
        System.out.println(testClass.isPalindromePermutation("dad"));
        System.out.println(testClass.isPalindromePermutation("nnn"));
        System.out.println(testClass.isPalindromePermutation("not"));
        System.out.println(testClass.isPalindromePermutation("haeh"));
    }

    /**
     * Taco cat is a palindrome, to be a palindrome you need to have the same letters at the start and end. Two cases
     * now, odd and even number of characters.
     *
     * Example odd - dad, odd length = 3, so all but one character has to have
     * the same number of repetitions, all even repetitions except one odd
     *
     * Example even - emme, even length = 4, all characters have the same length, all even repetitions
     *
     * so in both cases, the max number of odd repetitions can be 1
     */
    boolean isPalindromePermutation(String str) {
        System.out.println("for string " + str);

        String lowerCaseStr = str.toLowerCase();
        int[] freqChar = new int[128];
        int oddFreqCounter = 0;

        for (char c : lowerCaseStr.toCharArray()) {
            // skip empty space, assume that it is not part of palindrome
            if (Character.isAlphabetic(c)) {
                freqChar[c]++;
                if ((freqChar[c] % 2) == 0) {
                    oddFreqCounter--;
                } else {
                    oddFreqCounter++;
                }
            }
        }

        return oddFreqCounter <= 1;
    }
}
