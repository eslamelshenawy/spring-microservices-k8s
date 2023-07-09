FROM  openjdk:17-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} user-service-1.1.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/user-service-1.1.jar"]
