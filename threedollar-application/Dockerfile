FROM openjdk:17
ENV HOME=/usr/app
ARG JAR_FILE=$HOME/threedollar-application/build/libs/threedollar-application.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
