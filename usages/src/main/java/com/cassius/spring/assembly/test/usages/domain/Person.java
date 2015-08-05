package com.cassius.spring.assembly.test.usages.domain;

/**
 * Person
 *
 * @author Cassius Cai
 * @version v 0.1 5/30/15 15:20 Exp $
 */
public class Person {

    /**
     * The Name.
     */
    private String  name;
    /**
     * The Age.
     */
    private int     age;
    /**
     * The Home.
     */
    private Address home;

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets age.
     *
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets age.
     *
     * @param age the age
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Gets home.
     *
     * @return the home
     */
    public Address getHome() {
        return home;
    }

    /**
     * Sets home.
     *
     * @param home the home
     */
    public void setHome(Address home) {
        this.home = home;
    }
}
