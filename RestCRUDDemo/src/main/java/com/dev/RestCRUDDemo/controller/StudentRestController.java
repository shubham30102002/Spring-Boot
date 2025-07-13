package com.dev.RestCRUDDemo.controller;

import com.dev.RestCRUDDemo.entity.Student;
import com.dev.RestCRUDDemo.exceptionHandling.StudentNotFoundException;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private List<Student> theStudents;

    // define @PostConstruct to load the student data ... only once!
    @PostConstruct
    public void loadData() {

        theStudents = new ArrayList<>();

        theStudents.add(new Student("Poornima", "Patel"));
        theStudents.add(new Student("Mario", "Rossi"));
        theStudents.add(new Student("Mary", "Smith"));
    }


    @GetMapping("/students")
    public List<Student> getStudents(){
        return theStudents;
    }

    @GetMapping("/student/{studentId}")
    public  Student getStudent(@PathVariable int studentId)  {
        if ( (studentId >= theStudents.size()) || (studentId < 0)) {
           throw new StudentNotFoundException("Student id not found - " + studentId);
           //return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return theStudents.get(studentId);
    }
}
