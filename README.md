Tripmate – Ride Sharing Backend (Spring Boot + MongoDB)

A simple ride-sharing backend built using Spring Boot, MongoDB, and JWT Authentication.
This project demonstrates clean API design, layered architecture, authentication, and MongoDB data management.

🚀 Features
🔐 Authentication & Security

User Signup (Passenger / Driver roles)

Login with JWT Token Generation

Secure Endpoints using JWT Filter

Password Hashing with BCrypt

🚕 Passenger Capabilities (ROLE_USER)

Request a trip

View personal trips

Complete a trip after it is accepted

🚗 Driver Capabilities (ROLE_DRIVER)

View pending trip requests

Accept a trip request

Complete the trip

🛠 Additional Features

Input validation using Jakarta Validation

Clean Controller → Service → Repository structure

Global exception handling

Clear separation of layers & DTO usage

📁 Project Structure
tripmate/
├── controller/
│   ├── AuthController.java
│   ├── TripController.java
│   ├── DriverController.java
│   └── UserController.java
│
├── dto/
│   ├── SignupPayload.java
│   ├── LoginPayload.java
│   ├── TokenPayload.java
│   └── TripCreatePayload.java
│
├── service/
│   ├── AuthService.java
│   └── TripService.java
│
├── repository/
│   ├── AppUserRepository.java
│   └── TripRepository.java
│
├── model/
│   ├── AppUser.java
│   └── Trip.java
│
├── config/
│   ├── SecurityConfig.java
│   └── JwtFilter.java
│
├── exception/
│   ├── ApiError.java
│   ├── BadRequestEx.java
│   ├── NotFoundEx.java
│   └── GlobalHandler.java
│
└── util/
    ├── JwtProvider.java
    └── SecurityHelper.java

Root Files:
pom.xml  
src/main/resources/application.properties  

🔗 API Endpoints
Authentication
Method	Endpoint	Description
POST	/api/v2/auth/signup	Register a new user (PASSENGER or DRIVER)
POST	/api/v2/auth/signin	Login and receive JWT token
Passenger Endpoints (ROLE_USER)
Method	Endpoint	Description
POST	/api/v2/trips	Request a new trip
POST	/api/v2/trips/{id}/finish	Complete an accepted trip
GET	/api/v2/user/trips	View trips made by the user
Driver Endpoints (ROLE_DRIVER)
Method	Endpoint	Description
GET	/api/v2/driver/trips/requests	View all pending trip requests
POST	/api/v2/driver/trips/{id}/accept	Accept a trip
🗃 Database

MongoDB Collections:

users → Stores user details

trips → Stores trip requests and statuses

Status flow:

REQUESTED → ACCEPTED → COMPLETED

⚙️ Technologies Used

Java 17

Spring Boot 3

Spring Security

MongoDB (Spring Data MongoDB)

JWT Authentication

Maven

▶️ Running the Project
mvn clean package
mvn spring-boot:run


Make sure MongoDB is running locally on:

mongodb://localhost:27017/tripmate

🔑 JWT Usage

Include the token in every protected request:

Authorization: Bearer <your-token-here>

✨ About

This project was developed as part of a backend assignment to demonstrate Spring Boot skills such as:

Clean API development

Proper authentication flow

MongoDB repository usage

Layer separation & validation
