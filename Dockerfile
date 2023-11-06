FROM openjdk:8
EXPOSE 8082
ADD target/achat-app.jar achat-app.jar
ENTRYPOINT ["java","-jar","/achat-app.jar"]