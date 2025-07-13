package com.dev.RestCRUDDemo2.controller;

import com.dev.RestCRUDDemo2.dao.EmployeeDAO;
import com.dev.RestCRUDDemo2.entity.Employee;
import com.dev.RestCRUDDemo2.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    @Autowired
    EmployeeService service;

    @Autowired
    ObjectMapper objectMapper;

    @GetMapping("/employee")
    public List<Employee> getAllEmployee(){
        return service.findAll();
    }

    @GetMapping("/employee/{employeeId}")
    public Employee getEmployeeById(@PathVariable int employeeId) throws Exception {
        Employee employee = service.findById(employeeId);
        if(employee == null)
            throw new Exception("EmployeeId not found");
        return employee;
    }

    @PostMapping("/employee/{employeeId}")
    public void addEmployee(@RequestBody Employee employee){
        service.save(employee);
        System.out.println("Employee saved in db with id "+ employee.getId());
    }

    @PutMapping("/employee")
    public void updateEmployee(@RequestBody Employee employee){
        service.save(employee);
        System.out.println("Employee updated with id "+ employee.getId());
    }

    @DeleteMapping("/employee/{employeeId}")
    public  void deleteEmployee(@PathVariable int employeeId) throws Exception {
        Employee employee = service.findById(employeeId);
        if(employee == null)
            throw new Exception("EmployeeId not found");
        System.out.println("Deleting employee "+ employee);
        service.delete(employeeId);
    }

    //for partial update
    @PatchMapping("/employee/{employeeId}")
    public Employee patchEmployee(@PathVariable int employeeId, @RequestBody Map<String, Object> patchPayload) {
        Employee employee = service.findById(employeeId);
        if(employee == null)
            throw new RuntimeException("EmployeeId not found for id "+ employeeId);

        if(patchPayload.containsKey("id"))
            throw new RuntimeException("Id is not allowed to be updated");

        Employee patchEmployee = apply(patchPayload, employee);
        return service.save(patchEmployee);
    }

    private Employee apply(Map<String, Object> patchPayload, Employee tempEmployee) {

        // Convert employee object to a JSON object node
        ObjectNode employeeNode = objectMapper.convertValue(tempEmployee, ObjectNode.class);

        // Convert the patchPayload map to a JSON object node
        ObjectNode patchNode = objectMapper.convertValue(patchPayload, ObjectNode.class);

        // Merge the patch updates into the employee node
        employeeNode.setAll(patchNode);

        return objectMapper.convertValue(employeeNode, Employee.class);
    }

}
