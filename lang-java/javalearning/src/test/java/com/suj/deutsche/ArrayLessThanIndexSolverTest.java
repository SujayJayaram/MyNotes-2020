package com.suj.deutsche;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class ArrayLessThanIndexSolverTest {

    public static void main(String[] args) {
        {
            int[] array = {};
            assertThat(ArrayLessThanIndexSolver.getLessThanIndex(array, 12), is(equalTo(0)));
        }


        {
            int[] array = {1};
            assertThat(ArrayLessThanIndexSolver.getLessThanIndex(array, 12), is(equalTo(-1)));
        }

        {
            int[] array = {1,1,2,2,4,5,7,7,9};
            assertThat(ArrayLessThanIndexSolver.getLessThanIndex(array, 2), is(equalTo(4)));
        }

        {
            int[] array = {1,1,2,2,4,5,7,7,9};
            assertThat(ArrayLessThanIndexSolver.getLessThanIndex(array, 12), is(equalTo(-1)));
        }
    }
}