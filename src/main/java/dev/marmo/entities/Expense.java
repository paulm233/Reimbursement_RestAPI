package dev.marmo.entities;

public class Expense {

    private int expenseID;
    private String description;
    private double amount;
    private String status;
    private int employeeID;

    public Expense(){

    }

    public Expense(int expenseID, String description, double amount, String status, int employeeID) {
        this.expenseID = expenseID;
        this.description = description;
        this.amount = amount;
        this.status = status;
        this.employeeID = employeeID;
    }

    public int getExpenseID() {
        return expenseID;
    }

    public void setExpenseID(int expenseID) {
        this.expenseID = expenseID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {

        return amount;
    }

    public void setAmount(double amount) {

        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "expenseID=" + expenseID +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                ", employeeID=" + employeeID +
                '}';
    }
}
