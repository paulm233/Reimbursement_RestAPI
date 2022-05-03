package dev.marmo.data;

import dev.marmo.entities.Expense;

import java.util.List;

public interface ExpenseDAO {

    //create
    Expense createExpense (Expense expense);


    //Read
    List<Expense> getAllExpenses();

    Expense getExpenseByID (int expenseID);

    List <Expense> getExpensesByEmployeeID(int employeeID);




    //Update
    Expense updateExpense(Expense expense);


    //Delete
    boolean deleteExpenseByID(int expenseID);


}
