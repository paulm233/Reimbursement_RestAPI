package dev.marmo.servicetests;

import dev.marmo.daotests.EmployeeDAOTest;
import dev.marmo.data.EmployeeDAO;
import dev.marmo.data.EmployeeDAOPostgresImpl;
import dev.marmo.entities.Employee;
import dev.marmo.services.EmployeeService;
import dev.marmo.services.EmployeeServiceImpl;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class EmployeeServiceTest {

//    static EmployeeService employeeService = new EmployeeServiceImpl();
//    static Employee testAccount = null;
//
//
//    @Test
//    @Order(1)
//    void register_employee_test() {
//        // An entity that is created but not yet saved should have an id of 0
//        //once saved that book should be some non-zero value
//
//        Employee billnye = new Employee(0, "Bill", "Nye");
//        Employee savedAccount = EmployeeService;
//        EmployeeDAOTest.testAccount = savedAccount;
//        Assertions.assertNotEquals(0, savedAccount.getEmployeeID());
 //   }
}
