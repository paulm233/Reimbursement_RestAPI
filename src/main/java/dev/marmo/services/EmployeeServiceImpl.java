package dev.marmo.services;

import dev.marmo.data.EmployeeDAO;
import dev.marmo.entities.Employee;
import dev.marmo.utilities.LogLevel;
import dev.marmo.utilities.Logger;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDAO employeeDAO;

    //dependency injection. Building an object that uses another object within it
    public EmployeeServiceImpl(EmployeeDAO employeeDAO ){

        this.employeeDAO = employeeDAO;
    }

    @Override
    public Employee registerEmployee(Employee employee) {

        Employee employee1 = this.employeeDAO.createEmployee(employee);
        Logger.log("The employee with ID " + employee1.getEmployeeID()+ " was created ", LogLevel.INFO);
        return employee1;
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
        Logger.log("The employee information has been updated", LogLevel.INFO);
        return this.employeeDAO.updateEmployee(employee);
    }

    @Override
    public boolean removeEmployeeByID(int id) {
        return this.employeeDAO.deleteEmployeeByID(id);
    }
}
