
# Beginner Java User Management App

**This project is designed for absolute beginners who are just starting to learn Java, especially those interested in building backend or API services.**

It demonstrates how to structure a simple Java application for backend development, using only core Java features and minimal dependencies. The code is well-documented and easy to follow, making it ideal for those coming from Python or Node.js backgrounds who want to understand how Java projects are organized and how backend APIs can be built in Java.

**Key learning points:**
- How to organize Java code into packages and classes
- How to use encapsulation and object-oriented principles
- How to perform file-based data storage
- How to build a simple HTTP API server in Java
- How Java project structure differs from Python scripts or Node.js apps


## Features
- Add, view, update, and delete users
- Data stored in a file (`user.txt`)
- HTTP API endpoints for CRUD (testable via curl/Postman)
- Simple, well-documented code for learning Java basics
- Clear structure for backend/API development


## Project Structure
```
userManagement/
├── src/
│   └── com/rotimi/
│       ├── Main.java           # Starts HTTP server, handles requests
│       ├── User.java           # User model class
│       └── UserRepository.java # Handles file-based data storage
├── user.txt                    # User data file (CSV format)
├── README.md                   # Project documentation
```

**How is this different from Python or Node.js?**
- Java uses packages and classes for structure, while Python and Node.js often use modules and functions.
- Java code is compiled, not interpreted, and requires explicit type definitions.
- Java projects typically have a clear entry point (`main` method) and use object-oriented design.
- File I/O and HTTP server setup are more verbose in Java, but provide strong type safety and structure.


## How to Run
1. Compile all Java files:
  ```sh
  javac -cp gson-2.8.9.jar src/com/rotimi/*.java
  ```
2. Start the server:
  ```sh
  java -cp gson-2.8.9.jar:src com.rotimi.Main
  ```

## API Endpoints
- `GET /users`         - List all users
- `GET /users/{id}`    - Get user by ID
- `POST /users`        - Create new user (JSON body)
- `PUT /users/{id}`    - Update user (JSON body)
- `DELETE /users/{id}` - Delete user

## Example curl Commands
- Create user:
  ```sh
  curl -X POST -H "Content-Type: application/json" -d '{"id":2,"name":"Jane","email":"jane@doe.com"}' http://localhost:8000/users
  ```
- List users:
  ```sh
  curl http://localhost:8000/users
  ```
- Get user:
  ```sh
  curl http://localhost:8000/users/2
  ```
- Update user:
  ```sh
  curl -X PUT -H "Content-Type: application/json" -d '{"name":"Janet","email":"janet@doe.com"}' http://localhost:8000/users/2
  ```
- Delete user:
  ```sh
  curl -X DELETE http://localhost:8000/users/2
  ```

## Dependencies
- [Gson](https://github.com/google/gson) (for JSON serialization)


## Learning Resources
- See code comments for explanations of Java concepts: classes, objects, encapsulation, file I/O, HTTP server, etc.
- Compare the structure here to Python and Node.js projects to understand the differences and similarities.

---
Feel free to explore, modify, and learn! This project is a great starting point for building more advanced backend and API services in Java.
