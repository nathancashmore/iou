FROM gradle:jdk8

USER root

COPY . /app

WORKDIR /app

RUN chmod 777 * -R

RUN ls -l

RUN gradle build

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
