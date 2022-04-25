package dev.marmo.data;

import dev.marmo.entities.Employee;

import java.util.List;

public interface EmployeeDAO {

    //create
    Employee createEmployee (Employee employee);

    //read
    List<Employee> getAllEmployees();

    Employee getEmployeeByID (int id);


    //update
    Employee updateEmployee (Employee employee);

    //delete
    boolean deleteEmployeeByID (int id);



}
