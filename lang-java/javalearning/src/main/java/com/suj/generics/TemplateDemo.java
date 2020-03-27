package com.suj.generics;

public class TemplateDemo<T extends  Fruit> {

    private T myT;

    public TemplateDemo(T t) {
        this.myT = t;
        System.out.println(myT.toString());
    }

    public static void main(String[] args) {

        TemplateDemo<Fruit> t1 = new TemplateDemo<>(new Fruit());

        TemplateDemo<Apple> t2 = new TemplateDemo<>(new Apple());

        // Won't compile
        // TemplateDemo<Plant> t3 = new TemplateDemo<>();
    }

    public <P extends Fruit> P doIt(P test) {
        test.fooMethod();

        if ( test != null )
            return test;

        return null;
    }
}
