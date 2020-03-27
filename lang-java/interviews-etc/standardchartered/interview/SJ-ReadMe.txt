CODE REVIEW BRIEF:
*** These are just ideas. We should not 'review out' the soul of the person who wote the code!!!

- Team has issues delivering reliable releases of software
Q - What is wrong with the software
A - There are bugs in HireRecord.getRate() and Car.getAge() methods.
Also some SQL strings are not correctly escaped.
I wrote unit tests for this.

Q - how it can be improved
A -
Code Hygiene Factors:
ADDED LOGGING
ADDED CONFIGURATION
ADDED UNIT TESTS

Better entity model,
Less coupling with db layer,
Added generics type safety in Client class,
Added Ctr parameter safety in (new) Vehicle class

- educate to develop reliable, maintainable and extensible code
Redesign of model classes makes code more extensible

Decoupling from DbService has allowed the core classes to have unit tests and has
made the code more maintainable.

A new VehicleReg class introduces data validation and encapsulates 'age' calculation.

Adding Apache Commons Configuration has made the code more configurable
-> easier to maintain no need to redeploy if discount rate for Cars changes etc.

Created a Maven pom to allow others to easily checkout, build and run this code.
Unified use of JodaTime

Could consider wiring up HireService using Spring IOC??

* QUESTION: The DbService is provided by an external team - does this mean it uses a
generic ORM layer
i.e. is the design of the model within our own control???
* (Note consider using java.time instead of Joda Time if using Java SE8)



