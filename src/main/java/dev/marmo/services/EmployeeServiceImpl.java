package dev.marmo.services;

import dev.marmo.data.EmployeeDAO;
import dev.marmo.entities.Employee;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDAO employeeDAO;

    //dependency injection. Building an object that uses another object within it
    public EmployeeServiceImpl(EmployeeDAO employeeDAO ){

        this.employeeDAO = employeeDAO;
    }

    @Override
    public Employee registerEmployee(Employee employee) {

        return this.employeeDAO.createEmployee(employee);
}

    @Override
    public List<Employee> retrieveAllEmployees() {
        return this.employeeDAO.getAllEmployees();
    }

    @Override
    public Employee retrieveEmployeeById(int id) {

        return this.employeeDAO.getEmployeeByID(id);
    }

    @Override
    public Employee editEmployee(Employee employee) {
        return this.employeeDAO.updateEmployee(employee);
    }

    @Override
    public boolean removeEmployeeByID(int id) {
        return this.employeeDAO.deleteEmployeeByID(id);
    }
}
