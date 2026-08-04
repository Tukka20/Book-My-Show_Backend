# 🎬 Book-My-Show_Backend 

A backend REST API for an online movie ticket booking system built with **Spring Boot**, **Spring Data JPA**, **Spring Web** and **MySQL**.

The project simulates the core functionality of a movie booking platform, including movie management, theatre management, show scheduling, seat booking, payment handling, and automatic booking expiration.

------------
## ✨ Features

### 👤 User Management
- Register users
- Login users
- View user details
- Update user profile
- Update user password

### 🎬 Movie Management
- Add, update and delete movies
- Search movies by title
- Filter movies by language and genre

### 🏢 Theatre Management
- Add, update and delete theatres
- Search theatres by name
- Filter theatres by city

### 🏢 Screen Management
- Add, update and delete screens
- Manage theatre with screens
- Manages seat types and total seats in that particular screen 

### 🎭 Show Management
- Schedule movie shows
- Assign movies to screens
- View available shows
- View the prices of a show in a specific screen  based on the seat types

### 🎟 Booking Management
- Book one or multiple seats
- Generate unique booking number
- Prevent duplicate seat booking
- Track booking status
- Cancel booking

### 💳 Payment Management
- Create payment records
- Store transaction details
- Track payment status
- Confirmation of bookings

### ⏳ Automatic Booking Expiration
- Cancel unpaid bookings after **5 minutes**
- Release reserved seats automatically

------

# 🛠 Tech Stack

|   **Technology**   |    **Purpose**        |
|--------------------|-----------------------|
| Java 17            | Programming Language  |
| Spring Boot        | Backend Framework     |
| Spring Web         | REST APIs             |
| Spring Data JPA    | Database Access       |
| Hibernate          | ORM                   |
| MySQL              | Database              |
| Maven              | Dependency Management |
| Lombok             | Reduce Boilerplate    |
| Jakarta Validation | Request Validation    |

-----

# 📂 Project Structure

```
src/main/java/com.bookmyshow.Book_MY_Show

│

├── controller

├── dto

     ├── response

     └── request

├── entity

├── exception

├── mapper

├── repo

├── scheduler

└── service
```
------

# 🏗 Architecture

```
Client
   │
REST API
   │
Controller
   │
Service
   │
Repository
   │
MySQL
```
The project follows a layered architecture to keep the code clean, maintainable, and scalable.

-----

# 🎟 Booking Workflow

```
User
 │
 ▼
Select Movie
 │
 ▼
Select Theatre
 │
 ▼
Select Show
 │
 ▼
Choose Seats
 │
 ▼
Create Booking
 │
 ▼
Complete Payment
 │
 ▼
Booking Confirmed
```
If payment is not completed within **5 minutes**, the booking is cancelled automatically and the reserved seats become available again.

----

### Configure MySQL

Update **application.properties**

```
spring.datasource.url=jdbc:mysql://localhost:3306/bookmyshow
spring.datasource.username=your username
spring.datasource.password=your_password

```

----

# 📌 Key Concepts Used

- RESTful API Design
- Layered Architecture
- DTO Pattern
- Entity Relationships
- Validation
- Global Exception Handling
- Transaction Management
- Scheduler
- Spring Data JPA

----

# 🚀 Future Improvements

- JWT Authentication
- Role-Based Authorization
- Refund Management
- Email Notifications
- Swagger / OpenAPI Documentation
- Docker Support
- Unit Testing
- Integration Testing

---

# 👨‍💻 Author

**Rohan Biswas**

If you found this project helpful, consider giving it a ⭐ on GitHub.

