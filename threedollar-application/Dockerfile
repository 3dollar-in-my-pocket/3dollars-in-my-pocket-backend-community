FROM openjdk:17

ADD https://github.com/pinpoint-apm/pinpoint/releases/download/v2.5.4/pinpoint-agent-2.5.4.tar.gz /usr/local
RUN tar -zxvf /usr/local/pinpoint-agent-2.5.2.tar.gz -C /usr/local

RUN sed -i 's/profiler.transport.grpc.collector.ip=127.0.0.1/profiler.transport.grpc.collector.ip=${PINPOINT_IP}/g' /usr/local/pinpoint-agent-2.5.2/pinpoint-root.config
RUN sed -i 's/profiler.collector.ip=127.0.0.1/profiler.collector.ip=${PINPOINT_IP}/g' /usr/local/pinpoint-agent-2.5.2/pinpoint-root.config


ARG JAR_FILE=./threedollar-application/build/libs/threedollar-application.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
