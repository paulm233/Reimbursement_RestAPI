package dev.marmo.daotests;


import dev.marmo.data.ExpenseDAO;
import dev.marmo.data.ExpenseDAOPostgresImpl;
import dev.marmo.entities.Employee;
import dev.marmo.entities.Expense;
import org.junit.jupiter.api.*;

import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ExpenseDAOTest {

    static ExpenseDAO expenseDAO = new ExpenseDAOPostgresImpl();
    static Expense  testExpense = null;

    @Test
    @Order(1)
    public void create_expense_test(){
        Expense sample = new Expense(0,"flight tickets",57.50,"Pending",1);
        Expense saved = expenseDAO.createExpense(sample);
        ExpenseDAOTest.testExpense = saved;
        Assertions.assertNotEquals(0,saved.getExpenseID());

    }

    @Test
    @Order(2)
    void get_all_expenses(){
        Expense a = new Expense(0,"Taco Bell",12.60,"Pending",7);
        Expense b = new Expense(0,"Gasoline",45.37,"Pending",8);
        Expense c = new Expense(0,"Candy",4.32,"Pending",9);
        expenseDAO.createExpense(a);
        expenseDAO.createExpense(b);
        expenseDAO.createExpense(c);
        List<Expense> expenses = expenseDAO.getAllExpenses();
        int totalExpenses = expenses.size();
        Assertions.assertTrue(totalExpenses>= 3);

    }


    @Test
    @Order(3)
    void get_expense_by_id(){
        Expense retrievedExpense = expenseDAO.getExpenseByID(testExpense.getExpenseID());
        System.out.println(retrievedExpense);
        Assertions.assertEquals(retrievedExpense.getExpenseID(), retrievedExpense.getExpenseID());
    }

    @Test
    @Order(4)
    void get_expenses_by_employee_id(){
        Expense a = new Expense(0,"fast food",15.75,"Pending",7);
        Expense b = new Expense(0,"Gasoline",10.50,"Pending",7);
        expenseDAO.createExpense(a);
        expenseDAO.createExpense(b);
        List <Expense> employeeExpenses = expenseDAO.getExpensesByEmployeeID(testExpense.getEmployeeID());
        System.out.println(employeeExpenses);
        int totalExpenses = employeeExpenses.size();
        System.out.println(totalExpenses);
        Assertions.assertTrue(totalExpenses>= 2);

    }

    @Test
    @Order(5)
    void update_expense(){
        ExpenseDAOTest.testExpense.setDescription("Chicken Joint");
        expenseDAO.updateExpense(testExpense);

        Expense retrievedExpense = expenseDAO.getExpenseByID(testExpense.getExpenseID());
        Assertions.assertEquals("Chicken Joint",retrievedExpense.getDescription());
    }

    @Test
    @Order(6)
    void delete_expense(){
        boolean result = expenseDAO.deleteExpenseByID(testExpense.getExpenseID()); //true if successful
        Assertions.assertTrue(result);
    }
}
