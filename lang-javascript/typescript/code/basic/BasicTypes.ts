/**
 * Created by sujayjayaram on 01/02/2020.
 */

// https://www.typescriptlang.org/docs/handbook/basic-types.html
// https://javascript.info/types

// Boolean
let isDone: boolean = false;


// Numbers
let decimal: number = 6.12345;
let hex: number = 0xf00d;
let binary: number = 0b1010;
let octal: number = 0o744;


// Strings
let fullName: string = `Bob Bobbington`;
let age: number = 37;

// Loop
let strings = ["Hello", "98052", "101"];
for (let s of strings) {
    console.log(s);
}


// This one spans many lines due to the use of backtick
let sentence: string = `Hello, my name is ${ fullName }.
I'll be ${ age + 1 } years old next month.`;

// Above is same as
let sentence2 : string = "Hello, my name is " + fullName + ".\n\n" +
    "I'll be " + (age + 1) + " years old next month.";

// Arrays - two ways
let list: number[] = [1, 2, 3];
let list2 : Array<number> = [1, 2, 3];
for (let l of list) {
    console.log(l);
}


// Tuples
// Declare a tuple type
let x: [string, number];
// Initialize it
x = ["hello", 10]; // OK
// Initialize it incorrectly
// x = [10, "hello"]; // Error


// Enum
enum Color {Red, Green, Blue}
let c: Color = Color.Green;

// Any types can be assigned to different types
let notSure: any = 4;
notSure = "maybe a string instead";
notSure = false; // okay, definitely a boolean

