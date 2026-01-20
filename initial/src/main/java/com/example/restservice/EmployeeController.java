
package com.example.restservice;

import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping; // ADD THIS
import org.springframework.web.bind.annotation.RequestBody; // ADD THIS
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity; // ADD THIS

import com.example.restservice.Employees;
import com.example.restservice.EmployeeManager;

// Creating the REST controller
@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

    @Autowired
    private EmployeeManager employeeManager;

    @GetMapping(path = "/", produces = "application/json")
    public Employees getEmployees() {
        return employeeManager.getAllEmployees();
    }

    @PostMapping(path = "/", consumes = "application/json")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        employeeManager.addEmployee(employee);
        // condense into URI location variable
        // EX: URI location = ...
        return ResponseEntity.created(URI.create("/employees/" + employee.getId())).body(employee);
    }
}