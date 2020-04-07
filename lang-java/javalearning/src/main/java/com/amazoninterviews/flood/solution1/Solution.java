package com.amazoninterviews.flood.solution1;

@SuppressWarnings("Duplicates")
public class Solution {
    // https://www.youtube.com/watch?v=kYqMC3dXC0Q&t=402s

    public static void main(String[] args) {

        // Fill this array rather like the bucket in MSPaint
        int[][] array = {
                { 1,1,1,1,1,1},
                { 1,1,2,2,1,1},
                { 1,1,2,2,1,1},
                { 2,2,2,2,2,2}
        };


        int startRow = 2;
        int startCol = 2;
        int newColour = 3;
        int oldColour = 2;

        doPaint(array, startRow, startCol, newColour, oldColour);

        int debugMe = 0;
    }

    private static void doPaint(int[][] array, int row, int col, int newColour, int oldColour) {
        if ( (row < 0) || (col < 0) || (row >= array.length) ||  (col >= array[0].length) || (array[row][col] != oldColour) )
            return;

        array[row][col] = newColour;

        doPaint(array, row+1, col, newColour, oldColour);
        doPaint(array, row-1, col, newColour, oldColour);
        doPaint(array, row, col+1, newColour, oldColour);
        doPaint(array, row, col-1, newColour, oldColour);
    }
}
