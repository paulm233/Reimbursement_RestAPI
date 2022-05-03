package dev.marmo.services;

import dev.marmo.entities.Expense;

import java.util.List;

public interface ExpenseService {


    //create
    Expense registerExpense (Expense expense);


    //Read
    List<Expense> retrieveAllExpenses();

    Expense retrieveExpenseByID (int expenseID);

    List<Expense> retrieveExpensesByEmployeeID (int employeeID);


    //Update
    Expense replaceExpense (Expense expense);

    Expense approveExpense (Expense expense);

    Expense denyExpense (Expense expense);

    //Delete
    boolean destroyExpenseByID (int expenseID);

}
