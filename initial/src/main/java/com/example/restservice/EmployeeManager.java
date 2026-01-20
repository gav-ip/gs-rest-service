package com.example.restservice;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeManager {
    private Employees employees = new Employees();

    public EmployeeManager(){
        employees.addEmployee(new Employee("1", "John", "Doe", "john.doe@example.com", "Software Engineer"));
        employees.addEmployee(new Employee("2", "Jane", "Smith", "jane.smith@example.com", "Software Engineer"));
        employees.addEmployee(new Employee("3", "Jim", "Beam", "jim.beam@example.com", "Software Engineer"));
    }

    public Employees getAllEmployees() {
        return employees;
    }

    public void addEmployee(Employee employee) {
        employees.addEmployee(employee);
    }

    public void removeEmployee(Employee employee) {
        employees.removeEmployee(employee);
    }
}