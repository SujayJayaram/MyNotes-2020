package com.algoexpert.numberofrectanglesmadebypoints;

public class RectangleCounter {
    static int countRectangles(Point[] points) {
        int count = 0;
        for(int i = 0; i < points.length; i++) {
            for(int j = 0; j < points.length; j++) {
                if ( i != j) {
                    // Check 1
                    if ( points[i].getX() == points[j].getX() ) {
                        for(int k = 0; k < points.length; k++) {
                            if ( (k != i) && (k != j) ) {
                                // Check 2
                                if ( points[j].getY() == points[k].getY() ) {
                                    for(int l = 0; l < points.length; l++) {
                                        if ( (l != i) && (l != j) && (l != k) ) {
                                            // Check 3
                                            if ( points[l].getY() == points[i].getY() ) {
                                                count++;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return count/4;
    }
}
