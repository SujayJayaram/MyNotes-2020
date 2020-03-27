package com.suj.problems.sumofcubes;

/**
 * Created by sujayjayaram on 02/12/2016.
 */
public class Result3 {
    private int i;
    private int j;
    private double sumOfCubes;

    public Result3(int i, int j, double sumOfCubes) {
        if ( i <= j ) {
            this.i = i;
            this.j = j;
        }
        else {
            this.i = j;
            this.j = i;
        }

        this.sumOfCubes = sumOfCubes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Result3 result3 = (Result3) o;

        if (i != result3.i) return false;
        if (j != result3.j) return false;
        return Double.compare(result3.sumOfCubes, sumOfCubes) == 0;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = i;
        result = 31 * result + j;
        temp = Double.doubleToLongBits(sumOfCubes);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Result3{" +
                "i=" + i +
                ", j=" + j +
                ", sumOfCubes=" + sumOfCubes +
                '}';
    }
}
