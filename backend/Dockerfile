FROM openjdk:17-jdk
EXPOSE 8080
ARG JAR_FILE=/target/Backend-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
CMD ["java", "-jar", "app.jar"]