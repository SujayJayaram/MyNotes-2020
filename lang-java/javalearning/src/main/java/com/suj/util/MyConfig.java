package com.suj.util;


import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;


/**
 * Created by sujayjayaram on 13/01/2016.
 *
 * Singleton class which uses Apache Commons Configuration for properties
 * I am not doing any fancy volatile/double checked locking patterns -
 * just using an Enum-as-Singleton Pattern
 */
public enum MyConfig {

    INSTANCE;

    private Configuration config = null;

    private MyConfig() {
        try {
            config = new PropertiesConfiguration("javalearning.properties");
        }
        catch(ConfigurationException cEx){
            cEx.printStackTrace();
            throw new RuntimeException("Could not load config", cEx);
        }
    }

    public int getInt(String propName) {
        return config.getInt(propName);
    }

    public double getDouble(String propName){
        return config.getDouble(propName);
    }
}
