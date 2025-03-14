#setting official Open Jdk runtime as base image
FROM openjdk:17-jdk-slim

#Set the working directory
WORKDIR /app

#Copying application jar to the container 
COPY target/library-0.0.1-SNAPSHOT.jar library-management-system.jar

#Expose the application port
EXPOSE 8080

#command to run the applicatioon
CMD [ "java","-jar","library-management-system.jar"]