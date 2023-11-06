FROM openjdk:8-jdk-alpine

ARG NEXUS_URL=http://192.168.0.103:8081/repository/maven-releases
ARG JAR_PATH=esprit/rh/achat/1.0/achat-1.0.jar
ARG FULL_PATH=http://192.168.0.103:8081/repository/maven-releases/tn/esprit/rh/achat/1.0/achat-1.0.jar
RUN apk add --no-cache curl \
    && curl -o Project.jar -L "http://192.168.0.103:8081/repository/maven-releases/tn/esprit/rh/achat/1.0/achat-1.0.jar" \
    && apk del curl

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "Project.jar"]