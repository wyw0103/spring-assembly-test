package com.cassius.spring.assembly.test.usages.domain;

/**
 * Person
 *
 * @author Cassius Cai
 * @version v 0.1 5/30/15 15:20 Exp $
 */
public class Person {

    private String  name;
    private int     age;
    private Address home;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Address getHome() {
        return home;
    }

    public void setHome(Address home) {
        this.home = home;
    }
}
