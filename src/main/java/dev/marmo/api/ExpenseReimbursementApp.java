package dev.marmo.api;

import com.google.gson.Gson;
import dev.marmo.data.EmployeeDAOPostgresImpl;
import dev.marmo.data.ExpenseDAOPostgresImpl;
import dev.marmo.entities.Employee;
import dev.marmo.entities.Expense;
import dev.marmo.exceptions.ResourceNotFound;
import dev.marmo.services.EmployeeService;
import dev.marmo.services.EmployeeServiceImpl;
import dev.marmo.services.ExpenseService;
import dev.marmo.services.ExpenseServiceImpl;
import io.javalin.Javalin;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ExpenseReimbursementApp {

    public static Gson gson = new Gson();
    public static EmployeeService employeeService = new EmployeeServiceImpl(new EmployeeDAOPostgresImpl());
   public static ExpenseService expenseService = new ExpenseServiceImpl(new ExpenseDAOPostgresImpl());

    public static void main(String[] args) {


        Javalin app = Javalin.create();


        //employee routes


        //Create

        // employee
        app.post("/employees", context -> {

            String body = context.body();
            Employee employee = gson.fromJson(body, Employee.class);
            Employee employee1 = employeeService.registerEmployee(employee);
            context.status(201);
            String employeeJson = gson.toJson(employee1);
            context.result(employeeJson);
        });


       // Read

        // Get all employees <list>
        app.get("/employees", context -> {
            List<Employee> employees = employeeService.retrieveAllEmployees();
            String employeeJSON = gson.toJson(employees);
            context.result(employeeJSON);
        });



        //Get employee by employeeID
        app.get("/employees/{employeeID}", context -> {
                    int employeeID = Integer.parseInt(context.pathParam("employeeID"));
                    try {
                        String employeeJSON = gson.toJson(employeeService.retrieveEmployeeById(employeeID));

                        context.result(employeeJSON);
                    }catch (ResourceNotFound e) {
                        context.status(404);
                        context.result("The employee with that id was not found.");
                    }
                });



        //Update

        // employee by ID
        app.put("/employees/{employeeID}", context -> {
            int employeeID = Integer.parseInt(context.pathParam("employeeID"));
            try {
                String body = context.body();
                Employee employee = gson.fromJson(body, Employee.class);
                employee.setEmployeeID(employeeID); //the id in the uri should take precedence
                employeeService.editEmployee(employee);
                context.result("Employee updated");
            }catch (ResourceNotFound e){
                context.status(404);
                context.result("The employee with that id was not found.");
            }

        });





        // may edit
        //Delete

        // employee by ID
        app.delete("/employees/{employeeID}", context -> {
            int employeeID = Integer.parseInt(context.pathParam("employeeID"));
            boolean result = employeeService.removeEmployeeByID(employeeID);
            if (result){
                context.status(204);
               // context.result("Employee record successfully deleted"); not displaying
            }else{
                context.status(404);
            }
        });


//-----------------------------------------------------------------------------------------------------------------------------------------------------------



        //Expense routes


        //Create

        //Register expense
        app.post("/expenses", context -> {
            String body = context.body();
            Expense expense = gson.fromJson(body, Expense.class);
            expense.setStatus("Pending");
            Expense expense1 = expenseService.registerExpense(expense);
            context.status(201);
            String expenseJson = gson.toJson(expense1);
            context.result(expenseJson);
        });


        //Add expense to Employee
        app.post("/employees/{employeeID}/expenses", context -> {
            int employeeID = Integer.parseInt(context.pathParam("employeeID"));
            String body = context.body();
            Expense expense = gson.fromJson(body, Expense.class);
            expense.setEmployeeID(employeeID);
            expense.setStatus("Pending");
            if(expense.getAmount() <0)
            {
                context.status(400);
                context.result("Cannot post a negative expense " + expense);

            }else{
                Expense expense2 = expenseService.registerExpense(expense);
                context.status(201);
                context.result("New expense created " + expense);
            }
        });




        //Read

        //Get all expenses by status <list>
        app.get("/expenses", context -> {
            String status = context.queryParam("status");
            List<Expense> expenses = expenseService.retrieveAllExpenses();
            if(status==null){
                context.result(gson.toJson(expenses));
                System.out.println("retrieved all expenses");
            }else{
                List<Expense> requestedStatus = new ArrayList<>();
               for(Expense expense : expenses){
                   if(expense.getStatus().equals(status)){
                       requestedStatus.add(expense);
                   }
               }
                String expenseJSON = gson.toJson(requestedStatus);
                context.result(expenseJSON);
            }

        });



        //Get expense by expenseID
        app.get("/expenses/{expenseID}", context -> {
            int expenseID = Integer.parseInt(context.pathParam("expenseID"));
            try {
                String expenseJSON = gson.toJson(expenseService.retrieveExpenseByID(expenseID));
                context.result(expenseJSON);
            }catch(ResourceNotFound e) {
                context.status(404);
                context.result("The expense record with that record number was not found.");
            }
        });


        //Get All Expenses Associated with employeeID (tracked employee)
        app.get("/employees/{employeeID}/expenses", context -> {
            int employeeID = Integer.parseInt(context.pathParam("employeeID"));
            try{
                String employeeJSON = gson.toJson(employeeService.retrieveEmployeeById(employeeID));
                String expensesJSON = gson.toJson(expenseService.retrieveExpensesByEmployeeID(employeeID));
                context.result(expensesJSON + employeeJSON);
            }catch(ResourceNotFound e){
                context.status(404);
            }

        });



        //Update

        //Replace an expense
        //Cannot update if expense finalized
        app.put("/expenses/{expenseID}", context -> {
            int expenseID = Integer.parseInt(context.pathParam("expenseID"));
            String body = context.body();
            Expense retrievedExpense = expenseService.retrieveExpenseByID(expenseID);
            Expense expense = gson.fromJson(body,Expense.class);
            expense.setExpenseID(expenseID);
            if(retrievedExpense!=null) {
                System.out.println(retrievedExpense);
                if (retrievedExpense.getStatus().equals("Pending")) {
                    expenseService.replaceExpense(expense);
                    context.result("Expense record has been replaced");
                } else {
                    context.result("Cannot update a finalized expense");
                    context.status(401);
                }
            }else{
                context.status(404);
                context.result("Cannot find expense record with that ID");

            }
        });



        //Approve expense
        app.patch("/expenses/{expenseID}/approve", context -> {
            try {
                int expenseID = Integer.parseInt(context.pathParam("expenseID"));
                Expense expense = expenseService.retrieveExpenseByID(expenseID);
                if(Objects.equals(expense.getStatus(), "Pending")){
                    expenseService.approveExpense(expense);
                    context.result("The expense has been approved");
                    context.status(200);
                }else {
                    context.result("Cannot update a finalized expense.");
                }
            }catch(ResourceNotFound e){
                context.status(404);
                context.result(e.getMessage());
            }
                });


        //Deny
        app.patch("expenses/{expenseID}/deny", context -> {
            try {
                int expenseID = Integer.parseInt(context.pathParam("expenseID"));
                Expense expense = expenseService.retrieveExpenseByID(expenseID);
                if(Objects.equals(expense.getStatus(),"Pending")){
                    expenseService.denyExpense(expense);
                    context.result("The expense has been denied");
                    context.status(200);
                } else{
                    context.result("Cannot update a finalized expense");
                }
            }catch(ResourceNotFound e){
                context.result(e.getMessage());
                context.status(404);
            }
        });


        //Delete

        //Expense by ID
        app.delete("expenses/{expenseID}", context -> {
            int expenseID = Integer.parseInt(context.pathParam("expenseID"));
            Expense expense = expenseService.retrieveExpenseByID(expenseID);
            if(expense!=null) {
                if (Objects.equals(expense.getStatus(), "Pending")) {
                    expenseService.destroyExpenseByID(expenseID);
                    context.status(201);
                    context.result("Expense deleted");
                } else if(expense.getStatus()!="Pending"){
                    context.status(400);
                    context.result("Cannot delete a finalized record");
                }
            }else {
                context.status(404);
                context.result("There is no expense with that record number");
            }
        });



            app.start(6000);
    }
}

