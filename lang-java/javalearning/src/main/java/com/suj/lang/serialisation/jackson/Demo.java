package com.suj.lang.serialisation.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.List;

/**
 * Created by sujayjayaram on 13/02/2016.
 */
public class Demo {

    // see http://www.mkyong.com/java/jackson-2-convert-java-object-to-from-json/
    private String name;
    private int age;
    private List<String> instruments;

    // Needed by Jackson - it doesn't need setters though!!!
    public Demo() {}

    public Demo(String name, int age, List<String> instruments) {
        this.name = name;
        this.age = age;
        this.instruments = instruments;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public List<String> getInstruments() {
        return instruments;
    }

    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Demo obj = new Demo("sujay", 48, Arrays.asList("bass", "guitar", "drums"));

        //Object to JSON in file
        //mapper.writeValue(new File("c:\\file.json"), obj);

        //Object to JSON in String
        String jsonInString = mapper.writeValueAsString(obj);
        System.out.println(jsonInString);

        //JSON from file to Object
        //Staff obj = mapper.readValue(new File("c:\\file.json"), Staff.class);

        //JSON from URL to Object
        //Staff obj = mapper.readValue(new URL("http://mkyong.com/api/staff.json"), Staff.class);

        //JSON from String to Object
        Demo obj2 = mapper.readValue(jsonInString, Demo.class);

        int i = 0; // set breakpoint here.
    }
}
