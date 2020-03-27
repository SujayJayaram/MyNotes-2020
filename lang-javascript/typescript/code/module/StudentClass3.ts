/**
 * Created by sujayjayaram on 01/02/2020.
 */

    export class Student3 {
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
