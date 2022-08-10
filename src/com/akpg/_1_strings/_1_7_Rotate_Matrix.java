package com.akpg._1_strings;

import java.util.Arrays;

/**
 * Given an image represented by an NxN matrix, where each pixel in the image is 4 bytes, write a method to rotate
 * the image by 90 degrees.
 *
 * Follow up, do it in-place
 */
public class _1_7_Rotate_Matrix {

    public static void main(String[] args) {
        _1_7_Rotate_Matrix testClass = new _1_7_Rotate_Matrix();
        int[][] testMatrix4x4 = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        System.out.println(Arrays.deepToString(testClass.rotateMatrixTransposeReflect(testMatrix4x4)));
        System.out.println(Arrays.deepToString(testMatrix4x4));
        System.out.println(Arrays.deepToString(testClass.rotateMatrixCyclicRotate(testMatrix4x4)));
        System.out.println(Arrays.deepToString(testMatrix4x4));
    }

    /**
     * Input -
     * [ a b c d ]
     * [ e f g h ]
     * [ i j k l ]
     * [ m n o p ]
     *
     * rotated 90 ->
     *
     * [ m i e a ]
     * [ o j f b ]
     * [ n k g c ]
     * [ p l h d ]
     *
     * 2 approaches:
     *
     *  1. Transpose and reflect O(n^2) O(1)
     *  2. Per layer swap n elements, then go to next layer and swap (n-2) layers
     */
    int[][] rotateMatrixTransposeReflect(int[][] input) {
        int[][] matrix = Arrays.stream(input).map(int[]::clone).toArray(int[][]::new);
        int len = matrix.length;
        int tmp;

        // transpose
        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }

        // reflect
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len / 2; j++) {
                tmp = matrix[i][j];
                matrix[i][j] = matrix[i][len - 1 -j];
                matrix[i][len - 1 -j] = tmp;
            }
        }
        return matrix;
    }

    int[][] rotateMatrixCyclicRotate(int[][] input) {
        int[][] matrix = Arrays.stream(input).map(int[]::clone).toArray(int[][]::new);
        int n = matrix.length;
        int tmp;

        // len / 2 = number of layers in the matrix. We cycle perimeter of each layer essentially
        for (int i = 0; i < (n / 2); i++) {
            // number of elements per layer, on a single side, decrements by 2 each layer --> ++i on the left, --i on
            // the right
            for (int j = i; j < (n - 1 -i); j++) {
                // per layer = i, in layer = j
                // top = matrix[i][j] -> rows increment left-right per layer, col increments left-right per layer
                // left = matrix[n-1-j][i] -> rows decrement down-up in layer, col increments left-right per layer
                // bottom = matrix[n-1-i][n-1-j] -> rows decrement down-up per layer, col decrement right-left in
                // layer
                // right = matrix[j][n-1-i] -> rows increment up-down per layer, column decrement right-left per layer

                tmp = matrix[i][j];
                // top = left
                matrix[i][j] = matrix[n-1-j][i];
                // left = bottom
                matrix[n-1-j][i] = matrix[n-1-i][n-1-j];
                // bottom = right
                matrix[n-1-i][n-1-j] = matrix[j][n-1-i];
                // right = top
                matrix[j][n-1-i] = tmp;
            }
        }

        return matrix;
    }
}
