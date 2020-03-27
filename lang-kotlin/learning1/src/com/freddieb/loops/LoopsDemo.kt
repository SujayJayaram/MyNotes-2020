package com.freddieb.loops

fun main(args: Array<String>) {
    println("Hello World")

    // Goes up to 10
    for( i in 0..10 step 2) {
        println("Loop 1 using i with a value of $i")
    }

    println("")

    //
    for( i in 0 until 10 step 2) {
        println("Loop 2 using i with a value of $i")
    }

    println("")

    for( i in 10 downTo  0 step 2) {
        println("Loop 3 using i with a value of $i")
    }
}