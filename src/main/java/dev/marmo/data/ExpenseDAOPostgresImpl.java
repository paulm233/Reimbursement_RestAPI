package dev.marmo.data;

import dev.marmo.entities.Expense;
import dev.marmo.utilities.ConnectionUtil;

import java.sql.*;
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
            return null;
        }
    }

    @Override
    public Expense getExpenseByID(int expenseID) {
        return null;
    }

    @Override
    public Expense getExpenseByEmployeeID(int employeeID) {
        return null;
    }

    @Override
    public List<Expense> getAllExpenses() {
        return null;
    }

    @Override
    public Expense updateExpense(Expense expense) {
        return null;
    }

    @Override
    public Expense deleteExpenseByID(int expenseID) {
        return null;
    }
}
