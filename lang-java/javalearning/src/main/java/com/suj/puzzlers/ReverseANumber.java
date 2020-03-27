package com.suj.puzzlers;

/**
 * Created by sujayjayaram on 13/02/2016.
 */
public class ReverseANumber {
    public static  void main(String... args) {

        int num = 5348843;
        String s = Integer.toString(num);
        StringBuilder sb = new StringBuilder();
        char[] chars = s.toCharArray();
        for(int i = chars.length-1; i >= 0; i--){
            sb.append(chars[i]);
        }

        int numReverse = (new Integer(sb.toString())).intValue();
        System.out.print("Reverse = " + numReverse);
    }
}
