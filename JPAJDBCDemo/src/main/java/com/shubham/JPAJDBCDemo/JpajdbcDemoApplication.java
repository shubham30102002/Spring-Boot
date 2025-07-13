package com.shubham.JPAJDBCDemo;

import com.shubham.JPAJDBCDemo.entity.Employee;
import com.shubham.JPAJDBCDemo.entity.employee.EmployeeDAO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.SQLOutput;
import java.util.List;

@SpringBootApplication
public class JpajdbcDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpajdbcDemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(EmployeeDAO employeeDAO) {
		return runner -> {
			//createEmployee(employeeDAO);
			//readEmployee(employeeDAO);
			//queryForEmployee(employeeDAO);
			//queryForEmployeeByLastName(employeeDAO);
			//updateEmployee(employeeDAO);
			//deleteEmployee(employeeDAO);
			deleteAllEmpoyee(employeeDAO);
		};
	}

	private void deleteAllEmpoyee(EmployeeDAO employeeDAO) {
		System.out.println("Company shutdown");
		int numRowsDeleted = employeeDAO.deleteAll();
		System.out.println("Number of rows affected "+ numRowsDeleted);
	}

	private void deleteEmployee(EmployeeDAO employeeDAO) {
		int id = 2;
		System.out.println("Employee "+ employeeDAO.findById(id));
		employeeDAO.delete(1);
		System.out.println("Employee is deleted");
	}

	private void updateEmployee(EmployeeDAO employeeDAO) {
		int id = 1;
		Employee employee = employeeDAO.findById(id);
		employee.setFirstName("Yash");
		employee.setLastName("Yadav");
		employeeDAO.update(employee);
		System.out.println("Updated employee details-> "+ employee);

	}

	private void queryForEmployeeByLastName(EmployeeDAO employeeDAO) {
		List<Employee> employees = employeeDAO.findByLastName("Gupta");
		for (Employee employee : employees){
			System.out.println(employee);
		}
	}

	private void queryForEmployee(EmployeeDAO employeeDAO) {
		List<Employee> employees = employeeDAO.findAll();
		for (Employee employee : employees){
			System.out.println(employee);
		}
	}

	private void readEmployee(EmployeeDAO employeeDAO) {
		Employee employee = new Employee("Shubham", "Gupta", 30000, "UPI");
		employeeDAO.save(employee);
		Integer id = employee.getId();
		System.out.println("Employee has reterving employee with id " + id);

		System.out.println("Employee with id "+ id + " is -> "+ employeeDAO.findById(id));
	}

	private void createEmployee(EmployeeDAO employeeDAO) {
		System.out.println("Creating new employee.....");
		Employee employee = new Employee("John", "Doe", 3000, "IT");

		System.out.println("Saving the employee");
		employeeDAO.save(employee);

		System.out.println("Employee has been saved with id "+ employee.getId());
	}
}
