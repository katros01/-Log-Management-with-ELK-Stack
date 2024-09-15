package com.gtp.ELKStack.Logging.Service;

import com.gtp.ELKStack.Logging.Model.Employee;
import com.gtp.ELKStack.Logging.exception.EmployeeNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);
    private List<Employee> employees = new ArrayList<>();

    public EmployeeService() {
        employees.add(new Employee(1L, "John", "Doe", "john.doe@example.com", "IT"));
        employees.add(new Employee(2L, "Jane", "Smith", "jane.smith@example.com", "HR"));
        employees.add(new Employee(3L, "Tom", "Smith", "tom@example.com", "software development"));
    }

    public List<Employee> getAllEmployees() {
        logger.info("Fetching all employees");
        return employees;
    }

    public Employee getEmployeeById(Long id) throws EmployeeNotFoundException {
        logger.info("Fetching employee with ID: {}", id);
        return employees.stream()
                .filter(emp -> emp.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with ID " + id + " not found"));
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
        logger.info("Added new employee: {}", employee.getFirstName());
    }

    public void deleteEmployee(Long id) {
        employees.removeIf(emp -> emp.getId().equals(id));
        logger.info("Deleted employee with ID: {}", id);
    }
}