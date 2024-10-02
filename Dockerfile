FROM openjdk:17-jdk

COPY /target/*.jar /usr/local/app.jar
WORKDIR /usr/local/

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]