# paul_marmo_p1

## Project Description

- Created a REST API for an expense reimbursement system. <br/>
- The system allows the company to track expenses and analyze spending. <br/>
- Contains two entities: Employee and Expense. <br/>

## Technologies Used
- Java <br/>
- Javalin <br/>
- PostgreSQL <br/>
- Postman <br/>
- REST <br/>
- JUnit<br/>

## Features
- All expenses have a single employee as the issuer </br>
- Expenses start as pending and must then be approved or denied </br>
- Once approved or denied they CANNOT be deleted or edited </br>
- Negative expenses are not allowed </br>

## Getting Started
- No environment variables required
- #### Run Locally:
    - Clone project to your IDE
    - Run the application within the IDE
    - send http requests via postman or web browser

## Usage
With the backend running / deployed on a hosting platform such as AWS, the user can perfom different functions in postman or a web browser using the various routes: <br/>

Running locally: http://localhost:5000 + route </br>
If Deployed on AWS: URL of aws environment + route </br>

Employee Routes: </br>

- POST /employees </br>
  creates an employee {employeeID, firstname, lastname} </br>
  returns a 201</br>
  
- GET /employees </br>
 retrieves all employees in database </br>
 
- GET /employees/{employeeID} </br>
  Gets employee by their specific employeeID </br>
  returns a 404 if employee not found </br>

- PUT /employees/{employeeID} </br>
  edits a specific employee by their employeeID </br>
returns a 404 if employee not found </br>

- DELETE /employees/employeeID </br?
  deltes a specific employee by their employeeID </br>
  returns a 404 if employee not found </br>
  
  Expense Routes: </br>
  
- POST /expenses </br>
   Creates an expense {employeeID, firstname, lastname} </br>
 
- GET /expenses </br>
  Retrieves all expenses from database</br>
  
- GET /expenses?status={status} </br>
  Utilizes query </br>
  Retrieves all expenses by its status (Pending, Approved, Denied) </br>
  
- GET /expenses/{expenseID} </br>
  Retrieves an expense by its expenseID </br>
  Returns a 404 if expense not found </br>

- PUT /expenses/{expenseID} </br>
  Edits an expense by its expense ID </br>
  Cannot edit an finalized expense (only pending expenses) </br>
  Returns a 404 if expense not found </br>

- PATCH /expenses/20/approve </br>
  Finalizes expense by setting status from pending to approved </br>
  Cannot be changed once finalized</br>
  Returns a 404 if expense not found </br>

- PATCH /expenses/20/deny
  Finalizes expense by setting status from pending to approved </br>
  Cannot be changed once finalized</br>
  Returns a 404 if expense not found

- DELETE /expenses/{expenseID} </br>
  Deletes a specific expense by its expenseID </br>
  Cannot delete finalized expenses (status must be pending) </br>
  Returns a 404 if cannot found </br>

Nested Routes: </br>

Employees:

GET /employees/{employeeID}/expenses </br>
Retrieves all expenses for a specific employee by employeeID </br>

POST /employees/120/expenses </br>
adds an expense to a specific employee by employeeID </br>

