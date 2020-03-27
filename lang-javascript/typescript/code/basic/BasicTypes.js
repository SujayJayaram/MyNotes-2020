/**
 * Created by sujayjayaram on 01/02/2020.
 */
// https://www.typescriptlang.org/docs/handbook/basic-types.html
// https://javascript.info/types
// Boolean
var isDone = false;
// Numbers
var decimal = 6.12345;
var hex = 0xf00d;
var binary = 10;
var octal = 484;
// Strings
var fullName = "Bob Bobbington";
var age = 37;
// Loop
var strings = ["Hello", "98052", "101"];
for (var _i = 0, strings_1 = strings; _i < strings_1.length; _i++) {
    var s = strings_1[_i];
    console.log(s);
}
// This one spans many lines due to the use of backtick
var sentence = "Hello, my name is " + fullName + ".\nI'll be " + (age + 1) + " years old next month.";
// Above is same as
var sentence2 = "Hello, my name is " + fullName + ".\n\n" +
    "I'll be " + (age + 1) + " years old next month.";
// Arrays - two ways
var list = [1, 2, 3];
var list2 = [1, 2, 3];
for (var _a = 0, list_1 = list; _a < list_1.length; _a++) {
    var l = list_1[_a];
    console.log(l);
}
// Tuples
// Declare a tuple type
var x;
// Initialize it
x = ["hello", 10]; // OK
// Initialize it incorrectly
// x = [10, "hello"]; // Error
// Enum
var Color;
(function (Color) {
    Color[Color["Red"] = 0] = "Red";
    Color[Color["Green"] = 1] = "Green";
    Color[Color["Blue"] = 2] = "Blue";
})(Color || (Color = {}));
var c = Color.Green;
// Any types can be assigned to different types
var notSure = 4;
notSure = "maybe a string instead";
notSure = false; // okay, definitely a boolean
