package com.top30javainterviewquestions.verifyispoweroftwo;

public class Solution {

    public static boolean isPowerOfTwo(int num) {
        //int i2 = num & 0x1;
        return (num&1) > 0? false : true;
    }
}
