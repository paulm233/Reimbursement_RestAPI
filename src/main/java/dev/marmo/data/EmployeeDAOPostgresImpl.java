package dev.marmo.data;

import dev.marmo.entities.Employee;
import dev.marmo.utilities.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOPostgresImpl implements EmployeeDAO {


    @Override
    public Employee createEmployee(Employee employee) {
        // insert into employee values (0, "Bill, "Nye")

        Connection conn = ConnectionUtil.createConnection();
        String sql = "insert into employee values (default, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, employee.getFirstName()); //for first question mark
            ps.setString(2, employee.getLastName()); //for second question mark

            ps.execute();

            ResultSet rs = ps.getGeneratedKeys(); //ResultSet is a virtual table
            rs.next(); //move the first record of the result set
            int generatedId = rs.getInt("employee_id");
            employee.setEmployeeID(generatedId);
            return employee;



        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Employee> getAllEmployees() {
        Connection conn = ConnectionUtil.createConnection();
        String sql = "select * from employee";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            List<Employee> employees = new ArrayList();
            while(rs.next()){
                Employee employee = new Employee();
                employee.setEmployeeID(rs.getInt("employee_id"));
                employee.setFirstName(rs.getString("f_name"));
                employee.setLastName(rs.getString("l_name"));
                employees.add(employee);
            }

            return employees;




        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Employee getEmployeeByID(int id) {
        Connection conn = ConnectionUtil.createConnection();
        String sql = "select * from employee where employee_id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);

            ResultSet rs = ps.executeQuery();
            rs.next();
            Employee employee = new Employee();
            employee.setEmployeeID(rs.getInt("employee_id"));
            employee.setFirstName(rs.getString("f_name"));
            employee.setLastName(rs.getString("l_name"));

            return employee;




        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }



    @Override
    public Employee updateEmployee(Employee employee) {
        try {
            Connection conn =  ConnectionUtil.createConnection();
            String sql = "update employee set f_name = ?, l_name = ? where employee_id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, employee.getFirstName());
            ps.setString(2, employee.getLastName());
            ps.setInt(3,employee.getEmployeeID());
            ps.executeUpdate();

            return employee;


        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deleteEmployeeByID(int id) {
        try {
            Connection conn = ConnectionUtil.createConnection();
            String sql = "delete from employee where employee_id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
