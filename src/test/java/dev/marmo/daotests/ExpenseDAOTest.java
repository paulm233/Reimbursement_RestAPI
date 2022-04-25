package dev.marmo.daotests;


import dev.marmo.data.ExpenseDAO;
import dev.marmo.data.ExpenseDAOPostgresImpl;
import dev.marmo.entities.Expense;
import org.junit.jupiter.api.*;

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


}
