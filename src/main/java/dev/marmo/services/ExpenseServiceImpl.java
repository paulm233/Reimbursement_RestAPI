package dev.marmo.services;

import dev.marmo.data.ExpenseDAO;
import dev.marmo.data.ExpenseDAOPostgresImpl;
import dev.marmo.entities.Expense;
import dev.marmo.utilities.LogLevel;
import dev.marmo.utilities.Logger;

import java.util.List;

public class ExpenseServiceImpl implements ExpenseService {

    private ExpenseDAO expenseDAO;

    //dependency injection
    public ExpenseServiceImpl(ExpenseDAOPostgresImpl expenseDAO){
        this.expenseDAO = expenseDAO;
    }


    @Override
    public Expense registerExpense(Expense expense) {
          return this.expenseDAO.createExpense(expense);
    }

    @Override
    public List<Expense> retrieveAllExpenses() {
        return this.expenseDAO.getAllExpenses();
    }

    @Override
    public Expense retrieveExpenseByID(int expenseID) {
        return this.expenseDAO.getExpenseByID(expenseID);
    }

    @Override
    public List <Expense> retrieveExpensesByEmployeeID(int employeeID) {
        return this.expenseDAO.getExpensesByEmployeeID(employeeID);
    }

    @Override
    public Expense replaceExpense(Expense expense) {
        Logger.log("The record "+ expense.getExpenseID() + " was updated", LogLevel.INFO);
        return this.expenseDAO.updateExpense(expense);
    }

    @Override
    public Expense approveExpense(Expense expense) {
        expense.setStatus("Approved");
        return expenseDAO.updateExpense(expense);
    }

    @Override
    public Expense denyExpense(Expense expense) {
        expense.setStatus("Denied");
        return expenseDAO.updateExpense(expense);
    }

    @Override
    public boolean destroyExpenseByID(int expenseID) {
        return this.expenseDAO.deleteExpenseByID(expenseID);
    }
}
