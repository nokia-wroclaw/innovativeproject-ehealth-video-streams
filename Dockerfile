FROM openjdk:11-jre-slim
COPY /target/ehealth*.jar /app.jar
CMD ["/usr/bin/java", "-jar", "-Dspring.profiles.active=docker", "/app.jar"]