package com.tudor;

public class IsItPossible {

    // (x,y) -> (x+y,y) or (x,x+y)
    // Can we get from (a,b) to (c,d) using these two possible transforms?
    public static void main(String[] args) {

        int a = 1;
        int b = 2;
        int c = 3;
        int d = 5;

        boolean poss = isItPossible(a,b,c,d);

        System.out.print("Boolean = " + poss);
    }

    private static boolean isItPossible(int a, int b, int c, int d) {
        if ( (a == c) && (b == d) )
            return true;

        if ( (a > c) || (b > d) )
            return false;

        // As recursive/dynamic prohgramming solutions often do not scale well, we could use 'memoization'
        // (or caching) in a more elegant solution to this problem.
        return isItPossible(a+b, b, c, d)
                || isItPossible(a, a+b, c, d);
    }

}

