Tripmate – Ride Sharing Backend (Spring Boot + MongoDB)

This project is a simplified backend for a ride-sharing application built using Spring Boot, MongoDB, and JWT Authentication.
It supports user registration, login, requesting trips, accepting trips, and completing rides.

This is a basic academic project demonstrating:

REST API design

DTO-based request handling

JWT login + authorization

MongoDB repositories

Layered architecture (Controller → Service → Repository)

Input validation & global exception handling

🗂 Project Structure
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


Root files:

pom.xml
src/main/resources/application.properties

🧩 Features Implemented
🔐 Authentication

User signup (ROLE_USER or ROLE_DRIVER)

Login returns JWT token

Passwords stored using BCrypt

JWT validation filter for protected endpoints

🚕 Passenger Features

Request a ride

View own rides

Complete a ride once accepted

🚗 Driver Features

View pending trip requests

Accept a trip

Complete trip

🛠 Other

Global exception handling

Validation using Jakarta annotations

Clean service & repository layers

📌 API Endpoints Summary
Auth
Method	Endpoint	Description
POST	/api/v2/auth/signup	Register user
POST	/api/v2/auth/signin	Login + receive JWT
Passenger APIs (ROLE_USER)
Method	Endpoint	Description
POST	/api/v2/trips	Request a new trip
POST	/api/v2/trips/{id}/finish	Complete accepted trip
GET	/api/v2/user/trips	View all trips requested by user
Driver APIs (ROLE_DRIVER)
Method	Endpoint	Description
GET	/api/v2/driver/trips/requests	List all pending trip requests
POST	/api/v2/driver/trips/{id}/accept	Accept a trip request
🗄 Database

This project uses MongoDB collections:

users

trips

Documents store user info, hashed passwords, ride details, and status updates.

⚙️ Technologies Used

Java 17

Spring Boot 3

Spring Security

Spring Data MongoDB

JWT (JSON Web Token)

Maven

Ubuntu / Linux Compatible

▶️ Running the Project

Install Java 17 and MongoDB

Update application.properties if needed

Build with:

mvn clean package


Run:

mvn spring-boot:run

📝 Notes

JWT must be included in every protected request:

Authorization: Bearer <token>


Default port is 8081 (can be changed in properties file)

📬 Author

Tripmate Backend Project
Developed for academic learning and assignment purposes.
