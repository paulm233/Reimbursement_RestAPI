package dev.marmo.api;

import com.google.gson.Gson;
import dev.marmo.data.EmployeeDAOPostgresImpl;
import dev.marmo.data.ExpenseDAOPostgresImpl;
import dev.marmo.entities.Employee;
import dev.marmo.exceptions.ResourceNotFound;
import dev.marmo.services.EmployeeService;
import dev.marmo.services.EmployeeServiceImpl;
import dev.marmo.services.ExpenseService;
import dev.marmo.services.ExpenseServiceImpl;
import io.javalin.Javalin;

import java.util.List;

public class ExpenseReimbursementApp {

    public static Gson gson = new Gson();
    public static EmployeeService employeeService = new EmployeeServiceImpl(new EmployeeDAOPostgresImpl());
//    public static ExpenseService expenseRecordService = new ExpenseServiceImpl(new ExpenseDAOPostgresImpl());

    public static void main(String[] args) {


        Javalin app = Javalin.create();


        //employee routes


        //Create
        app.post("/employees", context -> {

            String body = context.body();
            Employee employee = gson.fromJson(body, Employee.class);
            Employee employee1 = employeeService.registerEmployee(employee);
            context.status(201);
            String employeeJson = gson.toJson(employee1);
            context.result(employeeJson);
        });


       // Read

        //all employees
        app.get("/employees", context -> {
            List<Employee> employees = employeeService.retrieveAllEmployees();
            String employeeJSON = gson.toJson(employees);
            context.result(employeeJSON);
        });



        //employee by ID
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


        app.put("/employees/{employeeID}", context -> {
            int employeeID = Integer.parseInt(context.pathParam("employeeID"));
            String body = context.body();
            Employee employee = gson.fromJson(body,Employee.class);
            employee.setEmployeeID(employeeID); //the id in the uri should take precedence
            employeeService.editEmployee(employee);
            context.result("Employee updated");

        });



        //Delete

        app.delete("/employees/{employeeID}", context -> {
            int employeeID = Integer.parseInt(context.pathParam("employeeID"));
            boolean result = employeeService.removeEmployeeByID(employeeID);
            if (result){
                context.status(204);
               // context.result("Employee record successfully deleted"); not displaying
            }else{
                context.status(500);
            }
        });



        //Expense routes




            app.start(9000);
    }
}

