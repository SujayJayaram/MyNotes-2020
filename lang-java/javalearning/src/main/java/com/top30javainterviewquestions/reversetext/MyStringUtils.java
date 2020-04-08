package com.top30javainterviewquestions.reversetext;

public class MyStringUtils {

    // ***** See test class for info on running with params
    public String reverse(String s) {
        char[] charArray = s.toCharArray();
        int halfLength = charArray.length/2;
        for(int i = 0; i < halfLength; i++) {
            int idx2 = charArray.length-1 -i;
            char temp = charArray[i];
            charArray[i] = charArray[idx2];
            charArray[idx2] = temp;
        }

        return String.valueOf(charArray);
    }
}
