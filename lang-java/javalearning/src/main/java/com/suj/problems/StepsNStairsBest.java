package com.suj.problems;

import java.util.*;

/**
 * Created by sujayjayaram on 20/02/2016.
 */
public class StepsNStairsBest {

    private final int numSteps;
    private final List<Integer> stepSizes;

    // There are N stairs and a child is able to walk up in step sizes of s1, s2, s3 etc.
    // How many possible combinations are there?
    public StepsNStairsBest(int numSteps, Integer[] stepSizesArray) {
        this.numSteps = numSteps;
        this.stepSizes = new ArrayList<Integer>(Arrays.asList(stepSizesArray));
        Collections.sort(stepSizes);
    }

    public int getNumberOfPossibilities() {
        Deque<Integer> stack = new ArrayDeque<Integer>();
        return getNumberOfPossibilities(numSteps, stack);
    }

    private int getNumberOfPossibilities(int numSteps, Deque<Integer> stack) {
        if ( numSteps < 0 )
            return 0; // This combination of steps 'overshot'

        if ( numSteps == 0 ) {
            printSuccessfulPath(stack);
            return 1; // This combination of steps worked and counts towards our overall number
        }

        int rv = 0;

        // These will recurse into smaller numSteps values
        // which will either match the stepSizes.contains()
        // line above or will return 0 in which case the whole
        // calc stream degenerates to zero.
        for (int thisStep : stepSizes) {
            stack.push(thisStep);
            rv += getNumberOfPossibilities(numSteps - thisStep, stack);
            stack.pop();
        }

        return rv;
    }

    private void printSuccessfulPath(Deque<Integer> stack) {
        StringBuilder sb = new StringBuilder();
        boolean bFirst = true;
        for(Integer i : stack) {
            if ( !bFirst )
                sb.append(", ");

            sb.append(i);
            bFirst = false;
        }

        System.out.println("Successful combination = [" + sb + "] => " + numSteps);
    }


    public static void main(String... args){
        Integer[] array = {1,2,3,4,5};
        StepsNStairsBest stepsNStairs = new StepsNStairsBest(5, array);

        System.out.println("Number of possibilities = " + stepsNStairs.getNumberOfPossibilities());

    }
}
