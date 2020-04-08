package com.top30javainterviewquestions.reversealinkedlist;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListTest {

    @Test
    void reverse() {
        LinkedList<String> list = new LinkedList<>();
        list.add("Hello");
        list.add("From");
        list.add("Sunny");
        list.add("London");

        list.reverse();

        int xdebugMe = 0;
    }
}