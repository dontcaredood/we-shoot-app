# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the application JAR file into the container
COPY target/we-shoot-app-rest-0.0.1-SNAPSHOT.jar /app/we-shoot-app-rest.jar

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "we-shoot-app-rest.jar"]
