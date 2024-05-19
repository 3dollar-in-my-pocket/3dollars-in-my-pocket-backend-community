FROM openjdk:17
ARG JAR_FILE=./threedollar-application/build/libs/threedollar-application.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-Duser.timezone=Asia/Seoul", "-jar", "/app.jar"]
