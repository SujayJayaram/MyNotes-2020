package com.top30javainterviewquestions.wordispalindrome;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    @Test
    void isPalindrome() {

        assertEquals(true, Solution.isPalindrome("ABBA"));
        assertEquals(true, Solution.isPalindrome("ABCBA"));

        assertEquals(false, Solution.isPalindrome("CBBA"));
        assertEquals(false, Solution.isPalindrome("CBCBA"));
    }
}