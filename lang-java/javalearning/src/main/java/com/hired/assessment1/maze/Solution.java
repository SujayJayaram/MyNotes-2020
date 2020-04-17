package com.hired.assessment1.maze;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static boolean solution(long[][] maze, long n) {
        // Blocked at the start or end
        if ( maze[0][0] == 1 )
            return false;

        if ( maze[(int)(n-1)][(int)(n-1)] == 1 )
            return false;

        int row = 0;
        int col = 0;

        return checkOptionAt(row, col, maze, (int)n);
    }

    static class Point {
        int row;
        int col;

        Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    private static boolean checkOptionAt(int row, int col, long[][] maze, int n) {
        List<Point> points = new ArrayList<>();
        Point point = new Point(row, col);
        points.add(point);

        while(points.size() > 0) {
            Point p = points.remove(0);
            if( (p.row < 0) || (p.row >= n)
                    || (p.col < 0) || (p.col >= n)
                    || (maze[p.row][p.col] == 1) ) {
                // Ignore
            }
            else {
                if ( (p.row == n-1) && (p.col == n-1) ) {
                    return true; // Success
                }

                if ( row -1 > 0 )
                    points.add(new Point(p.row-1, p.col));

                if ( row +1 < n )
                    points.add(new Point(p.row+1, p.col));

                if ( col -1 > 0 )
                    points.add(new Point(p.row, p.col-1));

                if ( col + 1 < n )
                    points.add(new Point(p.row, p.col+1));
            }

        }

        return false;
    }
}
