# Use OpenJDK 17 as the base image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy pom.xml and source code into the container
COPY pom.xml .
COPY src ./src

# Run Maven to build the app
RUN apt-get update && apt-get install -y maven
RUN mvn clean package

# Copy the built jar file into the container
COPY examserver/target/examserver-0.0.1-SNAPSHOT.jar


# Expose the port your Spring Boot app runs on (default is 8080)
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
