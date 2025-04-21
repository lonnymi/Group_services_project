FROM eclipse-temurin:17.0.8-jdk-alpine

LABEL maintainer="Lonny"
LABEL application="group-service"

RUN addgroup -S spring && adduser -S spring -G spring

WORKDIR /app

COPY target/*.jar app.jar

RUN chown spring:spring /app/app.jar

USER spring

EXPOSE 8081

HEALTHCHECK --interval=30s --timeout=3s \
  CMD wget -q --spider http://localhost:8081/actuator/health || exit 1

ENV JAVA_OPTS="-Xmx512m -Xms256m"

ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar /app/app.jar"]