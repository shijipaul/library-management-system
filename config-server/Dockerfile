FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY . .

RUN ./mvnw clean package -DskipTests

EXPOSE 8888

ENTRYPOINT ["java", "-jar", "target/config-server-0.0.1-SNAPSHOT.jar"]