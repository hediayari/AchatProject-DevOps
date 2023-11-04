FROM openjdk:8
EXPOSE 8080
ADD target/nadia-app.jar nadia-app.jar
ENTRYPOINT ["java","-jar","nadia-app.jar"]