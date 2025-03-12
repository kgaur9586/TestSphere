# Use OpenJDK 17 as the base image
FROM openjdk:17-jdk-slim

# Install Maven
RUN apt-get update && apt-get install -y maven

# Set the working directory inside the container
WORKDIR /app

# Copy the examserver directory into the container
COPY examserver /app/examserver

# Set examserver as the working directory
WORKDIR /app/examserver

# Run Maven to build the app
RUN mvn clean package

# Copy the built jar file into the container root
COPY target/examserver-0.0.1-SNAPSHOT.jar /app/app.jar

# Expose the port your Spring Boot app runs on (default is 8080)
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
