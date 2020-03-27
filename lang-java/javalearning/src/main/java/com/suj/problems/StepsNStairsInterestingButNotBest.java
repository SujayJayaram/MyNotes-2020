package com.suj.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by sujayjayaram on 20/02/2016.
 */
public class StepsNStairsInterestingButNotBest {

    private final int numSteps;
    private final List<Integer> stepSizes;

    // There are N stairs and a child is able to walk up in step sizes of s1, s2, s3 etc.
    // How many possible combinations are there?
    public StepsNStairsInterestingButNotBest(int numSteps, Integer[] stepSizesArray) {
        this.numSteps = numSteps;
        this.stepSizes = new ArrayList<Integer>(Arrays.asList(stepSizesArray));
        Collections.sort(stepSizes);
    }

    public int getNumberOfPossibilities() {
        return getNumberOfPossibilities(numSteps, stepSizes);
    }

    private int getNumberOfPossibilities(int numSteps, List<Integer> stepSizes) {
        if ( (numSteps <= 0) || (stepSizes.size() == 0) || (numSteps < stepSizes.get(0)) ) // Already sorted by size so if its less than the minimum step size...
            return 0;

        if ( numSteps == stepSizes.get(0) ) // Only one possibility if its only as big as the smallest step.
            return 1;

        int rv = 0;

        // If the numSteps matches one of our steps, then count that as a possibility and work out what other possibilites
        // we could have.....doing it this way (see the filter below where we look for steps less than numSteps)
        // prevents us calling this method recursively with the same arguments and getting a Stack Overflow!
        if (stepSizes.contains(numSteps))
           rv = 1; // **** This line is VERY important as its the ONLY place where we give rv a value!


        // Need a reduced list with only the smaller steps in it
        // See http://zeroturnaround.com/rebellabs/java-8-explained-applying-lambdas-to-java-collections/
        List<Integer> reducedStepsList = stepSizes.stream()
                //.filter(i -> i != numSteps)
                .collect(Collectors.toCollection(ArrayList::new));

        // For each possible step, we need to see how many possible varients could take us to numSteps-thisStep
        // (so that thisStep can complete the process)
        for (int thisStep : reducedStepsList)
            rv += getNumberOfPossibilities(numSteps-thisStep, reducedStepsList);


        System.out.println("For " + numSteps + " steps there are " + rv + " possibilities");
        return rv;
    }


    public static void main(String... args){
        Integer[] array = {1,2,3,7,8,9};
        StepsNStairsInterestingButNotBest stepsNStairs = new StepsNStairsInterestingButNotBest(3, array);

        System.out.println("Number of possibilities = " + stepsNStairs.getNumberOfPossibilities());
    }
}
