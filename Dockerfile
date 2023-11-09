FROM openjdk:8-jdk-alpine
EXPOSE 8089
ADD http://localhost:8081/repository/maven-releases/tn/esprit/rh/achat/1.5/achat-1.5.jar achat-1.5.jar
ENTRYPOINT ["java","-jar","/achat-1.0.jar"]
