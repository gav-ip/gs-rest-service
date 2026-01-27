package com.example.restservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {

    @Mock
    private EmployeeManager employeeManager;

    @InjectMocks
    private EmployeeController employeeController;

    private Employees mockEmployees;

    @BeforeEach
    void setUp() {
        mockEmployees = new Employees();
        mockEmployees.addEmployee(new Employee("1", "John", "Doe", "john@example.com", "Engineer"));
    }

    @Test
    void getEmployees_shouldReturnEmployeesFromManager() {
        when(employeeManager.getAllEmployees()).thenReturn(mockEmployees);

        Employees result = employeeController.getEmployees();

        assertNotNull(result);
        assertEquals(1, result.getEmployees().size());
        assertEquals("John", result.getEmployees().get(0).getFirst_name());
    }

    @Test
    void getEmployees_shouldCallManagerOnce() {
        when(employeeManager.getAllEmployees()).thenReturn(mockEmployees);

        employeeController.getEmployees();

        verify(employeeManager, times(1)).getAllEmployees();
    }

    @Test
    void addEmployee_shouldReturnCreatedStatus() {
        Employee newEmployee = new Employee("5", "New", "User", "new@example.com", "Dev");
        doNothing().when(employeeManager).addEmployee(any(Employee.class));

        ResponseEntity<Employee> response = employeeController.addEmployee(newEmployee);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void addEmployee_shouldReturnEmployeeInBody() {
        Employee newEmployee = new Employee("5", "New", "User", "new@example.com", "Dev");
        doNothing().when(employeeManager).addEmployee(any(Employee.class));

        ResponseEntity<Employee> response = employeeController.addEmployee(newEmployee);

        assertNotNull(response.getBody());
        assertEquals("5", response.getBody().getId());
        assertEquals("New", response.getBody().getFirst_name());
    }

    @Test
    void addEmployee_shouldReturnCorrectLocationHeader() {
        Employee newEmployee = new Employee("42", "Test", "User", "test@example.com", "QA");
        doNothing().when(employeeManager).addEmployee(any(Employee.class));

        ResponseEntity<Employee> response = employeeController.addEmployee(newEmployee);

        assertNotNull(response.getHeaders().getLocation());
        assertTrue(response.getHeaders().getLocation().toString().contains("/employees/42"));
    }

    @Test
    void addEmployee_shouldCallManagerAddEmployee() {
        Employee newEmployee = new Employee("5", "New", "User", "new@example.com", "Dev");
        doNothing().when(employeeManager).addEmployee(any(Employee.class));

        employeeController.addEmployee(newEmployee);

        verify(employeeManager, times(1)).addEmployee(newEmployee);
    }
}
