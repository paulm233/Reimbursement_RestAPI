package dev.marmo.data;

import dev.marmo.entities.Employee;

public interface EmployeeDAO {

    //create
    Employee createEmployee (Employee employee);

    //read
    Employee getEmployeeByID (int id);

    //update
    Employee updateEmployee (Employee employee);

    //delete
    boolean deleteEmployeeByID (int id);

}
