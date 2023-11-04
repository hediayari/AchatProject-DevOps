FROM openjdk:8
WORKDIR /app
EXPOSE 8089
ADD target/achat-1.0.jar achat1.0.jar
ENTRYPOINT ["java","-jar","achat1.0.jar"]