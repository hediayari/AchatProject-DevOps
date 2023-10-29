FROM openjdk:8
EXPOSE 8080
ADD target/oubaid-app.jar oubaid-app.jar
ENTRYPOINT ["java","-jar","oubaid-app.jar"]