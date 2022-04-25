package dev.marmo.daotests;


import dev.marmo.data.EmployeeDAO;
import dev.marmo.data.EmployeeDAOPostgresImpl;
import dev.marmo.entities.Employee;
import org.junit.jupiter.api.*;

import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class EmployeeDAOTest {

    //fix the grammar of this, camel casing
    static EmployeeDAO EmployeeDAO = new EmployeeDAOPostgresImpl();
    static Employee testAccount = null;


    @Test
    @Order(1)
    void create_employee_test() {
        // An entity that is created but not yet saved should have an id of 0
        //once saved that book should be some non-zero value


        Employee billnye = new Employee(0, "Bill", "Nye");
        Employee savedAccount = EmployeeDAO.createEmployee(billnye);
        EmployeeDAOTest.testAccount = savedAccount;
        Assertions.assertNotEquals(0, savedAccount.getEmployeeID());


    }

    @Test
    @Order(2)
    void get_all_accounts(){
        Employee a = new Employee(0, "Mike","Laundry");
        Employee b = new Employee(0, "Randy","Savage");
        Employee c = new Employee(0, "Eric","Andre");
        EmployeeDAO.createEmployee(a);
        EmployeeDAO.createEmployee(b);
        EmployeeDAO.createEmployee(c);
        List<Employee> employees = EmployeeDAO.getAllEmployees();
        int totalAccounts = employees.size();
        Assertions.assertTrue(totalAccounts>= 3);
        //System.out.println(accounts.get(12));

    }

    @Test
    @Order(3)
    void get_employee_by_id(){
        Employee retrievedEmployee = EmployeeDAO.getEmployeeByID(testAccount.getEmployeeID());
        System.out.println(retrievedEmployee);
        Assertions.assertEquals("Bill", retrievedEmployee.getFirstName());
    }

    @Test
    @Order(4)
    void update_employee(){
        EmployeeDAOTest.testAccount.setFirstName("Jeff");
        EmployeeDAO.updateEmployee(testAccount); //the new name should be saved to the

        Employee retrievedUser = EmployeeDAO.getEmployeeByID(testAccount.getEmployeeID());
        Assertions.assertEquals("Jeff", retrievedUser.getFirstName());

    }

    @Test
    @Order(5)
    void delete_employee_by_id(){
        boolean result = EmployeeDAO.deleteEmployeeByID(testAccount.getEmployeeID()); //true if successful
        Assertions.assertTrue(result);

    }




}
