# 1. Use a lightweight Java Runtime (JRE) for the environment
FROM eclipse-temurin:17-jdk-alpine

# 2. Set the working directory inside the container
WORKDIR /app

# 3. Copy the 'Feedback' JAR file from your local target folder
# Note: Maven puts the built JAR inside the 'target' folder
COPY target/*.jar feedback-app.jar

# 4. Open port 8080 (standard for Spring Boot)
EXPOSE 8080

# 5. Command to run the Feedback application
ENTRYPOINT ["java", "-jar", "feedback-app.jar"]