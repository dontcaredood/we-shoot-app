# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Create a group and user to run the app
RUN groupadd -r appgroup && useradd -r -g appgroup appuser

# Set the working directory in the container
WORKDIR /app

# Copy the application JAR file into the container
COPY target/we-shoot-app-rest-0.0.1-SNAPSHOT.jar /app/we-shoot-app-rest.jar

# Change ownership of the app directory
RUN chown -R appuser:appgroup /app

# Switch to the non-root user
USER appuser

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "we-shoot-app-rest.jar"]
