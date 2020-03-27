package interview.model;


import interview.service.HireRecord;

import java.util.ArrayList;

/**
 * Changes:
 * 1. licenseNumber made private (it was public but had a getter too!).
 * 2. addHirerecrd() -> addHireRecord() to conform with JavaBeans naming convention.
 * 3. renamed records -> hireRecords to make it clearer. Its type is now ArrayList<HireRecord>
 *     as we don't need the synchronisation overhead of Vector here but seek the type safety of a generic type.
 *     It's also now initialised at the point of declaration instead of in the addHireRecord() method.
 * 4. getHireRecords() now returns a COPY of the hireRecords list to preserve encapsulation.
 * 5. Client class is a pojo and should NOT need to know about connection details to db!
 * (this was what the 'cd' member was - I have removed this and it's getter).
 * 6. Made toString() slightly more readable
 *
 * Consider also adding equals() and hashCode() if clients are ever stored in collections etc.
 */
public class Client {
    private String name;
    private String licenseNumber;

    // Josh Bloc has an interesting article on using generics type inference
    // prior to Java 7/Diamond Operators
    // public static <K,V> HashMap<K,V> newHashMap() {
    //     return new HashMap<K, V>();
    // }
    private ArrayList<HireRecord> hireRecords = new ArrayList<>();

    public Client(String name, String licenseNumber) {
        this.name = name;
        this.licenseNumber = licenseNumber;
    }

    public String getName() {
        return name;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public ArrayList<HireRecord> getHireRecords() {
        return new ArrayList<HireRecord>(hireRecords);
    }

    public void addHireRecord(HireRecord r) {
        hireRecords.add(r);
    }

    @Override
    public String toString() {
        return "Name: " + name + " Licence:" + licenseNumber;
    }
}
