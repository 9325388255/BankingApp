Banking Application 🏦

A Spring Boot-based Banking Management System that allows customers to create accounts, manage multiple bank accounts, and perform transactions like deposits, withdrawals, and balance checks.
This project demonstrates the use of Spring Boot, Spring Data JPA, MySQL, and REST APIs.


Features ✨

Customer Management 👤

Create, update, and delete customers.

One customer can have multiple accounts.

Account Management 💳

Open new bank accounts.

Fetch account details.

Delete accounts.

Transaction Management 💰

Deposit money.

Withdraw money.

Check account balance.

Automatic Customer Deletion 🗑

If a customer has no accounts left, the customer record is automatically deleted.

RESTful API 🌐

Supports GET, POST, PUT, and DELETE endpoints.

Error Handling & Validations ✅

Tech Stack 🛠️
Technology	Purpose
Java 17	Programming Language
Spring Boot	Application framework
Spring Data JPA	ORM for database operations
MySQL	Database
Maven	Build & dependency management
Lombok	reduces boilerplate code
STS / IntelliJ	IDE
Project Structure 📂
banking-app/
├── src/main/java/com/banking/
│   ├── controller/      # REST API Controllers
│   ├── service/         # Business Logic Layer
│   ├── repository/      # JPA Repositories
│   ├── model/           # Entity Classes
│   └── BankingApplication.java
├── src/main/resources/
│   ├── application.properties
│   └── data.sql
├── pom.xml              # Maven Dependencies
└── README.md            # Project Documentation


