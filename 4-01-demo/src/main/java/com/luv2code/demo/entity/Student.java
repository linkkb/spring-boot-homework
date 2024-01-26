package com.luv2code.demo.entity;

public class Student {

    private String firstName;
    private String lastName;

    public Student() {

    }

    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        System.out.println("getting firstname");
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        System.out.println("getting lastname");
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
