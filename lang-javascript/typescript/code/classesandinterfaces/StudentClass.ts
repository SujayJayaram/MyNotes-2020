/**
 * Created by sujayjayaram on 01/02/2020.
 */


    /*
     In TypeScript, two types are compatible if their internal structure is compatible.
     This allows us to implement an interface just by having the shape the interface
     requires, without an explicit implements clause.
     */
    class Student {
        fullName: string;

        // **** Anything passed into the ctr is always captured as member data
        constructor(public firstName: string, public middleInitial: string, public lastName: string) {
            this.fullName = firstName + " " + middleInitial + " " + lastName;
        }

        greetMe(): string {
            console.log(this.firstName + " XXXX " + this.lastName);
            return "foo";
        }
    }

    interface Person {
        firstName: string;
        lastName: string;
    }

    function greeter(person: Person) {
        return "Hello, " + person.firstName + " " + person.lastName;
    }

    let user = new Student("Jane", "M.", "User");
    user.greetMe();

    console.log(greeter(user));


