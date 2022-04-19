package dev.marmo.entities;

public class Expense {

    private int expenseID;
    private int amount;
    private String description;
    private int employeeID;

    public Expense(){

    }

    public int getExpenseID() {
        return expenseID;
    }

    public void setExpenseID(int expenseID) {
        this.expenseID = expenseID;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", employeeID=" + employeeID +
                '}';
    }
}
