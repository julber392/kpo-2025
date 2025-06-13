FROM eclipse-temurin:21-jdk-jammy
WORKDIR /app
COPY payment-service/build/libs/payment-service.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]