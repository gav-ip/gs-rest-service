package com.example.restservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class EmployeeManagerTest {

    private EmployeeManager employeeManager;

    @BeforeEach
    void setUp() {
        employeeManager = new EmployeeManager();
    }

    @Test
    void getAllEmployees_shouldReturnPreloadedEmployees() {
        Employees result = employeeManager.getAllEmployees();

        assertNotNull(result);
        assertEquals(3, result.getEmployees().size());
    }

    @Test
    void getAllEmployees_shouldContainExpectedEmployee() {
        Employees result = employeeManager.getAllEmployees();

        boolean hasJohn = result.getEmployees().stream()
            .anyMatch(e -> "John".equals(e.getFirst_name()) && "Doe".equals(e.getLast_name()));

        assertTrue(hasJohn);
    }

    @Test
    void addEmployee_shouldIncreaseEmployeeCount() {
        int initialCount = employeeManager.getAllEmployees().getEmployees().size();

        Employee newEmployee = new Employee("4", "New", "Person", "new@example.com", "Intern");
        employeeManager.addEmployee(newEmployee);

        assertEquals(initialCount + 1, employeeManager.getAllEmployees().getEmployees().size());
    }

    @Test
    void addEmployee_shouldMakeEmployeeRetrievable() {
        Employee newEmployee = new Employee("99", "Test", "User", "test@example.com", "QA");

        employeeManager.addEmployee(newEmployee);

        boolean found = employeeManager.getAllEmployees().getEmployees().stream()
            .anyMatch(e -> "99".equals(e.getId()));
        assertTrue(found);
    }

    @Test
    void removeEmployee_shouldDecreaseEmployeeCount() {
        Employee toRemove = employeeManager.getAllEmployees().getEmployees().get(0);
        int initialCount = employeeManager.getAllEmployees().getEmployees().size();

        employeeManager.removeEmployee(toRemove);

        assertEquals(initialCount - 1, employeeManager.getAllEmployees().getEmployees().size());
    }
}
