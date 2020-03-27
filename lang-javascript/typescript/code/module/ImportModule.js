/**
 * Created by sujayjayaram on 01/02/2020.
 */
"use strict";
var StudentClass2_1 = require("./StudentClass2");
var Student = StudentClass2_1.Sujay.Student;
var StudentClass3_1 = require("./StudentClass3");
var s = new Student("Jane", "M.", "User");
s.greetMe();
var s3 = new StudentClass3_1.Student3("Jane", "M.", "User");
s3.greetMe();
