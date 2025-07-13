package com.example.thymleafDemo.controller;

import com.example.thymleafDemo.model.Student;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class StudentController {

    @Value("${student.courses}")
    public List<String> courses;

    @Value("${student.gender}")
    public List<String> genders;

    @Value("${student.favOs}")
    public List<String> favSystemList;

    @GetMapping("/showForm")
    public String showForm(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        model.addAttribute("courses", courses);
        model.addAttribute("genders", genders);
        model.addAttribute("favSystemList", favSystemList);
        return "student-form";
    }

    @PostMapping("/processStudent")
    public String processForm(@ModelAttribute("student") Student student, Model model) {
        // Add logic to process student data
        //logging the student
        System.out.println("The student details enter: \nFirst name:" + student.getFirstName()
                +"\tLast name: "+ student.getLastName() +"\tEmail: "+student.getEmail()+ "\tGender: "+
                student.getGender()                         + "\tCourse: "+ student.getCourse()
                +"\tFavorite Operating System: "+student.getFavSystems());
        System.out.println("Submitting the student.........");

        model.addAttribute("student", student);
        return "student-confirmation";
    }
}
