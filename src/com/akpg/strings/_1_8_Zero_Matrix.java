package com.akpg.strings;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Write an algorithm such that if an element in an MxN matrix is 0, its entire row and column are set to 0
 */
public class _1_8_Zero_Matrix {

    public static void main(String[] args) {
        _1_8_Zero_Matrix testClass = new _1_8_Zero_Matrix();
        int[][] testMatrix4x4 = {
                {0, 1, 2, 0},
                {3, 4, 5, 2},
                {1, 3, 1, 5}
        };

        System.out.println(Arrays.deepToString(testMatrix4x4));
        System.out.println(Arrays.deepToString(testClass.zeroMatrix(testMatrix4x4)));
    }

    /**
     * Example:
     *
     *  [0 1 2 0]
     *  [3 4 5 2]
     *  [1 3 1 5]
     *
     *  -->
     *
     *  [0 0 0 0]
     *  [0 4 5 0]
     *  [0 3 1 0]
     *
     *  create function to nullify col
     *  create function to nullify row
     *
     *  1. iterate over matrix initially to identify which rows and cols have zeros
     *  2. iterave over matrix and set the index val to 0 if either the curr row or col has a zero
     *
     * Runtime O(MxN) traverse all elements
     * Space O(M+N)
     */
    int[][] zeroMatrix(int[][] input) {
        int[][] matrix = Arrays.stream(input).map(int[]::clone).toArray(int[][]::new);

        HashSet<Integer> rows = new HashSet<>();
        HashSet<Integer> cols = new HashSet<>();

        for (int m = 0; m < matrix.length; m++) {
            for (int n = 0; n < matrix[m].length; n++) {
                if (matrix[m][n] == 0) {
                    rows.add(m);
                    cols.add(n);
                }
            }
        }

        for (int m = 0; m < matrix.length; m++) {
            for (int n = 0; n < matrix[m].length; n++) {
                if (rows.contains(m) || cols.contains(n)) {
                    matrix[m][n] = 0;
                }
            }
        }
        return matrix;
    }
}
