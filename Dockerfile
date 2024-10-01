FROM openjdk:17-jdk

COPY /target/*.jar /usr/local/app.jar
WORKDIR /userEntity/local/

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app.jar"]