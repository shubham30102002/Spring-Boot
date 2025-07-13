package com.example.thymleafDemo.model;

public class Student {
    private String firstName;
    private String lastName;
    private String email;
    private String course;
    private String gender;
    private String favSystems;

    // Constructors
    public Student() {}

    public Student(String firstName, String lastName, String email, String course) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.course = course;
    }

    // Getters and Setters
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFavSystems() {
        return favSystems;
    }

    public void setFavSystems(String favSystems) {
        this.favSystems = favSystems;
    }
}
