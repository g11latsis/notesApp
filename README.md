# Notes App

## Project Setup and Run Instructions

### Backend (Spring Boot)

Requirements
Before starting, ensure you have the following installed:

JDK (Java Development Kit) version compatible with your Spring Boot version.
Maven or Gradle (choose one) build tool installed.
Database (e.g., MySQL, PostgreSQL) with credentials and database name ready.

1. Clone the repository:
```bash
git clone <backend_repository_url>
cd <backend_directory>
```

2. Configure the database:

Open src/main/resources/application.properties (or application.yml) and configure your database connection properties:

Example for MySQL:
```bash
spring.datasource.url=jdbc:mysql://localhost:3306/db_name
spring.datasource.username=db_username
spring.datasource.password=db_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```
Replace db_name, db_username, and db_password with your database name, username, and password.

3. Build the Application:
Use Maven to build the Spring Boot application:
```bash
mvn clean package
```

4. Run the Application
Start the Spring Boot application:
```bash
java -jar target/<jar_file_name>.jar
```
Replace <jar_file_name> with the actual name of the JAR file generated in the target directory.

5. Verify Backend Server
Once the application starts, verify that the backend server is running:

Open a web browser and go to http://localhost:8080 (assuming default Spring Boot configuration).
If configured, you may see a default landing page or a message indicating the server is running.

6. Access Swagger Documentation
Once the application starts, you can access the Swagger documentation:

Open a web browser and go to http://localhost:8080/swagger-ui.html.

This URL should display the Swagger UI interface where you can explore and test the available API endpoints interactively.

# NoteApp Frontend

This is the frontend application for the NoteApp project. The application is built using React and communicates with a backend API to manage user notes.

## Prerequisites

Before you begin, ensure you have met the following requirements:
- **Node.js**: Make sure you have Node.js installed. You can download it from [nodejs.org](https://nodejs.org/).
- **npm**: npm is included with Node.js. You can verify the installation by running `npm -v`.

## Getting Started

Follow these instructions to set up and run the project on your local machine.

 Clone the repository:
```bash
git clone <repository_url>
```

Navigate to the project directory:
```bash
cd NoteApp/frontend/

```

## Install Dependencies
Install the necessary dependencies using npm:
```bash
npm install
```

## Running the application
Start the development server
```bash
npm start
```


