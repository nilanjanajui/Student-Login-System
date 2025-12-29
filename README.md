# Student Registration & Login System (Java Swing)

A simple and efficient desktop application built with **Java Swing** and **SQLite**, allowing students to register, log in using their Student ID, and view their stored biodata on a personal dashboard.

---

## Features

- Student **Registration** with unique ID and password
- **Login Authentication**
- **Dashboard** to display student biodata
- Data stored using **SQLite database (`students.db`)**
- UI built using **Java Swing**

---

## Project Structure

studentlogin/
│── bin/
│ └── studentlogin/
│ ├── DatabaseManager.class
│ ├── LoginPage.class
│ ├── RegisterPage.class
│ ├── StudentDashboard.class
│
│── src/
│ └── studentlogin/
│ ├── DatabaseManager.java
│ ├── LoginPage.java
│ ├── RegisterPage.java
│ ├── StudentDashboard.java
│
│── students.db
│── README.md

yaml
Copy code

---

## Tech Stack

| Component | Used Technology |
|---------|----------------|
| Programming Language | Java |
| UI | Java Swing |
| Database | SQLite |
| Authentication | Student ID + Password |

---

## What this project does

- Registers student information and saves it in the database
- Authenticates login credentials
- Displays stored student details on the dashboard

---

## What I learned

- Implementing **User Authentication in Java**
- Designing UIs using **Java Swing**
- Working with **SQLite database**
- Debugging and improving application flow

---

## How to Run

1. Clone the repository:
git clone <repo-link>

markdown
Copy code

2. Open the project in any Java IDE
3. Add SQLite JDBC to classpath if required
4. Run the application:
src/studentlogin/LoginPage.java

yaml
Copy code

---

## Contributing

Suggestions and improvements are welcome! Feel free to open an issue or create a pull request.

---

## License

MIT License — Free to use and modify.

---

## Acknowledgements

- Java Swing documentation
- SQLite database
- Community tutorials and debugging references
