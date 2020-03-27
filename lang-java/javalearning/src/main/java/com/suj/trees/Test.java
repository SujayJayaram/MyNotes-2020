package com.suj.trees;


import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * Created by sujayjayaram on 05/12/2016.
 */
public class Test {
    public static void main(String... args){
        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(20);

        BinarySearchTreeNode<Integer> tree = new BinarySearchTreeNode<>(null, null, randomInt);

        IntStream.range(0, 30).forEach( i -> tree.insert( randomGenerator.nextInt(20) ) );

        tree.printTree();
        System.out.println("Depth = " + tree.maxDepth());
        System.out.println("Size = " + tree.size());

        List<Integer> list = tree.asList();
        System.out.println("$$$$$$$$$$$$$$$$$");
        StringBuilder sb = new StringBuilder();
        for(Integer i : list)
            sb.append(i).append(", ");

        String s = sb.toString();
        s = s.substring(0, s.length()-2);
        System.out.println(s);
        String[] sArray = s.split(", ");

        Integer[] iArray = new Integer[sArray.length];
        for(int i = 0; i < sArray.length; i++)
            iArray[i] = new Integer(sArray[i]);

        System.out.println("$$$$$$$$$$$$$$$$$");
        BinarySearchTreeNode<Integer> balancedTree = new BinarySearchTreeNode<>(Integer.class, iArray);
        balancedTree.printTree();
        System.out.println("Depth = " + balancedTree.maxDepth());
        System.out.println("Size = " + balancedTree.size());
    }
}
