# Build stage
FROM eclipse-temurin:17-jdk AS builder
WORKDIR /app
COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn
COPY src ./src
RUN chmod +x ./mvnw
RUN ./mvnw clean package -DskipTests

# Runtime stage
FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
