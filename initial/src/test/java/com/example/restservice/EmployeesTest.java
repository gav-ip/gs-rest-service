package com.example.restservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeesTest {

    private Employees employees;

    @BeforeEach
    void setUp() {
        employees = new Employees();
    }

    @Test
    void addEmployee_shouldAddSingleEmployee() {
        Employee employee = new Employee("1", "John", "Doe", "john@example.com", "Engineer");

        employees.addEmployee(employee);

        assertEquals(1, employees.getEmployees().size());
        assertTrue(employees.getEmployees().contains(employee));
    }

    @Test
    void addEmployee_shouldAddMultipleEmployees() {
        Employee emp1 = new Employee("1", "John", "Doe", "john@example.com", "Engineer");
        Employee emp2 = new Employee("2", "Jane", "Smith", "jane@example.com", "Manager");

        employees.addEmployee(emp1);
        employees.addEmployee(emp2);

        assertEquals(2, employees.getEmployees().size());
        assertTrue(employees.getEmployees().contains(emp1));
        assertTrue(employees.getEmployees().contains(emp2));
    }

    @Test
    void getEmployees_shouldReturnEmptyListWhenNoEmployees() {
        List<Employee> result = employees.getEmployees();

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void getEmployees_shouldReturnAllAddedEmployees() {
        Employee emp1 = new Employee("1", "John", "Doe", "john@example.com", "Engineer");
        employees.addEmployee(emp1);

        List<Employee> result = employees.getEmployees();

        assertEquals(1, result.size());
        assertEquals("John", result.get(0).getFirst_name());
        assertEquals("Doe", result.get(0).getLast_name());
    }

    @Test
    void removeEmployee_shouldRemoveExistingEmployee() {
        Employee employee = new Employee("1", "John", "Doe", "john@example.com", "Engineer");
        employees.addEmployee(employee);

        employees.removeEmployee(employee);

        assertTrue(employees.getEmployees().isEmpty());
    }
}
