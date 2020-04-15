package com.hired.sampletest1;

import java.util.ArrayList;
import java.util.List;

class Solution {
    static class Node {
        long val;
        Node leftChild;
        Node rightChild;
    }

    public static String solution(long[] arr) {
        if ( arr.length == 0 )
            return "";


        // Build up a tree model parsing the array and using breadth first
        List<Node> nodes = new ArrayList<>();
        int count = 0;

        Node rootNode = new Node();
        rootNode.val = arr[count++];
        nodes.add(rootNode);

        while( (nodes.size() > 0) && (count < arr.length) ) {
            Node popped = nodes.remove(0);

            if ( arr[count] != -1) {
                Node leftChild = new Node();
                leftChild.val = arr[count];
                popped.leftChild = leftChild;
                nodes.add(leftChild);
            }

            count++;

            if ( count < arr.length ) {
                if ( arr[count] != -1) {
                    Node rightChild = new Node();
                    rightChild.val = arr[count];
                    popped.rightChild = rightChild;
                    nodes.add(rightChild);
                }
            }

            count++;
        }

        long leftSum = sum(rootNode.leftChild);
        long rightSum = sum(rootNode.rightChild);

        if ( leftSum == rightSum)
            return "";

        return  (leftSum > rightSum)? "Left" : "Right";
    }

    private static long sum(Node node) {
        if ( node == null )
            return 0;

        return node.val + sum(node.leftChild) + sum(node.rightChild);
    }
}
