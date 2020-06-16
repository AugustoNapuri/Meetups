FROM openjdk:8-jdk-alpine
ARG WAR_FILE=MeetUps-0.0.1-SNAPSHOT.war
COPY ${WAR_FILE} app.war
EXPOSE 8081
CMD ["java", "-jar", "/app.war"]

