package dev.marmo.services;

import dev.marmo.entities.Employee;

import java.util.List;

public interface EmployeeService {


     //create
     Employee registerEmployee(Employee employee);

     //read
     List<Employee> retrieveAllEmployees();

     Employee retrieveEmployeeById(int id);


     //update
     Employee editEmployee (Employee employee);


     //delete

     boolean removeEmployeeByID (int id);


}
