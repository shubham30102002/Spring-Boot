package com.dev.RestCRUDDemo.entity;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
public class Student {
    private String firstName;
    private String lastName;

    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
