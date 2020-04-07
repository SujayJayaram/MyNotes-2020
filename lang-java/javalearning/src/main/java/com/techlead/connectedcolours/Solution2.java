package com.techlead.connectedcolours;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@SuppressWarnings("Duplicates")
public class Solution2 {

    public static void main(String[] args) {

        // Seee https://www.youtube.com/watch?v=IWvbPIYQPFM at 5m 29s
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
                    int count = countSimilarColouredPointsUsingBredthFirstSearch(array, array[i][j], i, j);

                    if ( count > maxCount )
                        maxCount = count;
                }
            }
        }

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

    private static int countSimilarColouredPointsUsingBredthFirstSearch(int[][] array, int colour, int i, int j) {

        int count = 0;
        List<Pair> list = new ArrayList<>();
        list.add(new Pair(i, j));

        while(list.size() > 0) {
            Pair p = list.remove(0);
            if ( array[p.row][p.col] == colour ) {
                count++;
                array[p.row][p.col] = -1; // Mark as visited

                if ( p.row > 0 )
                    list.add(new Pair(p.row-1, p.col));

                if ( p.row < (array.length-1) )
                    list.add(new Pair(p.row+1, p.col));

                if ( p.col > 0 )
                    list.add(new Pair(p.row, p.col-1));

                if ( p.col < (array[0].length-1) )
                    list.add(new Pair(p.row, p.col+1));
            }
        }

        return count;
    }
}
