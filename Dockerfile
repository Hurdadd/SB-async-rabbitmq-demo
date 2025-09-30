FROM maven:3.8.8-eclipse-temurin-17 AS build
WORKDIR /order-service
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jdk-jammy
WORKDIR /order-service
COPY --from=build /order-service/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]



