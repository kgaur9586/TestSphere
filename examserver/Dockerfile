# Use Maven to build the app
FROM maven:3.8.7-openjdk-17 AS builder

# Set the working directory inside the container
WORKDIR /app

# Copy only the backend code from examserver into the container
COPY examserver /app

# Run Maven to build the app
RUN mvn clean package -DskipTests

# Use OpenJDK 17 for running the app
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the built jar from the builder stage to the app directory
COPY --from=builder /app/target/examserver-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your Spring Boot app runs on (default is 8080)
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
