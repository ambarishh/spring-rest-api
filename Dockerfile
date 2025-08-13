# Build stage
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /workspace/app

# Copy only the files needed for building to leverage Docker cache
COPY pom.xml .
COPY src src/

# Build the application
RUN mvn clean package -DskipTests

# Run stage
FROM eclipse-temurin:21-jre-jammy

# Set the working directory
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /workspace/app/target/*.jar app.jar

# Expose the application port
EXPOSE 8080

# Set the entry point to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
