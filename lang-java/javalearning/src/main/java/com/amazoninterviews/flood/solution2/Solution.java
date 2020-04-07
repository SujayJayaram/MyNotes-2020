package com.amazoninterviews.flood.solution2;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    static class Pair {
        int row;
        int col;

        public Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return row == pair.row &&
                    col == pair.col;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, col);
        }
    }


    private static void doPaint(int[][] array, int row, int col, int newColour, int oldColour) {

        List<Pair> items = new ArrayList<>();
        items.add(new Pair(row, col));

        while(items.size() > 0) {
            Pair p = items.remove(0);

            if ( array[p.row][p.col] == oldColour ) {
                array[p.row][p.col] = newColour;

                if ( p.row > 0 )
                    items.add(new Pair(p.row-1, p.col));

                if ( p.row < (array.length-1) )
                    items.add(new Pair(p.row+1, p.col));

                if ( p.col > 0 )
                    items.add(new Pair(p.row, p.col-1));

                if ( p.col < (array[0].length-1) )
                    items.add(new Pair(p.row, p.col+1));
            }
        }
    }
}
