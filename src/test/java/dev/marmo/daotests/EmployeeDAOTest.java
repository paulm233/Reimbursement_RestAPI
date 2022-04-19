package dev.marmo.daotests;


import dev.marmo.data.EmployeeDAO;
import dev.marmo.data.EmployeeDAOPostgresImpl;
import dev.marmo.entities.Employee;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class EmployeeDAOTest {

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
    void get_employee_by_id(){
        Employee retrievedEmployee = EmployeeDAO.getEmployeeByID(testAccount.getEmployeeID());
        System.out.println(retrievedEmployee);
        Assertions.assertEquals("Bill", retrievedEmployee.getFirstName());
    }


}
