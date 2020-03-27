/**
 * Created by sujayjayaram on 01/02/2020.
 */
// This gives type safety
function greeter(person) {
    return "Hello, " + person.firstName + " " + person.lastName;
}
var user = { firstName: "Jane", lastName: "Smith" };
// If user does not have the same shape, a compilation error occurs
// let user = { firstNameZZZ: "Jane", lastName: "User" };
console.log("hello " + greeter(user));
// firstName must exist or compilation error
user.firstName = "James";
console.log("hello " + greeter(user));
// firstName need not exist.
user["firstName"] = "Sally";
console.log("hello " + greeter(user));
delete user["firstName"];
console.log("hello " + greeter(user));
