# Use Maven with Java 23 to build the app
FROM maven:3.9.6-eclipse-temurin-23 AS builder

# Set the working directory inside the container
WORKDIR /app

# Copy pom.xml and download dependencies
COPY examserver/pom.xml .
RUN mvn dependency:go-offline

# Copy the source code and build the app
COPY examserver/src ./src
RUN mvn clean package -DskipTests

# Use Java 23 for running the app
FROM eclipse-temurin:23-jdk

# Set the working directory inside the container
WORKDIR /app

# Copy the built JAR file from the builder stage
COPY --from=builder /app/target/examserver-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your Spring Boot app runs on (default is 8080)
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
