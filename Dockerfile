FROM maven:3.9.1-eclipse-temurin-17-focal AS build

RUN mkdir -p app
WORKDIR /app
COPY src /app/src/
COPY checkstyle.xml /app/
COPY pom.xml /app/
COPY .git /app/.git/

RUN mvn clean install

FROM openjdk:17.0.2
LABEL Description="Docker container for Spring Boot App Template on OpenJDK17"


COPY --from=build /app/target/*.jar /home/appuser/app.jar
COPY README.md /home/appuser/

EXPOSE 8080

RUN groupadd -g 10000 appuser && \
    useradd -g 10000 -u 10000 -d /home/appuser -s /bin/bash -c "Java Application User" appuser && \
    chmod 644 /home/appuser/app.jar && \
    chown -R appuser:appuser /home/appuser

WORKDIR /home/appuser

USER appuser

CMD [ "/usr/java/openjdk-17/bin/java","-XX:MaxRAMPercentage=50","-Djava.security.egd=file:/dev/urandom","-Duser.timezone=Europe/Berlin","-jar","app.jar" ]
