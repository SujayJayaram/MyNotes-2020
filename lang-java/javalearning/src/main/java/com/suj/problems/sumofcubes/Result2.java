package com.suj.problems.sumofcubes;

/**
 * Created by sujayjayaram on 02/12/2016.
 */
public class Result2 {
    private int myI;
    private int myJ;
    private int myK;
    private int myL;

    Result2(int i, int j, int k, int l) {
        // Always make myI <= myJ and myK <= myL
        if (i <= j) {
            this.myI = i;
            this.myJ = j;

        } else {
            this.myI = j;
            this.myJ = i;
        }

        if (k <= l) {
            this.myK = k;
            this.myL = l;

        } else {
            this.myK = l;
            this.myL = k;
        }

    }

    boolean isValid() {
        if ((myI == myK) && (myJ == myL))
            return false;

        return true;
    }

    public int getMyI() {
        return myI;
    }

    public int getMyJ() {
        return myJ;
    }

    public int getMyK() {
        return myK;
    }

    public int getMyL() {
        return myL;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Result2 result2 = (Result2) o;

        if (myI != result2.myI) return false;
        if (myJ != result2.myJ) return false;
        if (myK != result2.myK) return false;
        return myL == result2.myL;

    }

    @Override
    public int hashCode() {
        int result = myI;
        result = 31 * result + myJ;
        result = 31 * result + myK;
        result = 31 * result + myL;
        return result;
    }

    @Override
    public String toString() {
        return "Result2{" +
                "myI=" + myI +
                ", myJ=" + myJ +
                ", myK=" + myK +
                ", myL=" + myL +
                '}';
    }
}