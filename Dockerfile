FROM openjdk:8-jdk-alpine
VOLUME /tmp

ARG JAR_FILE

RUN echo "Building with JAR_FILE : ${JAR_FILE}"

COPY ${JAR_FILE} app.jar

RUN ls -la app.jar

ENV DB_URL not-set
ENV DB_USER not-set
ENV DB_PASSWORD not-set
ENV DB_DRIVER not-set

EXPOSE 8080

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]