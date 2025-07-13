package com.example.validateDemo;

import com.example.validateDemo.validation.CourseCode;
import jakarta.validation.constraints.*;

public class Customer {

    private String firstName;

    //not null should be initialized
    @NotNull(message="is required")
    @Size(min=1, message="is required")
    private String lastName = "";

    @NotNull(message="is required")
    @Min(value=1, message = "Min value must be 1")
    @Max(value=10, message = "Max value can be only 10")
    private Integer freePasses;

    @Pattern(regexp="^[a-zA-Z0-9]{6}", message="only 6 chars/digits")
    private String postalCode;

    //custom annotation for validation
    @CourseCode
    private String courseCode;

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Integer getFreePasses() {
        return freePasses;
    }

    public void setFreePasses(Integer freePasses) {
        this.freePasses = freePasses;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}