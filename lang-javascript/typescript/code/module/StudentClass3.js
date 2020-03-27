/**
 * Created by sujayjayaram on 01/02/2020.
 */
"use strict";
var Student3 = (function () {
    // **** Anything passed into the ctr is always captured as member data
    function Student3(firstName, middleInitial, lastName) {
        this.firstName = firstName;
        this.middleInitial = middleInitial;
        this.lastName = lastName;
        this.fullName = firstName + " " + middleInitial + " " + lastName;
    }
    Student3.prototype.greetMe = function () {
        console.log(this.firstName + " XXXX " + this.lastName);
        return "foo";
    };
    return Student3;
}());
exports.Student3 = Student3;
