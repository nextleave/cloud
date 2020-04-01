package com.geblob.cloud.matrix;

import org.javatuples.Pair;

public class MatrixTravelTest {
    public static void main(String[] args) {
        Integer[][] matrix = new Integer[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {10, 11, 12}};
        int height = matrix.length;
        int length = matrix[0].length;
        new RightForward(matrix, Pair.with(length, height), Pair.with(0, 0)).forward();
    }
}
