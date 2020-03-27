/**
 * Created by sujayjayaram on 01/02/2020.
 */
/*
 In TypeScript, two types are compatible if their internal structure is compatible.
 This allows us to implement an interface just by having the shape the interface
 requires, without an explicit implements clause.
 */
var Student = (function () {
    // **** Anything passed into the ctr is always captured as member data
    function Student(firstName, middleInitial, lastName) {
        this.firstName = firstName;
        this.middleInitial = middleInitial;
        this.lastName = lastName;
        this.fullName = firstName + " " + middleInitial + " " + lastName;
    }
    Student.prototype.greetMe = function () {
        console.log(this.firstName + " XXXX " + this.lastName);
        return "foo";
    };
    return Student;
}());
function greeter(person) {
    return "Hello, " + person.firstName + " " + person.lastName;
}
var user = new Student("Jane", "M.", "User");
user.greetMe();
console.log(greeter(user));
