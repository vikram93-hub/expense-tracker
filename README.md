# Expense Tracker

A simple backend for tracking personal expenses and income.

I made this project while learning Spring Boot. I wanted to build something where I could practice REST APIs, Spring Security, JWT authentication, JPA and MySQL instead of only following small examples.

## Features

* User registration and login
* Password hashing using BCrypt
* JWT based authentication
* Create and view expense categories
* Add and view expenses
* Add and view income
* Monthly income and expense summary
* Basic request validation
* Global exception handling

## Tech Used

* Java
* Spring Boot
* Spring Security
* JWT
* Spring Data JPA
* MySQL
* Maven
* Postman

## How it works

The project is divided into a few layers:

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
MySQL
```

JWT is used to protect the APIs after login.

Each expense and income record belongs to the logged-in user, so users don't access another user's data.

## Main APIs

### Authentication

```text
POST /api/auth/register
POST /api/auth/login
```

### Categories

```text
POST /api/categories
GET  /api/categories
```

Example:

```json
{
  "name": "Food"
}
```

### Expenses

```text
POST /api/expenses
GET  /api/expenses
```

Example:

```json
{
  "amount": 500,
  "gst": 90,
  "date": "2026-10-06",
  "categoryId": 1
}
```

### Income

```text
POST /api/income
GET  /api/income
```

Example:

```json
{
  "amount": 25000,
  "date": "2026-10-01",
  "source": "Internship"
}
```

### Monthly Summary

```text
GET /api/summary?year=2026&month=10
```

Example response:

```json
{
  "totalIncome": 25000.0,
  "totalExpense": 1000.0,
  "balance": 24000.0
}
```

## Database

The project currently has four main tables:

```text
User
 ├── Categories
 ├── Expenses
 └── Income

Category
 └── Expenses
```

An expense belongs to one user and one category.

## Running Locally

### 1. Create the database

```sql
CREATE DATABASE expense_tracker;
```

### 2. Configure MySQL

Update `application.properties` with your MySQL username and password.

The JWT secret should also be kept outside the source code.

### 3. Run the project

```powershell
.\mvnw.cmd spring-boot:run
```

The application runs on:

```text
http://localhost:8080
```

## Testing

I used Postman to test the APIs.

The basic flow is:

```text
Register
   ↓
Login
   ↓
Get JWT token
   ↓
Use token for protected APIs
   ↓
Create category
   ↓
Add expenses/income
   ↓
Check monthly summary
```

## What I practiced

While building this project, I worked with:

* Spring Boot REST APIs
* JPA entity relationships
* DTOs
* Service and repository layers
* Spring Security
* JWT authentication
* BCrypt
* MySQL
* Validation
* Exception handling
* Postman API testing

## Future Improvements

Some things I may add later:

* Frontend
* Better expense filtering
* Charts
* Budget tracking
* Recurring expenses
* Deployment

## Author

**Vikramarka Mahendra**

VIT-AP University
Integrated M.Tech - Software Engineering
