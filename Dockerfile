# Use an official OpenJDK runtime as the base image
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the application JAR file into the container
COPY build/libs/article-sales-api-0.0.1-SNAPSHOT.jar app.jar

# Expose the application port
EXPOSE 8005

# Set the command to run the application
CMD ["java", "-jar", "app.jar"]
