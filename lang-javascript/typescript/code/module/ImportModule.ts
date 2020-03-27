/**
 * Created by sujayjayaram on 01/02/2020.
 */

import { Sujay } from "./StudentClass2";
import Student = Sujay.Student;

import { Student3 } from "./StudentClass3";

let s = new Student("Jane", "M.", "User");
s.greetMe();


let s3 = new Student3("Jane", "M.", "User");
s3.greetMe();