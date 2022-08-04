package com.akpg.strings;

/**
 * Write a method to replace all spaces in a string with "%20". You may assume that the string has sufficient space
 * at the end to hold the additional characters, and that you are given the "true" length of the string.
 * (Note: If implementing in Java, please use a character array so that you can perform this operation in place.)
 *
 *
 * Example:
 *
 * input - "Mr John Smith   "
 * output - "Mr%20John%20Smith"
 */
public class URLify {

    public static void main(String[] args) {
        URLify testClass = new URLify();
        String testStr = "Mr John Smith    ";

        System.out.println(testClass.urlify(testStr));

        System.out.println("\n");

        char[] testCharArr = testStr.toCharArray();
        testClass.urlifyInPlace(testCharArr, testStr.trim().length());
        System.out.println(testCharArr);
    }

    /**
     * Trim leading and trailing white spaces. Iterate over string O(n) and append "%20" for each whitespace and
     * original character if not whitespace :)
     */
    private String urlify(String str) {
        String trimmedStr = str.trim();
        String spaceReplace = "%20";
        StringBuilder urlifiedStr = new StringBuilder();

        StringBuilder res = new StringBuilder();
        for (char c : trimmedStr.toCharArray()) {
            if (c == ' ') {
                res.append(spaceReplace);
            } else {
                res.append(c);
            }
        }
        return res.toString();
    }

    /**
     * In place approach. Input char[] and real len of string
     *
     * example: "Mr John Smith    ", 13
     *
     * assume we get a char array with at least enough space. First find how many white spaces need replacement. Now
     * since we know num of real chars = real len, and that we need whitespaces * 2 more chars for "%20" (* 2 because
     * we already have 1/3 chars for the "%20" in original string). Now just go tail to head of the char array to urlify
     */
    private void urlifyInPlace(char[] str, int trueLen) {
       int spaces = 0;

       for (int i = 0; i < trueLen; i++) {
           if (str[i] == ' ') {
               spaces++;
           }
       }

       int rightToLeftIndex = trueLen + (spaces * 2);

       if (trueLen < str.length) {
           str[trueLen] = '\0';
       }

        for (int i = (trueLen - 1); i >= 0; i--) {
            if (str[i] == ' ') {
                str[rightToLeftIndex - 1] = '%';
                str[rightToLeftIndex - 2] = '0';
                str[rightToLeftIndex - 3] = '2';
                rightToLeftIndex = rightToLeftIndex - 3;
            } else {
                str[rightToLeftIndex - 1] = str[i];
                rightToLeftIndex--;
            }
        }

    }
}
