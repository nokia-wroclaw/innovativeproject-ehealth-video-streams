FROM openjdk:11-jdk-slim AS builder
WORKDIR /java/
COPY . .
RUN ./mvnw clean package

FROM openjdk:11-jre-slim AS runner
COPY --from=builder /java/target/*.jar ./app.jar
CMD ["/usr/bin/java", "-jar", "-Dspring.profiles.active=docker", "/app.jar"]