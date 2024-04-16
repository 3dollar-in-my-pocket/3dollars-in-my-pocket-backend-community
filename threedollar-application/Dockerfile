FROM openjdk:17
ARG JAR_FILE=./build/libs/threedollar-application.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
