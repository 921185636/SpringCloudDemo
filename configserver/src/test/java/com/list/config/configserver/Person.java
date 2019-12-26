package com.list.config.configserver;

public class Person {
    private String name;
    private int age;
    public Person(String name, int age){
        this.name = name;
        this.age = age;
    }

    @Override
    protected void finalize() throws Throwable {
//        super.finalize();
        System.out.println("对象被释放---》"+this);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
