package com.example.springMVCCrud.controller;

import com.example.springMVCCrud.entity.Employee;
import com.example.springMVCCrud.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeRestController {

    @Autowired
    EmployeeService service;

    @GetMapping("/list")
    public String listEmployee(Model model){
        //get the employee from the db
        List<Employee> employees = service.findAll();
        System.out.println("Employee List: " + employees);
        //add the employee to the model
        model.addAttribute("employees", employees);

        return "list-employees";
    }


    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "employee-form";  // You'll create employee-form.html
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("employeeId") int id, Model model) {
        Employee employee = service.findById(id);
        model.addAttribute("employee", employee);
        return "employee-form";
    }

    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam("employeeId") int id) {
        service.deleteById(id);
        return "redirect:/employees/list";  // Redirect to employee list after deletion
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        service.save(employee);
        return "redirect:/employees/list";
    }

}