package com.techlead.connectedcolours;

@SuppressWarnings("Duplicates")
public class Solution1 {

    public static void main(String[] args) {

        // See https://www.youtube.com/watch?v=IWvbPIYQPFM at 5m 29s
        int[][] array = {
                {1, 1, 2, 3},
                {1, 2, 3, 2},
                {3, 2, 2, 2}
        };

        int maxCount = 0;
        for(int i = 0; i < array.length; i++) {
            for(int j = 0; j < array[0].length; j++) {
                if ( array[i][j] != -1) {
                    // Unprocessed data
                    int count = countSimilarColouredPointsUsingRecursion(array, array[i][j], i, j, 0);

                    if ( count > maxCount )
                        maxCount = count;
                }
            }
        }

        int debugMe = 0;
    }

    private static int countSimilarColouredPointsUsingRecursion(int[][] array, int colour, int i, int j, int count) {
        if ( (i < 0) || (j < 0) || (i >= array.length) || (j >= array[0].length) || (array[i][j] != colour) )
            return count; // Terminal

        count++;
        array[i][j] = -1; // Mark as visited

        count = countSimilarColouredPointsUsingRecursion(array, colour, i-1, j, count);
        count = countSimilarColouredPointsUsingRecursion(array, colour, i+1, j, count);
        count = countSimilarColouredPointsUsingRecursion(array, colour, i, j-1, count);
        count = countSimilarColouredPointsUsingRecursion(array, colour, i, j+1, count);

        return count;
    }
}
