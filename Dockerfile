FROM openjdk:8u111-jre-alpine

VOLUME /tmp
EXPOSE 8080

ADD ./build/libs/iou-0.0.1-SNAPSHOT.jar app.jar

ENV DB_URL not-set
ENV DB_USER not-set
ENV DB_PASSWORD not-set
ENV DB_DRIVER not-set

RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]
