FROM gradle:jdk11 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

FROM openjdk:11.0-jdk-slim
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/*SNAPSHOT.jar /app/task-backend-1.0.0.jar
ENTRYPOINT ["java", "-XX:+UseContainerSupport", "-Djava.security.egd=file:/dev/./urandom","-jar","/app/task-backend-1.0.0.jar"]