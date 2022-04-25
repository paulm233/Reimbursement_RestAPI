package dev.marmo.data;

import dev.marmo.entities.Expense;

import java.util.List;

public interface ExpenseDAO {

    //create
    Expense createExpense (Expense expense);


    //Read
    Expense getExpenseByID (int expenseID);

    Expense getExpenseByEmployeeID(int employeeID);

    List<Expense> getAllExpenses();


    //Update
    Expense updateExpense(Expense expense);


    //Delete
    Expense deleteExpenseByID(int expenseID);


}
