FROM openjdk:8-jdk-alpine

WORKDIR /app

COPY lib/build/libs/lib-1.0-SNAPSHOT.jar /app/

EXPOSE 8080

ENV JAVA_OPTS=""

CMD ["java", "-jar", "lib-1.0-SNAPSHOT.jar"]
