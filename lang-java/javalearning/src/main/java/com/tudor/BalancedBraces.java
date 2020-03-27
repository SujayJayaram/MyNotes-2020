package com.tudor;

import java.util.ArrayDeque;
import java.util.Deque;

public class BalancedBraces {
    // Parse an array of strings and determine whether the three types of braces are nested in the allowed fashion.
    public static void main(String[] args) {
        String[] values = {"{}[]()", "{[}]"};

        String[] results = Braces(values);

        for(String result : results)
            System.out.println(result);
    }

    static String[] Braces(String[] values) {
        String[] rv = new String[values.length];
        for(int i = 0; i < values.length; i++) {
            rv[i] = checkBracesString(values[i]);
        }

        return rv;
    }

    private static String checkBracesString(String s) {
        // A well formed string will close all open brace types
        int openCount1 = 0;
        int openCount2 = 0;
        int openCount3 = 0;

        // Use a Stack to store the last type of opened brace as that is the
        // type that must be closed first.
        Deque<Character> lastOpenBraceType = new ArrayDeque<Character>();

        for(int i = 0; i < s.length(); i++) {
            switch(s.charAt(i)) {
                case '{':
                    openCount1++;
                    lastOpenBraceType.push('{');
                    break;
                case '[':
                    openCount2++;
                    lastOpenBraceType.push('[');
                    break;
                case '(':
                    openCount3++;
                    lastOpenBraceType.push('(');
                    break;
                case '}':
                    openCount1--;
                    {
                        if (lastOpenBraceType.size() == 0 )
                            return "NO";

                        char c = lastOpenBraceType.pop();
                        if (c != '{')
                            return "NO";
                    }
                    break;
                case ']':
                    openCount2--;
                    {
                        if (lastOpenBraceType.size() == 0 )
                            return "NO";

                        char c = lastOpenBraceType.pop();
                        if (c != '[')
                            return "NO";
                    }
                    break;
                case ')':
                    openCount3--;
                    {
                        if (lastOpenBraceType.size() == 0 )
                            return "NO";

                        char c = lastOpenBraceType.pop();
                        if (c != '(')
                            return "NO";
                    }
                    break;
                default:
                    break; // Ignore all other chars
            }

        }

        // A well formed string will close all open brace types
        if ( (openCount1 + openCount2 + openCount3) > 0 )
            return "NO";

        return "YES";
    }
}
