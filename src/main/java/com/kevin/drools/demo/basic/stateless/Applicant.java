package com.kevin.drools.demo.basic.stateless;

/**
 * @author Kevin
 * @date 2014/4/19.
 */
public class Applicant {
    private String name;
    private int age;

    public Applicant(String name, int age) {
        this.name = name;
        this.age = age;
    }

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
}
