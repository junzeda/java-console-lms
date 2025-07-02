# JoysisLMSApplication

A **console-based Library Management System** built using **Java 8**.
This application allows admins and students to perform core library operations such as borrowing, returning books, and managing inventory.


## FEATURES

### Student Users
- Browse available books
- Borrow and return books
- View transaction history
- Receive overdue or incoming due notification
- Estimated fine calculation for overdue books
- Submit book suggestions
- Update or edit profile account

### Admin / Librarian
- Dashboard with summary info
- Manage books, authors, publishers, and categories
- Manage student/member records
- Manage borrow request (approve/decline)
- Track overdue and incoming due 
- Calculate fines for damaged/lost books and overdue 

---

## Technologies Used
- Java 8
- JDBC (MySQL)
- Console-based UI (Scanner)
- MVC Architecture

src/
├── config/
├── controller/
├── dao/
	├── impl/
├── model/
├── service/
├── util/
├── view/


---

## How to Run
1. Open the project in Eclipse
2. Make sure your MySQL database is configured in 'DBConnection.java'
3. Run the 'App.java' file to launch the program

---

## Notes
- Overdue books incur a ₱10/day fine
- Lost or damaged books will include the overdue fine in the total penalty
- Return remarks are logged to track book condition

## License
This project is for academic and learning purposes only.
