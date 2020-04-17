package com.hired.assessment1;

import java.util.ArrayList;
import java.util.List;

class Solution3 {

    static class Node {
        long val;
        Node lhsChild;
        Node rhsChild;
    }

    public static String solution(long[] numbers) {
        if ( numbers.length ==  0 )
            return "";

        if( numbers[0] == -1)
            return "";

        List<Node> nodeList = new ArrayList<>();
        int idx = 0;

        Node n = new Node();
        n.val = numbers[idx++];
        nodeList.add(n);

        while( (nodeList.size() > 0) && (idx < numbers.length) ) {
            Node thisNode = nodeList.remove(0);

            if ( numbers[idx] != -1 ) {
                long thisVal = numbers[idx];
                Node lhs = new Node();
                lhs.val = thisVal;
                thisNode.lhsChild = lhs;
                nodeList.add(lhs);
            }

            idx++;

            if ( (idx < numbers.length) && (numbers[idx] != -1) ) {
                long thisVal = numbers[idx];
                Node rhs = new Node();
                rhs.val = thisVal;
                thisNode.rhsChild = rhs;
                nodeList.add(rhs);
            }

            idx++;
        }

        long sumLHS = sum(n.lhsChild);
        long sumRHS = sum(n.rhsChild);

        if ( sumLHS > sumRHS )
            return "Left";
        else if( sumRHS > sumLHS )
            return "Right";

        return "";
    }

    private static long sum(Node node) {
        if ( node == null )
            return 0;

        return node.val + sum(node.lhsChild) + sum(node.rhsChild);
    }

    public static void main(String[] args) {
        long[] arr = {1,4,100,5};

        String s = solution(arr);

        System.out.println(s);
    }
}
