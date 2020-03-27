package com.suj.problems.bitwise;

/**
 * Created by sujayjayaram on 03/12/2016.
 */
public class CopyOneBitPatternIntoAnother {

    // See http://www.theserverside.com/tutorial/Java-7-and-Binary-Notation
    // See http://www.theserverside.com/tutorial/New-Java-7-Features-Numeric-Underscores-with-Literals-the-OCPJP-Certification
    public static void main(String[] args){
        /*
        byte fourTimesThree = 0b1100;
        byte data = 0b0000110011;
        short number = 0b111111111111111;
        int overflow = 0b10101010101010101010101010101011;
        long bow = 0b101010101010101010101010101010111L;
*/
        int iMaster = 0b1100_0011_1010_1100;
        int iInner = 0b1011;
        int iStartPost = 4;
        int iNumDigits = 3;

        int val = overlayNumber(iMaster, iInner, iStartPost, iNumDigits);

        int b = 0;

    }

    private static int overlayNumber(int iMaster, int iInner, int iStartPos, int iNumDigits) {

        // Create a mask to blat out all the existing digits.
        int i1 = ~0;
        i1 = i1 << (iStartPos + iNumDigits - 1);

        int i2 = 1;
        i2 = i2 << (iStartPos-1);
        i2 -= 1;

        int iMask = i1 | i2;
        int iMask2 = ~iMask;

        int iInner2 = (iInner << (iStartPos-1)) & iMask2;

        int iMaster2 = iMaster & iMask;

        return iMaster2 | iInner2;
    }
}
