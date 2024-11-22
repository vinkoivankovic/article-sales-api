```bash
Article Sales API

This is a Spring Boot application that manages sales contracts and articles. It is containerized using Docker and connects to a PostgreSQL database. Below are the steps to build, run, and test the application using Docker.

Prerequisites

Before you start, make sure you have the following tools installed:

- Docker
- Docker Compose
- Gradle (for building the project)

## Steps to Run the Application with Docker

### 1. Clone the Repository

Clone this repository to your local machine:


cd article-sales-api

2. Build the Project

Since the application uses Gradle, you can build the project with:

./gradlew build

This will generate a .jar file inside the build/libs directory.
3. Docker Compose Setup

To run the application along with PostgreSQL, Docker Compose is used.
Docker Compose Configuration

Make sure your docker-compose.yml file is set up to build and run both the application and the database. Here's an example configuration:


Dockerfile Configuration

Your Dockerfile should be properly set to build the Spring Boot app. Here's a simple example of a Dockerfile:

# Use OpenJDK 17 for the base image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy the jar file into the container
COPY build/libs/article-sales-api-0.0.1-SNAPSHOT.jar app.jar

# Expose the port the app will run on
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]

4. Run the Application with Docker Compose

Now, you can use Docker Compose to build and start both your app and the PostgreSQL database:

docker-compose up --build

This will:

    Build the Docker image for the Spring Boot application.
    Start the PostgreSQL container.
    Start the Spring Boot application container.

Once the application starts, it will be accessible on http://localhost:8080.
5. Test the Application

You can test the application by sending HTTP requests to the running server. For example:

    Login Endpoint: POST http://localhost:8080/api/auth/login
    Sales Contracts: GET http://localhost:8080/api/sales-contracts

You can test using tools like curl or Postman.
6. Stop the Containers

When you`re done, you can stop and remove the containers using:

docker-compose down
