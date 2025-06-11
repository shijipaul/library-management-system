# Stage 1: Build the Application
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests


#Stage 2 : Run the Application

#setting official Open Jdk runtime as base image
FROM openjdk:17-jdk-slim

#Set the working directory
WORKDIR /app

#Copying application jar to the container 
COPY --from=build /app/target/library-0.0.1-SNAPSHOT.jar library-management-system.jar

#Expose the application port
EXPOSE 8080

#command to run the applicatioon
CMD [ "java","-jar","library-management-system.jar"]