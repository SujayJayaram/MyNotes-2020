package com.suj.lang.reflection;


import java.lang.reflect.Field;
import java.lang.reflect.Method;

// See http://tutorials.jenkov.com/java-reflection/private-fields-and-methods.html
public class FieldTroubleToo {
    public final boolean b = true;

    private String privateMethod(String s, int i) {
        return "XXX" + s + Integer.toString(i);
    }

    public static void main(String... args)  throws Exception {
        callPrivateMethod();
    }

    public static void callPrivateMethod() throws Exception {
        FieldTroubleToo ft = new FieldTroubleToo();

        Method privateStringMethod = FieldTroubleToo.class.getDeclaredMethod("privateMethod", String.class, int.class);

        privateStringMethod.setAccessible(true);

        String returnValue = (String)privateStringMethod.invoke(ft, "Fred", 20);

        System.out.println("returnValue = " + returnValue);
    }

    public void setPrivateMember() {
        FieldTroubleToo ft = new FieldTroubleToo();
        try {
            Class<?> c = ft.getClass();
            Field f = c.getDeclaredField("b");

            // If this line below is not present, the setBoolean call will throw IllegalAccessException
            f.setAccessible(true); // solution
            f.setBoolean(ft, Boolean.FALSE); // IllegalAccessException

            // production code should handle these exceptions more gracefully
        } catch (NoSuchFieldException x) {
            x.printStackTrace();
        } catch (IllegalArgumentException x) {
            x.printStackTrace();
        } catch (IllegalAccessException x) {
            x.printStackTrace();
        }
    }
}