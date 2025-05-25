FROM eclipse-temurin:21-jdk-jammy
WORKDIR /app
COPY . .
RUN ./gradlew bootJar -x test
ENTRYPOINT ["java", "-jar", "build/libs/api-gateway-0.0.1-SNAPSHOT.jar"]