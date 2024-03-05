FROM eclipse-temurin:21

ARG WAR_FILE=./target/knowit-integration.war
COPY ${WAR_FILE} /usr/app/
WORKDIR /usr/app
EXPOSE 8080

CMD ["java", "-jar", "knowit-integration.war"]