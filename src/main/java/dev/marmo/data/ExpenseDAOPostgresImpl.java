package dev.marmo.data;


import dev.marmo.entities.Employee;
import dev.marmo.entities.Expense;
import dev.marmo.utilities.ConnectionUtil;
import dev.marmo.utilities.LogLevel;
import dev.marmo.utilities.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExpenseDAOPostgresImpl implements ExpenseDAO {

    @Override
    public Expense createExpense(Expense expense) {
        // insert into expense values (0, ", "Nye")

        Connection conn = ConnectionUtil.createConnection();
        String sql = "insert into expense values (default, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, expense.getDescription()); //get expense description
            ps.setDouble(2, expense.getAmount()); //get expense amount
            ps.setString(3, expense.getStatus()); // get status of expense
            ps.setInt(4,expense.getEmployeeID()); // get employee ID associated w expense
            ps.execute();

            ResultSet rs = ps.getGeneratedKeys(); //ResultSet is a virtual table
            rs.next(); //move the first record of the result set
            int generatedId = rs.getInt("expense_id");
            expense.setExpenseID(generatedId);
            return expense;



        } catch (SQLException e) {
            e.printStackTrace();
            Logger.log(e.getMessage(), LogLevel.ERROR);
            return null;
        }
    }

    @Override
    public List<Expense> getAllExpenses() {
        Connection conn = ConnectionUtil.createConnection();
        String sql = "select * from expense";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            List<Expense> expenses = new ArrayList();
            while(rs.next()){
                Expense expense = new Expense();
                expense.setExpenseID(rs.getInt("expense_id"));
                expense.setDescription(rs.getString("description"));
                expense.setAmount(rs.getDouble("amount"));
                expense.setStatus(rs.getString("status"));
                expense.setEmployeeID(rs.getInt("employee_id"));
                expenses.add(expense);
            }

            return expenses;




        } catch (SQLException e) {
            e.printStackTrace();
            Logger.log(e.getMessage(), LogLevel.ERROR);
            return null;
        }
    }

    @Override
    public Expense getExpenseByID(int expenseID) {
        Connection conn = ConnectionUtil.createConnection();
        String sql = "select * from expense where expense_id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,expenseID);

            ResultSet rs = ps.executeQuery();
            rs.next();
            Expense expense = new Expense();
            expense.setExpenseID(rs.getInt("expense_id"));
            expense.setDescription(rs.getString("description"));
            expense.setAmount(rs.getDouble("amount"));
            expense.setStatus(rs.getString("status"));
            expense.setEmployeeID(rs.getInt("employee_id"));

            return expense;




        } catch (SQLException e) {
            e.printStackTrace();
            Logger.log(e.getMessage(), LogLevel.ERROR);
            return null;
        }
    }

    @Override
    public List<Expense> getExpensesByEmployeeID(int employeeID) {
        Connection conn = ConnectionUtil.createConnection();
        String sql = "select * from expense where employee_id=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,employeeID);
            ResultSet rs = ps.executeQuery();

            List<Expense> expenses = new ArrayList();
            while(rs.next()){
                Expense expense = new Expense();
                expense.setExpenseID(rs.getInt("expense_id"));
                expense.setDescription(rs.getString("description"));
                expense.setAmount(rs.getDouble("amount"));
                expense.setStatus(rs.getString("status"));
                expense.setEmployeeID(rs.getInt("employee_id"));
                expenses.add(expense);
            }

            return expenses;




        } catch (SQLException e) {
            e.printStackTrace();
            Logger.log(e.getMessage(), LogLevel.ERROR);
            return null;
        }
    }


    @Override
    public Expense updateExpense(Expense expense) {
        try {
            Connection conn =  ConnectionUtil.createConnection();
            String sql = "update expense set description = ?, amount = ?, status = ?, employee_id = ? where expense_id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, expense.getDescription());
            ps.setDouble(2, expense.getAmount());
            ps.setString(3,expense.getStatus());
            ps.setInt(4, expense.getEmployeeID());
            ps.setInt(5, expense.getExpenseID());
            ps.executeUpdate();

            return expense;


        } catch (SQLException e) {
            e.printStackTrace();
            Logger.log(e.getMessage(), LogLevel.ERROR);
            return null;
        }
    }

    @Override
    public boolean deleteExpenseByID(int expenseID) {
        try{
            Connection conn = ConnectionUtil.createConnection();
            String sql = "delete from expense where expense_id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,expenseID);
            ps.execute();
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            Logger.log(e.getMessage(), LogLevel.ERROR);
            return false;

        }
    }
}
