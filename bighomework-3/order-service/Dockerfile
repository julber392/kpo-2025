FROM eclipse-temurin:21-jdk-jammy
WORKDIR /app
COPY order-service/build/libs/order-service.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]